package tasks.ThirdLab;

import algorithms.ThirdLab.GradientDescent;
import algorithms.ThirdLab.Point;
import functions.ThirdLab.Function3;
import gradient.ThirdLab.Gradient3;

public class FirstTask {
	public static void main(String[] args) {
		System.out.println("Task 1 Gradient Descent");
		System.out.println("Result point is (2, -3)\n");
		
		double[] values = {0 ,0};
		
		try {
			
			Point point = new Point(values);
			
			Function3 function = new Function3();
			
			Gradient3 gradient = new Gradient3();
			
			GradientDescent gdf = new GradientDescent(function, gradient, point, false);
			
			Point resultWithoutGoldenRatio = gdf.getResultPoint();
			
			System.out.println("Result gotten without ratio: " + resultWithoutGoldenRatio);
			
			System.out.println("Number of function calls: " + function.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient.getGradientValueCallCounter());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===========");
		
		try {
			
			Point point = new Point(values);
			
			Function3 function = new Function3();
			
			Gradient3 gradient = new Gradient3();
				
			GradientDescent gdt = new GradientDescent(function, gradient, point, true);
			
			Point resultWithGoldenRatio = gdt.getResultPoint();
			
			System.out.println("Result gotten with ratio: " + resultWithGoldenRatio);
			
			System.out.println("Number of function calls: " + function.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient.getGradientValueCallCounter());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
