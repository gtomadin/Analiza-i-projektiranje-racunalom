package algorithms.FourthLab;

import functions.FourthLab.Function6;

public class Test6 {
	public static void main(String[] args) {
		Function6 function = new Function6();
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
