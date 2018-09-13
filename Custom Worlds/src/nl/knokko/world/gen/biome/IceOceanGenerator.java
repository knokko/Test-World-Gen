package nl.knokko.world.gen.biome;

import java.util.Random;

import nl.knokko.world.gen.CustomGenerator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class IceOceanGenerator implements BiomeGenerator {

	public void generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, int x, int z) {
		chunk.setRegion(x, 30, z, x + 1, world.getSeaLevel(), z + 1, Material.WATER);
		chunk.setRegion(x, 0, z, x + 1, 30, z + 1, Material.STONE);
		CustomGenerator.setGround(chunk, chunkX, chunkZ, x, z, 30, 45, world.getSeed(), random, Material.SAND);
	}

}
