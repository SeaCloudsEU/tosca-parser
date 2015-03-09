package eu.seaclouds.toscaparser.nodes;

public class Scaling_horizontal extends Operation {

	private boolean hasScalingHorizontal;

	
	
	public Scaling_horizontal() {
		super();
	}

	public Scaling_horizontal(boolean hasScalingHorizontal) {
		super();
		this.hasScalingHorizontal = hasScalingHorizontal;
	}

	public boolean isHasScalingHorizontal() {
		return hasScalingHorizontal;
	}

	public void setHasScalingHorizontal(boolean hasScalingHorizontal) {
		this.hasScalingHorizontal = hasScalingHorizontal;
	}
	
	@Override
	public String toString(){return ""+hasScalingHorizontal;}
}
