package algorithms.FifthLab;

import matrix.Matrix;
import matrix.MatrixOperations;

public class Trapezoid {
	
	
	public Trapezoid(Matrix Xk, Matrix A, Matrix B, boolean time, double t0, double periodT, double tMax, AbstractFunction function, int print) throws Exception {
		
		double difference = 0;
		
		Matrix X = new Matrix(Xk);
		int i = 0;
		for(double t = t0; t < tMax; t += periodT) {
			
			//X = getNextX(X, A, B, time, periodT, t);
			
			Matrix tM = new Matrix(B.getNumberOfRows(), 1);
			
			for(int j = 0; j<tM.getNumberOfRows(); ++j) {
				if(time) {
					tM.setValueAt(j, 0, t + periodT + t);
				}else {
					tM.setValueAt(j, 0, 1);
				}
			}
		
			Matrix U = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
			for(int j = 0; j < U.getNumberOfRows(); ++j) {
				U.setValueAt(j, j, 1);
			}
			
			Matrix AT = MatrixOperations.scalar_Multiplication(A, periodT/2);
			Matrix U_AT = MatrixOperations.matrix_Subtraction(U, AT);
			
			Matrix invU_AT = MatrixOperations.inverse(U_AT);
			
			Matrix U_AT_2 = MatrixOperations.matrix_Addition(U, AT);
			Matrix U_ATXk = MatrixOperations.matrix_Multiplication(U_AT_2, X);
			
			Matrix TB = MatrixOperations.scalar_Multiplication(B, periodT);
			Matrix TBt = MatrixOperations.matrix_Multiplication(TB, tM);
			
			Matrix U_ATXk_TBt = MatrixOperations.matrix_Addition(U_ATXk, TBt);
			
			Matrix result = MatrixOperations.matrix_Multiplication(invU_AT, U_ATXk_TBt);
			
			X = new Matrix(result);
			//System.out.println(U_ATXk);
			//System.out.println(TBt);
	        //System.exit(1);
			
			
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
}
