package matrix;

public class Testing {

	public static void main(String[] args) throws Exception {
		
//		MatrixReader mr1 = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaA.txt");
//		MatrixReader mr2 = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaA.txt");
//		
//		Matrix A = mr1.readFile();
//		Matrix B = mr2.readFile();
		
//		System.out.println(!A.isDimensionZero());
//		System.out.println(A.isMatrixSquare());
//		System.out.println(A.isSameSize(A));
		
		
		
//		
//		System.out.println(A.toString());
//		Matrix B = new Matrix(2, 1);
//		
//		B.setValueAt(0, 0, 4.5);
//		B.setValueAt(1, 0, 3.5);
		
//		Matrix C = MatrixOperations.matrix_Transpose(A);
//		
//		C.addAssigmentOperator(MatrixOperations.matrix_Multiplication(A, MatrixOperations.scalar_Multiplication(MatrixOperations.matrix_Multiplication(B, MatrixOperations.matrix_Subtraction(A, MatrixOperations.scalar_Multiplication(B, 2.))), 0.5)));
		
		
//		System.out.println(C.toString());
//		C.removeRow(1);
//		C.removeColumn(1);
		
//		System.out.println(C.toString());
		
//		MatrixReader mr3 = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaC.txt");
//		
//		C = mr3.readFile();
		
		
//		Matrix D = Decomposition.LU_decomposition(C);
//		System.out.println(C);
//		System.out.println(D);
		
		//Matrix L = new Matrix(D.getNumberOfRows(), 1);
		
//		L.setValueAt(0, 0, 1);
//		L.setValueAt(1, 0, 2);
//		L.setValueAt(2, 0, 3);
//		L.setValueAt(3, 0, 4);
		
		//Substitution.BackwardSubstitution(C, L);
		
//		MatrixReader mrE = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaE.txt");
//		MatrixReader mrF = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaF.txt");
//		
//		Matrix E = mrE.readFile();
//		Matrix F = mrF.readFile();
//		
////		System.out.println(E);
////		System.out.println(F);
//		
//		Matrix LU = Decomposition.LU_decomposition(E);
//		
////		System.out.println(LU);
//		
//		Matrix Y = Substitution.ForwardSubstitution(LU, F);
////		System.out.println(Y);
//		
//		Matrix X = Substitution.BackwardSubstitution(LU, Y);
////		System.out.println(X);
//		
//		
//		MatrixReader mrG = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaG.txt");
//		Matrix G = mrG.readFile();
////		System.out.println(G.toString());
//		Matrix LU2 = Decomposition.LU_decomposition(G);
////		System.out.println(LU2.toString());
//		
//		MatrixReader mrH = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaH.txt");
//		MatrixReader mrI = new MatrixReader("C:/Code/Eclipse/APR/APR_Labos/MatricaI.txt");
//		
//		Matrix H = mrH.readFile();
//		Matrix I = mrI.readFile();
		
//		Matrix LU3 = Decomposition.LU_decomposition(H);
//		System.out.println(LU3.toString());
//		
//		Matrix Y = Substitution.ForwardSubstitution(LU3, I);
//		System.out.println(Y);
//		
//		Matrix X = Substitution.BackwardSubstitution(LU3, Y);
//		System.out.println(X);
		
		Matrix M = new Matrix(3, 3);
		M.setValueAt(0, 0, 3);
		M.setValueAt(0, 1, 2);
		M.setValueAt(0, 2, 1);
		M.setValueAt(1, 0, 1);
		M.setValueAt(1, 1, 2);
		M.setValueAt(1, 2, 2);
		M.setValueAt(2, 0, 4);
		M.setValueAt(2, 1, 3);
		M.setValueAt(2, 2, 4);
		
		Matrix N = new Matrix(3, 3);
		N.setValueAt(0, 0, 5);
		N.setValueAt(0, 1, 6);
		N.setValueAt(0, 2, 7);
		N.setValueAt(1, 0, 4);
		N.setValueAt(1, 1, 2);
		N.setValueAt(1, 2, 1);
		N.setValueAt(2, 0, 4);
		N.setValueAt(2, 1, 2);
		N.setValueAt(2, 2, 2);
		
		System.out.println(MatrixOperations.determinant(N));
		
		Matrix R = new Matrix(3, 1);
		
		R.setValueAt(0, 0, 4);
		R.setValueAt(1, 0, 3);
		R.setValueAt(2, 0, 8);
		
		
		System.out.println(M.toString());
		
		try {
			System.out.println(MatrixOperations.determinant(M));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Matrix invM = null;
		try {
			invM = MatrixOperations.inverse(M);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(invM.toString());
		
//		Matrix[] MUP = Decomposition.LUP_decomposition(M);
//		Matrix LU4 = MUP[0];
//		Matrix P = MUP[1];
//		System.out.println(LU4.toString());
//		System.out.println(P.toString());
//		M.removeColumn(1);
//		
//		System.out.println(M.getColumn(2).toString());
//	
//		Matrix t = new Matrix(0, 0);
//		System.out.println(t.getNumberOfColumns());
//		I = MatrixOperations.matrix_Multiplication(P, R);
//		
//		Matrix Y = Substitution.ForwardSubstitution(LU4, I);
//		System.out.println(Y);
//		
//		Matrix X = Substitution.BackwardSubstitution(LU4, Y);
//		System.out.println(X);
//		
//		Matrix LUP = Decomposition.LUP_decomposition(E);
//		System.out.println(LUP.toString());
	}
}
