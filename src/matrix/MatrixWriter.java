package matrix;

import java.io.FileWriter;
import java.io.IOException;

public class MatrixWriter {
	
	
	// constructor
	public MatrixWriter() {}
	
	// method that write a matrix in the given file
	public void writeFile(Matrix matrix, String file) {
		
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(matrix.toString());
			writer.close();
		} catch (IOException e) {
			System.err.println("Exception: writting matrix");
		}
		
	}
	
}
