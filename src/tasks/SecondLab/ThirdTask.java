package tasks.SecondLab;

import algorithms.SecondLab.HookeJeeves;
import algorithms.SecondLab.Point;
import algorithms.SecondLab.Simplex;
import functions.SecondLab.Function4;

public class ThirdTask {
	
	public static void main(String[] args) {
		try {
			
			double[] pointd = {5,5};
		
		
			Point point = new Point(pointd);
			Function4 function = new Function4();
			
			
			System.out.println("Minimum : 0, 0");
			
			Point si = null;
			si = new Simplex(function, point).getResultPoint();
			
			if(si != null) {
				System.out.println("Simplex : " + si);
			}
			
			Point hj = null;
			hj = new HookeJeeves(function, point).getResultPoint();
			
			if(hj != null) {
				System.out.println("HookeJeeves : " + hj);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
