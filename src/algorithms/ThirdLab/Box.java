package algorithms.ThirdLab;

import java.util.List;
import java.util.Random;

import functions.ThirdLab.AbstractFunction;
import matrix.Matrix;
import matrix.MatrixOperations;

public class Box {
	
	// defining the constants
	final private static double ALFA = 1.3;
	private double E = 1e-6;
	private Point resultPoint;
	
	// constructor: creating a box algorithm 
	public Box(AbstractFunction function, Point point, List<AbstractImplicitRestriction> implicitRestrictions, List<AbstractExplicitRestriction> explicitRestrictions) throws Exception {
		
		if(function != null && point != null && implicitRestrictions != null && explicitRestrictions != null) {
			initBox(function, point, implicitRestrictions, explicitRestrictions);
		}
		
		
	}
	
	// method that calculate the result point using the starting point and implicit and explicit restrictions
	private void initBox(AbstractFunction function, Point point, List<AbstractImplicitRestriction> implicitRestrictions, List<AbstractExplicitRestriction> explicitRestrictions) throws Exception{
	
		// checking the explicit restrictions
		for(int i=0; i<explicitRestrictions.size(); ++i) {
//			explicitRestrictions.get(i).validate(point);
			for(int j=0;j<point.getNumberOfColumns(); ++j) {
				if(explicitRestrictions.get(i).getMaxValue() < point.getValueAt(0, j) || explicitRestrictions.get(i).getMinValue() > point.getValueAt(0, j)) {
					throw new Exception("Explicit Restriction not satisfied");
				}
			}
		}
		
		// checking the implicit restrictions
		for(int i=0; i<implicitRestrictions.size(); ++i) {
			if(!implicitRestrictions.get(i).validate(point)) {
				throw new Exception("Implicit Restriction not satisfied");
			}
		}
		
		
		Point x_C = new Point(point);
		
		Point[] boxPoints = new Point[2 * x_C.getNumberOfColumns()]; // defining the box points array 
	
		Random random = new Random();
		
		// generating a set of 2n points
		for(int t=0; t < (2 * x_C.getNumberOfColumns()); ++t) {
			double[] values = new double[x_C.getNumberOfColumns()];
			for(int i=0; i < x_C.getNumberOfColumns(); ++i) {
				for(int j=0; j<explicitRestrictions.size(); ++j) {
					// new point between explicit min and max
					values[i] = explicitRestrictions.get(j).getMinValue() + random.nextDouble() * (explicitRestrictions.get(j).getMaxValue() - explicitRestrictions.get(j).getMinValue());
				}
//				System.out.println(values[i]);
			}
			
			Point newTpoint = new Point(values);
//			System.out.println(newTpoint);
			// checking the implicit restrictions for the new point
			while(implicitRestrictionsAreNotSatisfied(newTpoint, implicitRestrictions)) {
				Point helpPoint = new Point(MatrixOperations.matrix_Addition(newTpoint, x_C));
				newTpoint = new Point(MatrixOperations.scalar_Multiplication(helpPoint, 0.5));
				
			}
		
			// adding the new point to the set
			boxPoints[t] = new  Point(newTpoint);
			

			
//			// calculating the Centroid
			x_C = calculateCentroidWithNoH(boxPoints);
//			System.out.println(x_C.getNumberOfColumns());
		}
		
		int loopCounter = 0; // initializing the loop counter
		

		
		while(true) {
			
			double[] FxBoxValues = new double[boxPoints.length]; // defining the box points array 
			
			// calculating the function values for all the points
			for(int i=0; i<boxPoints.length; ++i) {
				FxBoxValues[i] = function.getFunctionValue(boxPoints[i]);
			}
			
			// finding the Max and second Max indexes
			double hFxvalue = FxBoxValues[0];
			double h2Fxvalue = FxBoxValues[0];
			
			int h = 0;
			int h2 = 0;
			
			for(int i=1; i<FxBoxValues.length; ++i) {
				if(FxBoxValues[i] > h2Fxvalue) {
					h2Fxvalue = FxBoxValues[i];
					h2 = i;
				}
				if(h2Fxvalue > hFxvalue) {
					double Fxhelp = hFxvalue;
					hFxvalue = h2Fxvalue;
					h2Fxvalue = Fxhelp;
					
					int hhelp = h;
					h = h2;
					h2 = hhelp;
				}
			}
			
			// calculating the Centroid
			x_C = calculateCentroid(boxPoints, h);
//			System.out.println(x_C);
			
			
			// calculating the reflection
			Point x_R = reflection(x_C, boxPoints[h]);
//			System.out.println("prije: " + x_R);
			
			// setting the reflection
			for(int i=0; i<x_R.getNumberOfColumns(); ++i) {
				for(int j=0; j<explicitRestrictions.size(); ++j) {
					if(x_R.getValueAt(0, i) < explicitRestrictions.get(j).getMinValue()) {
					
						x_R.setValueAt(0, i, explicitRestrictions.get(j).getMinValue());			
					
					}else if(x_R.getValueAt(0, i) > explicitRestrictions.get(j).getMaxValue()) {
						
						x_R.setValueAt(0, i, explicitRestrictions.get(j).getMaxValue());
						
					}
				}
			}

			// checking the implicit restrictions for the new point
			while(implicitRestrictionsAreNotSatisfied(x_R, implicitRestrictions)) {
				Point helpPoint = new Point(MatrixOperations.matrix_Addition(x_R, x_C));
				x_R = new Point(MatrixOperations.scalar_Multiplication(helpPoint, 0.5));
			}
			
//			System.out.println("pole2: " + x_R);
			// if the point is not good enough
			if(function.getFunctionValue(x_R) > FxBoxValues[h2]) {
				Point helpPoint = new Point(MatrixOperations.matrix_Addition(x_R, x_C));
				x_R = new Point(MatrixOperations.scalar_Multiplication(helpPoint, 0.5));
			}
			

			boxPoints[h] = x_R;
			
			// calculating the difference between F(x_C) and all the other values
			double sum = 0;
			double FxC = function.getFunctionValue(x_C);
			
			for(int i=0; i<FxBoxValues.length; ++i) {
				double a = FxC - FxBoxValues[i]; 
				sum += (a * a);
			}
			
			double result = sum / FxBoxValues.length;

			// if they are the define the result point
			if(Math.sqrt(result) < E) {
//				resultPoint = x_C;
//				break;
				resultPoint = x_C;
				break;
			}
			++loopCounter;
			
			// if is an infinite loop throw an exception 
			if(loopCounter  == 100000) {
				resultPoint = x_C;
				break;
//				throw new Exception("Impossible to calculate Simplex");
			}
		}
		
	}
	
	// method that checks all the implicit restriction
	private static boolean implicitRestrictionsAreNotSatisfied(Point point,
			List<AbstractImplicitRestriction> implicitRestrictions) throws Exception {
		for(int i = 0; i < implicitRestrictions.size(); ++i) {
			if(!implicitRestrictions.get(i).validate(point)) {
				return true;
			}
		}
		return false;
	}

	// method that calculate the reflection using the centroid and the h index point 
	private static Point reflection(Point x_C, Point x_H) throws Exception {
		Matrix Ax_C = MatrixOperations.scalar_Multiplication(x_C, 1 + ALFA);
		Matrix Ax_H = MatrixOperations.scalar_Multiplication(x_H, ALFA);
		return new Point(MatrixOperations.matrix_Subtraction(Ax_C, Ax_H));
	}
	
	// method that calculate the contraction using the centroid and the h index point
	private static Point calculateCentroid(Point[] simplexPoint, int h) throws Exception {
		double[] values = new double[simplexPoint.length / 2];
		
		for(int i = 0; i<simplexPoint.length; ++i) {
			if(i != h) {
				for(int j = 0; j<simplexPoint[i].getNumberOfColumns(); ++j) {
					values[j] += simplexPoint[i].getValueAt(0, j);
				}
			}
		}
		Point x_C = new Point(values);
		
		double skalar = simplexPoint.length - 1;
		skalar = 1 / skalar;
		
		x_C = new Point(MatrixOperations.scalar_Multiplication(x_C, skalar));
		
		return x_C;
	}
	
	// method that calculate the contraction using the centroid
	private static Point calculateCentroidWithNoH(Point[] simplexPoint) throws Exception {
		double[] values = new double[simplexPoint.length / 2];
		
		for(int i = 0; i<simplexPoint.length; ++i) {
			
			if(simplexPoint[i] != null) {
//				System.out.println(simplexPoint[i]);
				for(int j = 0; j<simplexPoint[i].getNumberOfColumns(); ++j) {
				
					values[j] += simplexPoint[i].getValueAt(0, j);
				}
			}
		}
		Point x_C = new Point(values);
		
		double skalar = simplexPoint.length - 1;
		skalar = 1 / skalar;
		
		x_C = new Point(MatrixOperations.scalar_Multiplication(x_C, skalar));
		
		return x_C;
	}
	
	// method that returns the result point
	public Point getResultPoint() {
		return resultPoint;
	}
	
	
	// class that defines Explicit Restrictions
	public static abstract class AbstractExplicitRestriction {
		private double minValue;
		private double maxValue;
		
		
		public AbstractExplicitRestriction(double minValue, double maxValue) {
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		public double getMinValue() {
			return minValue;
		}

		public double getMaxValue() {
			return maxValue;
		}

	}
	
	// class that defines Implicit Restrictions
	public static abstract class AbstractImplicitRestriction {
		
		public boolean validate(Point point) throws Exception {
			throw new Exception("Method not overrided");
		}
	}

}


