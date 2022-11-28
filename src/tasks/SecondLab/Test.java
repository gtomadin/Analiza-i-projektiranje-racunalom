package tasks.SecondLab;


import algorithms.SecondLab.HookeJeeves;
import algorithms.SecondLab.OptimizationAlgorithms;
import algorithms.SecondLab.Point;
import algorithms.SecondLab.Simplex;
import functions.SecondLab.AbstractFunction;
import functions.SecondLab.Function1;
import functions.SecondLab.Function2;
import functions.SecondLab.Function3;
import functions.SecondLab.Function4;
import functions.SecondLab.Function6;

public class Test {
	public static void main(String[] args){
		AbstractFunction[] functions = new AbstractFunction[5];
		Point[] points = new Point[5];
		
		//Function 1
		try {
			functions[0] = new Function1();
			double[] point1 = {-1.9, 2};
			points[0] = new Point(point1);
			
			functions[1] = new Function2();
			double[] point2 = {0.1, 0.3};
			points[1] = new Point(point2);
			
			functions[2] = new Function3();
			double[] point3 = {0, 0, 0, 0};
			points[2] = new Point(point3);
			
			functions[3] = new Function4();
			double[] point4 = {5.1, 1.1};
			points[3] = new Point(point4);
			
			functions[4] = new Function6();
			double[] point6 = {0, 0, 0, 0};
			points[4] = new Point(point6);
			
			for(int i=0; i< functions.length; ++i) {
//				System.out.println(OptimizationAlgorithms.coordinateSearch(functions[i], points[i]));
				
				System.out.println("Simplex");
				Simplex si = new Simplex(functions[i], points[i]);
				System.out.println("Funkcija " + (i+1) + " minimum = " + si.getResultPoint() +" pronaden u : " + functions[i].getCallCounter());
				System.out.println("F(x) = " + functions[i].getFunctionValue(si.getResultPoint()));
				functions[i].setCallCounter();
				
				System.out.println();
				
				System.out.println("HookeJeeves");
				HookeJeeves hj = new HookeJeeves(functions[i], points[i]);
				System.out.println("Funkcija " + (i+1) + " minimum = " + hj.getResultPoint() +" pronaden u : " + functions[i].getCallCounter());
				System.out.println("F(x) = " + functions[i].getFunctionValue(hj.getResultPoint()));
				functions[i].setCallCounter();
				
				System.out.println();
				
				System.out.println("CoordinateSearch");
				Point cs = OptimizationAlgorithms.coordinateSearch(functions[i], points[i]);
				System.out.println("Funkcija " + (i+1) + " minimum = " + cs +" pronaden u : " + functions[i].getCallCounter());
				System.out.println("F(x) = " + functions[i].getFunctionValue(cs));
				functions[i].setCallCounter();
				
				System.out.println("============================================================================================================");
				
			}
			
			
			
//			System.out.println(functions[0].getCallCounter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
