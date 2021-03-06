/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.plugin.inventory.player;

import org.spout.api.entity.Player;
import org.spout.api.inventory.Inventory;
import org.spout.api.inventory.ItemStack;

import org.spout.vanilla.api.inventory.Slot;


public class PlayerQuickbar extends Inventory {
	private static final long serialVersionUID = 1L;
	public static final int SIZE = 9;
	private int selected = 0;

	public PlayerQuickbar() {
		super(SIZE);
	}

	public Slot getSelectedSlot() {
		return new Slot(this, this.selected);
	}

	public void setSelectedSlot(int currentSlot) {
		this.selected = currentSlot;
	}

	/**
	 * Informs a player of a certain equipment (held item) change
	 * 
	 * @param player to inform
	 */
	@SuppressWarnings("unused")
	public void updateHeldItem(Player player) {
		ItemStack item = this.get(this.selected);
		//TODO: This needs implementation
		// EntityEquipmentEvent does NOT WORK!
		// Equipment messages with slots 0 - 3 are (now) reserved for armor slots
		// Slot -1 and 4 both throw an index out of bounds exception on the client
		// If someone finds out the new method of doing this, that would be great
	}
}
