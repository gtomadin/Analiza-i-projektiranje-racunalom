package functions.FourthLab;

import algorithms.FourthLab.Point;

public class Function7 extends AbstractFunction {

	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		
		
		double sum = 0;
		
		for(int i=0; i<point.getNumberOfColumns(); ++i) {
			double x = point.getValueAt(0, i);
			
			sum += x*x;
		}
		
		
		double sumpow_25 = Math.pow(sum, 0.25);
		
		double sumpow_01 = Math.pow(sum, 0.1);
		
		double sinsum = Math.sin(50 * sumpow_01);
		
		double a = 1 + sinsum * sinsum;
		
		double result = sumpow_25 * a;
		
		return result;
		
		
	}

}
