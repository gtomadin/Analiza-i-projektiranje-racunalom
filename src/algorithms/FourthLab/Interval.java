package algorithms.FourthLab;

public class Interval {
	
	private double left;
	private double right;
	
	public Interval(double left, double right) {
		super();
		this.left = left;
		this.right = right;
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Interval [left=" + left + ", right=" + right + "]";
	}
}
