package tasks.FourthLab;

import algorithms.FourthLab.GeneticAlgorithm;
import algorithms.FourthLab.Interval;
import algorithms.FourthLab.binary.GeneticAlgorithmBinary;
import functions.FourthLab.Function1;
import functions.FourthLab.Function3;
import functions.FourthLab.Function6;
import functions.FourthLab.Function7;

public class FirstTask {
	
	
	public static void main(String[] args) {
		
		Function1 function1 = new Function1();
		Function3 function3 = new Function3();
		Function6 function6 = new Function6();
		Function7 function7 = new Function7();
		
		int PopulationSize = 100;
		
		int PopulationSizeBinary = 200;
		
	
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 100000;
		
		
		int dimension1 = 2;
		int dimension3 = 5;
		int dimension6 = 2;
		int dimension7 = 2;
		
		int p = 3;
		
		try {
			GeneticAlgorithm ga1 = new GeneticAlgorithm(function1, PopulationSize, interval, GenerationNumber, dimension1);
			
			System.out.println("Funcion 1: " + ga1.getResult().getPoint());
			System.out.println("Function value: " + function1.getFunctionValue(ga1.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		try {
			GeneticAlgorithm ga3 = new GeneticAlgorithm(function3, PopulationSize, interval, GenerationNumber, dimension3);
			
			System.out.println("Funcion 3: " + ga3.getResult().getPoint());
			System.out.println("Function value: " + function3.getFunctionValue(ga3.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga6 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension6);
			
			System.out.println("Funcion 6: " + ga6.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(ga6.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithm ga7 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension7);
			
			System.out.println("Funcion 7: " + ga7.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(ga7.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=====================================================");
		System.out.println("");
		
		try {
			GeneticAlgorithmBinary gab1 = new GeneticAlgorithmBinary(function1, PopulationSizeBinary, interval, GenerationNumber, dimension1, p);
			
			System.out.println("Funcion 1 binary: " + gab1.getResult().getPoint());
			System.out.println("Function value: " + function1.getFunctionValue(gab1.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithmBinary gab3 = new GeneticAlgorithmBinary(function3, PopulationSizeBinary, interval, GenerationNumber, dimension3, p);
			
			System.out.println("Funcion 3 binary: " + gab3.getResult().getPoint());
			System.out.println("Function value: " + function3.getFunctionValue(gab3.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithmBinary gab6 = new GeneticAlgorithmBinary(function6, PopulationSizeBinary, interval, GenerationNumber, dimension6, p);
			
			System.out.println("Funcion 6 binary: " + gab6.getResult().getPoint());
			System.out.println("Function value: " + function6.getFunctionValue(gab6.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GeneticAlgorithmBinary gab7 = new GeneticAlgorithmBinary(function7, PopulationSizeBinary, interval, GenerationNumber, dimension7, p);
			
			System.out.println("Funcion 7 binary: " + gab7.getResult().getPoint());
			System.out.println("Function value: " + function7.getFunctionValue(gab7.getResult().getPoint()));
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
