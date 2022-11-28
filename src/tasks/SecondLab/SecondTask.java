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

public class SecondTask {
	public static void main(String[] args){
		AbstractFunction[] functions = new AbstractFunction[4];
		Point[] points = new Point[4];
		Point[] minPoint = new Point[4];
		double[] Fxmin = new double[4];
		
		
		try {
			//Function 1
			functions[0] = new Function1();
			double[] point1 = {-1.9, 2};
			points[0] = new Point(point1);
			double[] point11 = {1, 1};
			minPoint[0] = new Point(point11);
			Fxmin[0] = functions[0].getFunctionValue(minPoint[0]);
			
			//Function 2
			functions[1] = new Function2();
			double[] point2 = {0.1, 0.3};
			points[1] = new Point(point2);
			double[] point22 = {4, 2};
			minPoint[1] = new Point(point22);
			Fxmin[1] = functions[1].getFunctionValue(minPoint[1]);
			
			//Function 3
			functions[2] = new Function3();
			double[] point3 = {0, 0, 0, 0, 0};
			points[2] = new Point(point3);
			double[] point33 = {1, 2, 3, 4, 5};
			minPoint[2] = new Point(point33);
			Fxmin[2] = functions[2].getFunctionValue(minPoint[2]);
			
			//Function 4
			functions[3] = new Function4();
			double[] point4 = {5.1, 1.1};
			points[3] = new Point(point4);
			double[] point44 = {0, 0};
			minPoint[3] = new Point(point44);
			Fxmin[3] = functions[3].getFunctionValue(minPoint[3]);
			

			
			for(int i=0; i< functions.length; ++i) {
				functions[i].setCallCounter();

				
				System.out.println("Funkcija : " + (i+1) +" pocetak : " + points[i] + " minimum : " + minPoint[i] + " Fmin = " + Fxmin[i]);
				
				/// Simplex
				Point si =  null;
				try {
					si = new Simplex(functions[i], points[i]).getResultPoint();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				int siCount = functions[i].getCallCounter();
				functions[i].setCallCounter();
				
				if(si != null) {
					System.out.println("Simplex  minimun : " + si + " broj evaluacija: " + siCount);
				}
				
				
				// HookeJeeves
				Point hj = null;
				try {
					hj = new HookeJeeves(functions[i], points[i]).getResultPoint();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				int hjCount = functions[i].getCallCounter();
				functions[i].setCallCounter();
				
				if(hj != null) {
					System.out.println("HookeJeeves  minimun : " + hj + " broj evaluacija: " + hjCount);
				}
				
				// Coordinate Search
				Point co = null;
				try {
					co = OptimizationAlgorithms.coordinateSearch(functions[i], points[i]);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				int coCount = functions[i].getCallCounter();
				functions[i].setCallCounter();
				
				if(co != null) {
					System.out.println("CoordinateSearch minimun : " + co + " broj evaluacija: " + coCount);
				}
				
				
				System.out.println("============================================================================================================");
				
			}
			
			
			
//			System.out.println(functions[0].getCallCounter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}	
