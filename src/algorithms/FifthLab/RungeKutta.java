package algorithms.FifthLab;

import matrix.Matrix;
import matrix.MatrixOperations;

public class RungeKutta {
	
	public RungeKutta(Matrix Xk, Matrix A, Matrix B, boolean time, double t0, double periodT, double tMax, AbstractFunction function, int print) throws Exception {
		
		double difference = 0;
		
		Matrix X = new Matrix(Xk);
		int i = 0;
		for(double t = t0; t < tMax; t += periodT) {
			
			//X = getNextX(X, A, B, time, periodT, t);
			
			//Matrix m1 = euler.getNextX(Xk, A, B, time, periodT, currentT);
			Matrix m1 = getEuler(X, A, B, time, periodT, t);
			Matrix m1T = MatrixOperations.scalar_Multiplication(m1, periodT/2);
			Matrix Xkm1T = MatrixOperations.matrix_Addition(X, m1T);
			
			//Matrix m2 = euler.getNextX(Xkm1T, A, B, time, periodT, currentT + periodT/2);
			Matrix m2 = getEuler(Xkm1T, A, B, time, periodT, t + periodT/2);
			Matrix m2T = MatrixOperations.scalar_Multiplication(m2, periodT/2);
			Matrix Xkm2T = MatrixOperations.matrix_Addition(X, m2T);
			
			//Matrix m3 = euler.getNextX(Xkm2T, A, B, time, periodT, currentT + periodT/2);
			Matrix m3 = getEuler(Xkm2T, A, B, time, periodT, t + periodT/2);
			Matrix m3T = MatrixOperations.scalar_Multiplication(m3, periodT/2);
			Matrix Xkm3T = MatrixOperations.matrix_Addition(X, m3T);
			
			//Matrix m4 = euler.getNextX(Xkm3T, A, B, time, periodT, currentT + periodT/2);
			Matrix m4 = getEuler(Xkm3T, A, B, time, periodT, t + periodT/2);
			
			
//			System.out.println(m1);
//			System.out.println(m2);
//			System.out.println(m3);
//			System.out.println(m4);
			
			Matrix m22 = MatrixOperations.scalar_Multiplication(m2, 2);
			Matrix m32 = MatrixOperations.scalar_Multiplication(m3, 2);
			
			Matrix m1m22 = MatrixOperations.matrix_Addition(m1, m22);
			Matrix m1m22m32 = MatrixOperations.matrix_Addition(m1m22, m32);
			Matrix m1m22m32m4 = MatrixOperations.matrix_Addition(m1m22m32, m4);
			
			Matrix tm = MatrixOperations.scalar_Multiplication(m1m22m32m4, periodT/6);
			
//			System.out.println(tm);
//			System.exit(1);
			Matrix result = MatrixOperations.matrix_Addition(X, tm);
			
			X = new Matrix(result);
			
			if(function != null) {
				for(int k = 0; k<X.getNumberOfRows(); ++k) {
					difference += Math.abs(X.getValueAt(k, 0) - function.getValue(Xk, k, t));
				}
			}
			
			++i;
			
			if(i == print) {
				//System.out.println(t);
				Matrix help = MatrixOperations.matrix_Transpose(X);
				System.out.println("[" + t + " , " + help.toString() + "]");
				i=0;
			}
			
		}
		
		if(function != null) {
			System.out.println(difference);
		}
	}

	private Matrix getEuler(Matrix Xk, Matrix A, Matrix B, boolean time, double periodT, double currentT) throws Exception {

		Matrix t = new Matrix(B.getNumberOfRows(), 1);
		
		for(int i = 0; i<t.getNumberOfRows(); ++i) {
			if(time) {
				t.setValueAt(i, 0, currentT);
			}else {
				t.setValueAt(i, 0, 1);
			}
		}
		
		Matrix AXk = MatrixOperations.matrix_Multiplication(A, Xk);
		Matrix Bt = MatrixOperations.matrix_Multiplication(B, t);
		
		Matrix AXk_Bt  = MatrixOperations.matrix_Addition(AXk, Bt);
		
		return AXk_Bt;
	}
}
