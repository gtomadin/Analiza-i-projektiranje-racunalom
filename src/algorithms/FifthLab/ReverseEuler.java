package algorithms.FifthLab;

import matrix.Matrix;
import matrix.MatrixOperations;

public class ReverseEuler {

	public ReverseEuler(Matrix Xk, Matrix A, Matrix B, boolean time, double t0, double periodT, double tMax, AbstractFunction function, int print) throws Exception {
		
		double difference = 0;
		
		Matrix X = new Matrix(Xk);
		int i = 0;
		for(double t = t0; t < tMax; t += periodT) {
			
			//X = getNextX(X, A, B, time, periodT, t);
			
			Matrix tM = new Matrix(B.getNumberOfRows(), 1);
			
			for(int j = 0; j<tM.getNumberOfRows(); ++j) {
				if(time) {
					tM.setValueAt(j, 0, t + periodT);
				}else {
					tM.setValueAt(j, 0, 1);
				}
			}
			
			Matrix U = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
		
			for(int j = 0; j < U.getNumberOfRows(); ++j) {
				U.setValueAt(j, j, 1);
			}
			
			Matrix AT = MatrixOperations.scalar_Multiplication(A, periodT);
			Matrix U_AT = MatrixOperations.matrix_Subtraction(U, AT);
			
			Matrix invU_AT = MatrixOperations.inverse(U_AT);
			//System.out.println(invU_AT);
			
			Matrix TB = MatrixOperations.scalar_Multiplication(B, periodT);
			Matrix TBt = MatrixOperations.matrix_Multiplication(TB, tM);
			Matrix Xk_TBt = MatrixOperations.matrix_Addition(X, TBt);
			//System.out.println(Xk_TBt);
			
			Matrix result = MatrixOperations.matrix_Multiplication(invU_AT, Xk_TBt);
			
			X = new Matrix(result);
			
			if(function != null) {
				for(int k = 0; k<X.getNumberOfRows(); ++k) {
					difference += Math.abs(X.getValueAt(k, 0) - function.getValue(Xk, k, t));
				}
			}
	
			++i;
			
			if(i == print) {
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
