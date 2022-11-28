package algorithms.FifthLab;

import matrix.Matrix;
import matrix.MatrixOperations;

public class PredictorCorrectorEulerTrapezoid {
	
public PredictorCorrectorEulerTrapezoid(Matrix Xk, Matrix A, Matrix B, boolean time, double t0, double periodT, double tMax, AbstractFunction function, int print, int iteration) throws Exception {
		
		double difference = 0;
		
		Matrix X = new Matrix(Xk);
		int i = 0;
		for(double t = t0; t < tMax; t += periodT) {
			
			X = getNextX(X, A, B, time, periodT, t, iteration);
			
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

	public Matrix getNextX(Matrix Xk, Matrix A, Matrix B, boolean time, double periodT, double t, int iteration) throws Exception {
		
		Matrix X = new Matrix(Xk);

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
		
		for(int i = 0; i<iteration; ++i) {
			Matrix AX = MatrixOperations.matrix_Multiplication(A, X);
			Matrix Bt_2 = MatrixOperations.matrix_Multiplication(B, tMat);
			Matrix AX_Bt = MatrixOperations.matrix_Addition(AX, Bt_2);
			
			Matrix AXk_2 = MatrixOperations.matrix_Multiplication(A, Xk);
			//Matrix Bt = MatrixOperations.matrix_Multiplication(B, t);
			Matrix AXk_Bt_2 = MatrixOperations.matrix_Addition(AXk_2, Bt);
			
			Matrix help = MatrixOperations.matrix_Addition(AXk_Bt_2, AX_Bt);
			
			Matrix helpT = MatrixOperations.scalar_Multiplication(help, periodT/2);
			
			X = MatrixOperations.matrix_Addition(Xk, helpT);
		
		}
		
		
		return X;
		
	}
}
