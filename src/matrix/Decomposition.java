package matrix;

public class Decomposition {

	// method that returns a matrix thats is a result of the LU decomposition
	public static Matrix LU_decomposition(Matrix A) throws Exception {
		if(A.isMatrixSquare()) {
			Matrix LU = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
			MatrixOperations.copyValues(A, LU);
				
			// LU decomposition algorithm 
			for(int i=0; i< LU.getNumberOfRows()-1; ++i) {
				if(!LU.isSameValue(0, LU.getValueAt(i, i))) { //checking if the pivot is zero
					
					for(int j=i+1; j<LU.getNumberOfRows(); ++j) {
						LU.setValueAt(j, i, LU.getValueAt(j, i)/LU.getValueAt(i, i)); // dividing the column elements with the pivot
						for(int k=i+1; k<LU.getNumberOfRows(); ++k) {
							LU.setValueAt(j, k, LU.getValueAt(j, k) - LU.getValueAt(j, i)*LU.getValueAt(i, k)); // A[j][k] = A[j][k] - A[j][i]*A[i][k]
						}
					}	
				}else {
					//System.err.println("Pivot is zero");
					throw new Exception("Pivot is zero");
				}
			}
			return LU;
			
		}else {
			throw new Exception("Matrix must be a square to performe LU decomposition");
		}
	}
	
	private static int numberOfSwitchs;
	
	// method that returns a matrix thats is a result of the LUP decomposition
	public static Matrix[] LUP_decomposition(Matrix A) throws Exception {
		if(A.isMatrixSquare()) {
			Matrix P = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			Matrix LU = new Matrix(A.getNumberOfRows(), A.getNumberOfColumns());
			
			MatrixOperations.copyValues(A, LU);
			
			numberOfSwitchs = 0;
			
			// creating P matrix 
			for(int i=0; i<LU.getNumberOfRows(); ++i) {
				P.setValueAt(i, i, 1);
			}
			
			// LUP decomposition algorithm 
			for(int i=0; i<LU.getNumberOfRows()-1; ++i) {
				
				double maxValue = LU.getValueAt(i, i);
				int positionWithMaxValue = i;
				
				// finding the max(abs(value) in the column
				for(int j= i+1; j<LU.getNumberOfColumns(); ++j) {
					if(Math.abs(maxValue) < Math.abs(LU.getValueAt(j, i))) {
						maxValue = LU.getValueAt(j, i);
						positionWithMaxValue = j;
					}
				}
				
				// checking if the pivot is zero
				if(LU.isSameValue(0, maxValue)) {
					throw new Exception("Pivot iz zero");
				}
				
				// switching the rows
				if(positionWithMaxValue != i) {
					LU.switchRows(i, positionWithMaxValue);
					P.switchRows(i, positionWithMaxValue);
					numberOfSwitchs++;
				}
				
				for(int j= i+1; j<LU.getNumberOfRows(); ++j) {
					LU.setValueAt(j, i, LU.getValueAt(j, i)/LU.getValueAt(i, i)); // dividing the column elements with the pivot
					for(int k= i+1; k<LU.getNumberOfRows(); ++k) {
						
						LU.setValueAt(j, k, LU.getValueAt(j, k) - LU.getValueAt(j, i)*LU.getValueAt(i, k)); // A[j][k] = A[j][k] - A[j][i]*A[i][k]
						
					}
				}
//				System.out.println(LU);
			}
			Matrix[] LU_P = new Matrix[2];
			LU_P[0] = LU;
			LU_P[1] = P;
			return LU_P;
		}else {
			throw new Exception("Matrix must be a square to performe LU decomposition");
		}
	}
	
	// method that returns the number of row switches during the decomposition
	public static int getPswitchs() {
		return numberOfSwitchs;
	}
}
