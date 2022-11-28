package hessianmatrix.ThirdLab;

import algorithms.ThirdLab.Point;
import matrix.Matrix;

public class HessianMatrix1 extends AbstractHessianMatrix{

	@Override
	protected Matrix CalculateHessianMatrixValue(Point point) throws Exception {
		double x_1 = point.getValueAt(0, 0);
		double x_2 = point.getValueAt(0, 1);
		
		double a = x_2 - 3 * x_1 * x_1;
		
		double[][] values = new double[2][2];
		
		values[0][0] = -400 * a + 2;
		values[0][1] = -400 * x_1;
		values[1][0] = -400 * x_1;
		values[1][1] = 200;
		
		return new Matrix(values);
	}
	
}
