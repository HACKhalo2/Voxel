package io.github.guerra24.voxel.client.kernel.world.chunks;

import java.util.Random;

import io.github.guerra24.voxel.client.kernel.util.Maths;
import io.github.guerra24.voxel.client.kernel.world.DimensionalWorld;
import io.github.guerra24.voxel.client.kernel.world.block.Block;

public class ChunkGenerator {
	public void addTree(byte[][][] blocks, int xo, int yo, int zo, int treeHeight, Random rand) {
		for (int y = 0; y < treeHeight; y++) {
			blocks[xo][yo + y][zo] = Block.Wood.getId();
		}

		for (int x = 0; x < treeHeight; x++) {
			for (int z = 0; z < treeHeight; z++) {
				for (int y = 0; y < treeHeight; y++) {
					int xx = x - (treeHeight - 1) / 2;
					int yy = y - (treeHeight - 1) / 2;
					int zz = z - (treeHeight - 1) / 2;
					if (xx == 0 && zz == 0 && yy <= 0)
						continue;
					double test = Math.sqrt((double) xx * xx + yy * yy + zz * zz);
					if (test < (treeHeight - 1) / 2) {
						if (rand.nextDouble() < 0.8) {
							blocks[xo + xx][yo + yy + treeHeight - 1][zo + zz] = Block.Leaves.getId();
						}
					}
				}
			}
		}

	}

	public void generateCaves(byte[][][] blocks, DimensionalWorld world, int sizeX, int sizeZ, int cx, int cz) {
		for (int x = 0; x < sizeX; x++) {
			for (int z = 0; z < sizeZ; z++) {
				double tempHeight = world.getNoise().getNoise(x + cx * 16, z + cz * 16);
				tempHeight += 1;
				int height = (int) (32 * Maths.clamp(tempHeight));
				for (int y = 0; y < height; y++) {
					if (y > 16)
						blocks[x][y][z] = 0;
				}
			}
		}
	}
}
