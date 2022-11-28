package tasks.ThirdLab;

import algorithms.ThirdLab.GradientDescent;
import algorithms.ThirdLab.NewtonRaphson;
import algorithms.ThirdLab.Point;
import functions.ThirdLab.Function2;
import functions.ThirdLab.Function1;
import gradient.ThirdLab.Gradient1;
import gradient.ThirdLab.Gradient2;
import hessianmatrix.ThirdLab.HessianMatrix1;
import hessianmatrix.ThirdLab.HessianMatrix2;

public class SecondTask {
	public static void main(String[] args) {
		
		Function1 function1 = new Function1();
		Gradient1 gradient1 = new Gradient1();
		HessianMatrix1 hessianMatrix1 = new HessianMatrix1();
		double[] double1 = {-1.9, 2};
		
		System.out.println("Function 1: result point is (1, 1)");
		
		try {
			Point point1 = new Point(double1);
			
			GradientDescent gradientDescent1 = new GradientDescent(function1, gradient1, point1, true);
			
			System.out.println("Min with Gradient Descent: " + gradientDescent1.getResultPoint());
			
			System.out.println("Number of function calls: " + function1.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient1.getGradientValueCallCounter());
			
			function1.setFunctionValueCallCounter();
			gradient1.setGradientValueCallCounter();
			
			System.out.println("-");
			
			NewtonRaphson newtonRaphsonov1 = new NewtonRaphson(function1, gradient1, hessianMatrix1, point1, true);
			
			System.out.println("Min with Newton-Raphson : " + newtonRaphsonov1.getResultPoint());
			
			System.out.println("Number of function calls: " + function1.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient1.getGradientValueCallCounter() + 
					"\nNumber of hessianMatrix calls: " + hessianMatrix1.getHessianMatrixCallCounter());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("==============");
		
		Function2 function2 = new Function2();
		Gradient2 gradient2 = new Gradient2();
		HessianMatrix2 hessianMatrix2 = new HessianMatrix2();
		double[] double2 = {0.1, 0.3};
		System.out.println("Function 2: result point is (4, 2)");
		try {
			Point point2 = new Point(double2);
			
			GradientDescent gradientDescent2 = new GradientDescent(function2, gradient2, point2, true);
			
			System.out.println("Min with Gradient Descent: " + gradientDescent2.getResultPoint());
			
			System.out.println("Number of function calls: " + function2.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient2.getGradientValueCallCounter());
			
			
			function2.setFunctionValueCallCounter();
			gradient2.setGradientValueCallCounter();
			
			System.out.println("-");
			
			NewtonRaphson newtonRaphsonov2 = new NewtonRaphson(function2, gradient2, hessianMatrix2, point2, true);
			
			System.out.println("Min with Newton-Raphson : " + newtonRaphsonov2.getResultPoint());
			
			System.out.println("Number of function calls: " + function2.getFunctionValueCallCounter() + 
					"\nNumber of gradinet calls: " + gradient2.getGradientValueCallCounter() + 
					"\nNumber of hessianMatrix calls: " + hessianMatrix2.getHessianMatrixCallCounter());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
