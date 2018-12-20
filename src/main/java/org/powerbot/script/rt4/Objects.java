package org.powerbot.script.rt4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.powerbot.bot.ReflectProxy;
import org.powerbot.bot.rt4.client.Client;
import org.powerbot.bot.rt4.client.Tile;
import org.powerbot.script.Locatable;

/**
 * Objects
 */
public class Objects<K extends Locatable & Identifiable & Nameable & Viewable & Actionable> extends BasicQuery<K> {
	private GameObject NIL;

	public Objects(final ClientContext ctx) {
		super(ctx);
		NIL = new GameObject(ctx, null, GameObject.Type.UNKNOWN);
	}

	public BasicQuery<GameObject> select(final int radius) {
		return select(get(radius));
	}

	public BasicQuery<GameObject> select(final Locatable l, final int radius) {
		return select(get(l.tile(), radius));
	}

	@Override
	public List<GameObject> get() {
		return get(Integer.MAX_VALUE);
	}

	public List<GameObject> get(final int radius) {
		return get(ctx.players.local().tile(), radius);
	}

	public List<GameObject> get(final Locatable l, int radius) {
		radius = Math.min(radius, 110);
		final List<GameObject> r = new CopyOnWriteArrayList<GameObject>();
		final Client client = ctx.client();
		if (client == null) {
			return r;
		}
		final Tile[][][] tiles = client.getLandscape().getTiles();
		final int floor = client.getFloor();
		if (floor < 0 || floor >= tiles.length) {
			return r;
		}
		final Tile[][] rows = tiles[floor];
		final HashSet<GameObject> set = new HashSet<GameObject>();
		int start_x = 0, end_x = Integer.MAX_VALUE, start_y = 0, end_y = Integer.MAX_VALUE;
		if (radius >= 0) {
			final org.powerbot.script.Tile mo = ctx.game.mapOffset(), lp = l.tile();
			if (mo != org.powerbot.script.Tile.NIL && lp != org.powerbot.script.Tile.NIL) {
				final org.powerbot.script.Tile t = lp.derive(-mo.x(), -mo.y());
				start_x = t.x() - radius;
				end_x = t.x() + radius;
				start_y = t.y() - radius;
				end_y = t.y() + radius;
			}
		}
		for (int x = Math.max(0, start_x); x <= Math.min(end_x, rows.length - 1); x++) {
			final Tile[] col = rows[x];
			for (int y = Math.max(0, start_y); y <= Math.min(end_y, col.length - 1); y++) {
				final Tile tile = col[y];
				if (tile.isNull()) {
					continue;
				}
				final int len = Math.max(0, tile.getGameObjectLength());
				final ReflectProxy[] fo = {tile.getBoundaryObject(), tile.getFloorObject(), tile.getWallObject()};
				final ReflectProxy[] arr = new ReflectProxy[3 + len];
				System.arraycopy(fo, 0, arr, 0, 3);
				final org.powerbot.bot.rt4.client.GameObject[] interactive = tile.getGameObjects();
				System.arraycopy(interactive, 0, arr, 3, Math.min(len, interactive.length));

				for (final ReflectProxy p : arr) {
					final BasicObject o = new BasicObject(p);
					if (!o.object.isNull()) {
						final int t = o.getMeta() & 0x3f;
						final GameObject.Type type;
						if (t == 0 || t == 1 || t == 9) {
							type = GameObject.Type.BOUNDARY;
						} else if (t == 2 || t == 3 || t == 4 || t == 5 || t == 6 || t == 7 || t == 8) {
							type = GameObject.Type.WALL_DECORATION;
						} else if (t == 10 || t == 11) {
							type = GameObject.Type.INTERACTIVE;
						} else if (t == 22) {
							type = GameObject.Type.FLOOR_DECORATION;
						} else {
							type = GameObject.Type.UNKNOWN;
						}
						set.add(new GameObject(ctx, o, type));
					}
				}
			}
		}
		return new ArrayList<GameObject>(set);
	}

	@Override
	public GameObject nil() {
		return NIL;
	}
}
