package net.luxvacuos.voxel.client.api;

import com.badlogic.ashley.core.Entity;

import net.luxvacuos.voxel.client.resources.GameResources;
import net.luxvacuos.voxel.client.world.chunks.Chunk;

public class MoltenAPI {

	public void testPrint() {
		System.out.println("TEST");
	}

	public Chunk getChunk(int cx, int cy, int cz) {
		return GameResources.getInstance().getWorldsHandler().getActiveWorld().getActiveDimension().getChunk(cx, cy,
				cz);
	}

	public void addChunk(Chunk chunk) {
		GameResources.getInstance().getWorldsHandler().getActiveWorld().getActiveDimension().addChunk(chunk);
	}

	public void addEntity(Entity entity) {
		GameResources.getInstance().getWorldsHandler().getActiveWorld().getActiveDimension().getPhysicsEngine()
				.addEntity(entity);
	}

}
