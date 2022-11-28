package tasks.FirstLab;

import matrix.Decomposition;
import matrix.EquationSolver;
import matrix.Matrix;
import matrix.MatrixReader;

public class ThirdTask {
	public static void main(String[] args) {
MatrixReader matrixReader = new MatrixReader();
		
		
		// 3. task
		Matrix A_2 = matrixReader.readFile("1. labos data/3_7_MatA.txt");
		System.out.println(A_2.toString());
		
		
		try {
			Matrix LU_2 = Decomposition.LU_decomposition(A_2);
			System.out.println(LU_2.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			Matrix[] LU_P_2 = Decomposition.LUP_decomposition(A_2);
			System.out.println(LU_P_2[0].toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Matrix b_2 = null;
		try {
			b_2 = new Matrix(3, 1);
			b_2.setValueAt(0, 0, 1);
			b_2.setValueAt(1, 0, 1);
			b_2.setValueAt(2, 0, 1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Matrix X_2_1 = EquationSolver.solveEquationUsingLU(A_2, b_2);
			System.out.println(X_2_1.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Matrix X_2_2 = EquationSolver.solveEquationUsingLUP(A_2, b_2);
			System.out.println(X_2_2.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
