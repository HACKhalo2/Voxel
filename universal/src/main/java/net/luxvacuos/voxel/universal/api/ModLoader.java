/*
 * This file is part of UVoxel
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

package net.luxvacuos.voxel.universal.api;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.luxvacuos.voxel.universal.api.mod.MoltenAPIMod;

public class ModLoader {

	private List<Class<?>> modsClass;
	private File modsFolder;

	public ModLoader() {
		modsClass = new ArrayList<Class<?>>();
	}

	public void loadMods(String prefix) throws Exception {
		modsFolder = new File(prefix + "/mods");
		if (!modsFolder.exists())
			modsFolder.mkdirs();
		Files.walk(Paths.get(modsFolder.toURI())).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				if (filePath.toFile().getAbsolutePath().endsWith(".jar")) {
					try {
						URLClassLoader child = new URLClassLoader(new URL[] { filePath.toFile().toURI().toURL() },
								this.getClass().getClassLoader());
						String name = filePath.getFileName().toString();
						name = name.substring(0, name.lastIndexOf('.'));
						Class<?> classToLoad;
						classToLoad = Class.forName("mod_" + name, true, child);
						if (classToLoad.isAnnotationPresent(MoltenAPIMod.class)) {
							modsClass.add(classToLoad);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	public List<Class<?>> getModsClass() {
		return modsClass;
	}
}
