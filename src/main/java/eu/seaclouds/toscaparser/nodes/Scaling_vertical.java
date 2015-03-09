package eu.seaclouds.toscaparser.nodes;

public class Scaling_vertical extends Operation {

	private boolean hasScalingVertical;
	
	

	public Scaling_vertical() {
		super();
	}

	public Scaling_vertical(boolean hasScalingVertical) {
		super();
		this.hasScalingVertical = hasScalingVertical;
	}

	public boolean isHasScalingVertical() {
		return hasScalingVertical;
	}

	public void setHasScalingVertical(boolean hasScalingVertical) {
		this.hasScalingVertical = hasScalingVertical;
	}
	
	@Override
	public String toString(){return ""+hasScalingVertical;}
		
	
	
}
