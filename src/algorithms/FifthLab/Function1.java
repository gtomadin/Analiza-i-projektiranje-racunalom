package algorithms.FifthLab;

import matrix.Matrix;

public class Function1 extends AbstractFunction {

	@Override
	public double getValue(Matrix Xk, int index, double t) throws Exception {
		
		double X1 = Xk.getValueAt(0, 0);
		double X2 = Xk.getValueAt(1, 0);
		
		if(index==0) {	
			return (X1 * Math.cos(t) + X2 * Math.sin(t));
		}else {
			return (X2 * Math.cos(t) - X1 * Math.sin(t));
		}
	}

	

}
