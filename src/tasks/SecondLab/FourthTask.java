package tasks.SecondLab;

import algorithms.SecondLab.Point;
import algorithms.SecondLab.Simplex;
import functions.SecondLab.Function1;

public class FourthTask {
	public static void main(String[] args) {
		

		double[] point1d = {0.5, 0.5};
		double[] point2d = {20, 20};
		
		try {
			Point point1 = new Point(point1d);
		
			Point point2 = new Point(point2d);
			
			Function1 function = new Function1();
			
			Point[] result1 = new Point[20];
			Point[] result2 = new Point[20];
			
			int[] count1 = new int[20]; 
			int[] count2 = new int[20]; 
			
			
			for(int i=0; i<20;++i) {
				Simplex.SIMPLEX_STEP = (i+1);
				
				// point(0.5, 0.5)
				try {
					Simplex si1 = new Simplex(function, point1);
					result1[i] = si1.getResultPoint();
					count1[i]  = function.getCallCounter();
					System.out.println("(0.5, 0.5) " + (i+1) + " : " + result1[i] + " " + count1[i]);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				function.setCallCounter();
				
				// point(20, 20)
				try {
					Simplex si2 = new Simplex(function, point2);
					result2[i] = si2.getResultPoint();
					count2[i]  = function.getCallCounter();
					System.out.println("(20, 20) " + (i+1) + " : " + result2[i] + " " + count2[i]);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				
				function.setCallCounter();
				
			}
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
