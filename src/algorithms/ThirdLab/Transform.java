package algorithms.ThirdLab;

import java.util.List;

import functions.ThirdLab.AbstractFunction;

public class Transform {
	
	// defining the constants
	private double E = 1e-6;
	private Point resultPoint;
	
	// constructor: creating a Transform algorithm
	public Transform(AbstractFunction function, Point point, List<AbstractImplicitRestriction> inequality, List<AbstractImplicitRestriction> equality) throws Exception {
		
		if(function != null && point != null) {
			initTransforamtion(function, point, inequality, equality);
		}
	}
	
	// method that calculate the result point using the starting point and equality and inequality restrictions
	private void initTransforamtion(AbstractFunction function, Point point,
			List<AbstractImplicitRestriction> inequality, List<AbstractImplicitRestriction> equality) throws Exception {
	
	double t = 1; // defining the initial t value
	
	Point previousPoint = new Point(point);
	
//	System.out.println("Previous Point: " + previousPoint);
	
	// checking the inequality implicit restrictions
	// if they are not satisfied get new point
	for(int i = 0; i<inequality.size(); ++i) {
		if(!inequality.get(i).validate(previousPoint)) {
			previousPoint = getInnerPoint(point, inequality);
			System.out.println("New start point: " + previousPoint);
		}
	}
//	System.out.println("NewPrevious Point: " + previousPoint);
	
	int loopCounter = 0; // initializing the loop counter
	
	do {
		double innerT = t;
		
		// defining the transform function
		AbstractFunction transformFunction = new AbstractFunction() {
			
			@Override
			protected double CalculateFunctionValue(Point point) throws Exception {
				double Fxvalue = function.getFunctionValue(point);
//				System.out.println(Fxvalue);
				
				
				// -1/t * sum(log(g(x)))
				if(inequality != null) {
					for(int i = 0; i<inequality.size(); ++i) {
							
						double restictions = inequality.get(i).getRestrictionValue(point);
							
						if(restictions <= 0) {
							restictions = Double.MAX_VALUE;
						}else {
							
							restictions = -Math.log(restictions) / innerT;
						}
						Fxvalue += restictions;
					}
				}
				
				// t * sum(h(x)^2)
				if(equality != null) {
					for(int i = 0; i<equality.size(); ++i) {
						
						double restrictions = equality.get(i).getRestrictionValue(point);
						
						restrictions = innerT * restrictions * restrictions;
						
						Fxvalue += restrictions;
					}
				}
				
				// F = F - 1/t * sum(log(g(x))) + t * sum(h(x)^2)
				return Fxvalue;
			}
		};
		
		// uning HookeJeeves to calculate the new point
		HookeJeeves hj = new HookeJeeves(transformFunction, previousPoint);
		Point currentPoint = hj.getResultPoint();
		
//		System.out.println("CurrentPoint: " + currentPoint);
		
		t *= 10; // new value of T
		
		// calculating the difference between current and previous point
		double sum = 0;
		for(int i=0; i<currentPoint.getNumberOfColumns(); ++i) {
			sum += Math.abs(currentPoint.getValueAt(0, i) - previousPoint.getValueAt(0, i));
		}
		
		double result = sum / currentPoint.getNumberOfColumns();
		
		// if they are the same define the result point
		if(Math.sqrt(result) < E) {
			resultPoint = currentPoint;
			break;
		}
		++loopCounter;
		
		// if is an infinite loop throw an exception 
		if(loopCounter  == 10000) {
			throw new Exception("Impossible to calculate Simplex");
		}
	
		previousPoint = currentPoint; // setting the the current point
		
		}while(true);
	
	}

	
	// method that returns the inner point using HookeJavies algorithm and inequality restrictions
	private Point getInnerPoint(Point point, List<AbstractImplicitRestriction> inequality) throws Exception {
		AbstractFunction innerPointFunction = new AbstractFunction() {

			@Override
			protected double CalculateFunctionValue(Point point) throws Exception {
				double sum = 0;
				for(int i=0 ; i< inequality.size(); ++i) {
					double newvalue;
				
					if(inequality.get(i).getRestrictionValue(point) < 0) {
						newvalue = inequality.get(i).getRestrictionValue(point);
					}else {
						newvalue = 0;
					}
					
					sum -= newvalue;
			
				}
				return sum;
			}
			
			
		};
		
		HookeJeeves hj = new HookeJeeves(innerPointFunction, point);	
		
		return hj.getResultPoint();
			
	}
	
	// method that returns the result point
	public Point getResultPoint() {
		return resultPoint;
	}
	
	// class that defines Implicit Restrictions
	public static abstract class AbstractImplicitRestriction {
			
		public boolean validate(Point point) throws Exception {
			throw new Exception("Method not overrided");
		}
			
		public double getRestrictionValue(Point point) throws Exception {
			throw new Exception("Method not overrided");
		}
	}
}
