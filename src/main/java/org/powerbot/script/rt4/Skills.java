package org.powerbot.script.rt4;

import org.powerbot.bot.rt4.client.Client;

/**
 * Skills
 */
public class Skills extends ClientAccessor {
	public Skills(final ClientContext ctx) {
		super(ctx);
	}

	public int[] exps_at() {
		int points = 0;
		final int[] exp = new int[100];
		for (int lvl = 1; lvl < 100; lvl++) {
			points += Math.floor(lvl + 300d * Math.pow(2, lvl / 7d));
			exp[lvl] = (int) Math.floor(points / 4);
		}
		return exp;
	}

	/**
	 * Returns the effective level of the skill at the provided index.
	 * (This is the level that is effected by boosts/reductions)
	 *
	 * @param index the index of the skill
	 * @return the current level at the specified index
	 */
	public int realLevel(final int index) {
		final int[] levels = realLevels();
		if (index >= 0 && index < levels.length) {
			return levels[index];
		}
		return -1;
	}

	/**
	 * Returns the hard level of the skill at the provided index.
	 * (This is the level that stays the same and reflects your true level)
	 *
	 * @param index the index of the skill
	 * @return the hard level at the specified index
	 */
	public int level(final int index) {
		final int[] levels = levels();
		if (index >= 0 && index < levels.length) {
			return levels[index];
		}
		return -1;
	}

	/**
	 * Returns the experience of the skill at the provided index.
	 *
	 * @param index the index of the skill
	 * @return the experience at the specified index
	 */
	public int experience(final int index) {
		final int[] exps = experiences();
		if (index >= 0 && index < exps.length) {
			return exps[index];
		}
		return -1;
	}

	public int[] realLevels() {
		final Client c = ctx.client();
		final int[] arr = c != null ? c.getSkillLevels2() : new int[0];
		return arr != null ? arr : new int[0];
	}

	public int[] levels() {
		final Client c = ctx.client();
		final int[] arr = c != null ? c.getSkillLevels1() : new int[0];
		return arr != null ? arr : new int[0];
	}

	public int[] experiences() {
		final Client c = ctx.client();
		final int[] arr = c != null ? c.getSkillExps() : new int[0];
		return arr != null ? arr : new int[0];
	}

	/**
	 * Determines the level at the specified amount of exp.
	 *
	 * @param exp the exp to convert to level
	 * @return the level with the given amount of exp
	 */
	public int levelAt(final int exp) {
		for (int i = Constants.SKILLS_XP.length - 1; i > 0; i--) {
			if (exp > Constants.SKILLS_XP[i]) {
				return i;
			}
		}
		return 1;
	}

	/**
	 * Determines the experience required for the specified level.
	 *
	 * @param level the level to get the exp at
	 * @return the exp at the specified level
	 */
	public int experienceAt(final int level) {
		if (level < 0 || level > 120) {
			return -1;
		}
		return Constants.SKILLS_XP[level];
	}
}
