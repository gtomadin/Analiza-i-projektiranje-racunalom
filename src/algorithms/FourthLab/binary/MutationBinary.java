package algorithms.FourthLab.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import algorithms.FourthLab.Interval;

public class MutationBinary {
	
	
	MutationEnumBinary mutationEnumBinary;
	double PofMutation;
	Interval interval;
	public MutationBinary(MutationEnumBinary mutationEnumBinary, Interval interval, double pofMutation) {
		super();
		this.mutationEnumBinary = mutationEnumBinary;
		this.PofMutation = pofMutation;
		this.interval = interval;
	}
	
	public ChromosomeBinary makeMutation(ChromosomeBinary chromosomeBinary) throws Exception {
		
		if(mutationEnumBinary.equals(MutationEnumBinary.UNIFORM)) {
			return uniform(chromosomeBinary);
		}else if(mutationEnumBinary.equals(MutationEnumBinary.SIMPLE)) {
			return simple(chromosomeBinary);
		}else {
			throw new Exception("Wrong mutation");
		}
		
		
	}

	private ChromosomeBinary uniform(ChromosomeBinary chromosomeBinary) throws Exception {
		
		List<BinaryString> pointBinaryStrings = chromosomeBinary.getPointBinary();
		
		List<BinaryString> newpointBinaryStrings = new ArrayList<BinaryString>();
		
		Random rand = new Random();
		
		for(int i=0; i<pointBinaryStrings.size(); ++i) {
			
			BinaryString bs = pointBinaryStrings.get(i);
			
			for(int j=0; j< bs.getN(); ++j) {
			
				if(rand.nextDouble() < PofMutation) {
					
					int bit = bs.getBit(j);
					
					if(bit == 0) {
						bs.setBin(j, 1);
					}else {
						bs.setBin(j, 0);
					}
					
				}
			}
			
			newpointBinaryStrings.add(bs);
		}
		
		ChromosomeBinary cb = new ChromosomeBinary(newpointBinaryStrings, newpointBinaryStrings.get(0).getN(), interval);
		
		
		return cb;
	}
	

	private ChromosomeBinary simple(ChromosomeBinary chromosomeBinary) throws Exception {
		
		List<BinaryString> pointBinaryStrings = chromosomeBinary.getPointBinary();
		
		List<BinaryString> newpointBinaryStrings = new ArrayList<BinaryString>();
		
		Random rand = new Random();
		
		for(int i=0; i<pointBinaryStrings.size(); ++i) {
			
			BinaryString bs = pointBinaryStrings.get(i);
			
			if(rand.nextDouble() < PofMutation) {
				
				int m = rand.nextInt(bs.getN());
				
				int bit = bs.getBit(m);
				
				if(bit == 0) {
					bs.setBin(m, 1);
				}else {
					bs.setBin(m, 0);
				}
			}
			
			newpointBinaryStrings.add(bs);
		}
		
		ChromosomeBinary cb = new ChromosomeBinary(newpointBinaryStrings, newpointBinaryStrings.get(0).getN(), interval);
		
		
		return cb;
	}
	
}
