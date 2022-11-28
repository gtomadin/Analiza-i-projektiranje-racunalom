package algorithms.SecondLab;


import functions.SecondLab.AbstractFunction;
import matrix.Matrix;

public class OptimizationAlgorithms {
		
	
	public static class Interval{ // class that defines a interval
		
		private double left; // defining a left bound 
		private double right; // defining a right bound
		
		// constructor: creating an interval object using the left and right bound  
		public Interval(double left, double right) {
			this.left = left;
			this.right = right;
		}
		
		// method that returns the left bound
		public double getLeft() {
			return left;
		}
		
		// method that returns the right bound
		public double getRight() {
			return right;
		}
		
		@Override
		public String toString() {
			return "[ " + getLeft() + ", " + getRight() + " ]"; 
		}
	}
	
	// method that returns the interval that is calculated using the unimodal algorithm with the starting point, the starting step and the objective function 
	public static Interval unimodalInterval(double point, double h, AbstractFunction function) throws Exception {
		
		double left = point - h; // initializing the beginning left bound
		double right = point + h; // initializing the beginning right bound
		double middle = point; // initializing the beginning middle
		
		double functionLeft, functionMiddle, functionRight; // defining the function values
		int step = 1; // initializing the step value
		
		functionMiddle = function.getFunctionValue(new Point(middle)); //calculating the function value of the middle 
		functionLeft = function.getFunctionValue(new Point(left)); //calculating the function value of the left bound 
		functionRight = function.getFunctionValue(new Point(right)); //calculating the function value of the right bound
		
//		System.out.println(left + " " + middle + " " + right);
//		System.out.println(functionLeft + " " + functionMiddle + " " + functionRight);
		
		
		// unimodel algorithm
		if(functionMiddle < functionRight && functionMiddle < functionLeft) {
			
			return new Interval(left, right);
		
		}else if(functionMiddle > functionRight){
			
			do {
				left = middle;
				middle = right;
				functionMiddle = functionRight;
				step *= 2;
				right = point + h * step;
				functionRight = function.getFunctionValue(new Point(right));
			}while(functionMiddle > functionRight);
			
		}else {
			
			do {
				right = middle;
				middle = left;
				functionMiddle = functionLeft;
				step *= 2;
				left = point - h * step;
				functionLeft = function.getFunctionValue(new Point(left));
			}while(functionMiddle > functionLeft);
		}
		
		
		Interval interval = new Interval(left, right);
		
		return interval;
		
	}
	
	static double k = 0.5 * (Math.sqrt(5) - 1); // 
	static double E = 1e-6;
	
	// method that returns the value of the golden radio algorithm using two points and the objective function
	public static double goldenRatio(double a, double b, AbstractFunction function) throws Exception {
		
		double c = b - k * (b - a); // initializing the beginning c point
		double d = a + k * (b - a); // initializing the beginning d point
		
		double functionC = function.getFunctionValue(new Point(c));
		double functionD = function.getFunctionValue(new Point(d));
		
		while((b - a) > E) {
//			System.out.println("a = " + a
//					+ " c = " + c
//					+ " d = " + d
//					+ " b = " + b);
//	
//			System.out.println("f(a) = " + function.getFunctionValue(new Point(a))
//					+ " f(b) = " + function.getFunctionValue(new Point(b))
//					+ " f(c) = " + function.getFunctionValue(new Point(c))
//					+ " f(d) = " + function.getFunctionValue(new Point(d)));
			
			
			
			if(functionC < functionD) {
				b = d;
				d = c;
				c = b - k * (b - a);
				functionD = functionC;
				functionC = function.getFunctionValue(new Point(c));
			}else {
				a = c;
				c = d;
				d = a + k * (b - a);;
				functionC = functionD;
				functionD = function.getFunctionValue(new Point(d));
			}
			
			
		}
		
//		System.out.println("a = " + a + ", b = " + b);
		return (a+b)/2;
	}
	
	static double deltaX = 1;
	static double EPS = 1e-6;
	
	// method that returns the result point calculated using the coordinate search algorithm with the starting point and the objective function
	public static Point coordinateSearch(AbstractFunction function, Point point) throws Exception {
		Point x = new Point(point); // initializing the x point
		
		Matrix E = new Matrix(point.getNumberOfColumns(), point.getNumberOfColumns()); // defining the E matrix
		
		for(int i = 0; i< E.getNumberOfColumns();++i) {
			E.setValueAt(i, i, 1);
		}
		
		boolean exitFlag = true; // initializing the exit flag
		int loopCounter = 0; // initializing the loop counter
		do {
			
			Point xS = new Point(x); // initializing the xS point
			
			for(int i = 0; i < point.getNumberOfColumns(); ++i) {
				Point Ei = new Point(E.getRow(i)); // getting the i row of the E matrix
				
				// defining the inner function
				AbstractFunction innerfunction = new AbstractFunction() {
					
					@Override
					protected double CalculateFunctionValue(Point innerpoint) throws Exception {
						double[] values = new double[x.getNumberOfColumns()];
						for(int j = 0; j<x.getNumberOfColumns(); ++j) {
							values[j] = Ei.getValueAt(0, j) * innerpoint.getValueAt(0, 0) + x.getValueAt(0, j);
						}
						return function.getFunctionValue(new Point(values));
					}
				};
				
				Interval interval = unimodalInterval(x.getValueAt(0, i), 1, innerfunction); // calculating the interval using the unimodal algorithm 
				double lambda = goldenRatio(interval.getLeft(), interval.getRight(), innerfunction); // calculating the lambda value using the golden radio algorithm
				
				x.setValueAt(0, i, x.getValueAt(0, i) + lambda); // setting the new value in the x point
			}
			
//			System.out.println("x: " + x);
//			System.out.println("xs: " + xS);
			
			// calculating the difference between xs and x
			double sum = 0;
			for(int i = 0; i<x.getNumberOfColumns(); ++i) {
				sum += Math.abs(xS.getValueAt(0, i) - x.getValueAt(0, i));
			}
			
			// if x and xs are the same end the loop
			if(sum < EPS) {
				exitFlag = false;
			}
			
			++loopCounter;
			
			// if is an infinite loop throw an exception 
			if(loopCounter  == 1000000) {
				throw new Exception("Impossible to calculate Coordinate Search");
			}
			
		}while(exitFlag);
		
		
		return  x;
	}
//	
//	private static Point HookeJeeves(AbstractFunction function, Point point) throws Exception {
//		
////		Point x_0 = new Point(point);
//		Point x_B = new Point(point);
//		Point x_P = new Point(point);
//		
////		MatrixOperations.copyValues(point, x_0);
////		MatrixOperations.copyValues(point, x_B);
////		MatrixOperations.copyValues(point, x_P);
//		
//		double dx = deltaX;
//		
//		do {
//		
//			Point x_N = find(x_P, dx, function);
//
//			System.out.println("xB = " + x_B + " xP = " + x_P + " xN = " + x_N);
//			
//			if(function.getFunctionValue(x_N) < function.getFunctionValue(x_B)) {
//				Point x_N_2 = new Point(MatrixOperations.scalar_Multiplication(x_N, 2));
//				x_P = new Point(MatrixOperations.matrix_Subtraction(x_N_2, x_B));
//				x_B = x_N; 
//			}else {
//				dx = dx / 2;
//				x_P = x_B;
//			}
//			
//		}while(dx> E);
//		
//		
//		return x_B;
//	}
//
//
//	private static Point find(Point x_P, double dx, AbstractFunction function) throws Exception {
//		Point x = new Point(x_P);
////		MatrixOperations.copyValues(x_P, x);
//		
//		for(int i = 0; i<x.getNumberOfColumns(); ++i) {
//			
//			double P = function.getFunctionValue(x);
//			
//			x.setValueAt(0, i, x.getValueAt(0, i) + dx);
//			
//			double N = function.getFunctionValue(x);
//			
//			if(N > P) {
//				x.setValueAt(0, i, x.getValueAt(0, i) - 2 * dx);
//				N = function.getFunctionValue(x);
//				if(N > P) {
//					x.setValueAt(0, i, x.getValueAt(0, i) + dx);
//				}
//			}
//		}
//		return x;
//	}
//	
//	private static double simplex_step = 2;
//	static double EP = 10e-6;
//	static double ALFA = 1;
//	static double BETA = 0.5;
//	static double GAMMA = 2;
//	static double SIGMA = 0.5;
//	
//	private static Point Simplex(AbstractFunction function, Point point) throws Exception {
//		
//		List<Point> simplexPoints = new ArrayList<>();
//		simplexPoints.add(point);
//		
//		for(int i = 0; i<point.getNumberOfColumns(); ++i) {
//			Point help = new Point(point);
////			MatrixOperations.copyValues(point, help);
//			help.setValueAt(0, i, help.getValueAt(0, i) + simplex_step);
//			simplexPoints.add(help);
//			
//		}
//		
////		for(int i=0; i<simplexPoints.size(); ++i) {
////			System.out.println(simplexPoints.get(i));
////		}
//		simplexPoints.clear();
//		double[] a = {0,0};
//		double[] b = {2,0};
//		double[] c = {1,2};
//		simplexPoints.add(new Point(a));
//		simplexPoints.add(new Point(b));
//		simplexPoints.add(new Point(c));
//		
//		while(true) {
//			
//			List<Double> fxvalues = new ArrayList<Double>();
//			
//			for(int i=0; i<simplexPoints.size(); ++i) {
//				double fx_i = function.getFunctionValue(simplexPoints.get(i));
//				fxvalues.add(fx_i);
//			}
//			System.out.println(Arrays.toString(simplexPoints.toArray()));
//
//			System.out.println(Arrays.toString(fxvalues.toArray()));
//			double MaxFXvalue = fxvalues.get(0); 
//			double MinFXvalue = fxvalues.get(0);
//			
//			int l = 0;
//			int h = 0;
//			
//			for(int i=0; i<fxvalues.size(); ++i) {
//				if(fxvalues.get(i) - MaxFXvalue > EP) {
//					h = i;
//					MaxFXvalue = fxvalues.get(i);
//				}
//				if(MinFXvalue - fxvalues.get(i) > EP) {
//					l = i;
//					MinFXvalue = fxvalues.get(i);
//				}
//			}
////			System.out.println(MaxFXvalue + " " + h);
////			System.out.println(MinFXvalue + " " + l);
////			
//			Point x_C = calculateCentroid(simplexPoints, h);
//			System.out.println("x_C = " + x_C);
//			System.out.println(x_C + " " + simplexPoints.get(h));
//			
//			Point x_R = reflection(x_C, simplexPoints.get(h));
//			System.out.println("x_R = " + x_R);
//			
//			double FxC = function.getFunctionValue(x_C);
//			double FxR = function.getFunctionValue(x_R);
//			System.out.println("F(x_C) = " + FxC + ", F(x_R) = " + FxR);
//			
//			if(FxR < MinFXvalue) {
//				
//				Point x_E = expansion(x_C, x_R);
//				double FxE = function.getFunctionValue(x_E);
//				
//				System.out.println("x_E = " + x_E);
//				System.out.println("F(x_E) = " + FxE);
//				if(FxE < MinFXvalue) {
//					simplexPoints.add(h, x_E);
//				}else {
//					simplexPoints.add(h, x_R);
//				}
//				
//			}else {
//				boolean AreAllLess = true;
//				for(int j=0; j<fxvalues.size(); ++j) {
//					if(!(FxR > fxvalues.get(j) && j!=h)) {
//						AreAllLess = false;
//					}
//				}
//				if(AreAllLess) {
//					
//					if(FxR < fxvalues.get(h)) {
//						simplexPoints.add(h, x_R);
//					}
//					Point x_K = contraction(x_C, simplexPoints.get(h));
//					double FxK = function.getFunctionValue(x_K);
//						
//					System.out.println("x_K = " + x_K);
//					System.out.println("F(x_K) = " + FxK);
//						
//						
//					System.exit(1);
//					if(FxK < fxvalues.get(h)) {
//						simplexPoints.add(h, x_K);
//					}else {
//						simplexPoints = movePoints(simplexPoints, simplexPoints.get(l));
//					}
//					
//				}else {
//					simplexPoints.add(h, x_R);
//				}
//				
//			}
//			
//			int length = fxvalues.size();
//			double stopValue = 0;
//			double centroidValue = function.getFunctionValue(x_C);
//			for(int i = 0; i<length; ++i) {
//				stopValue += Math.pow(centroidValue - fxvalues.get(i), 2);
//			}
//			if(Math.sqrt(stopValue/length)< EP) {
//				return x_C;
//			}
//		}
//		
//	}
//
//	private static Point calculateCentroid(List<Point> simplexPoints, int h) throws Exception {
//		double[] values = new double[simplexPoints.size() - 1];
//		for(int i=0; i<simplexPoints.size(); ++i) {
//			if(i != h) {
//				for(int j = 0; j<simplexPoints.size() - 1; ++j) {
//					values[j] += simplexPoints.get(i).getValueAt(0, j);
//				}
//			}
//		}
//		
//		Point x_C = new Point(values);
//		
//		double skalar = simplexPoints.size() - 1;
//		skalar = 1/skalar;
//		
//		System.out.println(x_C.toString() + " * "  + skalar);
//		x_C = new Point(MatrixOperations.scalar_Multiplication(x_C, skalar));
//		return x_C;
//	}
//	
//	private static Point reflection(Point x_C, Point x_H) throws Exception {
//		Matrix Ax_C = MatrixOperations.scalar_Multiplication(x_C, 1+ALFA);
//		Matrix Ax_H = MatrixOperations.scalar_Multiplication(x_H, ALFA);
//		return new Point(MatrixOperations.matrix_Subtraction(Ax_C, Ax_H));
//	}
//	
//	private static Point expansion(Point x_C, Point x_R) throws Exception {
//		Matrix Gx_C = MatrixOperations.scalar_Multiplication(x_C, 1-GAMMA);
//		Matrix Gx_R = MatrixOperations.scalar_Multiplication(x_R, GAMMA);
//		return new Point(MatrixOperations.matrix_Addition(Gx_C, Gx_R));
//	}
//	
//	private static Point contraction(Point x_C, Point x_H) throws Exception {
//		Matrix Kx_C = MatrixOperations.scalar_Multiplication(x_C, 1-BETA);
//		Matrix Kx_H = MatrixOperations.scalar_Multiplication(x_H, BETA);
//		return new Point(MatrixOperations.matrix_Addition(Kx_C, Kx_H));
//	}
//
//
//	private static List<Point> movePoints(List<Point> simplexPoints, Point matrix) throws Exception {
//		List<Point> moved = new ArrayList<>();
//		for(Point point : simplexPoints) {
//			moved.add(new Point(MatrixOperations.scalar_Multiplication(point, SIGMA)));
//		}
//		return moved;
//	}
//
	
	
}
