package nl.knokko.world.gen;

import java.util.Random;
import java.util.logging.Level;

import nl.knokko.world.gen.biome.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import static org.bukkit.block.Biome.*;

public class CustomGenerator extends ChunkGenerator {
	
	private static BiomeGenerator[] biomeMap = new BiomeGenerator[Biome.values().length];
	
	private static void put(Biome biome, BiomeGenerator generator){
		biomeMap[biome.ordinal()] = generator;
	}
	
	private static BiomeGenerator oceanGenerator = new OceanGenerator();
	private static BiomeGenerator iceOceanGenerator = new IceOceanGenerator();
	private static BiomeGenerator deepOceanGenerator = new DeepOceanGenerator();
	private static BiomeGenerator plainGenerator = new PlainGenerator();
	private static BiomeGenerator desertGenerator = new DesertGenerator();
	private static BiomeGenerator extremeGenerator = new ExtremeGenerator();
	private static BiomeGenerator forestGenerator = new PlainGenerator();//TODO just for testing
	private static BiomeGenerator mushroomGenerator = new PlainGenerator();
	
	static {
		put(OCEAN, oceanGenerator);
		put(PLAINS, plainGenerator);
		put(DESERT, desertGenerator);
		put(EXTREME_HILLS, extremeGenerator);
		put(FOREST, forestGenerator);
		put(TAIGA, forestGenerator);
		put(SWAMPLAND, forestGenerator);
		put(RIVER, oceanGenerator);
		put(HELL, null);
		put(SKY, null);
		put(FROZEN_OCEAN, iceOceanGenerator);
		put(FROZEN_RIVER, iceOceanGenerator);
		put(ICE_FLATS, plainGenerator);
		put(ICE_MOUNTAINS, extremeGenerator);
		put(MUSHROOM_ISLAND, mushroomGenerator);
		put(MUSHROOM_ISLAND_SHORE, mushroomGenerator);
		put(BEACHES, desertGenerator);
		put(DESERT_HILLS, desertGenerator);
		put(FOREST_HILLS, forestGenerator);
		put(TAIGA_HILLS, forestGenerator);
		put(SMALLER_EXTREME_HILLS, extremeGenerator);
		put(JUNGLE, forestGenerator);
		put(JUNGLE_HILLS, forestGenerator);
		put(JUNGLE_EDGE, forestGenerator);
		put(DEEP_OCEAN, deepOceanGenerator);
		put(STONE_BEACH, plainGenerator);
		put(COLD_BEACH, desertGenerator);
		put(BIRCH_FOREST, forestGenerator);
		put(BIRCH_FOREST_HILLS, forestGenerator);
		put(ROOFED_FOREST, forestGenerator);
		put(TAIGA_COLD, forestGenerator);
		put(TAIGA_COLD_HILLS, forestGenerator);
		put(REDWOOD_TAIGA, forestGenerator);
		put(REDWOOD_TAIGA_HILLS, forestGenerator);
		put(EXTREME_HILLS_WITH_TREES, extremeGenerator);
		put(SAVANNA, plainGenerator);
		put(SAVANNA_ROCK, plainGenerator);
		put(MESA, desertGenerator);
		put(MESA_ROCK, desertGenerator);
		put(MESA_CLEAR_ROCK, desertGenerator);
		put(VOID, null);
		put(MUTATED_PLAINS, plainGenerator);
		put(MUTATED_DESERT, desertGenerator);
		put(MUTATED_EXTREME_HILLS, extremeGenerator);
		put(MUTATED_FOREST, forestGenerator);
		put(MUTATED_TAIGA, forestGenerator);
		put(MUTATED_SWAMPLAND, forestGenerator);
		put(MUTATED_ICE_FLATS, plainGenerator);
		put(MUTATED_JUNGLE, forestGenerator);
		put(MUTATED_JUNGLE_EDGE, forestGenerator);
		put(MUTATED_BIRCH_FOREST, forestGenerator);
	    put(MUTATED_BIRCH_FOREST_HILLS, forestGenerator);
	    put(MUTATED_ROOFED_FOREST, forestGenerator);
	    put(MUTATED_TAIGA_COLD, forestGenerator);
	    put(MUTATED_REDWOOD_TAIGA, forestGenerator);
	    put(MUTATED_REDWOOD_TAIGA_HILLS, forestGenerator);
	    put(MUTATED_EXTREME_HILLS_WITH_TREES, extremeGenerator);
	    put(MUTATED_SAVANNA, plainGenerator);
	    put(MUTATED_SAVANNA_ROCK, plainGenerator);
	    put(MUTATED_MESA, desertGenerator);
	    put(MUTATED_MESA_ROCK, desertGenerator);
	    put(MUTATED_MESA_CLEAR_ROCK, desertGenerator);
	}
	
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome){
		try {
			ChunkData chunk = createChunkData(world);
			for(int x = 0; x < 16; x++)
				for(int z = 0; z < 16; z++)
					getGenerator(biome.getBiome(x, z)).generate(chunk, world, random, chunkX, chunkZ, x, z);
			//setGround(chunk, chunkX, chunkZ, world.getSeed(), random, Material.OBSIDIAN);
			return chunk;
		} catch(Exception ex){
			Bukkit.getLogger().log(Level.WARNING, "Failed to generate chunk (" + chunkX + "," + chunkZ + ")", ex);
			return super.generateChunkData(world, random, chunkX, chunkZ, biome);
		}
	}
	
	/*
	private static void setGround(ChunkData chunk, int chunkX, int chunkZ, long seed, Random random, Material block){
		for(int x = 0; x < 16; x++)
			for(int z = 0; z < 16; z++)
				setGround(chunk, chunkX, chunkZ, x, z, seed, random, block);
	}
	*/
	
	public static int setGround(ChunkData chunk, int chunkX, int chunkZ, int x, int z, long seed, Random random, Material block){
		int height = GeneratorMaths.getHeight(x + chunkX * 16, z + chunkZ * 16, 100, 200, 0.006, seed, random);
		chunk.setRegion(x, 0, z, x + 1, height, z + 1, block);
		return height;
	}
	
	public static int setGround(ChunkData chunk, int chunkX, int chunkZ, int x, int z, int minY, int maxY, long seed, Random random, Material block){
		int height = GeneratorMaths.getHeight(x + chunkX * 16, z + chunkZ * 16, minY, maxY - minY, 0.006, seed, random);
		chunk.setRegion(x, 0, z, x + 1, height, z + 1, block);
		return height;
	}
	
	private static BiomeGenerator getGenerator(Biome biome){
		return biomeMap[biome.ordinal()];
	}
}
