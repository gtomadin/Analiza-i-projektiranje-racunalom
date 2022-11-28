package functions.SecondLab;

import algorithms.SecondLab.Point;

public class Function6 extends AbstractFunction{
	
	@Override
	protected double CalculateFunctionValue(Point point) throws Exception {
		
		double sum = 0;
		
		for(int i=0; i<point.getNumberOfColumns(); ++i) {
			double x = point.getValueAt(0, i);
			
			sum += x*x;
		}
		
		double sqrtsum = Math.sqrt(sum);
		double sin = Math.sin(sqrtsum);
		
		double a = sin*sin - 0.5;
		double b = 1 + 0.001 * sum;
		
		double f = 0.5 + a/(b*b);
		
		
		return f;
	}
}
