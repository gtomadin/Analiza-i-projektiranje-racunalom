package gradient.ThirdLab;

import algorithms.ThirdLab.Point;

public class Gradient2 extends AbstractGradient {
	
	
	@Override
	protected Point CalculateGradientValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_1 - 4;
		double b = x_2 - 2;
		
		double[] values = new double[2];
		
		values[0] = 2 * a;
		values[1] = 8 * b;
		
		return new Point(values);
	}
}
