package org.powerbot.script.rt4;

import java.util.ArrayList;
import java.util.List;

/**
 * Equipment
 * A utility class for interacting with worn items on the player.
 */
public class Equipment extends ItemQuery<Item>{
	public Equipment(final ClientContext factory) {
		super(factory);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Item> get() {
		final List<Item> items = new ArrayList<>(11);
		for (final Slot slot : Slot.values()) {
			final Component c = ctx.widgets.widget(Constants.EQUIPMENT_WIDGET).component(slot.getComponentIndex()).component(1);
			items.add(new Item(ctx, c, c.itemId(), c.itemStackSize()));
		}
		return items;
	}
	
	/**
	 * Returns the {@link org.powerbot.script.rt4.Item} at the spcified {@link Slot}.
	 *
	 * @param slot the {@link Slot} to get the {@link org.powerbot.script.rt4.Item} at
	 * @return the {@link org.powerbot.script.rt4.Item} in the provided slot
	 */
	public Item itemAt(final Slot slot) {
		final int index = slot.getIndex();
		if (index < 0) {
			return nil();
		}
		final Component c = ctx.widgets.widget(Constants.EQUIPMENT_WIDGET).component(slot.getComponentIndex()).component(1);
		return new Item(ctx, c, c.itemId(), c.itemStackSize());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item nil() {
		return new Item(ctx, null, -1, -1);
	}
	
	/**
	 * An enumeration of equipment slots.
	 */
	public enum Slot {
		HEAD(0, 6),
		CAPE(1, 7),
		NECK(2, 8),
		MAIN_HAND(3, 9),
		TORSO(4, 10),
		OFF_HAND(5, 11),
		LEGS(7, 12),
		HANDS(9, 13),
		FEET(10, 14),
		RING(12, 15),
		QUIVER(13, 16);
		private final int index, component;
		
		Slot(final int index, final int component) {
			this.index = index;
			this.component = component;
		}
		
		public int getIndex() {
			return index;
		}
		
		public int getComponentIndex() {
			return component;
		}
	}
}
