package algorithms.FourthLab.binary;


import algorithms.FourthLab.Interval;
import functions.FourthLab.AbstractFunction;

public class GeneticAlgorithmBinary {
	
	static int k = 3;
	static Interval scalingInterval = new Interval(0, 100);
	static CrossoverEnumBinary crossoverEnumBinary = CrossoverEnumBinary.SINGLEPOINT;
	static MutationEnumBinary mutationEnumBinary = MutationEnumBinary.SIMPLE;
	private double PofMutation = 0.4;
	static double E = 10e-6;
	private ChromosomeBinary result;
	
	
	public GeneticAlgorithmBinary(AbstractFunction function, int PopulationSize, Interval interval, int GenerationNumber, int dimension, int p) throws Exception  {
		
		initGeneticAlgorithmBinary(function, PopulationSize, interval, GenerationNumber, dimension, p);
		
	}
	
	
	public GeneticAlgorithmBinary(AbstractFunction function, int PopulationSize, Interval interval, int GenerationNumber, int dimension, int p, double PofMutation) throws Exception  {
		this.PofMutation = PofMutation;
		initGeneticAlgorithmBinary(function, PopulationSize, interval, GenerationNumber, dimension, p);
		
	}

	private void initGeneticAlgorithmBinary(AbstractFunction function, int populationSize, Interval interval, int generationNumber, int dimension, int p) throws Exception {
		PopulationBinary populationBinary = new PopulationBinary(function, populationSize, interval, dimension, p);
		
		populationBinary.evaluatePopulation(scalingInterval);
		
		//System.out.println(populationBinary.toString());
		
		ChromosomeBinary oldBestChromosomeBinary =  populationBinary.getBestChromosomebBinary();
		
		//System.out.println("best chromosome: " + oldBestChromosomeBinary);
		
		for(int i=0; i < generationNumber; ++i) {
			
			SelectionBinary selectionBinary = new SelectionBinary(k, populationBinary);
			
			//System.out.println(selectionBinary.toString());
			
			ChromosomeBinary worstChromosomeBinary = selectionBinary.removeWorst();
			
			//System.out.println("worst: " + worstChromosomeBinary);
			
			CrossoverBinary crossoverBinary = new CrossoverBinary(crossoverEnumBinary, interval);
			
			ChromosomeBinary newChromosomeBinary = crossoverBinary.makeCrossover(selectionBinary);
			
			//System.out.println("new: " + newChromosomeBinary);
			
			MutationBinary mutationBinary = new MutationBinary(mutationEnumBinary, interval, PofMutation);
			
			ChromosomeBinary newChromosomeBinaryMutated = mutationBinary.makeMutation(newChromosomeBinary);
			
			//System.out.println("new mutated: " +newChromosomeBinaryMutated);
			
			populationBinary.replace(newChromosomeBinaryMutated, worstChromosomeBinary);
			
			populationBinary.evaluatePopulation(scalingInterval);
			
			ChromosomeBinary currentBestChromosomeBinary =  populationBinary.getBestChromosomebBinary();
			
			
			//System.out.println(i + " Old best: " + oldBestChromosomeBinary);
			//System.out.println(i + " New best: " + currentBestChromosomeBinary);
			
			//System.out.println(populationBinary.toString());
			
			if(oldBestChromosomeBinary.getFvalue() > currentBestChromosomeBinary.getFvalue()) {
				oldBestChromosomeBinary = currentBestChromosomeBinary;
				//System.out.println("new best chromosome: " + oldBestChromosomeBinary);
			}
			
			if(oldBestChromosomeBinary.getFvalue() < E) {
				break;
			}
			
		}
		
		//System.out.println(populationBinary.toString());
		
		//System.out.println("Final: " + oldBestChromosomeBinary);
		
		result = oldBestChromosomeBinary;
	}

	public ChromosomeBinary getResult() {
		return result;
	}

	public double getPofMutation() {
		return PofMutation;
	}

	public void setPofMutation(double pofMutation) {
		PofMutation = pofMutation;
	}
}
