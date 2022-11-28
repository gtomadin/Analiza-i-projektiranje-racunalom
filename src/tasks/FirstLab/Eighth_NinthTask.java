package tasks.FirstLab;

import matrix.Matrix;
import matrix.MatrixOperations;
import matrix.MatrixReader;

public class Eighth_NinthTask {
	public static void main(String[] args) {
		//5. task
		MatrixReader matrixReader = new MatrixReader();
				
		Matrix A_8 = matrixReader.readFile("1. labos data/8_9_MatA.txt");
			
	
		try {
			Matrix invA_8 = MatrixOperations.inverse(A_8);
			System.out.println(invA_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			double det_9 = MatrixOperations.determinant(A_8);
			System.out.println(det_9);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
