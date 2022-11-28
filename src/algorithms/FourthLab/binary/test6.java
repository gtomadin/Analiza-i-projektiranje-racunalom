package algorithms.FourthLab.binary;

import functions.FourthLab.Function6;
import algorithms.FourthLab.Interval;
public class test6 {
	
public static void main(String[] args) {
		
		Function6 function = new Function6();
		int PopulationSize = 200;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 1000000;
		int dimension = 9;
		
		int p = 4;
		
		try {
			GeneticAlgorithmBinary gab = new GeneticAlgorithmBinary(function, PopulationSize, interval, GenerationNumber, dimension, p);
			System.out.println(gab.getResult().getPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
