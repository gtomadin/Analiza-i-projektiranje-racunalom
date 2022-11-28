package algorithms.FifthLab;

import matrix.Matrix;

public abstract class AbstractFunction {
	
	public abstract double getValue(Matrix Xk, int index, double t) throws Exception;
}
