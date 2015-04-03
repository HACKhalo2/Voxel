package voxel.client.engine.world;

public class World {

	public static final int WORLD_SIZE = 1;

	public static void init() {
		for (int x = 0; x < WORLD_SIZE; x++) {
			for (int z = 0; z < WORLD_SIZE; z++) {
				Chunk.create(x + 16, z + 16);
			}
		}
	}

}