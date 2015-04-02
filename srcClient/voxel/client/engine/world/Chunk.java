package voxel.client.engine.world;

import org.lwjgl.util.vector.Vector3f;

import voxel.client.StartClient;
import voxel.client.engine.entities.Entity;
import voxel.client.engine.resources.models.TexturedModel;
import voxel.client.engine.world.blocks.Blocks;

public class Chunk extends Entity {

	public Chunk(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		create();
	}

	public static final int CHUNK_SIZE = 64;
	private static final int CHUNK_HEIGHT = 128;

	public static void create() {
		for (int x = 0; x < CHUNK_SIZE; x++) {
			for (int y = 0; y < CHUNK_HEIGHT; y++) {
				for (int z = 0; z < CHUNK_SIZE; z++) {
					// StartClient.allCubes.add(new Entity(Blocks.cubeGrass,
					// new Vector3f(x, 16, z), 0f, 0f, 0f, 1f));
					// StartClient.allCubes.add(new Entity(Blocks.cubeGlass,
					// new Vector3f(x, 32, z), 0f, 0f, 0f, 1f));
					if (y < 8) {
						if (StartClient.rand.nextInt(2) == 0) {
							if (StartClient.rand.nextBoolean()) {
								StartClient.allCubes.add(new Entity(
										Blocks.cubeStone,
										new Vector3f(x, y, z), 0f, 0f, 0f, 1f));
							}
							if (StartClient.rand.nextInt(2) == 0) {
								StartClient.allCubes.add(new Entity(
										Blocks.cubeSand, new Vector3f(x, y, z),
										0f, 0f, 0f, 1f));
							}
						}
					}
				}
			}
		}
	}
}
