package matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	
	private List<List<Double>> matrix;		// defining a matrix with a list of lists 
	private int NumberOfRows;				// defining number of matrix rows
	private int NumberOfColumns;			// defining number of matrix columns
	private double epsilon = 1e-9; 
	
	// constructor: creating a matrix using a number of rows and columns 
	public Matrix(int NumberOfRows, int NumberOfColumns) throws Exception {
		if(NumberOfRows>0) {
			this.NumberOfRows = NumberOfRows;
		}else {
			throw new Exception("The number of rows must be a positive number!");
		}
		
		if(NumberOfColumns>0) {
			this.NumberOfColumns = NumberOfColumns;
		}else {
			throw new Exception("The number of columns must be a positive number!");
		}
		
			initMatrix(); // initializing the matrix
			
	}
	
	// method that create a zero matrix with the dimensions given in the constructor method
	private void initMatrix() {
		matrix = new ArrayList<>();						// initializing matrix with a array list
		for(int i=0; i<NumberOfRows; ++i) {
			List<Double> row = new ArrayList<>(); 		// initializing array list for every row
			for(int j=0; j<NumberOfColumns; ++j) {
				row.add(0.);							// adding a zero for every column
			}
			matrix.add(row);
		}
	}
	
	// constructor: creating a matrix using a double[][] values
	public Matrix(double[][] values) throws Exception {
		if(values != null) {
			this.NumberOfRows = values.length;
			this.NumberOfColumns = values[0].length;
			initMatrix();
			
			for(int i=0;i<NumberOfRows;++i) {
				for(int j=0;j<NumberOfColumns;++j) {
					this.setValueAt(i, j, values[i][j]);
				}
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	// constructor: creating a matrix using a double[] values
	public Matrix(double[] values) throws Exception {
		if(values != null) {
			this.NumberOfRows = 1;
			this.NumberOfColumns = values.length;
			initMatrix();
			for(int i=0; i<NumberOfColumns; ++i) {
				setValueAt(0, i, values[i]);
			}
		}else {
			throw new NullPointerException();
		}
	}
	// constructor: creating a matrix using double value
	public Matrix(double value) throws Exception {
		
		this.NumberOfRows = 1;
		this.NumberOfColumns = 1;
		initMatrix();
		setValueAt(0, 0, value);
	}
	
	public Matrix(Matrix A) throws Exception {
		this(A.NumberOfRows, A.NumberOfColumns);
		initMatrix();
		for(int i=0; i<A.getNumberOfRows(); ++i) {
			for(int j=0; j<A.getNumberOfColumns(); ++j) {
				this.setValueAt(i, j, A.getValueAt(i, j));
			}
		}
	}
	
	// method that returns the number of rows in the matrix
	public int getNumberOfRows() {
		return NumberOfRows;
	}
	
	// method that sets the number of rows in the matrix
	public void setNumberOfRows(int numberOfRows) {
		NumberOfRows = numberOfRows;
	}
	
	// method that returns the number of columns in the matrix
	public int getNumberOfColumns() {
		return NumberOfColumns;
	}
	
	// method that sets the number of columns in the matrix
	public void setNumberOfColumns(int numberOfColumns) {
		NumberOfColumns = numberOfColumns;
	}
	

	// method that returns the value at the position that is given by the row and column number
	public double getValueAt(int rowIndex, int columnIndex) throws Exception {
		if(isOutofRowIndex(rowIndex)) {
			throw new Exception("The row index must be between 0 and " + getNumberOfRows());
		}else if(isOutofColumnIndex(columnIndex)) {
			throw new Exception("The column index must be between 0 and " + getNumberOfColumns());
		}else {
			return matrix.get(rowIndex).get(columnIndex);
		}
	}
	
	// method that sets the given value at the position that is given by the row and column number
	public void setValueAt(int rowIndex, int columnIndex, double value) throws Exception {
		if(isOutofRowIndex(rowIndex)) {
			throw new Exception("The row index must be between 0 and " + getNumberOfRows());
		}else if(isOutofColumnIndex(columnIndex)) {
			throw new Exception("The column index must be between 0 and " + getNumberOfColumns());
		}else {
			List<Double> row = matrix.get(rowIndex);
			row.set(columnIndex, value);
			matrix.set(rowIndex, row);
		}
		
	}
	
	// method that returns true if the number of rows or the number of columns is zero
	public boolean isDimensionZero() {
		return getNumberOfRows() == 0 || getNumberOfColumns() == 0;
	}
	
	// method that returns true if this matrix has the same size as the matrix A   
	public boolean isSameSize(Matrix A) {
		return getNumberOfRows() == A.getNumberOfRows() && getNumberOfColumns() == A.getNumberOfColumns();
	}
	
	// method that returns true if the number of rows is equal to the number of columns 
	public boolean isMatrixSquare() {
		return getNumberOfRows() == getNumberOfColumns();
	}
	
	// method that returns true if value a and value b are equal 
	public boolean isSameValue(double a, double b) {
		return Math.abs(a - b) < epsilon;
	}
	
	// method that returns a matrix in a form of a string
	@Override
	public String toString() {
		if(!isDimensionZero()) {
			String stringMatrix = "";
			
			for(int i=0;i<NumberOfRows;++i) {
				for(int j=0;j<NumberOfColumns;++j) {
			
					try {
						stringMatrix += getValueAt(i, j) + " ";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!(i == NumberOfRows - 1)) {
					stringMatrix += "\n";
				}
			}
			return stringMatrix;
		}
		return "Matrix is null";
		
	}
	
	public String toStringRound() {
		if(!isDimensionZero()) {
			String stringMatrix = "";
			
			for(int i=0;i<NumberOfRows;++i) {
				for(int j=0;j<NumberOfColumns;++j) {
			
					try {
						double t = getValueAt(i, j);
						t = t*100000;
						t = Math.round(t) / 100000;
						stringMatrix += t + " ";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!(i == NumberOfRows - 1)) {
					stringMatrix += "\n";
				}
			}
			return stringMatrix;
		}
		return "Matrix is null";
	
	}
	
	// method that returns true if a object is equal to this 
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Matrix) {
			Matrix new_matrix = (Matrix) obj;
			if(isSameSize(new_matrix)) {
				for(int i=0; i<getNumberOfRows(); ++i) {
					for(int j=0; j<getNumberOfColumns(); ++j) {
						try {
							if(!isSameValue(this.getValueAt(i, j), new_matrix.getValueAt(i, j))) {
								return false;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				return true;				
			}
		}
		return false;
	}
	
	// method that adds matrix A to this matrix
	public void addAssigmentOperator(Matrix A) throws Exception {
		if(isSameSize(A)) {
			Matrix B = MatrixOperations.matrix_Addition(this, A);	// creating matrix using the method matrix_Addition
			MatrixOperations.copyValues(this, B); 					// copying the values from the new matrix to this
		}
//		System.out.println(this.getNumberOfRows() + " " + this.getNumberOfColumns());
//		System.out.println(A.getNumberOfRows() + " " + A.getNumberOfColumns());
		throw new Exception("Two matrices that must be the same size to add");
	}
 	
 	// method that subs matrix A to this matrix
	public void subAssigmentOperator(Matrix A) throws Exception {
		if(isSameSize(A)){
			Matrix B = MatrixOperations.matrix_Subtraction(this, A);	// creating matrix using the method matrix_Subtraction	
			MatrixOperations.copyValues(this, B);						// copying the values from the new matrix to this
		}
		throw new Exception("Two matrices that must be the same size to subtract");
	}

	// method that remove a row from the matrix
	public void removeRow(int rowIndex) throws Exception {
		if(!isOutofRowIndex(rowIndex)) {
			rowIndex--;
			matrix.remove(rowIndex);
			setNumberOfRows(getNumberOfRows()-1);
		}else {
			throw new Exception("The row index must be between 0 and " + getNumberOfRows());
		}
	}
	
	// method that remove a column from the matrix
	public void removeColumn(int columnIndex) throws Exception {
		if(!isOutofColumnIndex(columnIndex)) {
			columnIndex--;
			for(int i=0; i< getNumberOfRows(); ++i) {
				matrix.get(i).remove(columnIndex);
			}
			setNumberOfColumns(getNumberOfColumns()-1);
		}else {
			throw new Exception("The column index must be between 0 and " + getNumberOfColumns());
		}
	}
	
	// method that switches two matrix rows
	public void switchRows(int rowA, int rowB) throws Exception {
		if(isOutofRowIndex(rowA)) {
			
			throw new Exception("The rowA index must be between 0 and " + getNumberOfRows());
		
		}else if(isOutofRowIndex(rowB)) {
		
			throw new Exception("The rowB index must be between 0 and " + getNumberOfRows());
		
		} else {
			double help;
			for(int i=0; i<getNumberOfRows(); ++i) {
				help = getValueAt(rowA, i);
				setValueAt(rowA, i, getValueAt(rowB, i));
				setValueAt(rowB, i, help);
			}
		}
	}
	
	// method that switches two matrix columns
	public void switchColumn(int columnA, int columnB) throws Exception {
		if(isOutofColumnIndex(columnA)) {
			
			throw new Exception("The columnA index must be between 0 and " + getNumberOfColumns());
		
		}else if(isOutofColumnIndex(columnB)) {
		
			throw new Exception("The columnB index must be between 0 and " + getNumberOfColumns());
		
		} else {
			
			double help;
			for(int i=0; i<getNumberOfColumns(); ++i) {
				help = getValueAt(i, columnA);
				setValueAt(i, columnA, getValueAt(i, columnB));
				setValueAt(i, columnB, help);
			}
		}
	}
	
	// method that returns the values of the selected row in form of a Matrix object
	public Matrix getRow(int rowIndex) throws Exception {
		if(!isOutofRowIndex(rowIndex)) {
			Matrix RowVector = new Matrix(1, this.getNumberOfColumns());
			for(int i=0; i<this.getNumberOfColumns(); ++i) {
				RowVector.setValueAt(0, i, this.getValueAt(rowIndex, i));
			}
			return RowVector;
		}else {
			throw new Exception("The row index must be between 0 and " + getNumberOfRows());
		}
	}
	
	// method that returns the values of the selected column in form of a Matrix object
	public Matrix getColumn(int columnIndex) throws Exception {
		if(!isOutofColumnIndex(columnIndex)) {
			Matrix ColumnVector = new Matrix(this.getNumberOfRows(), 1);
			for(int i=0; i<this.getNumberOfRows(); ++i) {
				ColumnVector.setValueAt(i, 0, this.getValueAt(i, columnIndex));
			}
			return ColumnVector;
		}else {
			throw new Exception("The column index must be between 0 and " + getNumberOfColumns());
		}
	}
	
	// method that changes the values of the selected row with the input values
	public void changeRowValues(Matrix rowVector, int rowIndex) throws Exception {
		if(!isOutofRowIndex(rowIndex) && this.getNumberOfColumns() == rowVector.getNumberOfColumns()) {
			for(int i=0; i<this.getNumberOfColumns(); ++i) {
				this.setValueAt(rowIndex, i, rowVector.getValueAt(0, i));
			}
		}else {
			throw new Exception("The row index must be between 0 and " + getNumberOfRows());
		}
	}
	
	// method that changes the values of the selected column with the input values
	public void changeColumnValues(Matrix columnVector, int columnIndex) throws Exception {
		if(!isOutofColumnIndex(columnIndex) && this.getNumberOfRows() == columnVector.getNumberOfRows()) {
			for(int i=0; i<this.getNumberOfRows(); ++i) {
				this.setValueAt(i, columnIndex, columnVector.getValueAt(i, 0));
			}
		}else {
			throw new Exception("The column index must be between 0 and " + getNumberOfColumns());
		}
	}
	
	// method that checks if the selected row index is present in the matrix 
	public boolean isOutofRowIndex(int rowIndex) {
		return rowIndex < 0 && rowIndex >= getNumberOfRows();
	}
	
	// method that checks if the selected row index is present in the matrix
	public boolean isOutofColumnIndex(int columnIndex) {
		return columnIndex < 0 && columnIndex >= getNumberOfColumns();
	}
}
