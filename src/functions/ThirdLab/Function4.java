package functions.ThirdLab;

import algorithms.ThirdLab.Point;

public class Function4 extends AbstractFunction {
	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_1 - 3;
		double b = x_2;
		
		double f = a*a + b*b;
		
		return f;
	}

}
