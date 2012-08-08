/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.controller.living.creature.util;

import java.util.HashSet;
import java.util.Set;

import org.spout.api.Source;
import org.spout.api.inventory.ItemStack;

import org.spout.vanilla.controller.VanillaControllerTypes;
import org.spout.vanilla.controller.VanillaEntityController;
import org.spout.vanilla.controller.living.Creature;
import org.spout.vanilla.controller.living.creature.Passive;
import org.spout.vanilla.material.VanillaMaterials;

public class SnowGolem extends Creature implements Passive {
	public SnowGolem() {
		super(VanillaControllerTypes.SNOW_GOLEM);
	}

	@Override
	public void onAttached() {
		getHealth().setSpawnHealth(6);
		getHealth().setDeathAnimation(false);
		super.onAttached();
	}

	@Override
	public Set<ItemStack> getDrops(Source source, VanillaEntityController lastDamager) {
		Set<ItemStack> drops = new HashSet<ItemStack>();
		int count = getRandom().nextInt(16);
		if (count > 0) {
			drops.add(new ItemStack(VanillaMaterials.SNOWBALL, count));
		}

		return drops;
	}
}
