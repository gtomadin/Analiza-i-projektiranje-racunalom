package matrix;

public class EquationSolver {
	
	// method that solves the Ax = b equation using the LU decomposition, returns the x vector  
	public static Matrix solveEquationUsingLU(Matrix A, Matrix b) throws Exception {
		if(A.isSameSize(A) && A.getNumberOfRows() == b.getNumberOfRows()) {
			
			Matrix LU = Decomposition.LU_decomposition(A);
//			System.out.println(LU.toString());
			Matrix Y = Substitution.ForwardSubstitution(LU, b);
			Matrix X = Substitution.BackwardSubstitution(LU, Y);
			
			return X;
		}
		
		return null;
	}
	
	// method that solves the Ax = b equation using the LUP decomposition, returns the x vector 
	public static Matrix solveEquationUsingLUP(Matrix A, Matrix b) throws Exception {
		if(A.isSameSize(A) && A.getNumberOfRows() == b.getNumberOfRows()) {
			
			Matrix[] LU_P = Decomposition.LUP_decomposition(A);
			Matrix LU = LU_P[0];
			Matrix P = LU_P[1];
//			System.out.println(LU.toString());
			Matrix Pb = MatrixOperations.matrix_Multiplication(P, b);
//			System.out.println(Pb.toString());
			Matrix Y = Substitution.ForwardSubstitution(LU, Pb);
//			System.out.println(Y);
			Matrix X = Substitution.BackwardSubstitution(LU, Y);
			
			return X;
		}
		
		return null;
	}
}
