package functions.ThirdLab;

import algorithms.ThirdLab.Point;

public abstract class AbstractFunction {
	int FunctionValueCallCounter; // defining the number of calls
	

	// constructor: set callCounter on 0 
	public AbstractFunction() {
		FunctionValueCallCounter = 0;
		
	}
	
	public int getFunctionValueCallCounter() {
		return FunctionValueCallCounter;
	}

	public void setFunctionValueCallCounter() {
		FunctionValueCallCounter = 0;
	}
	
	
	// method that returns the value of the function for a given point
	public double getFunctionValue(Point point) throws Exception {
		FunctionValueCallCounter++;
		
		return CalculateFunctionValue(point);
	}
	
	// method that calculate the value of the function in a given point 
	protected double CalculateFunctionValue(Point point) throws Exception {
		throw new Exception("Method not overrided");
	}

}
