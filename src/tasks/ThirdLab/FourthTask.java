package tasks.ThirdLab;

import java.util.ArrayList;
import java.util.List;

import algorithms.ThirdLab.Point;
import algorithms.ThirdLab.Transform;
import algorithms.ThirdLab.Transform.AbstractImplicitRestriction;
import functions.ThirdLab.Function1;
import functions.ThirdLab.Function2;

public class FourthTask {
	public static void main(String[] args) {
		
		// implicit restrictions 
		Transform.AbstractImplicitRestriction implicitRestriction1 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
//				System.out.println(x_2 + " - " + x_1 + " = " + (x_2 - x_1));
				return (x_2 - x_1) >= 0; 
			}

			@Override
			public double getRestrictionValue(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
//				System.out.println(x_2 + " - " + x_1 + " = " + (x_2 - x_1));
				return x_2 - x_1; 
			}
		};
		
		Transform.AbstractImplicitRestriction implicitRestriction2 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
//				System.out.println("2 - " + x_1 + " = " + (2 - x_1));
				return (2 - x_1) >= 0;
			}

			@Override
			public double getRestrictionValue(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
//				System.out.println("2 - " + x_1 + " = " + (2 - x_1));
				return 2 - x_1;
			}
			
		};
		
		
		List<AbstractImplicitRestriction> implicitRestrictions = new ArrayList<Transform.AbstractImplicitRestriction>();
		
		implicitRestrictions.add(implicitRestriction1);
		implicitRestrictions.add(implicitRestriction2);
		

		Function1 function1 = new Function1();
		double[] double1 = {-1.9, 2};
		
		System.out.println("Function 1: result point is (1, 1)");
		
		try {
			Point point1 = new Point(double1);
			
			Transform transform1 = new Transform(function1, point1, implicitRestrictions, null);
			
			System.out.println("Min with Transform: " + transform1.getResultPoint());
			
			System.out.println("Number of function calls: " + function1.getFunctionValueCallCounter());
			
			function1.setFunctionValueCallCounter();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("==============");
		
		Function2 function2 = new Function2();

		double[] double2 = {0.1, 0.3};
		System.out.println("Function 2: result point is (4, 2)");
		try {
			Point point2 = new Point(double2);
			
			Transform transform2 = new Transform(function2, point2, implicitRestrictions, null);
			
			System.out.println("Min with Transform: " + transform2.getResultPoint());
			
			System.out.println("Number of function calls: " + function2.getFunctionValueCallCounter());
			
			function2.setFunctionValueCallCounter();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
