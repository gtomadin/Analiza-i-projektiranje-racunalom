package algorithms.FourthLab;


import functions.FourthLab.Function3;

public class Test3 {
	
	public static void main(String[] args) {
		
		Function3 function = new Function3();
		int PopulationSize = 100;
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 100000;
		int dimension = 9;
		
		try {
			GeneticAlgorithm ga = new GeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
			System.out.println(ga.getResult().getPoint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
