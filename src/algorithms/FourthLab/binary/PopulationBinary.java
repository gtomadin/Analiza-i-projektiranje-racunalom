package algorithms.FourthLab.binary;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import algorithms.FourthLab.Interval;
import algorithms.FourthLab.Point;
import functions.FourthLab.AbstractFunction;

public class PopulationBinary {
	
	private AbstractFunction function;
	private List<ChromosomeBinary> population;
	private int populationSize;
	private Interval interval;
	private int dimension;
	private double E = 10e-6;
	private int n;
	private int p;
	
	public PopulationBinary(AbstractFunction function, int populationSize, Interval interval, int dimension, int p) throws Exception {
		super();
		this.function = function;
		this.populationSize = populationSize;
		this.interval = interval;
		this.dimension = dimension;
		this.p = p;
		this.n = getN();
		generatePopulation();
	}

	private int getN() {
		double a = Math.pow(10, p);
		double b = interval.getRight() - interval.getLeft();
		double v = 1 + a * b;
		double t = Math.log10(v) / Math.log10(2);
		//System.out.println(Math.log10(2));
		int n = (int) Math.ceil(t);
		
		return n;
	}

	private void generatePopulation() throws Exception {
		population = new ArrayList<>();
		//System.out.println(n);
		for(int i=0; i < populationSize; ++i) {
			double[] values = new double[dimension];
			
			Random rand = new Random();
			
			for(int j=0; j < dimension; ++j) {
				values[j] = interval.getLeft() + rand.nextDouble() * (interval.getRight() - interval.getLeft());
			}
			
			Point point = new Point(values);
			
			ChromosomeBinary chromosomeBinary = new ChromosomeBinary(point, n, interval);
			
			population.add(chromosomeBinary);
		}
		
	}

	public AbstractFunction getFunction() {
		return function;
	}

	public void setFunction(AbstractFunction function) {
		this.function = function;
	}

	public List<ChromosomeBinary> getPopulation() {
		return population;
	}

	public void setPopulation(List<ChromosomeBinary> population) {
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

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public void setN(int n) {
		this.n = n;
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
	
	public void replace(ChromosomeBinary newChromosome, ChromosomeBinary oldChromosome) {
		
		int index = population.indexOf(oldChromosome);
		population.set(index, newChromosome);
		
	}
	
	public ChromosomeBinary getChromosomebBinary(int index) {
		return population.get(index);
	}

	public ChromosomeBinary getBestChromosomebBinary() {
		double bestFitness = Double.MIN_VALUE;
		ChromosomeBinary best = null;
		
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
		for(ChromosomeBinary ch : population) {
			s += ch.toString() + "\n";
		}
		return s;
	}
}
