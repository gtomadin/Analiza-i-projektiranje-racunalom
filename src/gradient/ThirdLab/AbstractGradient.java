package gradient.ThirdLab;

import algorithms.ThirdLab.Point;

public abstract class AbstractGradient {
	int GradientValueCallCounter;

	public AbstractGradient() {
		GradientValueCallCounter = 0;
	}
	
	public int getGradientValueCallCounter() {
		return GradientValueCallCounter;
	}

	public void setGradientValueCallCounter() {
		GradientValueCallCounter = 0;
	}
	
	public Point getGradientValue(Point point) throws Exception {
		++GradientValueCallCounter;
		
		return CalculateGradientValue(point);
	}
	
	protected Point CalculateGradientValue(Point point) throws Exception {
		throw new Exception("Method not overrided");
	}
}
