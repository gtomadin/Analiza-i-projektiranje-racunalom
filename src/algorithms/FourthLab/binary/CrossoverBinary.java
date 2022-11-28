package algorithms.FourthLab.binary;
import algorithms.FourthLab.Interval;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossoverBinary {
	
	CrossoverEnumBinary crossoverEnumBinary;
	Interval interval;
	
	
	public CrossoverBinary(CrossoverEnumBinary crossoverEnumBinary, Interval interval) {
		this.crossoverEnumBinary = crossoverEnumBinary;
		this.interval = interval;
	}
	
	
	public ChromosomeBinary makeCrossover(SelectionBinary selectionBinary) throws Exception {
		
		
		if(crossoverEnumBinary.equals(CrossoverEnumBinary.UNIFORM)) {
			return uniform(selectionBinary);
		}else if(crossoverEnumBinary.equals(CrossoverEnumBinary.SINGLEPOINT)) {
			return singlepoint(selectionBinary);
		}else {
			throw new Exception("Wrong crossover");
		}
		
	}

	private ChromosomeBinary uniform(SelectionBinary selectionBinary) throws Exception {
		
		if(selectionBinary.getSize() != 2) {
			throw new Exception("Wrong selection dimesnsion");
		}
		
		ChromosomeBinary first = selectionBinary.getChromosomeBinary(0);
		ChromosomeBinary second = selectionBinary.getChromosomeBinary(1);
		
		int n = 0;
		
		List<BinaryString> binaryStrings = new ArrayList<BinaryString>();
		
		//Random rand = new Random();
		
		for(int i = 0; i < first.getPointBinary().size(); ++i) {
			
			BinaryString A = first.getPointBinary().get(i);
			
			BinaryString B = second.getPointBinary().get(i);
			
			n = A.getN();
			
			BinaryString D = new BinaryString(0, A.getN());
			
			BinaryString R = new BinaryString(0, B.getN());
			
			R.randomBinaryString();
			
			//System.out.println(R.toString());
			
			for(int j = 0; j < A.getN(); ++j) {
				
				int a = A.getBit(j);
				int b = B.getBit(j);
				int r = R.getBit(j);
				
				if(a == b){
					D.setBin(j, a);
				}else {
					D.setBin(j, r);
				}
				
			}
			
			binaryStrings.add(D);
			
		}
		
		ChromosomeBinary cb = new ChromosomeBinary(binaryStrings, n, interval);
		
		return cb;
	}

	private ChromosomeBinary singlepoint(SelectionBinary selectionBinary) throws Exception {
		
		if(selectionBinary.getSize() != 2) {
			throw new Exception("Wrong selection dimesnsion");
		}
		
		ChromosomeBinary first = selectionBinary.getChromosomeBinary(0);
		ChromosomeBinary second = selectionBinary.getChromosomeBinary(1);
		
		int n = 0;
		
		List<BinaryString> binaryStrings = new ArrayList<BinaryString>();
		
		Random rand = new Random();
		
		for(int i = 0; i < first.getPointBinary().size(); ++i) {
			
			BinaryString A = first.getPointBinary().get(i);
			
			BinaryString B = second.getPointBinary().get(i);
			
			n = A.getN();
			
			BinaryString D = new BinaryString(0, A.getN());
			
			int m = rand.nextInt(n);
			//System.out.println(m);
			for(int j = 0; j < n; ++j) {
				
				if(j <= m) {
					D.setBin(j, A.getBit(j));
				}else {
					D.setBin(j, B.getBit(j));
				}
				
			}
			//System.out.println(D);
			binaryStrings.add(D);
			
		}
		
		ChromosomeBinary cb = new ChromosomeBinary(binaryStrings, n, interval);
		
		return cb;
		
	}
	
}
