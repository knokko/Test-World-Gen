package nl.knokko.main;

import nl.knokko.world.gen.CustomGenerator;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.ChunkGenerator;
 
public class WorldGenPlugin extends JavaPlugin {
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new CustomGenerator();
	}
	
}