package functions.SecondLab;

import algorithms.SecondLab.Point;

public class GoldenRatioTestFunction extends AbstractFunction{

	@Override
	public double getFunctionValue(Point point) throws Exception {
		double x = point.getValueAt(0, 0);
		double a = x+1;
		return a * a;
	}
}
