package algorithms.FourthLab;

import java.util.Random;

public class Mutation {
	
	MutationEnum mutationType;
	double PofMutation;
	Interval interval;
	
	public Mutation(MutationEnum mutationType, Interval interval, double PofMutation) {	
		this.mutationType = mutationType;
		this.PofMutation = PofMutation;
		this.interval = interval;
	}
	 
	
	public Chromosome makeMutation(Chromosome chromosome) throws Exception {
		
		if(mutationType.equals(MutationEnum.UNIFORM)) {
			return uniform(chromosome);
		}else if(mutationType.equals(MutationEnum.GAUSSIAN)) {
			return gaussian(chromosome);
		}else {
			throw new Exception("Wrong mutation");
		}
		
	}


	private Chromosome uniform(Chromosome chromosome) throws Exception {
		double[] values = new double[chromosome.getPoint().getNumberOfColumns()];
		Random rand = new Random();
		for(int i = 0; i < values.length; ++i) {
			
			double value = chromosome.getPoint().getValueAt(0, i);
			
			if(rand.nextDouble() < PofMutation) {
				value = interval.getLeft() + rand.nextDouble() * (interval.getRight() - interval.getLeft());
			}
			
			values[i] = value;
		}
		
		Point point = new Point(values);
		Chromosome newChromosome = new Chromosome(point);
		
		
		return newChromosome;
	}


	private Chromosome gaussian(Chromosome chromosome) throws Exception {
		double[] values = new double[chromosome.getPoint().getNumberOfColumns()];
		Random rand = new Random();
		for(int i = 0; i < values.length; ++i) {
			
			double value = chromosome.getPoint().getValueAt(0, i);
			
			if(rand.nextDouble() < PofMutation) {
				value = value + value * (rand.nextGaussian() - 0.5); 
			}
			
			values[i] = value;
		}
		
		Point point = new Point(values);
		Chromosome newChromosome = new Chromosome(point);
		
		
		return newChromosome;
	}
	
}
