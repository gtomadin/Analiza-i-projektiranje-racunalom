package algorithms.FifthLab;

import matrix.Matrix;
import matrix.MatrixOperations;

public class Euler {

	public Euler(Matrix Xk, Matrix A, Matrix B, boolean time, double t0, double periodT, double tMax, AbstractFunction function, int print) throws Exception {
		
		double difference = 0;
		
		Matrix X = new Matrix(Xk);
		int i = 0;
		for(double t = t0; t < tMax; t += periodT) {
			
			//X = getNextX(X, A, B, time, periodT, t);
			
			Matrix tMat = new Matrix(B.getNumberOfRows(), 1);
			
			for(int j = 0; j<tMat.getNumberOfRows(); ++j) {
				if(time) {
					tMat.setValueAt(j, 0, t);
				}else {
					tMat.setValueAt(j, 0, 1);
				}
			}
			
			Matrix AXk = MatrixOperations.matrix_Multiplication(A, X);
			Matrix Bt = MatrixOperations.matrix_Multiplication(B, tMat);
			
			Matrix AXk_Bt  = MatrixOperations.matrix_Addition(AXk, Bt);
			
			Matrix T_AXK_Bt = MatrixOperations.scalar_Multiplication(AXk_Bt, periodT);
			
			Matrix result = MatrixOperations.matrix_Addition(X, T_AXK_Bt);
			
			X = new Matrix(result);
			
			
			if(function != null) {
				for(int k = 0; k<X.getNumberOfRows(); ++k) {
					difference += Math.abs(X.getValueAt(k, 0) - function.getValue(Xk, k, t));
				}
			}
			
			++i;
			
			if(i == print) {
				//System.out.println(t);
				//System.out.println(difference);
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
}
