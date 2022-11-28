package algorithms.FourthLab.binary;

import functions.FourthLab.Function1;
import algorithms.FourthLab.Interval;
public class test1 {
	public static void main(String[] args) {
	
		Function1 function = new Function1();
		int PopulationSize = 200;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 1000000;
		int dimension = 2;
		
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
