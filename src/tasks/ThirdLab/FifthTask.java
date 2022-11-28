package tasks.ThirdLab;

import java.util.ArrayList;
import java.util.List;

import algorithms.ThirdLab.Point;
import algorithms.ThirdLab.Transform;
import algorithms.ThirdLab.Transform.AbstractImplicitRestriction;
import functions.ThirdLab.Function4;

public class FifthTask {
	public static void main(String[] args) {
		
		// inequality restriction
		Transform.AbstractImplicitRestriction implicitRestriction1 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);

				return (3 - x_1 - x_2) >= 0; 
			}

			@Override
			public double getRestrictionValue(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);

				return 3 - x_1 - x_2; 
			}
			
			
			
		};
		
		Transform.AbstractImplicitRestriction implicitRestriction2 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
				
				return (3 + 1.5 * x_1 - x_2) >= 0;
			}

			@Override
			public double getRestrictionValue(Point point) throws Exception {
				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
				
				return 3 + 1.5 * x_1 - x_2;
			}
			
		};
		
		// equality restriction
		Transform.AbstractImplicitRestriction implicitRestriction3 = new AbstractImplicitRestriction() {

			@Override
			public boolean validate(Point point) throws Exception {
//				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
				
				return (x_2 - 1) == 0; 
			}

			@Override
			public double getRestrictionValue(Point point) throws Exception {
//				double x_1 = point.getValueAt(0, 0);
				double x_2 = point.getValueAt(0, 1);
				
				return x_2 - 1; 
			}
			
			
			
		};
		
		
		List<AbstractImplicitRestriction> inequality = new ArrayList<Transform.AbstractImplicitRestriction>();
		List<AbstractImplicitRestriction> equality = new ArrayList<Transform.AbstractImplicitRestriction>();
		
		inequality.add(implicitRestriction1);
		inequality.add(implicitRestriction2);
		equality.add(implicitRestriction3);
		
		
		System.out.println("Function 4: result point is (3, 0)");
		
		Function4 function4 = new Function4();
		
		double[] double1 = {5, 5};
		
		try {
			
			Point point1 = new Point(double1);
			System.out.println("Start point: " + point1);
			
			Transform transform = new Transform(function4, point1, inequality, equality);
			
			System.out.println("Min with Transform: " + transform.getResultPoint());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===============");
		
		double[] double2 = {0, 0};
		
		try {
			
			Point point2 = new Point(double2);
			System.out.println("Start point: " + point2);
			
			Transform transform = new Transform(function4, point2, inequality, equality);
			System.out.println("Min with Transform: " + transform.getResultPoint());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
