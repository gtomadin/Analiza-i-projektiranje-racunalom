package algorithms.FourthLab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import functions.FourthLab.AbstractFunction;

public class Population {
	
	private AbstractFunction function;
	private List<Chromosome> population;
	private int populationSize;
	private Interval interval;
	private int dimension;
	private double E = 10e-6;
	
	public Population(AbstractFunction function, int populationSize, Interval interval, int dimension) throws Exception {
		super();
		this.function = function;
		this.populationSize = populationSize;
		this.interval = interval;
		this.dimension = dimension;
		generatePopulation();
	}

	private void generatePopulation() throws Exception {
		population = new ArrayList<>();
		
		for(int i = 0; i < populationSize; ++i) {
			double[] values = new double[dimension];
			
			Random rand = new Random();
			
			for(int j=0; j < dimension; ++j) {
				values[j] = interval.getLeft() + rand.nextDouble() * (interval.getRight() - interval.getLeft());
			}
			
			Point point = new Point(values);
			
			Chromosome chromosome = new Chromosome(point);
		
			population.add(chromosome);
		}
	}

	public AbstractFunction getFunction() {
		return function;
	}

	public void setFunction(AbstractFunction function) {
		this.function = function;
	}

	public List<Chromosome> getPopulation() {
		return population;
	}

	public void setPopulation(List<Chromosome> population) {
		this.population = population;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
	public void calculateFvalueForEveryChromosome() throws Exception {
		
		for(int i = 0; i < populationSize; ++i) {
			population.get(i).calculateFvalue(function);
		}
	}
	
	public void evaluatePopulation(Interval scalingInterval) throws Exception {
		
		calculateFvalueForEveryChromosome();
		
		double bestFvalue = getBest();
		double worstFavlue = getWorst();
		
		
		for(int i = 0; i < populationSize; ++i) {
			
			double fitness = (population.get(i).getFvalue() - worstFavlue) / (bestFvalue - worstFavlue); 
			
			double scaledfitness = scalingInterval.getLeft() + (scalingInterval.getRight() - scalingInterval.getLeft()) * fitness;
			
			population.get(i).setFitness(scaledfitness);
		}
		
	}

	private double getWorst() {
		double worstFvalue = Double.MIN_VALUE;
		
		for(int i = 0; i < populationSize; ++i) {
			if(worstFvalue - population.get(i).getFvalue() < E) {
				worstFvalue = population.get(i).getFvalue();
			}
		}
		
		
		return worstFvalue;
	}

	private double getBest() {
		double bestFvalue = Double.MAX_VALUE;
		
		for(int i = 0; i < populationSize; ++i) {
			if(bestFvalue - population.get(i).getFvalue() > E) {
				bestFvalue = population.get(i).getFvalue();
			}
		}
		
		return bestFvalue;
	}
	
	public void replace(Chromosome newChromosome, Chromosome oldChromosome) {
		
		int index = population.indexOf(oldChromosome);
		population.set(index, newChromosome);
		
	}
	
	public Chromosome getChromosome(int index) {
		return population.get(index);
	}

	public Chromosome getBestChromosome() {
		double bestFitness = Double.MIN_VALUE;
		Chromosome best = null;
		
		for(int i = 0; i < populationSize; ++i) {
			if(bestFitness - population.get(i).getFitness() < E) {
				bestFitness = population.get(i).getFitness();
				best = population.get(i);
			}
		}
		
		return best;
	}
	
	@Override
	public String toString() {
		String s = "Population: \n";
		for(Chromosome ch : population) {
			s += ch.toString() + "\n";
		}
		return s;
	}
	
}
