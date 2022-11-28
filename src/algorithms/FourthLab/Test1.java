package algorithms.FourthLab;

import functions.FourthLab.Function1;

public class Test1 {
	public static void main(String[] args) {
		
		Function1 function = new Function1();
		int PopulationSize = 100;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 100000;
		int dimension = 2;
		
		try {
			GeneticAlgorithm ga = new GeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
			System.out.println(ga.getResult().getPoint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
