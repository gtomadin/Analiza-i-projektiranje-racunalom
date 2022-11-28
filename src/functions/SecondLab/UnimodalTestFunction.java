package functions.SecondLab;

import algorithms.SecondLab.Point;

public class UnimodalTestFunction extends AbstractFunction{
	
	@Override
	public double getFunctionValue(Point point) throws Exception {
		double x = point.getValueAt(0, 0);
		
		
		return x * x - 2;
	}
	
}
