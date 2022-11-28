package hessianmatrix.ThirdLab;

import algorithms.ThirdLab.Point;
import matrix.Matrix;

public class HessianMatrix4 extends AbstractHessianMatrix {
	
	@Override
	protected Matrix CalculateHessianMatrixValue(Point point) throws Exception {
		double[][] values = new double[2][2];
		
		values[0][0] = 2;
		values[0][1] = 0;
		values[1][0] = 0;
		values[1][1] = 2;
		
		return new Matrix(values);
	}
}
