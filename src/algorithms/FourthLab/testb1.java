package algorithms.FourthLab;


import java.util.ArrayList;
import java.util.List;

import algorithms.FourthLab.binary.BinaryString;

public class testb1 {
	public static void main(String[] args) throws Exception {
		
		BinaryString sb = new BinaryString(11, 9);
		
		//System.out.println(sb.toInt());
		
		BinaryString sb1 = new BinaryString(19, 9);
		
		System.out.println(sb.toString());
		List<BinaryString> lb = new ArrayList<BinaryString>();
		
		lb.add(sb);
		lb.add(sb1);
		
		try {
//			ChromosomeBinary cb = new ChromosomeBinary(lb, 9);
			
//			System.out.println(cb.toString());
//			System.out.println(cb.intPoint());
//			System.out.println(cb.getPoint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		double[] values = {13.8, 16};
//		Point point = new Point(values);
		
		//ChromosomeBinary chromosomeBinary = new ChromosomeBinary(point, 5);
		
//		System.out.println(chromosomeBinary.toString());
//		System.out.println(chromosomeBinary.intPoint());
//		System.out.println(chromosomeBinary.getPointBinary());
		
	}
}
