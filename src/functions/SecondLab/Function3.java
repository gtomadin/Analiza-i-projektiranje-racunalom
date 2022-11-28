package functions.SecondLab;

import algorithms.SecondLab.Point;

public class Function3 extends AbstractFunction{
	
	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		
		double sum = 0;
		
		for(int i=0; i<point.getNumberOfColumns(); ++i) {
			
			double x = point.getValueAt(0, i);
			
			double a = x-(i+1);
			
			sum += a*a;
			
		}
		
		return sum;
	}
}
