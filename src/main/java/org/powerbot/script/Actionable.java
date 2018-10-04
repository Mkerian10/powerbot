package org.powerbot.script;

import java.util.regex.Pattern;
import java.util.Collection;

/**
 * Actionable
 * An entity which holds an array of actions.
 */
public interface Actionable extends Interactive {
	/**
	 * The current actions for the entity.
	 *
	 * @return the current entity actions
	 */
	String[] actions();

	/**
	 * Query
	 * A base for queries that make use of {@link Actionable} entities.
	 *
	 * @param <T> the type of query to return for chaining
	 */
	interface Query<T> {
		/**
		 * Selects the entities which have one of the specified actions into the query cache.
		 *
		 * @param actions the valid actions
		 * @return {@code this} for the purpose of method chaining
		 */
		T action(String... actions);
		
		/**
		 * Selects the entities which have one of the specified actions into the query cache.
		 *
		 * @param actions the valid actions
		 * @return {@code this} for the purpose of method chaining
		 */
		T action(final Collection<String> actions);

		/**
		 * Selects the entities which have any action which matches one of the specified action patterns into the query cache.
		 *
		 * @param actions the valid patterns to check RegEx against
		 * @return {@code this} for the purpose of method chaining
		 */
		T action(Pattern... actions);
		
		/**
		 * Selects the entities which have any action which matches one of the specified action patterns into the query cache.
		 *
		 * @param actions the valid patterns to check RegEx against
		 * @return {@code this} for the purpose of method chaining
		 */
		T action(final Collection<Pattern> actions);
	}

	/**
	 * Matcher
	 */
	class Matcher implements Filter<Actionable> {
		private final String[] str;
		private final Pattern[] regex;

		public Matcher(final String... actions) {
			str = actions;
			regex = null;
		}

		public Matcher(final Pattern... actions) {
			regex = actions;
			str = null;
		}
		
		public Matcher(final Collection<String> actions){
			this(actions.toString(new String[actions.size]);
		}
			     
		public Matcher(final Collection<Pattern> actions){
			this(actions.toString(new String[actions.size]);
		}

		@Override
		public boolean accept(final Actionable actionable) {
			final String[] actions = actionable.actions();
			if (actions == null) {
				return false;
			}
			if (regex == null && str == null) {
				return false;
			}
			if (regex != null) {
				for (final String action : actions) {
					if (action == null) {
						continue;
					}
					for (final Pattern pattern : regex) {
						if (pattern.matcher(action).matches()) {
							return true;
						}
					}
				}
			} else {
				for (final String action : actions) {
					if (action == null) {
						continue;
					}
					for (final String string : str) {
						if (action.equalsIgnoreCase(string)) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}
