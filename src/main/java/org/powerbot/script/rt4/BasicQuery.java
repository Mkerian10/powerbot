package org.powerbot.script.rt4;

import java.util.regex.Pattern;
import java.util.Collection;

import org.powerbot.script.AbstractQuery;
import org.powerbot.script.Actionable;
import org.powerbot.script.Area;
import org.powerbot.script.Filter;
import org.powerbot.script.Identifiable;
import org.powerbot.script.Locatable;
import org.powerbot.script.Nameable;
import org.powerbot.script.Viewable;

/**
 * BasicQuery
 * A basic query for all entities within the viewport.
 *
 * @param <K> the type of entity within the viewport
 */
public abstract class BasicQuery<K extends Locatable & Identifiable & Nameable & Viewable & Actionable> extends AbstractQuery<BasicQuery<K>, K, org.powerbot.script.rt4.ClientContext>
		implements Locatable.Query<BasicQuery<K>>, Identifiable.Query<BasicQuery<K>>,
		Nameable.Query<BasicQuery<K>>, Viewable.Query<BasicQuery<K>>, Actionable.Query<BasicQuery<K>> {
	public BasicQuery(final ClientContext ctx) {
		super(ctx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BasicQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> at(final Locatable l) {
		return select(new Locatable.Matcher(l.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> within(final double radius) {
		return within(ctx.players.local().tile(), radius);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> within(final Locatable locatable, final double radius) {
		return select(new Locatable.WithinRange(locatable.tile(), radius));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> within(final Area area) {
		return select(new Locatable.WithinArea(area));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> nearest() {
		return nearest(ctx.players.local().tile());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> nearest(final Locatable locatable) {
		return sort(new Locatable.NearestTo(locatable.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> id(final int... ids) {
		return select(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> id(final int[]... ids) {
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
	public BasicQuery<K> id(final Identifiable... identifiables) {
		return select(new Identifiable.Matcher(identifiables));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> name(final String... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> name(final String[]... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> name(final Pattern... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> name(final Nameable... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> action(final String... actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> action(final Pattern... actions) {
		return select(new Actionable.Matcher(actions));
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> action(final Collection<String> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> action(final Collection<Pattern> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicQuery<K> viewable() {
		return select(new Filter<K>() {
			@Override
			public boolean accept(final K k) {
				return k.inViewport();
			}
		});
	}
}
