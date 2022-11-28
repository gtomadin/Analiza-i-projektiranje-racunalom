package matrix;

import java.io.BufferedReader;
import java.io.FileReader;



public class MatrixReader {
	
	
	
	// constructor
	public MatrixReader() {}
	
	// method that returns a matrix from the text file
	public Matrix readFile(String file) {
		int NumberOfRows = 0;
		int NumberOfColumns = 0;
		
		// get the number of rows and columns from the fist reading
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line;
			
			while((line = br.readLine()) != null) {
					String[] row = line.split("\\s+");
					NumberOfRows++;
					if(NumberOfColumns < row.length) {
						NumberOfColumns = row.length;
					}
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Exception: reading number of rows and columns");
		}
		
		// creating a zero matrix using the number of rows and columns
		Matrix matrix = null;
		try {
			matrix = new Matrix(NumberOfRows, NumberOfColumns);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// reading all the values in to the matrix
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line;
			int numRow = 0;
			
			while((line = br.readLine()) != null) {
				String[] row = line.split("\\s+");
				
				for(int i=0; i<row.length; ++i) {
					matrix.setValueAt(numRow, i, Double.parseDouble(row[i]));
				}
				
				numRow++;	
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Exception: reading matrix");
		}
		
		return matrix;
	}
}
