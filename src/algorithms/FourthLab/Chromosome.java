package algorithms.FourthLab;

import functions.FourthLab.AbstractFunction;

public class Chromosome {
	
	
	private Point point;
	private double Fvalue;
	private double fitness;
	
	public Chromosome(Point point) {
		super();
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	public double getFvalue() {
		return Fvalue;
	}

	public void setFvalue(double fvalue) {
		Fvalue = fvalue;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	
	public void calculateFvalue(AbstractFunction function) throws Exception {
		Fvalue = function.getFunctionValue(point);
	}

	@Override
	public String toString() {
		return "Chromosome [point=" + point + ", Fvalue=" + Fvalue + ", fitness=" + fitness + "]";
	}
	
	
}
