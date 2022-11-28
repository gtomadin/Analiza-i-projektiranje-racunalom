package algorithms.SecondLab;



import algorithms.SecondLab.OptimizationAlgorithms.Interval;
import functions.SecondLab.HookeJeevesTestFunction;
import functions.SecondLab.SimplexTestFunction;
import functions.SecondLab.UnimodalTestFunction;
import matrix.Matrix;
import matrix.MatrixOperations;

public class test1 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try {
			
			Matrix point = new Matrix(100);
			UnimodalTestFunction UMF = new UnimodalTestFunction();
			
			Interval interval = OptimizationAlgorithms.unimodalInterval(100, 1, UMF);
			
//			GoldenRatioTestFunction GRF = new GoldenRatioTestFunction();
			
//			double x = OptimizationAlgorithms.goldenRatio(-2, 2, GRF);
			
			//System.out.println(interval.toString());
			double[][] a1 = {{2, 3}};
			double[][] b1 = {{1, 1}};
			
			double[][] a = {{7, 3}};
			
			
			Matrix m = new Matrix(a1);
			Matrix n = new Matrix(b1);
			
			HookeJeevesTestFunction HJF = new HookeJeevesTestFunction(); 
			
//			Matrix Am = new Matrix(a);
//			System.out.println(Am.toString());
			
//			Matrix rez = OptimizationAlgorithms.HookeJeeves(HJF, Am);
			
//			double[][] b = {{2, 3, 4}};
//			
//			Matrix Bm = new Matrix(b);
//			
//			HookeJeevesTestFunction2 HJF2 = new HookeJeevesTestFunction2();
//			
//			Matrix rez = OptimizationAlgorithms.HookeJeeves(HJF2, Bm);
//			System.out.println(rez);
			
			Point  p = new Point(5);
			Point d = new Point(6);
			
			double[] r = {0, 0};
			Point o = new Point(r);
			Matrix pd = MatrixOperations.matrix_Multiplication(p, d);
			
			SimplexTestFunction STF = new SimplexTestFunction();
			
			Simplex simplex = new Simplex(STF, o);
			
			
			System.out.println(simplex.getResultPoint());
			
//			System.out.println(pd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
