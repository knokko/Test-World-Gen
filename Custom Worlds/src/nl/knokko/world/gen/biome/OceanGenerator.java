package nl.knokko.world.gen.biome;

import java.util.Random;

import nl.knokko.world.gen.CustomGenerator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class OceanGenerator implements BiomeGenerator {

	public void generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, int x, int z) {
		chunk.setRegion(x, 0, z, x + 1, 30, z + 1, Material.NETHERRACK);
		chunk.setRegion(x, 30, z, x + 1, world.getSeaLevel() + 1, z + 1, Material.LAVA);
		CustomGenerator.setGround(chunk, chunkX, chunkZ, x, z, 30, 50, world.getSeed(), random, Material.SOUL_SAND);
	}

}
