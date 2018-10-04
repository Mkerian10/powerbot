package org.powerbot.script.rt6;

import java.util.regex.Pattern;
import java.util.Collection;

import org.powerbot.script.AbstractQuery;
import org.powerbot.script.Actionable;
import org.powerbot.script.Area;
import org.powerbot.script.Filter;
import org.powerbot.script.Identifiable;
import org.powerbot.script.Locatable;
import org.powerbot.script.Nameable;
import org.powerbot.script.Stackable;
import org.powerbot.script.Viewable;

/**
 * GroundItemQuery
 *
 * @param <K> the type of query which must extend any one of the specified types
 */
public abstract class GroundItemQuery<K extends Locatable & Identifiable & Nameable & Stackable & Viewable & Actionable> extends AbstractQuery<GroundItemQuery<K>, K, ClientContext>
		implements Locatable.Query<GroundItemQuery<K>>, Identifiable.Query<GroundItemQuery<K>>,
		Nameable.Query<GroundItemQuery<K>>, Stackable.Query<GroundItemQuery<K>>, Viewable.Query<GroundItemQuery<K>>,
		Actionable.Query<GroundItemQuery<K>> {
	public GroundItemQuery(final ClientContext factory) {
		super(factory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GroundItemQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> at(final Locatable l) {
		return select(new Locatable.Matcher(l.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> within(final double radius) {
		return within(ctx.players.local().tile(), radius);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> within(final Locatable locatable, final double radius) {
		return select(new Locatable.WithinRange(locatable.tile(), radius));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> within(final Area area) {
		return select(new Locatable.WithinArea(area));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> nearest() {
		return nearest(ctx.players.local().tile());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> nearest(final Locatable locatable) {
		return sort(new Locatable.NearestTo(locatable.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> id(final int... ids) {
		return select(new Identifiable.Matcher(ids));
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> id(final Collection<Integer> ids) {
		return select(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> id(final int[]... ids) {
		int z = 0;

		for (final int[] x : ids) {
			z += x.length;
		}

		final int[] a = new int[z];
		int i = 0;

		for (final int[] x : ids) {
			for (final int y : x) {
				a[i++] = y;
			}
		}

		return select(new Identifiable.Matcher(a));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> id(final Identifiable... identifiables) {
		return select(new Identifiable.Matcher(identifiables));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> name(final String... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> name(final String[]... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> name(final Pattern... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> name(final Nameable... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> action(final String... actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> action(final Pattern... actions) {
		return select(new Actionable.Matcher(actions));
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> action(final Collection<String> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> action(final Collection<Pattern> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroundItemQuery<K> viewable() {
		return select(new Filter<K>() {
			@Override
			public boolean accept(final K k) {
				return k.inViewport();
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int count() {
		return size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int count(final boolean stacks) {
		if (!stacks) {
			return count();
		}
		int count = 0;
		for (final Stackable stackable : this) {
			count += stackable.stackSize();
		}
		return count;
	}
}
