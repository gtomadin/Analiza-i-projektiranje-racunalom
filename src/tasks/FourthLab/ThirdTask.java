package tasks.FourthLab;

import algorithms.FourthLab.GeneticAlgorithm;
import algorithms.FourthLab.Interval;
import algorithms.FourthLab.binary.GeneticAlgorithmBinary;
import functions.FourthLab.Function6;
import functions.FourthLab.Function7;

import java.util.ArrayList;
import java.util.List;

public class ThirdTask {
	
	public static void main(String[] args) {
		
		Function6 function6 = new Function6();
		Function7 function7 = new Function7();
		
		int PopulationSize = 100;
	
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 10000;
		
		
		int dimension1 = 3;
		int dimension2 = 6;
		
		int p = 4;
		
		List<Double> fun6_dim3 = new ArrayList<>(); 
		List<Double> fun6_dim6 = new ArrayList<>();
		List<Double> fun7_dim3 = new ArrayList<>();
		List<Double> fun7_dim6 = new ArrayList<>();
		
		List<Double> fun6_dim3_bin = new ArrayList<>();
		List<Double> fun6_dim6_bin = new ArrayList<>();
		List<Double> fun7_dim3_bin = new ArrayList<>();
		List<Double> fun7_dim6_bin = new ArrayList<>();
		
		double fun6_dim3c = 0; 
		double fun6_dim6c = 0;
		double fun7_dim3c = 0;
		double fun7_dim6c = 0;
		
		double fun6_dim3_binc = 0;
		double fun6_dim6_binc = 0;
		double fun7_dim3_binc = 0;
		double fun7_dim6_binc = 0;
		
		double E = 10e-6;
		
		for(int i = 0; i < 10; ++i) {
			try {
				GeneticAlgorithm ga1 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension1);
				
//				System.out.println("Function 6 dimension=" + dimension1 + " : " + ga1.getResult().getPoint());
//				System.out.println("Function value: " + function6.getFunctionValue(ga1.getResult().getPoint()));
//				System.out.println("");
				double a = function6.getFunctionValue(ga1.getResult().getPoint());
				fun6_dim3.add(a);
				if(a <= E) {
					fun6_dim3c++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithm ga2 = new GeneticAlgorithm(function6, PopulationSize, interval, GenerationNumber, dimension2);
				
//				System.out.println("Function 6 dimension=" + dimension2 + " : " + ga2.getResult().getPoint());
//				System.out.println("Function value: " + function6.getFunctionValue(ga2.getResult().getPoint()));
//				System.out.println("");
				double a = function6.getFunctionValue(ga2.getResult().getPoint());
				fun6_dim6.add(a);
				if(a <= E) {
					fun6_dim6c++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithm ga1 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension1);
				
//				System.out.println("Function 7 dimension=" + dimension1 + " : " + ga1.getResult().getPoint());
//				System.out.println("Function value: " + function7.getFunctionValue(ga1.getResult().getPoint()));
//				System.out.println("");
				double a = function7.getFunctionValue(ga1.getResult().getPoint());
				fun7_dim3.add(a);
				if(a <= E) {
					fun7_dim3c++;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithm ga2 = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension2);
				
//				System.out.println("Function 7 dimension=" + dimension2 + " : " + ga2.getResult().getPoint());
//				System.out.println("Function value: " + function7.getFunctionValue(ga2.getResult().getPoint()));
//				System.out.println("");
				double a = function7.getFunctionValue(ga2.getResult().getPoint());
				fun7_dim6.add(a);
				if(a <= E) {
					fun7_dim6c++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println("============================================================");
			
			try {
				GeneticAlgorithmBinary gab1 = new GeneticAlgorithmBinary(function6, PopulationSize, interval, GenerationNumber, dimension1, p);
				
//				System.out.println("Function 6 dimension=" + dimension1 + " : " + gab1.getResult().getPoint());
//				System.out.println("Function value: " + function6.getFunctionValue(gab1.getResult().getPoint()));
//				System.out.println("");
				double a = function6.getFunctionValue(gab1.getResult().getPoint());
				fun6_dim3_bin.add(a);
				if(a <= E) {
					fun6_dim3_binc++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithmBinary gab2 = new GeneticAlgorithmBinary(function6, PopulationSize, interval, GenerationNumber, dimension2, p);
				
//				System.out.println("Function 6 dimension=" + dimension2 + " : " + gab2.getResult().getPoint());
//				System.out.println("Function value: " + function6.getFunctionValue(gab2.getResult().getPoint()));
//				System.out.println("");
				double a = function6.getFunctionValue(gab2.getResult().getPoint());
				fun6_dim6_bin.add(a);
				if(a <= E) {
					fun6_dim6_binc++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithmBinary gab1 = new GeneticAlgorithmBinary(function7, PopulationSize, interval, GenerationNumber, dimension1, p);
				
//				System.out.println("Function 7 dimension=" + dimension1 + " : " + gab1.getResult().getPoint());
//				System.out.println("Function value: " + function7.getFunctionValue(gab1.getResult().getPoint()));
//				System.out.println("");
				double a = function7.getFunctionValue(gab1.getResult().getPoint());
				fun7_dim3_bin.add(a);
				if(a <= E) {
					fun7_dim3_binc++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				GeneticAlgorithmBinary gab1 = new GeneticAlgorithmBinary(function7, PopulationSize, interval, GenerationNumber, dimension2, p);
				
//				System.out.println("Function 7 dimension=" + dimension2 + " : " + gab1.getResult().getPoint());
//				System.out.println("Function value: " + function7.getFunctionValue(gab1.getResult().getPoint()));
//				System.out.println("");
				double a = function7.getFunctionValue(gab1.getResult().getPoint());
				fun7_dim6_bin.add(a);
				if(a <= E) {
					fun7_dim6_binc++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		System.out.println(fun6_dim3.size());
//		System.out.println(fun6_dim6.size());
//		System.out.println(fun7_dim3.size());
//		System.out.println(fun7_dim6.size());
//		
//		System.out.println(fun6_dim3_bin.size());
//		System.out.println(fun6_dim6_bin.size());
//		System.out.println(fun7_dim3_bin.size());
//		System.out.println(fun7_dim6_bin.size());
		
		for(int i=0;i<10;++i) {
			System.out.println(fun6_dim3.get(i) + "," +
								fun6_dim6.get(i) + "," +
								fun7_dim3.get(i) + "," +
								fun7_dim6.get(i) + "," +
								fun6_dim3_bin.get(i) + "," +
								fun6_dim6_bin.get(i) + "," +
								fun7_dim3_bin.get(i) + "," +
								fun7_dim6_bin.get(i));
		}
		
		System.out.println("");
		
		System.out.println("Pogotci funkcija 6 dimenzija 3: " + fun6_dim3c);
		System.out.println("Pogotci funkcija 6 dimenzija 6: " + fun6_dim6c);
		System.out.println("Pogotci funkcija 7 dimenzija 3: " + fun7_dim3c);
		System.out.println("Pogotci funkcija 7 dimenzija 6: " + fun7_dim6c);
		
		System.out.println("Pogotci funkcija 6 dimenzija 3 binarni: " + fun6_dim3_binc);
		System.out.println("Pogotci funkcija 6 dimenzija 6 binarni: " + fun6_dim6_binc);
		System.out.println("Pogotci funkcija 7 dimenzija 3 binarni: " + fun7_dim3_binc);
		System.out.println("Pogotci funkcija 7 dimenzija 6 binarni: " + fun7_dim6_binc);
		
	}
}
