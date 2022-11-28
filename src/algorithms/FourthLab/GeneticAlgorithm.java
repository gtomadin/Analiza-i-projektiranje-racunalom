package algorithms.FourthLab;


import functions.FourthLab.AbstractFunction;

public class GeneticAlgorithm {

	
	int k = 3;
	static Interval scalingInterval = new Interval(0, 100);
	static CrossoverEnum crossoverType = CrossoverEnum.ARITHMETIC;
	static MutationEnum mutationType = MutationEnum.GAUSSIAN;
	private double PofMutation = 0.3;
	static double E = 10e-6;
	Chromosome result;
	
	public GeneticAlgorithm(AbstractFunction function, int PopulationSize, Interval interval, int GenerationNumber, int dimension) throws Exception {
		initGeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
		
	}
	
	public GeneticAlgorithm(AbstractFunction function, int PopulationSize, Interval interval, int GenerationNumber, int dimension, double PofMutation) throws Exception {
		this.PofMutation = PofMutation;
		initGeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
		
	}
	
	public GeneticAlgorithm(AbstractFunction function, int PopulationSize, Interval interval, int GenerationNumber, int dimension, int k) throws Exception {
		this.k = k;
		initGeneticAlgorithm(function, PopulationSize, interval, GenerationNumber, dimension);
		
	}
	
	private void initGeneticAlgorithm(AbstractFunction function, int populationSize, Interval interval, int GenerationNumber, int dimension) throws Exception {
		
		//List<E> population = generatePopulation(populationSize, interval);
		
		Population population = new Population(function, populationSize, interval, dimension);
		
		
		//System.out.println(population.toString());
		
		
		
		population.evaluatePopulation(scalingInterval);
		
		//System.out.println(population.toString());
		
		
		
		Chromosome OldBestChromosome =  population.getBestChromosome();
		
		//System.out.println("best chromosome: " + OldBestChromosome);
		
		
		
		
		for(int i = 0; i < GenerationNumber; ++i) {
		
			Selection selection = new Selection(k, population);
			
			//System.out.println(selection.toString());
			
			
			
			Chromosome worstChromoson = selection.removeWorst();
			
			//System.out.println("worst: " + worstChromoson);
			
			//System.out.println(selection.toString());
			
			
			
			Crossover crossover = new Crossover(crossoverType);
			
			Chromosome newChromosome = crossover.makeCrossover(selection);
			
			//System.out.println("new: " + newChromosome);
			
			
			
			Mutation mutation = new Mutation(mutationType, interval, PofMutation);
			
			Chromosome newChromosonMutated = mutation.makeMutation(newChromosome);
			
			//System.out.println("new mutated: " +newChromosonMutated);
			
			
			//change(population, worstchromoson, newchromoson);
			population.replace(newChromosonMutated, worstChromoson);
			
			population.evaluatePopulation(scalingInterval);
			
			//System.out.println(population.toString());
			
			Chromosome CurrentBestChromosome =  population.getBestChromosome();
			
			//System.out.println(i + " Old best: " + OldBestChromosome);
			//System.out.println(i + " New best: " + CurrentBestChromosome);
			
			if(OldBestChromosome.getFvalue() > CurrentBestChromosome.getFvalue()) {
				OldBestChromosome = CurrentBestChromosome;
				//System.out.println("new best chromosome: " + OldBestChromosome);
			}
			
			if(OldBestChromosome.getFvalue() < E) {
				break;
			}
		}
		
		//System.out.println(population.toString());
		
		//System.out.println("Final: " + OldBestChromosome);
		
		result = OldBestChromosome;
	}

	public Chromosome getResult() {
		return result;
	}

	public double getPofMutation() {
		return PofMutation;
	}

	public void setPofMutation(double pofMutation) {
		PofMutation = pofMutation;
	}


	
	
	
	
}
