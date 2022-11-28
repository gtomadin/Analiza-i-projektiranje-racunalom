package functions.SecondLab;

import algorithms.SecondLab.Point;

public class Function4 extends AbstractFunction {
	
	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_1*x_1 - x_2*x_2;
		double b = x_1*x_1 + x_2*x_2;
		
		double f = Math.abs(a) + Math.sqrt(b);
		
		return f;
	}
}
