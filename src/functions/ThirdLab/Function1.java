package functions.ThirdLab;

import algorithms.ThirdLab.Point;

public class Function1 extends AbstractFunction {

	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_2 - x_1*x_1;
		double b = 1 - x_1;
		
		double f = 100*a*a + b*b; 
		
		return f;
	}
	
}
