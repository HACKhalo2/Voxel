package net.luxvacuos.voxel.client.world;

import java.util.HashMap;
import java.util.Map;

import net.luxvacuos.voxel.client.world.chunks.Chunk;
import net.luxvacuos.voxel.client.world.chunks.ChunkKey;

public class Region {

	private Map<ChunkKey, Chunk> chunks;

	public Region() {
		chunks = new HashMap<>();
	}

	public Chunk getChunk(int cx, int cy, int cz) {
		return chunks.get(new ChunkKey(cx, cy, cz));
	}

	public boolean hasChunk(int cx, int cy, int cz) {
		return chunks.containsKey(new ChunkKey(cx, cy, cz));
	}

	public void addChunk(ChunkKey key, Chunk chunk) {
		chunks.put(key, chunk);
	}

}
