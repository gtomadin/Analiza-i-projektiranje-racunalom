package algorithms.FourthLab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Selection {
	
	List<Chromosome> selection;
	int k;
	double E = 10e-6;
	
	public Selection() {		
	}

	public Selection(int k, Population population) {
		this.k = k;
		createSelection(population);
	}

	private void createSelection(Population population) {
		selection = new ArrayList<Chromosome>();
		
		Random rand = new Random();
		
		for(int i = 0; i < k; ++i){
			
			int index = rand.nextInt(population.getPopulationSize());
			
			selection.add(population.getChromosome(index));
		}
	}

	public Chromosome removeWorst() {
		
		double worstFitness= Double.MAX_VALUE;
		Chromosome worst = null;
		
		for(int i = 0; i < k; ++i) {
			if(worstFitness - selection.get(i).getFitness() > E) {
				worstFitness = selection.get(i).getFitness();
				worst = selection.get(i);
			}
		}
		
		selection.remove(worst);
		
		return worst;
	}
	
	public int getSize() {
		return selection.size();
	}
	
	public Chromosome getChromosome(int index) {
		return selection.get(index);
	}
	
	@Override
	public String toString() {
		String s = "Selection: \n";
		for(Chromosome ch : selection) {
			s += ch.toString() + "\n";
		}
		return s;
	}
}
