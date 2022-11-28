package algorithms.FourthLab.binary;

import java.util.ArrayList;
import java.util.List;

import algorithms.FourthLab.Point;
import functions.FourthLab.AbstractFunction;
import algorithms.FourthLab.Interval;

public class ChromosomeBinary {
	
	private Point point;
	private double Fvalue;
	private double fitness;
	private int[] pointInt;
	private List<BinaryString> pointBinary;
	private Interval interval;
	
	public ChromosomeBinary(Point point, int n, Interval interval) throws Exception {
		this.point = point;
		
		this.pointInt = new int[point.getNumberOfColumns()];
		
		this.pointBinary = new ArrayList<>();
		
		this.interval = interval;
		
		fromDoubleToInt(n);
		fromIntToBinary(n);
	}
	
	public ChromosomeBinary(List<BinaryString> pointBinary, int n, Interval interval) throws Exception {
		this.pointBinary = pointBinary;
		
		this.pointInt = new int[pointBinary.size()];
		
		this.interval = interval;
		
		fromBinaryToInt(n);
		fromIntToDouble(n);
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
	
	public int[] getPointInt() {
		return pointInt;
	}

	public void setPointInt(int[] pointInt) {
		this.pointInt = pointInt;
	}

	public List<BinaryString> getPointBinary() {
		return pointBinary;
	}

	public void setPointBinary(List<BinaryString> pointBinary) {
		this.pointBinary = pointBinary;
	}

	public void calculateFvalue(AbstractFunction function) throws Exception {
		Fvalue = function.getFunctionValue(point);
	}
	
	public void fromDoubleToInt(int n) throws Exception {
		
		
		double dg = interval.getLeft();
		double gg = interval.getRight();
		
		for(int i=0;i<point.getNumberOfColumns(); ++i) {
			
			double x = point.getValueAt(0, i);
			
			double b = (x - dg) / (gg - dg) * Math.pow(2, n);
			//System.out.println(b);
			int bi = (int) Math.round(b);
			
		
			pointInt[i] = bi;
		}
	}
	
	public void fromIntToDouble(int n) throws Exception {
		
		
		double dg = interval.getLeft();
		double gg = interval.getRight();
		
		double[] values = new double[pointInt.length];
		
		for(int i=0;i<pointInt.length; ++i) {
			
			double b = pointInt[i];
			
			double x = dg + b * (gg - dg) / Math.pow(2, n);
			
			values[i] = x;
			
		}
		
		point = new Point(values);
	}
	
	public void fromIntToBinary(int n) {
		
		for(int i=0;i<pointInt.length;++i) {
			int value = pointInt[i];
			BinaryString sb = new BinaryString(value, n);
			pointBinary.add(sb);
		}
	}
	
	public void fromBinaryToInt(int n) {
		
		for(int i=0;i<pointBinary.size();++i) {
			int value = pointBinary.get(i).toInt();
			pointInt[i] = value;
		}
	}
	
	@Override
	public String toString() {
		String s = "Chromosome [point= ";
		for(int i=0;i<pointBinary.size();++i) {
			try {
				s += pointBinary.get(i) + " : " + pointInt[i] + " : " + point.getValueAt(0, i) + ", ";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s += "Fvalue=" + Fvalue + ", fitness=" + fitness + "]";
		
		return s;
	}
	
	public String intPoint() {
		String s = "(";
		for(int i=0;i<pointInt.length; ++i) {
			s +=  + pointInt[i];
			if(i != pointInt.length -1) {
				s += ", ";
			}
		}
		s += ")";
		return s;
	}
}
