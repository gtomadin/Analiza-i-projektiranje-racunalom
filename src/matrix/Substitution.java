package matrix;

public class Substitution {
	
	// method that returns a vector thats is a result of the forward substitution
	public static Matrix ForwardSubstitution(Matrix L, Matrix B) throws Exception {
		
		if(L.isMatrixSquare()) { 
			if(L.getNumberOfRows() == B.getNumberOfRows()) {
			
				Matrix Y = new Matrix(L.getNumberOfRows(), 1);
				
				//y[i] = b[i] - sum( L[i][j] * y[j] ) ; i = (0, n-1) ; j = (0, i-1)
				for(int i=0; i<L.getNumberOfRows(); ++i) {
					double b = B.getValueAt(i, 0);
					double sum = 0;
					String s = b + "";
					
					for(int j=0; j<i; ++j) {
						sum -= L.getValueAt(i, j)*Y.getValueAt(j, 0); 
						s = s + " - " + L.getValueAt(i, j) + " * " + Y.getValueAt(j, 0);  
					}
					Y.setValueAt(i, 0, b+sum);
	//				System.out.println(s + " == " + (b + sum));
				}
				return Y;
			}else {
				throw new Exception("The number of rows in the L and b matrix must be the same");
			}
		}else {
			throw new Exception("Matrix must be a square to calculate a forward substitution");
		}
	}
	
	// method that returns a vector thats is a result of the backward substitution
	public static Matrix BackwardSubstitution(Matrix U, Matrix Y) throws Exception {
		
		if(U.isMatrixSquare()) {
			if(U.getNumberOfRows() == Y.getNumberOfRows()) {
		
				Matrix X = new Matrix(U.getNumberOfRows(), 1);
				
				// x[i] = 1/U[i][i] * ( y[i] - sum( U[i][j] * x[j] )) ; i = (n-2, 0) ; j = (i+1, n-1) 
				for(int i = U.getNumberOfRows()-1; i >= 0; --i) {
					double x = Y.getValueAt(i, 0)/U.getValueAt(i, i);
					double sum = 0;
					String s = Y.getValueAt(i, 0) + " / " + U.getValueAt(i, i);
					
					for(int j = i + 1; j<U.getNumberOfRows(); ++j) {
						s = s + " - (" + U.getValueAt(i, j) + " * " + X.getValueAt(j, 0) + ") / " + U.getValueAt(i, i);
						sum -= U.getValueAt(i, j) * X.getValueAt(j, 0) / U.getValueAt(i, i);
					}
					//System.out.println(s + " == " + (x + sum));
					X.setValueAt(i, 0, x + sum);
				}
				
				return X;
			}else {
				throw new Exception("The number of rows in the U and Y matrix must be the same");
			}
		}else {
			throw new Exception("Matrix must be a square to calculate a backward substitution");
		}
	}
}
