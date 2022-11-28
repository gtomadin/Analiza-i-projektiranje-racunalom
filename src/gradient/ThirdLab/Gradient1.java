package gradient.ThirdLab;

import algorithms.ThirdLab.Point;

public class Gradient1 extends AbstractGradient {

	@Override
	protected Point CalculateGradientValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_2 - x_1 * x_1;
		
		double[] values = new double[2];
		
		values[0] = -2 * (200 * a * x_1 + 1 - x_1);
		values[1] = 200 * a;		
		
		return new Point(values);
	}
	
	
	
}
