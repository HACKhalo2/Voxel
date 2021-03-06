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

package net.luxvacuos.voxel.client.resources.models;

import java.util.HashMap;
import java.util.Map;

import net.luxvacuos.igl.vector.Vector2f;
import net.luxvacuos.igl.vector.Vector8f;

public class TessellatorTextureAtlas {

	private Map<String, Vector8f> texcoords;
	private int width, height;
	private int texture;
	private float ax, ay;

	public TessellatorTextureAtlas(int textureWidth, int textureHeight, int texture) {
		texcoords = new HashMap<String, Vector8f>();
		this.width = textureWidth;
		this.height = textureHeight;
		this.texture = texture;
		ax = 16f / (float) width;
		ay = 16f / (float) height;
	}

	public void registerTextureCoords(String name, Vector2f texcoords) {
		Vector8f tex = new Vector8f(texcoords.x / width, (texcoords.y / height) + ay, texcoords.x / width,
				texcoords.y / height, (texcoords.x / width) + ax, texcoords.y / height, (texcoords.x / width) + ax,
				(texcoords.y / height) + ay);
		this.texcoords.put(name.toLowerCase(), tex);
	}

	public Vector8f getTextureCoords(String name) {
		Vector8f coords = texcoords.get(name.toLowerCase());
		if (coords == null)
			return new Vector8f(0, 0, 0, 1, 1, 1, 1, 0);
		return coords;
	}

	public int getTexture() {
		return texture;
	}

}
