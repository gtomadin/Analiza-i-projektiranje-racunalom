package algorithms.SecondLab;

import functions.SecondLab.AbstractFunction;
import matrix.MatrixOperations;

public class HookeJeeves {
	
	// defining the constants
	private double deltaX = 0.5;
	private double E = 1e-6;
	private Point resultPoint;
	
	// constructor: creating a Hooke Jeeves algorithm 
	public HookeJeeves(AbstractFunction function, Point point) throws Exception {
		
		if(function != null && point != null) {
			initHookeJeeves(function, point);
		}
	}
	// method that calculate the result point using the starting point 
	private void initHookeJeeves(AbstractFunction function, Point point) throws Exception {

//		Point x_0 = new Point(point);
		Point x_B = new Point(point); // defining the x_B point
		Point x_P = new Point(point); // defining the x_P point
		
		double dx = deltaX; 
		
		// Hooke Jeeves algorithm
		do {
		
			Point x_N = find(x_P, dx, function);

//			System.out.println("xB = " + x_B + " xP = " + x_P + " xN = " + x_N);
			
			if(function.getFunctionValue(x_N) < function.getFunctionValue(x_B)) {
				// x_P = 2 * x_N - x_B
				Point x_N_2 = new Point(MatrixOperations.scalar_Multiplication(x_N, 2));
				x_P = new Point(MatrixOperations.matrix_Subtraction(x_N_2, x_B));
				x_B = x_N; 
			}else {
				dx = dx / 2;
				x_P = x_B;
			}
			
		}while(dx> E);
		
		
		resultPoint = x_B;
	}
	
	// method that calculate the x_N point using the x_P, the dx constant and the objective function 
	private Point find(Point x_P, double dx, AbstractFunction function) throws Exception {
		Point x = new Point(x_P);

		for(int i = 0; i<x.getNumberOfColumns(); ++i) {
			
			double P = function.getFunctionValue(x);
			
			x.setValueAt(0, i, x.getValueAt(0, i) + dx);
			
			double N = function.getFunctionValue(x);
			
			if(N > P) {
				x.setValueAt(0, i, x.getValueAt(0, i) - 2 * dx);
				N = function.getFunctionValue(x);
				if(N > P) {
					x.setValueAt(0, i, x.getValueAt(0, i) + dx);
				}
			}
		}
		return x;
	}
	
	// method that returns the result point
	public Point getResultPoint() {
		return resultPoint;
	}
	
		
}
