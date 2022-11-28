package tasks.FirstLab;

import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixReader;

public class FifthTask {
	public static void main(String[] args) {
		//5. task
		MatrixReader matrixReader = new MatrixReader();
				
		Matrix A_5 = matrixReader.readFile("1. labos data/5_MatA.txt");
				
		Matrix b_5 = matrixReader.readFile("1. labos data/5_Matb.txt");
			
	
		try {
			Matrix X_5 = EquationSolver.solveEquationUsingLUP(A_5, b_5);
			System.out.println(X_5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
