package functions.SecondLab;

import algorithms.SecondLab.Point;

public class SimplexTestFunction extends AbstractFunction{
	
	@Override
	public double getFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		return x_1 * x_1 + x_2 * x_2;
	
	}	
}
