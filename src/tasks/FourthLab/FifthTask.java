package tasks.FourthLab;

import java.util.ArrayList;
import java.util.List;

import algorithms.FourthLab.GeneticAlgorithm;
import algorithms.FourthLab.Interval;
import functions.FourthLab.Function7;

public class FifthTask {

	public static void main(String[] args) {
		
		Function7 function7 = new Function7();
		
		int PopulationSize = 100;
	
		Interval interval = new Interval(-50, 150);
		int GenerationNumber = 1000;
		
		
	
		int dimension = 6;
		
		
		List<Double> k3 = new ArrayList<Double>(); 
		List<Double> k5 = new ArrayList<Double>();
		List<Double> k7 = new ArrayList<Double>();
		List<Double> k9 = new ArrayList<Double>();
		
		
		for(int i = 3; i<10; i=i+2) {
			
			for(int j=0;j<10;++j) {
			
				GeneticAlgorithm ga;
				try {
					ga = new GeneticAlgorithm(function7, PopulationSize, interval, GenerationNumber, dimension, i);
					
					//System.out.println(i + " : " + function7.getFunctionValue(ga.getResult().getPoint()));
					double a = function7.getFunctionValue(ga.getResult().getPoint());
					if(i == 3) {
						k3.add(a);
					}
					if(i == 5) {
						k5.add(a);
					}
					if(i == 7) {
						k7.add(a);
					}
					if(i == 9) {
						k9.add(a);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
		}
		
		System.out.println("3,5,7,9");
		for(int i=0;i<10;++i) {
			System.out.println(k3.get(i) + "," + k5.get(i) + "," + k7.get(i) + "," + k9.get(i));
			
		}
		
	}
}
