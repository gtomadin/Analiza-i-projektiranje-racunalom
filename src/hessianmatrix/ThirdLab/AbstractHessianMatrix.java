package hessianmatrix.ThirdLab;

import algorithms.ThirdLab.Point;
import matrix.Matrix;

public class AbstractHessianMatrix {
	
	int HessianMatrixCallCounter;

	public AbstractHessianMatrix() {
		HessianMatrixCallCounter = 0;
	}

	public int getHessianMatrixCallCounter() {
		return HessianMatrixCallCounter;
	}

	public void setHessianMatrixCallCounter(int hessianMatrixCallCounter) {
		HessianMatrixCallCounter = hessianMatrixCallCounter;
	}
	
	public Matrix getHessianMatrixValue(Point point) throws Exception {
		HessianMatrixCallCounter++;
		
		return CalculateHessianMatrixValue(point);
	}
	
	protected Matrix CalculateHessianMatrixValue(Point point) throws Exception{
		throw new Exception("Method not overrided");
	}
	
}
