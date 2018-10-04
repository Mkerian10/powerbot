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
import org.powerbot.script.Viewable;

/**
 * MobileIdNameQuery
 *
 * @param <K> the type of query which must extend any one of the specified types
 */
public abstract class MobileIdNameQuery<K extends Locatable & Identifiable & Nameable & Viewable & Actionable> extends AbstractQuery<MobileIdNameQuery<K>, K, ClientContext>
		implements Locatable.Query<MobileIdNameQuery<K>>, Identifiable.Query<MobileIdNameQuery<K>>,
		Nameable.Query<MobileIdNameQuery<K>>, Viewable.Query<MobileIdNameQuery<K>>,
		Actionable.Query<MobileIdNameQuery<K>> {

	public MobileIdNameQuery(final ClientContext ctx) {
		super(ctx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MobileIdNameQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> at(final Locatable l) {
		return select(new Locatable.Matcher(l.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> within(final double radius) {
		return within(ctx.players.local().tile(), radius);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> within(final Locatable locatable, final double radius) {
		return select(new Locatable.WithinRange(locatable.tile(), radius));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> within(final Area area) {
		return select(new Locatable.WithinArea(area));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> nearest() {
		return nearest(ctx.players.local().tile());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> nearest(final Locatable locatable) {
		return sort(new Locatable.NearestTo(locatable.tile()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> id(final int... ids) {
		return select(new Identifiable.Matcher(ids));
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> id(final Collection<Integer> ids) {
		return select(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> id(final int[]... ids) {
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
	public MobileIdNameQuery<K> id(final Identifiable... identifiables) {
		return select(new Identifiable.Matcher(identifiables));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> name(final String... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> name(final String[]... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> name(final Pattern... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> name(final Nameable... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> action(final String... actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> action(final Pattern... actions) {
		return select(new Actionable.Matcher(actions));
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> action(final Collection<String> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> action(final Collection<Pattern> actions) {
		return select(new Actionable.Matcher(actions));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MobileIdNameQuery<K> viewable() {
		return select(new Filter<K>() {
			@Override
			public boolean accept(final K k) {
				return k.inViewport();
			}
		});
	}
}
