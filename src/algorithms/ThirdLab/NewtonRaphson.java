package algorithms.ThirdLab;

import functions.ThirdLab.AbstractFunction;
import gradient.ThirdLab.AbstractGradient;
import hessianmatrix.ThirdLab.AbstractHessianMatrix;
import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixOperations;

public class NewtonRaphson {
	
	// defining the constants
	private double E = 1e-6;
	private Point resultPoint;
	
	// constructor: creating a Newton Raphson algorithm 
	public NewtonRaphson(AbstractFunction function, AbstractGradient gradient, AbstractHessianMatrix hessian, Point point, boolean usingGoldenRatio) throws Exception {
	
		if(function != null && gradient != null && hessian != null && point != null) {
			if(usingGoldenRatio) {
				NewtonRaphsonWithGoldenRatio(function, gradient, hessian, point);
			}else {
				NewtonRaphsonWithoutGoldenRatio(function, gradient, hessian, point);
			}
		}
	}
	// method that calculate the result point using the starting point without using the golden ratio
	private void NewtonRaphsonWithoutGoldenRatio(AbstractFunction function, AbstractGradient gradient, AbstractHessianMatrix hessian, Point point) throws Exception {
		
		Point x = new Point(point);
		
		int loopCounter = 0; // initializing the loop counter
		
		do {
			
//			System.out.println("x: " + x);
			
			Point gradientPoint = gradient.getGradientValue(x); // calculating the gradient value of the given function using the point
//			System.out.println("gradient point: " + gradientPoint);
			Matrix hessianMatrix = hessian.getHessianMatrixValue(x); // calculating the hessian matrix value of the given function using the point
//			System.out.println("hessian: " + hessianMatrix);
			
			// calculating the deltaX point
			Matrix transposeGradientpoint = MatrixOperations.matrix_Transpose(gradientPoint);
			transposeGradientpoint = MatrixOperations.scalar_Multiplication(transposeGradientpoint, -1);
			
			Matrix MatrixDeltaX = EquationSolver.solveEquationUsingLUP(hessianMatrix, transposeGradientpoint);
			
			Point deltaX = new Point(MatrixOperations.matrix_Transpose(MatrixDeltaX)); 
			
			
			x = new Point(MatrixOperations.matrix_Subtraction(x, deltaX)); // getting new point
			
			// if the norm of the gradient point is less then EPSILON return the result 
			if(deltaX.getNorm() < E) {
				resultPoint = x;
				break;
			}
			
			++loopCounter;
			
			// if is an infinite loop throw an exception 
			if(loopCounter == 100000) {
				resultPoint = x;
				break;
//				throw new Exception("Impossible to calculate Gradient Descent");
			}
			
			
		}while(true);
		
	}
	
	// method that calculate the result point using the starting point with using the golden ratio
	private void NewtonRaphsonWithGoldenRatio(AbstractFunction function, AbstractGradient gradient, AbstractHessianMatrix hessian, Point point) throws Exception {
		
		Point x = new Point(point);
		
		int loopCounter = 0; // initializing the loop counter
		
		do {
//			System.out.println("x: " + x);
			Point gradientPoint = gradient.getGradientValue(x); // calculating the gradient value of the given function using the point
//			System.out.println("gradient point: " + gradientPoint);
			Matrix hessianMatrix = hessian.getHessianMatrixValue(x); // calculating the hessian matrix value of the given function using the point
//			System.out.println("hessian: " + hessianMatrix);
			
			// calculating the deltaX point
			Matrix transposeGradientpoint = MatrixOperations.matrix_Transpose(gradientPoint);
			
			Matrix MatrixDeltaX = EquationSolver.solveEquationUsingLUP(hessianMatrix, transposeGradientpoint);
			
			Point deltaX = new Point(MatrixOperations.matrix_Transpose(MatrixDeltaX)); 
			
//			System.out.println("Deltax: " + deltaX);
			
	
			double lambda = 0;
			
			Point xhelp = new Point(x);
			
			// defining the inner function
			AbstractFunction innerFunction = new AbstractFunction() {

				@Override
				protected double CalculateFunctionValue(Point point) throws Exception {
					double value = point.getValueAt(0, 0);
					Point help = new Point(MatrixOperations.scalar_Multiplication(deltaX, value));
					Point pointhelp = new Point(MatrixOperations.matrix_Addition(xhelp, help));
					
					return function.getFunctionValue(pointhelp);
				}
			};
			
			Interval interval = unimodalInterval(0, 1, innerFunction); // calculating the interval using the unimodal algorithm 
//			System.out.println(lambda);
			lambda = goldenRatio(interval.getLeft(), interval.getRight(), innerFunction); // calculating the lambda value using the golden radio algorithm
			
			// x = x + lambda * deltaX
			Point newdeltaX = new Point(MatrixOperations.scalar_Multiplication(deltaX, lambda));
			
			x = new Point(MatrixOperations.matrix_Addition(x, newdeltaX));
			
//			System.out.println("new x: " + x);
			
			// if the norm of the gradient point is less then EPSILON return the result 
			if(deltaX.getNorm() < E) {
				resultPoint = x;
				break;
			}
			
			++loopCounter;
			
			// if is an infinite loop throw an exception 
			if(loopCounter == 1000000) {
				resultPoint = x;
				break;
//				throw new Exception("Impossible to calculate Gradient Descent");
			}
			
			
		}while(true);
		
	}
	
	// method that returns the result point
	public Point getResultPoint() {
		return resultPoint;
	}
	
	private class Interval{ // class that defines a interval
		
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
	private Interval unimodalInterval(double point, double h, AbstractFunction function) throws Exception {
		
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
	
	static double k = 0.5 * (Math.sqrt(5) - 1);
	
	// method that returns the value of the golden radio algorithm using two points and the objective function
	private double goldenRatio(double a, double b, AbstractFunction function) throws Exception {
		
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
}

	

