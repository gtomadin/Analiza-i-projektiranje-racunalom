package tasks.FirstLab;

import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixReader;

public class FourthTask {
	public static void main(String[] args) {
		
		
		//4. task
		MatrixReader matrixReader = new MatrixReader();
		
		Matrix A_4 = matrixReader.readFile("1. labos data/4_MatA.txt");
		
		Matrix b_4 = matrixReader.readFile("1. labos data/4_Matb.txt");
		
		try {
			Matrix X_4_1 = EquationSolver.solveEquationUsingLU(A_4, b_4);
			System.out.println(X_4_1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Matrix X_4_2 = EquationSolver.solveEquationUsingLUP(A_4, b_4);
			System.out.println(X_4_2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
