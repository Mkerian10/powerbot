package org.powerbot.script;

import java.util.Collection;

/**
 * Identifiable
 * An entity which has an identifier.
 */
public interface Identifiable {
	/**
	 * The identifier of this entity
	 *
	 * @return the entity identifier
	 */
	int id();

	/**
	 * Query
	 * A base for queries that make use of {@link Identifiable} entities.
	 *
	 * @param <T> the type of query to return for chaining
	 */
	interface Query<T> {
		/**
		 * Selects the entities which have one of the provided ids into the query cache.
		 *
		 * @param ids the valid ids
		 * @return {@code this} for the purpose of method chaining
		 */
		T id(int... ids);

		/**
		 * Selects the entities which have one of the provided ids into the query cache.
		 *
		 * @param ids the valid id arrays to check
		 * @return {@code this} for the purpose of method chaining
		 */
		T id(int[]... ids);
		
		/**
		 * Selects the entities which have one of the provided ids into the query cache.
		 *
		 * @param ids the valid id arrays to check
		 * @return {@code this} for the purpose of method chaining
		 */
		T id(final Collection<Integer> ids);

		/**
		 * Selects the entities which have one of the provided ids into the query cache.
		 *
		 * @param ids the valid identifiables to check their ids against
		 * @return {@code this} for the purpose of method chaining
		 */
		T id(Identifiable... ids);
	}

	/**
	 * Matcher
	 */
	class Matcher implements Filter<Identifiable> {
		private final int[] ids;

		public Matcher(final int... ids) {
			this.ids = ids;
		}

		public Matcher(final Identifiable... ids) {
			this.ids = new int[ids.length];
			for (int i = 0; i < ids.length; i++) {
				this.ids[i] = ids[i].id();
			}
		}
		
		public Matcher(final Collection<Integer> ids){
			this(ids.toString(new Integer[ids.size]);
		}

		@Override
		public boolean accept(final Identifiable identifiable) {
			final int x = identifiable != null ? identifiable.id() : -1;
			if (x == -1) {
				return false;
			}
			for (final int id : ids) {
				if (id == x) {
					return true;
				}
			}
			return false;
		}
	}
}
