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

package net.luxvacuos.voxel.server.world.entities.components;

import java.util.List;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;

import net.luxvacuos.igl.vector.Vector3f;
import net.luxvacuos.voxel.server.world.items.ItemDrop;

public class DropComponent implements Component {

	private List<ItemDrop> drop;

	public DropComponent(List<ItemDrop> drop) {
		this.drop = drop;
	}

	public void drop(Engine system, Vector3f pos) {
		for (ItemDrop itemDrop : drop) {
			itemDrop.getComponent(PositionComponent.class).position = pos;
			system.addEntity(itemDrop);
		}
		drop.clear();
	}

}
