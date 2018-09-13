package nl.knokko.world.gen.biome;

import java.util.Random;

import nl.knokko.world.gen.CustomGenerator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class ExtremeGenerator implements BiomeGenerator {

	public ExtremeGenerator() {
		
	}

	public void generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, int x, int z) {
		int stoneHeight = CustomGenerator.setGround(chunk, chunkX, chunkZ, x, z, world.getSeaLevel() + 30, 240, world.getSeed(), random, Material.STONE);
		chunk.setRegion(x, 0, z, x + 1, world.getSeaLevel(), z + 1, Material.STONE);
		int dirtHeight = CustomGenerator.setGround(chunk, chunkX, chunkZ, x, z, stoneHeight + 4, stoneHeight + 10, world.getSeed(), random, Material.DIRT);
		chunk.setBlock(x, dirtHeight, z, Material.GRASS);
	}

}
