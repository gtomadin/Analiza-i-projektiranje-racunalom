package algorithms.FourthLab;

import java.util.Random;

public class Crossover {
	
	CrossoverEnum crossoverType;
	
	 public Crossover(CrossoverEnum crossoverType) {	
		this.crossoverType = crossoverType;
	}
	 
	
	public Chromosome makeCrossover(Selection selection) throws Exception {
		
		if(crossoverType.equals(CrossoverEnum.ARITHMETIC)) {
			return arithmetic(selection);
		}else if(crossoverType.equals(CrossoverEnum.HEURISTIC)) {
			return heuristic(selection);
		}else {
			throw new Exception("Wrong crossover");
		}
		
	}


	private Chromosome arithmetic(Selection selection) throws Exception {
		
		if(selection.getSize() != (selection.k -1)) {
			throw new Exception("Wrong selection dimension");
		}
		
		// k = 2
		
		Chromosome first = selection.getChromosome(0);
		Chromosome second = selection.getChromosome(1);
		
		double[] values = new double[first.getPoint().getNumberOfColumns()];
		
		Random rand = new Random();
		double a = rand.nextDouble();
		
		for(int i = 0; i < values.length; ++i) {
			double x_1 = first.getPoint().getValueAt(0, i);
			double x_2 = second.getPoint().getValueAt(0, i);
			
			values[i] = a * x_1 + (1 - a) * x_2;
		}
		
		Point point = new Point(values);
		
		Chromosome newChromosome = new Chromosome(point);
		
		return newChromosome;
	}


	private Chromosome heuristic(Selection selection) throws Exception {
		if(selection.getSize() != 2) {
			throw new Exception("Wrong selection dimension");
		}
		
		// k = 2
		
		Chromosome first = selection.getChromosome(0);
		Chromosome second = selection.getChromosome(1);
		
		if(first.getFitness() > second.getFitness()) {
			Chromosome help = first;
			first = second;
			second = help;
		}
		
		double[] values = new double[first.getPoint().getNumberOfColumns()];
		
		Random rand = new Random();
		double a = rand.nextDouble();
		
		for(int i = 0; i < values.length; ++i) {
			double x_1 = first.getPoint().getValueAt(0, i);
			double x_2 = second.getPoint().getValueAt(0, i);
			
			values[i] = a * (x_2 - x_1) + x_2;
		}
		
		Point point = new Point(values);
		
		Chromosome newChromosome = new Chromosome(point);
		
		return newChromosome;
		
	}
	
	
}
