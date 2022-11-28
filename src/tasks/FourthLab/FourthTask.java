package tasks.FourthLab;

import java.util.ArrayList;
import java.util.List;

import algorithms.FourthLab.GeneticAlgorithm;
import algorithms.FourthLab.Interval;
import functions.FourthLab.Function6;

public class FourthTask {
	public static void main(String[] args) {
		
		
		Function6 function6 = new Function6();
		
		int[] PopulationSize = {30, 50, 100, 200};
	
		double[] Pmutation = {0.1, 0.3, 0.5, 0.9};
		
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 1000;
		
		
		int dimension = 6;
		
		List<Double> pop30 = new ArrayList<Double>();
		List<Double> pop50 = new ArrayList<Double>();
		List<Double> pop100 = new ArrayList<Double>();
		List<Double> pop200 = new ArrayList<Double>();
		
		List<Double> PM1 = new ArrayList<>();
		List<Double> PM3 = new ArrayList<>();
		List<Double> PM5 = new ArrayList<>();
		List<Double> PM9 = new ArrayList<>();
		
		
		for(int i=0;i<PopulationSize.length; ++i) {
			
			for(int j=0;j<10;++j) {	
				
				try {
					GeneticAlgorithm ga = new GeneticAlgorithm(function6, PopulationSize[i], interval, GenerationNumber, dimension, Pmutation[2]);
//					System.out.println("Population size = " + PopulationSize[i] + " , P(mutation) = " + Pmutation[1] + " : " + function6.getFunctionValue(ga.getResult().getPoint()));
					double a = function6.getFunctionValue(ga.getResult().getPoint()) * 1000;
					
					if(PopulationSize[i] == 30) {
						pop30.add(a);
					}
					if(PopulationSize[i] == 50) {
						pop50.add(a);
					}
					if(PopulationSize[i] == 100) {
						pop100.add(a);
					}
					if(PopulationSize[i] == 200) {
						pop200.add(a);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("==================================");
		for(int i=0;i<Pmutation.length; ++i) {
			
			for(int j=0;j<10;++j) {
			
				try {
					GeneticAlgorithm ga = new GeneticAlgorithm(function6, PopulationSize[2], interval, GenerationNumber, dimension, Pmutation[i]);
//					System.out.println("Population size = " + PopulationSize[2] + " , P(mutation) = " + Pmutation[i] + " : " + function6.getFunctionValue(ga.getResult().getPoint()));
					double a = function6.getFunctionValue(ga.getResult().getPoint())* 1000;
					
					if(Pmutation[i] == 0.1) {
						PM1.add(a);
					}
					if(Pmutation[i] == 0.3) {
						PM3.add(a);
					}
					if(Pmutation[i] == 0.5) {
						PM5.add(a);
					}
					if(Pmutation[i] == 0.9) {
						PM9.add(a);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}
	
	System.out.println("30,50,100,200");
	for(int i=0;i<10;++i) {
		System.out.println(pop30.get(i) + "," + pop50.get(i) + "," + pop100.get(i) + "," + pop200.get(i));
	}
	
	System.out.println("0.1,0.3,0.5,0.9");
	for(int i=0;i<10;++i) {
		System.out.println(PM1.get(i) + "," + PM3.get(i) + "," + PM5.get(i) + "," + PM9.get(i));
	}
		
	}
}
