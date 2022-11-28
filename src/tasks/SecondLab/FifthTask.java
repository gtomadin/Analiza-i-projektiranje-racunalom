package tasks.SecondLab;


import java.util.Random;

import algorithms.SecondLab.Point;
import algorithms.SecondLab.Simplex;
import functions.SecondLab.Function6;



public class FifthTask {
	public static void main(String[] args) {
		Random random = new Random();
		
		Function6 function = new Function6();
		int count = 0;
		int times = 1000;
		for(int i=0; i< times; ++i) {
			double value1 = random.nextDouble()*100 - 50;
			double value2 = random.nextDouble()*100 - 50;
			
			double[] pointd = {value1, value2};
			
			
			
			Point resPoint = null;
			double FPoint = 0;
			
			try {
				Point point = new Point(pointd);
//				resPoint = new HookeJeeves(function, point).getResultPoint();
				resPoint = new Simplex(function, point).getResultPoint();
//				resPoint = OptimizationAlgorithms.coordinateSearch(function, point);
				FPoint = function.getFunctionValue(resPoint);	
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			
			if(resPoint != null && FPoint < 1e-4) {
				++count;
			}
				
		}
		double probability = (double)count/times * 100;
		System.out.println(probability + "%");
		
		
	}
}
