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
package org.spout.vanilla.plugin.material.item.misc;

import org.spout.api.entity.Entity;
import org.spout.api.event.player.PlayerInteractEvent.Action;
import org.spout.api.geo.World;
import org.spout.api.math.Vector3;
import org.spout.vanilla.plugin.component.substance.object.projectile.XPBottle;
import org.spout.vanilla.plugin.material.item.VanillaItemMaterial;

import com.bulletphysics.collision.shapes.SphereShape;

public class BottleOEnchanting extends VanillaItemMaterial {

	public BottleOEnchanting(String name, int id) {
		super(name, id, null);
	}

	@Override
	public void onInteract(Entity entity, Action type) {
		super.onInteract(entity, type);
		if (type == Action.RIGHT_CLICK) {
			World world = entity.getWorld();
			XPBottle xpbottle = world.createEntity(entity.getTransform().getPosition(), XPBottle.class).add(XPBottle.class);
			xpbottle.getPhysics().setCollisionShape(new SphereShape(3)); //TODO: Correct this
			xpbottle.getPhysics().applyImpulse(new Vector3(20, 20, 20)); // TODO: Correct this
			xpbottle.setShooter(entity);
			world.spawnEntity(xpbottle.getOwner());
		}
	}
}
