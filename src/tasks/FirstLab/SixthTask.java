package tasks.FirstLab;

import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixReader;

public class SixthTask {
	public static void main(String[] args) {
		//6. task
		MatrixReader matrixReader = new MatrixReader();
				
		Matrix A_6 = matrixReader.readFile("1. labos data/6_MatA.txt");
				
		Matrix b_6 = matrixReader.readFile("1. labos data/6_Matb.txt");
				
		try {
			Matrix X_6_1 = EquationSolver.solveEquationUsingLU(A_6, b_6);
			System.out.println(X_6_1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		try {
			Matrix X_6_2 = EquationSolver.solveEquationUsingLUP(A_6, b_6);
			System.out.println(X_6_2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
