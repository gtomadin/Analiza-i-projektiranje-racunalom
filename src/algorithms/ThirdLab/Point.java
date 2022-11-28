package algorithms.ThirdLab;

import matrix.Matrix;

public class Point extends Matrix {
	
	// constructor: creating a point using a double value
	public Point(double value) throws Exception {
		super(value);
	}
	
	// constructor: creating a point using a double[] values
	public Point(double[] values) throws Exception {
		super(values);
	}
	
	// constructor: creating a point using a matrix
	public Point(Matrix matrix) throws Exception {
		super(matrix);
		if(matrix.getNumberOfRows() > 1) {
			throw new Exception("A point can not have more then 1 row");
		}
	}
	
	// constructor: creating a point using a point
	public Point(Point point) throws Exception {
		super(1, point.getNumberOfColumns());
		
		for(int i=0; i<point.getNumberOfColumns(); ++i) {
			super.setValueAt(0, i, point.getValueAt(0, i));
		}
		
	}
	
	@Override
	public double getValueAt(int rowIndex, int columnIndex) throws Exception {
		
		if(rowIndex != 0) {
			throw new Exception("Row index must be zero");
		}
		
		return super.getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public void setValueAt(int rowIndex, int columnIndex, double value) throws Exception {
		
		if(rowIndex != 0) {
			throw new Exception("Row index must be zero");
		}
		
		super.setValueAt(rowIndex, columnIndex, value);
	}
	
	public double getNorm() throws Exception {
		double norm = 0;
		
		for(int i = 0; i<this.getNumberOfColumns();++i) {
			double x = this.getValueAt(0, i);
			norm += x * x;
		}
		
		return Math.sqrt(norm);
	}
	
	public Point normalizePoint() throws Exception {
		double[] newValues = new double[this.getNumberOfColumns()];
		double norm = getNorm();
		
		for(int i=0;i<this.getNumberOfColumns();++i) {
			newValues[i] = this.getValueAt(0, i) / norm;
		}
		
		return new Point(newValues);
	}
}
