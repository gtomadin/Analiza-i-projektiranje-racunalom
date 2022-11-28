package algorithms.ThirdLab;


import functions.ThirdLab.AbstractFunction;
import gradient.ThirdLab.AbstractGradient;
import matrix.MatrixOperations;

public class GradientDescent {
	
	// defining the constants
	private double E = 1e-6;
	private Point resultPoint;
	
	// constructor: creating a gradient descent algorithm 
	public GradientDescent(AbstractFunction function, AbstractGradient gradient, Point point, boolean usingGoldenRatio) throws Exception {
		
		if(function != null && gradient != null && point != null) {
			if(usingGoldenRatio) {
				GradientDescentWithGoldenRatio(function, gradient, point);
			}else {
				GradientDescentWithoutGoldenRatio(function, gradient, point);
			}
		}
	}
	
	// method that calculate the result point using the starting point without using the golden ratio
	private void GradientDescentWithoutGoldenRatio(AbstractFunction function, AbstractGradient gradient, Point point) throws Exception {
		
		Point x = new Point(point);
		
		int loopCounter = 0; // initializing the loop counter
		
		do {
			Point gradientPoint = gradient.getGradientValue(x); // calculating the gradient value of the given function using the point
			
			gradientPoint.normalizePoint(); // gradient value is normalized
			
//			System.out.println(x + " - " + gradientPoint);
			x = new Point(MatrixOperations.matrix_Subtraction(x, gradientPoint)); // getting the new point
			
			// if the norm of the gradient point is less then EPSILON return the result 
			if(gradientPoint.getNorm() < E) { 
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
	private void GradientDescentWithGoldenRatio(AbstractFunction function, AbstractGradient gradient, Point point) throws Exception {
		
		Point x = new Point(point); 
		
		int loopCounter = 0; // initializing the loop counter
		
		do {
			
			Point gradientPoint = gradient.getGradientValue(x); // calculating the gradient value of the given function using the point
			
			gradientPoint.normalizePoint(); // gradient value is normalized
			
			double lambda = 0;
			
			Point xhelp = new Point(x);
			
			// defining the inner function
			AbstractFunction innerFunction = new AbstractFunction() {

				@Override
				protected double CalculateFunctionValue(Point point) throws Exception {
					double value = point.getValueAt(0, 0);
	
					Point help = new Point(MatrixOperations.scalar_Multiplication(gradientPoint, value));
					Point pointhelp = new Point(MatrixOperations.matrix_Addition(xhelp, help));
					
					// F(x + lambda * v)
					return function.getFunctionValue(pointhelp);
				}
				
				
			};
			
			Interval interval = unimodalInterval(0, 1, innerFunction); // calculating the interval using the unimodal algorithm 
//			System.out.println(lambda);
			lambda = goldenRatio(interval.getLeft(), interval.getRight(), innerFunction); // calculating the lambda value using the golden radio algorithm
			
			// F(X + lambda * V)
			Point lambda_v = new Point(MatrixOperations.scalar_Multiplication(gradientPoint, lambda));
			
			x = new Point(MatrixOperations.matrix_Addition(x, lambda_v));
			
			// if the norm of the gradient point is less then EPSILON return the result 
			if(gradientPoint.getNorm() < E) {
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
