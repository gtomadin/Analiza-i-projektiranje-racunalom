package functions.FourthLab;

import algorithms.FourthLab.Point;

public abstract class AbstractFunction {
	
	
	int callCounter; // defining the number of calls
	
	// constructor: set callCounter on 0 
	public AbstractFunction() {
		callCounter = 0;
	}
	
	// method that returns the value of the function for a given point
	public double getFunctionValue(Point point) throws Exception {
		callCounter++;
		
		return CalculateFunctionValue(point);
	}
	
	// method that calculate the value of the function in a given point 
	protected double CalculateFunctionValue(Point point) throws Exception {
		throw new Exception("Method not overrided");
	}
	
	// method that returns the value of the call counter
	public int getCallCounter() {
		return callCounter;
	}
	
	// method that set the call counter on 0 
	public void setCallCounter() {
		this.callCounter = 0;
	}
	
	
	
	
}
