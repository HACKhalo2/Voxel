/*
 * This file is part of Voxel
 * 
 * Copyright (C) 2016 Lux Vacuos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package net.luxvacuos.voxel.server.world.items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;

import net.luxvacuos.igl.vector.Vector3f;
import net.luxvacuos.voxel.server.world.block.BlockBase;
import net.luxvacuos.voxel.server.world.entities.components.CollisionComponent;
import net.luxvacuos.voxel.server.world.entities.components.PositionComponent;
import net.luxvacuos.voxel.server.world.entities.components.VelocityComponent;

public class ItemDrop extends Entity {

	private BlockBase block;
	private PositionComponent positionComponent;
	private VelocityComponent velocityComponent;
	private CollisionComponent collisionComponent;

	public ItemDrop(Vector3f pos, BlockBase block, float scale) {
		velocityComponent = new VelocityComponent();
		positionComponent = new PositionComponent();
		positionComponent.position = new Vector3f(pos);
		collisionComponent = new CollisionComponent();
		this.add(positionComponent);
		this.add(velocityComponent);
		this.add(collisionComponent);
		this.block = block;
		collisionComponent.min = new Vector3(-0.2f, -0.2f, -0.2f);
		collisionComponent.max = new Vector3(0.2f, 0.2f, 0.2f);
		collisionComponent.boundingBox.set(collisionComponent.min, collisionComponent.max);
	}

	public ItemDrop(BlockBase block) {
		velocityComponent = new VelocityComponent();
		positionComponent = new PositionComponent();
		positionComponent.position = new Vector3f();
		collisionComponent = new CollisionComponent();
		this.add(positionComponent);
		this.add(velocityComponent);
		this.add(collisionComponent);
		this.block = block;
		collisionComponent.min = new Vector3(-0.2f, -0.2f, -0.2f);
		collisionComponent.max = new Vector3(0.2f, 0.2f, 0.2f);
		collisionComponent.boundingBox.set(collisionComponent.min, collisionComponent.max);
	}

	public BlockBase getBlock() {
		return block;
	}

}
