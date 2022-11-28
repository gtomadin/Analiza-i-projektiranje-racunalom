package algorithms.FourthLab.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryString {
	
	private List<Integer> binaryString;
	private int n;
	
	public BinaryString(int value, int n) {
		binaryString = new ArrayList<>();
		this.n = n;
		
		List<Integer> help = new ArrayList<Integer>();

		int v = value;
	
		for(int i=0; i<n;++i) {
			int b = v % 2;
			help.add(b);
			v /= 2;
			//System.out.println(i + " : " + b);
		}
		
		for(int i=n-1;i>=0;--i) {
			int b = help.get(i);
			//System.out.println(i + " : " + b);
			binaryString.add(b);
		}
	}
	
	
	
	public List<Integer> getBinaryString() {
		return binaryString;
	}



	public void setBinaryString(List<Integer> binaryString) {
		this.binaryString = binaryString;
	}



	public int toInt() {
		
		int sum = 0;
		//System.out.println(binaryString);
		for(int i=0; i<n; ++i) {
			//System.out.println(i + " : " + binaryString.get(i));
			int m = n - 1 -i;
			int v = (int) Math.pow(2, m) * getBit(i);
			//System.out.println(v);
			sum += v;
		}
	
		return sum;
	}
	
	
	public int getBit(int index) {
		return binaryString.get(index);
	}
	
	public void setBin(int index, int bit) {
		binaryString.set(index, bit);
	}
	
	 public int getN() {
		 return n;
	 }


	@Override
	public String toString() {
		String s = "[";
		for(int i=0;i<binaryString.size();++i) {
			s += binaryString.get(i);
		}
		s += "]";
		return s;
	}
	
	public void randomBinaryString() {
		
		Random rand = new Random();
		
		for(int i = 0; i < n; ++i) {
			boolean b = rand.nextBoolean();
			
			if(b) {
				setBin(i, 1);
			}else {
				setBin(i, 0);
			}
		}
	}
}
