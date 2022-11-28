package algorithms.FourthLab;

import functions.FourthLab.Function7;

public class Test7 {
	
	public static void main(String[] args) {
		Function7 function = new Function7();
		int PopulationSize = 100;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 100000;
		int dimension = 10;
		
		try {
			GeneticAlgorithm ga = new GeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
			System.out.println(ga.getResult().getPoint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
