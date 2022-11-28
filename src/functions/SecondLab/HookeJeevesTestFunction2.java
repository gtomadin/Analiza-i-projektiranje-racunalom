package functions.SecondLab;

import algorithms.SecondLab.Point;
	
public class HookeJeevesTestFunction2 extends AbstractFunction{
	
	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		double x_3 = point.getValueAt(0, 2);
		
		return x_1 * x_1 + x_2 * x_2 + x_3 * x_3;
	}
}
