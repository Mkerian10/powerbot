package org.powerbot.script.rt4;

import org.powerbot.script.Tile;

/**
 * Constants
 * A utility class holding all the game constants for rt4.
 */
public final class Constants {

	public static final int GAME_LOGIN = 10;
	public static final int GAME_LOGGING = 20;
	public static final int GAME_LOBBY = 25;
	public static final int GAME_LOGGED = 30;

	public static final int BANK_WIDGET = 12;
	public static final int BANK_ITEMS = 23;
	public static final int BANK_SCROLLBAR = 24;
	public static final int BANK_MASTER = 14;
	public static final int BANK_CLOSE = 11;
	public static final int BANK_ITEM = 32;
	public static final int BANK_NOTE = 34;
	public static final int BANK_PLACEHOLDERS = 37;
	public static final int BANK_DEPOSIT_INVENTORY = 41;
	public static final int BANK_DEPOSIT_EQUIPMENT = 43;
	public static final int BANK_TABS = 867;
	public static final int BANK_TABS_HIDDEN = 0xc0000000;
	public static final int BANK_STATE = 115;
	public static final int BANKPIN_WIDGET = 213;

	public static final int DEPOSITBOX_WIDGET = 192;
	public static final int DEPOSITBOX_CLOSE = 11;
	public static final int DEPOSITBOX_INVENTORY = 4;
	public static final int DEPOSITBOX_WORN_ITEMS = 6;
	public static final int DEPOSITBOX_LOOT = 8;
	public static final int DEPOSITBOX_ITEMS = 2;

	public static final int EQUIPMENT_WIDGET = 387;

	public static final int GAME_LOADED = 30;
	public static final int GAME_LOADING = 25;

	public static final int LOBBY_WIDGET = 378;
	public static final int LOBBY_PLAY = 76;

	public static final int LOGOUT_BUTTON_WIDGET = 182;
	public static final int LOGOUT_BUTTON_COMPONENT = 12;

	public static final int INVENTORY_WIDGET = 149;
	public static final int INVENTORY_ITEMS = 0;
	public static final int INVENTORY_BANK_WIDGET = 15;
	@Deprecated
	public static final int INVENTORY_BANK = 3;
	public static final int INVENTORY_BANK_ITEMS = 3;
	public static final int INVENTORY_GRAND_EXCHANGE_WIDGET = 467;
	public static final int INVENTORY_GRAND_EXCHANGE_ITEMS = 0;
	public static final int INVENTORY_SHOP_WIDGET = 301;
	public static final int INVENTORY_SHOP_ITEMS = 0;
	public static final int[][] INVENTORY_ALTERNATIVES = {
			{INVENTORY_BANK_WIDGET, INVENTORY_BANK_ITEMS},
			{INVENTORY_GRAND_EXCHANGE_WIDGET, INVENTORY_GRAND_EXCHANGE_ITEMS},
			{INVENTORY_SHOP_WIDGET, INVENTORY_SHOP_ITEMS}
	};

	public static final int MOVEMENT_MAP = 160;
	public static final int MOVEMENT_RUN_ENERGY = 23;
	public static final int MOVEMENT_RUNNING = 173;
	public static final int MOVEMENT_QUICK_PRAYER = 14;

	public static final int[] SKILLS_XP = {0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107,
			2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
			16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983,
			75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
			302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
			1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594,
			3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629,
			11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069,
			31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828,
			85539082, 94442737, 104273167};
	public static final int SKILLS_ATTACK = 0;
	public static final int SKILLS_DEFENSE = 1;
	public static final int SKILLS_STRENGTH = 2;
	public static final int SKILLS_HITPOINTS = 3;
	public static final int SKILLS_RANGE = 4;
	public static final int SKILLS_PRAYER = 5;
	public static final int SKILLS_MAGIC = 6;
	public static final int SKILLS_COOKING = 7;
	public static final int SKILLS_WOODCUTTING = 8;
	public static final int SKILLS_FLETCHING = 9;
	public static final int SKILLS_FISHING = 10;
	public static final int SKILLS_FIREMAKING = 11;
	public static final int SKILLS_CRAFTING = 12;
	public static final int SKILLS_SMITHING = 13;
	public static final int SKILLS_MINING = 14;
	public static final int SKILLS_HERBLORE = 15;
	public static final int SKILLS_AGILITY = 16;
	public static final int SKILLS_THIEVING = 17;
	public static final int SKILLS_SLAYER = 18;
	public static final int SKILLS_FARMING = 19;
	public static final int SKILLS_RUNECRAFTING = 20;
	public static final int SKILLS_HUNTER = 21;
	public static final int SKILLS_CONSTRUCTION = 22;

	public static final int VIEWPORT_WIDGET = 548 << 16 | 12;

	@Deprecated
	public static final int CHAT_NPC = 231;
	@Deprecated
	public static final int CHAT_PLAYER = 217;
	@Deprecated
	public static final int CHAT_CONTINUE = 2;

	public static final int CHAT_INPUT = 162;
	public static final int CHAT_VIEWPORT = 30;
	public static final int CHAT_INPUT_TEXT = 36;
	public static final int CHAT_WIDGET = 219;
	public static final int[][] CHAT_CONTINUES = {
			{231, 2}, //npc
			{217, 2}, //player
			{229, 1},  //acknowledge
			{233, 2},  //skill level-up
			{11, 3},   //at tutorials island
	};
	public static final int[] CHAT_OPTIONS = {
			1, 2, 3, 4, 5
	};

	public static final int PRAYER_QUICK_SELECT = 77;
	public static final int PRAYER_QUICK_SELECT_CONTAINER = 4;
	public static final int PRAYER_SELECT = 541;
	public static final int PRAYER_QUICK_SELECTED = 375;
	public static final int PRAYER_QUICK_SELECTION = 84;
	public static final int PRAYER_SELECTION = 83;

	public static final int SPELLBOOK_VARPBIT = 439;
	public static final int SPELLBOOK_WIDGET = 218;

	public static final String[] BANK_NPCS = {"Banker", "Ghost banker", "Banker tutor", "Sirsal Banker", "Nardah Banker", "Gnome banker", "Fadli", "Emerald Benedict"};
	public static final String[] BANK_CHESTS = {"Bank chest"};
	public static final String[] BANK_BOOTHS = {"Bank booth"};
	public static final Tile[] BANK_UNREACHABLES = new Tile[]{
			new Tile(3187, 3446, 0), new Tile(3088, 3242, 0),
			new Tile(3096, 3242, 0), new Tile(3096, 3241, 0), new Tile(3096, 3245, 0),
	};

	public static final int COMBAT_OPTIONS_WIDGET = 593;

	public static final int[] WIDGETCLOSER_TRADE_ITEMS = {
			335 << 16 | 15,
	};
	//Textures of "X" close button
	public static final int[] CLOSE_BUTTON_TEXTURES = {831, 535, 537};
	
	public static final int[] WILDERNESS_LEVEL_WIDGET = {90, 46};
}
