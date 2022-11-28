package functions.ThirdLab;

import algorithms.ThirdLab.Point;

public class Function2 extends AbstractFunction{

	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_1 - 4;
		double b = x_2 - 2;
		
		double f = a*a + 4*b*b;
		
		return f;
	}
	
}
