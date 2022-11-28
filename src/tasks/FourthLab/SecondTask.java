package tasks.FourthLab;

import algorithms.FourthLab.GeneticAlgorithm;
import algorithms.FourthLab.Interval;
import functions.FourthLab.Function6;
import functions.FourthLab.Function7;

public class SecondTask {
	
	
	public static void main(String[] args) {
		
		
		Function6 function6 = new Function6();
		Function7 function7 = new Function7();
		
		
		int PopulationSize = 100;
		
	
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 100000;
		
		
		int dimension1 = 1;
		int dimension2 = 3;
		int dimension3 = 6;
		int dimension4 = 10;
		
		
		try {
			GeneticAlgorithm ga1 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension1);
			
			System.out.println("Function 6 dimension=" + dimension1 + " : " + ga1.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(ga1.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga2 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension2);
			
			System.out.println("Function 6 dimension=" + dimension2 + " : " + ga2.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(ga2.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga3 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension3);
			
			System.out.println("Function 6 dimension=" + dimension3 + " : " + ga3.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(ga3.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga4 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension4);
			
			System.out.println("Function 6 dimension=" + dimension4 + " : " + ga4.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(ga4.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("=================================================================================");
		System.out.println("");
		
		try {
			GeneticAlgorithm ga1 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension1);
			
			System.out.println("Function 7 dimension=" + dimension1 + " : " + ga1.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(ga1.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga2 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension2);
			
			System.out.println("Function 7 dimension=" + dimension2 + " : " + ga2.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(ga2.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga3 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension3);
			
			System.out.println("Function 7 dimension=" + dimension3 + " : " + ga3.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(ga3.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga4 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension4);
			
			System.out.println("Function 7 dimension=" + dimension4 + " : " + ga4.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(ga4.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
