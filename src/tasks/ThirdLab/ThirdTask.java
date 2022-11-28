package tasks.ThirdLab;

import java.util.ArrayList;
import java.util.List;

import algorithms.ThirdLab.Box;
import algorithms.ThirdLab.Box.AbstractExplicitRestriction;
import algorithms.ThirdLab.Box.AbstractImplicitRestriction;
import algorithms.ThirdLab.Point;
import functions.ThirdLab.Function1;
import functions.ThirdLab.Function2;


public class ThirdTask {
	
	public static void main(String[] args) {
		
		// explicit restrictions 
		Box.AbstractExplicitRestriction explicitRestriction = new AbstractExplicitRestriction(-100, 100) {};
		
		List<AbstractExplicitRestriction> explicitRestrictions = new ArrayList<Box.AbstractExplicitRestriction>();
		explicitRestrictions.add(explicitRestriction);
		
		// implicit restrictions
		Box.AbstractImplicitRestriction implicitRestriction1 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
//				System.out.println(x_2 + " - " + x_1 + " = " + (x_2 - x_1));
				return (x_2 - x_1) >= 0; 
			}
			
		};
		
		Box.AbstractImplicitRestriction implicitRestriction2 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
//				System.out.println("2 - " + x_1 + " = " + (2 - x_1));
				return (2 - x_1) >= 0;
			}
		};
		
		List<AbstractImplicitRestriction> implicitRestrictions = new ArrayList<Box.AbstractImplicitRestriction>();
		
		implicitRestrictions.add(implicitRestriction1);
		implicitRestrictions.add(implicitRestriction2);
		
		
		Function1 function1 = new Function1();
		double[] double1 = {-1.9, 2};
		
		System.out.println("Function 1: start point (-1.9, 2), result point is (1, 1)");
		
		try {
			Point point1 = new Point(double1);
						
			Box box1 = new Box(function1, point1, implicitRestrictions, explicitRestrictions);
			
			System.out.println("Min with Box: " + box1.getResultPoint());
			
			System.out.println("Number of function calls : " + function1.getFunctionValueCallCounter());
			function1.setFunctionValueCallCounter();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("==============");
		
		Function2 function2 = new Function2();

		double[] double2 = {0.1, 0.3};
		System.out.println("Function 2: start point (0.1, 0.3), result point is (4, 2)");
		try {
			Point point2 = new Point(double2);
			

			Box box2 = new Box(function2, point2, implicitRestrictions, explicitRestrictions);
			
			System.out.println("Min with Box: " + box2.getResultPoint());
			System.out.println("Number of function calls : " + function2.getFunctionValueCallCounter());
			
			function2.setFunctionValueCallCounter();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
