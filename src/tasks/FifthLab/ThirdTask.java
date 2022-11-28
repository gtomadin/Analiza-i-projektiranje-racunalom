package tasks.FifthLab;

import algorithms.FifthLab.Euler;
import algorithms.FifthLab.PredictorCorrectorEulerReverseEuler;
import algorithms.FifthLab.PredictorCorrectorEulerTrapezoid;
import algorithms.FifthLab.ReverseEuler;
import algorithms.FifthLab.RungeKutta;
import algorithms.FifthLab.Trapezoid;
import matrix.Matrix;
import matrix.MatrixReader;

public class ThirdTask {
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		
		MatrixReader matrixReader = new MatrixReader();
		
		Matrix Xk = matrixReader.readFile("5. labos data/3_MatXk.txt");
		Matrix A = matrixReader.readFile("5. labos data/3_MatA.txt");
		Matrix B = matrixReader.readFile("5. labos data/3_MatB.txt");
				
		System.out.println(Xk.toString());
		System.out.println(A.toString());
		System.out.println(B.toString());
		
		System.out.println();
		System.out.println("Euler");
		System.out.println();
		
		double periodT = 0.01;
		double tMax = 10;
		
		try {	
			Euler euler = new Euler(Xk, A, B, false, 0, periodT, tMax, null, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("ReverseEuler");
		System.out.println();
		
		try {
			ReverseEuler reverseEuler = new ReverseEuler(Xk, A, B, false, 0, periodT, tMax, null, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Trapezoid");
		System.out.println();
		
		try {
			Trapezoid trapezoid = new Trapezoid(Xk, A, B, false, 0, periodT, tMax, null, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("RungeKutta");
		System.out.println();
		
		try {
			RungeKutta rungekuta = new RungeKutta(Xk, A, B, false, 0, periodT, tMax, null, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("PredictorCorrectorEulerReverseEuler");
		System.out.println();
		
		try {
			PredictorCorrectorEulerReverseEuler PCERE = new PredictorCorrectorEulerReverseEuler(Xk, A, B, false, 0, periodT, tMax, null, 100, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("PredictorCorrectorEulerTrapezoid");
		System.out.println();
		
		try {
			PredictorCorrectorEulerTrapezoid PCET = new PredictorCorrectorEulerTrapezoid(Xk, A, B, false, 0, periodT, tMax, null, 100, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
