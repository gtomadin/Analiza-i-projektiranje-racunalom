package tasks.FirstLab;

import matrix.Matrix;
import matrix.MatrixOperations;
import matrix.MatrixReader;

public class SeventhTask {
	public static void main(String[] args) {
		//5. task
		MatrixReader matrixReader = new MatrixReader();
				
		Matrix A_7 = matrixReader.readFile("1. labos data/3_7_MatA.txt");
			
	
		try {
			Matrix invA_7 = MatrixOperations.inverse(A_7);
			System.out.println(invA_7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
