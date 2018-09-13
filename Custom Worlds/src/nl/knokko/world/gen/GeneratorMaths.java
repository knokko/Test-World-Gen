package nl.knokko.world.gen;

import java.util.HashMap;
import java.util.Random;

public class GeneratorMaths {
	
	private static final HashMap<Long, double[][]> seedMap = new HashMap<Long, double[][]>();
	
	private static int getHeight(int x, int z, double factor1, double factor2, double factor3, double factor4, double maxDeltaHeight){
		double factorX = modSin(x * factor1) * modSin(x * factor2);
		double factorZ = modSin(z * factor3) * modSin(z * factor4);
		double factor = (factorX + factorZ) / 2;
		return (int) (maxDeltaHeight * factor);
	}
	
	public static int getHeight(int x, int z, int minHeight, int maxDeltaHeight, double randomInfluence, long seed, Random random){
		if(!seedMap.containsKey(seed))
			setSeedMap(seed);
		double[][] value = seedMap.get(seed);
		double height = minHeight + random.nextInt(maxDeltaHeight) * ((randomInfluence * TOTAL_WEIGHT) / (TOTAL_WEIGHT + randomInfluence * TOTAL_WEIGHT));
		for(int i = 0; i < S; i++)
			height += getHeight(x, z, value[i][0], value[i][1], value[i][2], value[i][3], maxDeltaHeight * (getWeight(i) / (TOTAL_WEIGHT + randomInfluence * TOTAL_WEIGHT)));
		return (int) height;
		/*
		return minHeight
				+ getHeight(x, z, 0.00018348734, 0.00012847834, 0.00012847834, 0.0001847834, maxDeltaHeight * 0.5f, seed)
				+ getHeight(x, z, 0.0012923, 0.001237824, 0.00172367, 0.00123, maxDeltaHeight * 0.27f, seed)
				+ getHeight(x, z, 0.0394892, 0.032324386, 0.03623, 0.0332837, maxDeltaHeight * 0.2f, seed)
				+ getHeight(x, z, 0.42887834, 0.42247834, 0.4223346, 0.422439, maxDeltaHeight * 0.03f, seed);
				*/
	}
	
	private static double modSin(double d){
		return (1 + Math.sin(d)) / 2;
	}
	
	private static final int S = 10;
	
	private static final double TOTAL_WEIGHT;
	
	static {
		double weight = 0;
		for(int i = 0; i < S; i++){
			weight += getWeight(i);
			System.out.println("getWeight(" + i + ") is " + getWeight(i));
		}
		System.out.println("total weight = " + weight);
		TOTAL_WEIGHT = weight;
	}
	
	private static void setSeedMap(long seed){
		Random random = new Random(seed);
		double[][] values = new double[S][4];
		for(int i = 0; i < S; i++){
			values[i] = new double[4];
			String s = "values[" + i + "] = (";
			for(int j = 0; j < 4; j++){
				values[i][j] = around(1 / Math.pow(2, (i + 3)), random);
				s += values[i][j] + ",";
			}
			s += ")";
			System.out.println(s);
		}
		seedMap.put(seed, values);
	}
	
	private static double getWeight(int index){
		return Math.pow(1.3, index);
	}
	
	private static double around(double d, Random r){
		return d * (r.nextDouble() * 0.2 + 0.9);
	}
}
