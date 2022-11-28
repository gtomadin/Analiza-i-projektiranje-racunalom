package tasks.FirstLab;

import matrix.Matrix;
import matrix.MatrixOperations;
import matrix.MatrixReader;

public class TenthTask {
	public static void main(String[] args) {
		//5. task
		MatrixReader matrixReader = new MatrixReader();
				
		Matrix A_10 = matrixReader.readFile("1. labos data/10_MatA.txt");
			
		
		try {
			double det_10 = MatrixOperations.determinant(A_10);
			System.out.println(det_10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
