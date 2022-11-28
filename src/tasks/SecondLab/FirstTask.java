package tasks.SecondLab;


import algorithms.SecondLab.HookeJeeves;
import algorithms.SecondLab.OptimizationAlgorithms;
import algorithms.SecondLab.Point;
import algorithms.SecondLab.Simplex;
import functions.SecondLab.AbstractFunction;

public class FirstTask {
	
	
	public static void main(String[] args) {
		AbstractFunction function = new AbstractFunction() {
			@Override
			protected double CalculateFunctionValue(Point point) throws Exception {
				double x = point.getValueAt(0, 0);
				double a = x - 3; 
				return a*a; // (x -3)^2
			}
		};
		
		int times = 20;
		
		int jump = 5;
		
		try {
			
			Point siMin[] = new Point[times];
			Point hjMin[] = new Point[times];
			Point coMin[] = new Point[times];
			
			
			int siCount[] = new int[times];
			int hjCount[] = new int[times];
			int coCount[] = new int[times];
			
			for(int i = 0; i< times; ++i) {
				
			
				Point point = new Point(10 + i*jump);
//				System.out.println(point);

				Simplex si = new Simplex(function, point);
				siMin[i] = si.getResultPoint();
				siCount[i] = function.getCallCounter();
				function.setCallCounter();
				
				HookeJeeves hj = new HookeJeeves(function, point);
				hjMin[i] = hj.getResultPoint();
				hjCount[i] = function.getCallCounter();
				function.setCallCounter();
				
				Point cs = OptimizationAlgorithms.coordinateSearch(function, point);
				coMin[i] = cs;
				coCount[i] = function.getCallCounter();
				function.setCallCounter();
				
				
			}
			System.out.println("          Simplex    HookeJeeves     Coordinate Search ");
			for(int i=0; i<siMin.length; ++i) {
				System.out.println( "x =  " + (10 + jump*i) +  " :  " + siMin[i] + " " + siCount[i] + " || " + hjMin[i] + " " + hjCount[i] + " || " + coMin[i] + " " + coCount[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
