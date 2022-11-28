package tasks.FirstLab;

import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixReader;

public class SecondTask {
	public static void main(String[] args) {
		// 2. task
		
		MatrixReader matrixReader = new MatrixReader();
		
		Matrix A_1 = matrixReader.readFile("1. labos data/2_MatA.txt");
		Matrix b_1 = matrixReader.readFile("1. labos data/2_Matb.txt");
				
		System.out.println(A_1.toString());
		System.out.println(b_1.toString());
				
		
		try {
			Matrix X_1_1 = EquationSolver.solveEquationUsingLU(A_1, b_1);
			System.out.println(X_1_1.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		try {
			Matrix X_1_2 = EquationSolver.solveEquationUsingLUP(A_1, b_1);
			System.out.println(X_1_2.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
