package algorithms.FourthLab.binary;

import functions.FourthLab.Function7;
import algorithms.FourthLab.Interval;

public class test7 {
public static void main(String[] args) {
		
		Function7 function = new Function7();
		int PopulationSize = 200;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 1000000;
		int dimension = 9;
		
		int p = 4;
		
		try {
			GeneticAlgorithmBinary gab = new GeneticAlgorithmBinary(function, PopulationSize, interval, GenerationNumber, dimension, p);
			ChromosomeBinary cb  = gab.getResult();
			
			System.out.println(cb.getPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
