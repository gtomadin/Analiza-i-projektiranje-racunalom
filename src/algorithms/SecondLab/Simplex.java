package algorithms.SecondLab;


import functions.SecondLab.AbstractFunction;
import matrix.Matrix;
import matrix.MatrixOperations;


public class Simplex {
	
	// defining the constants
	static public double SIMPLEX_STEP = 1;
	private double EP = 1e-6;
	private double ALFA = 1;
	private double BETA = 0.5;
	private double GAMMA = 2;
	private double SIGMA = 0.5;
	private Point resultPoint;
	
	// constructor: creating a simplex algorithm 
	public Simplex(AbstractFunction function, Point point) throws Exception {
		 if(function != null && point != null) {
			 initSimplex(function, point);
		 }else {
			 throw new NullPointerException();
		 }
	}
	
	// method that calculate the result point using the starting point 
	private void initSimplex(AbstractFunction function, Point point) throws Exception {
		
		Point[] simplexPoints = new Point[point.getNumberOfColumns() + 1]; // defining the simplex points array 
		
		simplexPoints[0] = point; // inserting the starting point
		
		// inserting all the other points
		for(int i = 0; i<point.getNumberOfColumns(); ++i) {
			
			Point helpPoint = new Point(point);
			helpPoint.setValueAt(0, i, helpPoint.getValueAt(0, i) + SIMPLEX_STEP);
			simplexPoints[i + 1] = helpPoint;
		}
		
//		for(int i = 0;i<simplexPoints.length; ++i) {
//			System.out.println(simplexPoints[i]);
//		}
		
		
		int loopCounter = 0; // initializing the loop counter
		
		do {
			
			// calculating the faction values of the simplex points
			double[] FxSimplexValues = new double[simplexPoints.length];
			
			for(int i=0; i<simplexPoints.length; ++i) {
				FxSimplexValues[i] = function.getFunctionValue(simplexPoints[i]);
			}
			
			
//			System.out.println(Arrays.toString(simplexPoints));
//			System.out.println(Arrays.toString(FxSimplexValues));
			
			// finding the Max and Min indexes
			double MaxFxvalue = FxSimplexValues[0];
			double MinFxvalue = FxSimplexValues[0];
			
			int l = 0;
			int h = 0;
			
			for(int i=0; i<FxSimplexValues.length; ++i) {
				if(FxSimplexValues[i] - MaxFxvalue > EP) {
					h = i;
					MaxFxvalue = FxSimplexValues[i];
				}
				if(MinFxvalue - FxSimplexValues[i] > EP) {
					l = i;
					MinFxvalue = FxSimplexValues[i];
				}
			}
			
			Point x_C = calculateCentroid(simplexPoints, h); 
//			System.out.println("x_C = " + x_C);
//			System.out.println(x_C + " " + simplexPoints[h]);
			
			Point x_R = reflection(x_C, simplexPoints[h]);
//			System.out.println("x_R = " + x_R);
			
			double FxC = function.getFunctionValue(x_C);
			double FxR = function.getFunctionValue(x_R);
//			System.out.println("F(x_C) = " + FxC + ", F(x_R) = " + FxR);
//			System.out.println("MIN " + MinFxvalue + " MAX " + MaxFxvalue);
			
			
			// simplex algorithm
			if(FxR < MinFxvalue) {
				
				Point x_E = expansion(x_C, x_R);
				double FxE = function.getFunctionValue(x_E);
				
				if(FxE < MinFxvalue) {
					simplexPoints[h] = x_E;
					FxSimplexValues[h] = FxE;
				} else {
					simplexPoints[h] = x_R;
					FxSimplexValues[h] = FxR;
				}
				
			} else {
				
				boolean AllAreLess = true;
				for(int j=0; j<FxSimplexValues.length; ++j) {
					if(FxR <= FxSimplexValues[j] && j!=h) {
						AllAreLess = false;
					}
				}
				
				if(AllAreLess) {
					
					if(FxR < MaxFxvalue) {
						
						simplexPoints[h] = x_R;
						FxSimplexValues[h] = FxR;
					}
					
					Point x_K = contraction(x_C, simplexPoints[h]);
					double FxK = function.getFunctionValue(x_K);
						
//					System.out.println("x_K = " + x_K);
//					System.out.println("F(x_K) = " + FxK);
					
					if(FxK < function.getFunctionValue(simplexPoints[h])) {
						
						simplexPoints[h] = x_K;
						FxSimplexValues[h] = FxK;
					}else {
						
						simplexPoints = movePoints(simplexPoints, simplexPoints[l]);
					}
					
				}else {
					simplexPoints[h] = x_R;
					FxSimplexValues[h] = FxR;
					
				}
			}
			
			// calculating the difference between F(x_C) and all the other values
			double sum = 0;
			
			for(int i=0; i<FxSimplexValues.length; ++i) {
				double a = FxC - FxSimplexValues[i]; 
				sum += (a * a);
			}
			
			double result = sum / FxSimplexValues.length;
//			System.out.println(out + " result = " + Math.sqrt(result));
			
			// if they are the define the result point
			if(Math.sqrt(result) < EP) {
				resultPoint = x_C;
				break;
			}
			++loopCounter;
			
			// if is an infinite loop throw an exception 
			if(loopCounter  == 100000) {
				throw new Exception("Impossible to calculate Simplex");
			}
		}while(true);
		
	}
	
	// method that calculate the centroid using the simplex points and the h index 
	private Point calculateCentroid(Point[] simplexPoint, int h) throws Exception {
		double[] values = new double[simplexPoint.length - 1];
		
		for(int i = 0; i<simplexPoint.length; ++i) {
			if(i != h) {
				for(int j = 0; j<simplexPoint.length - 1; ++j) {
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
	
	// method that calculate the reflection using the centroid and the h index point 
	private Point reflection(Point x_C, Point x_H) throws Exception {
		Matrix Ax_C = MatrixOperations.scalar_Multiplication(x_C, 1 + ALFA);
		Matrix Ax_H = MatrixOperations.scalar_Multiplication(x_H, ALFA);
		return new Point(MatrixOperations.matrix_Subtraction(Ax_C, Ax_H));
	}
	
	// method that calculate the expansion using the centroid and reflection point
	private Point expansion(Point x_C, Point x_R) throws Exception {
		Matrix Gx_C = MatrixOperations.scalar_Multiplication(x_C, 1 - GAMMA);
		Matrix Gx_R = MatrixOperations.scalar_Multiplication(x_R, GAMMA);
		return new Point(MatrixOperations.matrix_Addition(Gx_C, Gx_R));
	}
	
	// method that calculate the contraction using the centroid and the h index point 
	private Point contraction(Point x_C, Point x_H) throws Exception {
		Matrix Bx_C = MatrixOperations.scalar_Multiplication(x_C, 1 - BETA);
		Matrix Bx_H = MatrixOperations.scalar_Multiplication(x_H, BETA);
		return new Point(MatrixOperations.matrix_Addition(Bx_C, Bx_H));
	}
	
	// method that returns the simplex point moved using the l index point 
	private Point[] movePoints(Point[] simplexPoints, Point x_L) throws Exception {
		Point[] moved = new Point[simplexPoints.length];
		
		for(int i = 0; i<simplexPoints.length; ++i) {
			Matrix helpMatrix = MatrixOperations.matrix_Addition(simplexPoints[i], x_L);
			helpMatrix = MatrixOperations.scalar_Multiplication(helpMatrix, SIGMA);
			
			moved[i] = new Point(helpMatrix);
		}
		
		return moved;
	}
	
	// method that returns the result point
	public Point getResultPoint() {
		return resultPoint;
	}
	
}
