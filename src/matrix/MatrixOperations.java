package matrix;

public class MatrixOperations {
	
	// method that copy all the values from matrix A to matrix B
	public static void copyValues(Matrix A, Matrix B) throws Exception {
		if(A.isSameSize(B)) {
			for(int i=0; i<A.getNumberOfRows(); ++i) {
				for(int j=0; j<A.getNumberOfColumns(); ++j) {	
					B.setValueAt(i, j, A.getValueAt(i, j));
				}
			}
		}else {
			throw new Exception("Two matrices that must be the same size");
		}
	}
	
	// method that returns a transpose matrix
	public static Matrix matrix_Transpose(Matrix A) throws Exception {
		Matrix B = new Matrix(A.getNumberOfColumns(), A.getNumberOfRows());
		for(int i=0; i<A.getNumberOfColumns(); ++i) {
			for(int j=0; j<A.getNumberOfRows(); ++j) {
				B.setValueAt(i, j, A.getValueAt(j, i));
			}
		}
		return B;
	}
	
	// method that returns a matrix thats is the result of adding the matrix A to matrix B
	public static Matrix matrix_Addition(Matrix A, Matrix B) throws Exception {
		if(A.isSameSize(B)) {
			Matrix C = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
			for(int i=0; i<A.getNumberOfRows(); ++i) {
				for(int j=0; j<A.getNumberOfColumns(); ++j) {
					
					double value = A.getValueAt(i, j) + B.getValueAt(i, j);
					C.setValueAt(i, j, value);
				}
			}			
			return C;
		}else {
			throw new Exception("Two matrices that must be the same size to add them");
		}
	}
	
	// method that returns a matrix thats is the result of subtracting the matrix B to matrix A
	public static Matrix matrix_Subtraction(Matrix A, Matrix B) throws Exception {
		if(A.isSameSize(B)) {
			Matrix C = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
			for(int i=0; i<A.getNumberOfRows(); ++i) {
				for(int j=0; j<A.getNumberOfColumns(); ++j) {
					
					double value = A.getValueAt(i, j) - B.getValueAt(i, j);
					C.setValueAt(i, j, value);
				}
			}			
			return C;
		}else {
			throw new Exception("Two matrices that must be the same size to sub them");
		}
	}

	// method that returns a matrix thats is the result of multiplying the matrix A and the scalar s 
	public static Matrix scalar_Multiplication(Matrix A, double s) throws Exception {
		Matrix B = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
		for(int i=0; i<A.getNumberOfRows(); ++i) {
			for(int j=0; j<A.getNumberOfColumns(); ++j) {
				B.setValueAt(i, j, A.getValueAt(i, j)*s);
			}
		}			
		return B;
		
	}

	// method that returns a matrix thats is the result of multiplying the matrix B to matrix A  
	public static Matrix matrix_Multiplication(Matrix A, Matrix B) throws Exception {
		if(A.getNumberOfColumns() == B.getNumberOfRows()) {
			Matrix C = new Matrix(A.getNumberOfRows(), B.getNumberOfColumns());
			
			for(int i=0; i<C.getNumberOfRows(); ++i) {
				for(int j=0; j<C.getNumberOfColumns(); ++j) {
				
					double value = 0.;
			
					for(int k=0; k<A.getNumberOfColumns(); ++k) {
						value += A.getValueAt(i, k)* B.getValueAt(k, j);
					}
					C.setValueAt(i, j, value);
				}
			}			
			return C;
		}else {
			throw new Exception("Wrong matrix size: A(" + A.getNumberOfRows() + ", " + A.getNumberOfColumns() + ") B:(" + B.getNumberOfRows() + ", " + B.getNumberOfColumns() + ")" );
		}
	}
	
	// method that returns a matrix thats is the inverse of matrix A 
	public static Matrix inverse(Matrix A) throws Exception {
		if(A.isMatrixSquare()) {
			Matrix invA = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			Matrix[] LU_P = Decomposition.LUP_decomposition(A); //using LUP_decomposition on the matrix A to get the L, U and P matrices
			Matrix LU = LU_P[0];
			Matrix P = LU_P[1];
			
//			System.out.println(LU.toString());
//			System.out.println(P.toString());
			
			for(int i=0; i<LU.getNumberOfColumns(); ++i) {
				
				Matrix Pi = P.getColumn(i); // getting the i column of the P matrix
				Matrix Y = Substitution.ForwardSubstitution(LU, Pi); // using the forward substitution to get the Y vector 
				
				Matrix X = Substitution.BackwardSubstitution(LU, Y); // using the backward substitution to get the X vector
				
				invA.changeColumnValues(X, i); // setting the values of the i column of the result matrix 
				
			}
			
			return invA;
			
		}else {
			throw new Exception("Matrix must be a square to calculate a inverse");
		}
	}
	
	// method that returns the matrix A determinant   
	public static double determinant(Matrix A) throws Exception {
		if(A.isMatrixSquare()) {
			
			Matrix[] LU_P = Decomposition.LUP_decomposition(A); // using LUP_decomposition on the matrix A to get the L, U and P matrices
			Matrix LU = LU_P[0];
			
			int numberOfSwitches = Decomposition.getPswitchs(); // getting the number of row switches during the decomposition
			
			// determinant(inv(P)) equal (-1)^(numberOfSwitches)
			double determinant = Math.pow(-1, numberOfSwitches);
			
			// determinant(L) = 1, determinant(U) = product(U[i][i])
			for(int i = 0; i < LU.getNumberOfRows(); ++i) {
				determinant *= LU.getValueAt(i, i);
			}
			
			return determinant;
			
		}else {
			throw new Exception("Matrix must be a square to calculate a inverse");
		}
	}
}	