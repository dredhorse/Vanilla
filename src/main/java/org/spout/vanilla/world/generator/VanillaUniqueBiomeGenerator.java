/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.world.generator;

import java.util.Arrays;

import org.spout.api.generator.GeneratorPopulator;
import org.spout.api.generator.biome.Biome;
import org.spout.api.generator.biome.BiomeManager;
import org.spout.api.generator.biome.Simple2DBiomeManager;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidShortBuffer;

public abstract class VanillaUniqueBiomeGenerator extends VanillaBiomeGenerator {
	private final int height;
	private final Biome biome;

	public VanillaUniqueBiomeGenerator(int height, Biome biome) {
		this.height = height;
		this.biome = biome;
	}

	@Override
	public BiomeManager generateBiomes(int chunkX, int chunkZ, World world) {
		final BiomeManager biomeManager = new Simple2DBiomeManager(chunkX, chunkZ);
		final byte[] biomeData = new byte[Chunk.BLOCKS.AREA];
		Arrays.fill(biomeData, (byte) biome.getId());
		biomeManager.deserialize(biomeData);
		return biomeManager;
	}

	@Override
	public void generate(CuboidShortBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		if (chunkY < 0) {
			super.generate(blockData, chunkX, chunkY, chunkZ, world);
		} else if (chunkY << Chunk.BLOCKS.BITS < height) {
			final int x = chunkX << Chunk.BLOCKS.BITS;
			final int y = chunkY << Chunk.BLOCKS.BITS;
			final int z = chunkZ << Chunk.BLOCKS.BITS;
			final long seed = world.getSeed();
			final BiomeManager manager = world.getBiomeManager(x, z, true);
			generateTerrain(blockData, x, y, z, manager, seed);
			for (GeneratorPopulator generatorPopulator : getGeneratorPopulators()) {
				generatorPopulator.populate(blockData, x, y, z, manager, seed);
			}
		}
	}
}