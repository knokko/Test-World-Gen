package nl.knokko.world.gen.biome;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public interface BiomeGenerator {
	
	void generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, int x, int z);
}
