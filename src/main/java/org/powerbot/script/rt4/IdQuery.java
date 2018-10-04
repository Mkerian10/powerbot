package org.powerbot.script.rt4;

import java.util.Collection;

import org.powerbot.script.AbstractQuery;
import org.powerbot.script.Identifiable;

/**
 * IdQuery
 *
 * @param <K> the type of query which must extend {@link Identifiable}
 */
public abstract class IdQuery<K extends Identifiable> extends AbstractQuery<IdQuery<K>, K, ClientContext>
		implements Identifiable.Query<IdQuery<K>> {

	public IdQuery(final ClientContext ctx) {
		super(ctx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IdQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdQuery<K> id(final int... ids) {
		return select(new Identifiable.Matcher(ids));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdQuery<K> id(final Collection<Integer> ids) {
		return select(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdQuery<K> id(final int[]... ids) {
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
	public IdQuery<K> id(final Identifiable... identifiables) {
		return select(new Identifiable.Matcher(identifiables));
	}
}
