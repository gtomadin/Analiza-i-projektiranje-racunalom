package algorithms.FourthLab.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SelectionBinary {
	
	List<ChromosomeBinary> selectionBinary;
	int k;
	double E = 10e-6;
	
	public SelectionBinary(int k, PopulationBinary populationBinary) {
		this.k = k;
		createSelectionBinary(populationBinary);
	}

	private void createSelectionBinary(PopulationBinary populationBinary) {
		selectionBinary = new ArrayList<>();
		
		Random rand = new Random();
		
		for(int i = 0; i < k; ++i) {
			
			int index = rand.nextInt(populationBinary.getPopulationSize());
			
			selectionBinary.add(populationBinary.getChromosomebBinary(index));
		}
	}
	
	public ChromosomeBinary removeWorst() {
		
		double worstFitness= Double.MAX_VALUE;
		ChromosomeBinary worst = null;
		
		for(int i = 0; i < k; ++i) {
			if(worstFitness - selectionBinary.get(i).getFitness() > E) {
				worstFitness = selectionBinary.get(i).getFitness();
				worst = selectionBinary.get(i);
			}
		}
		
		selectionBinary.remove(worst);
		
		return worst;
	}
	
	public int getSize() {
		return selectionBinary.size();
	}
	
	public ChromosomeBinary getChromosomeBinary(int index) {
		return selectionBinary.get(index);
	}
	
	@Override
	public String toString() {
		String s = "Selection: \n";
		for(ChromosomeBinary ch : selectionBinary) {
			s += ch.toString() + "\n";
		}
		return s;
	}
	
}
