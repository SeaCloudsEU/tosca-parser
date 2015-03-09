package eu.seaclouds.toscaparser.nodes;

public class Outbound_bandwidth extends Networking {

	private double averageBandwidth;
	private double upperBoundBandwidth;
	private double lowerBoundBandwidth;
	
	public Outbound_bandwidth(){
		super();
	}
	
	
	
	public Outbound_bandwidth(double averageBandwidth,
			double upperBoundBandwidth, double lowerBoundBandwidth) {
		super();
		this.averageBandwidth = averageBandwidth;
		this.upperBoundBandwidth = upperBoundBandwidth;
		this.lowerBoundBandwidth = lowerBoundBandwidth;
	}
	
	
	public double getAverageBandwidth() {
		return averageBandwidth;
	}
	public void setAverageBandwidth(double averageBandwidth) {
		this.averageBandwidth = averageBandwidth;
	}
	public double getUpperBoundBandwidth() {
		return upperBoundBandwidth;
	}
	public void setUpperBoundBandwidth(double upperBoundBandwidth) {
		this.upperBoundBandwidth = upperBoundBandwidth;
	}
	public double getLowerBoundBandwidth() {
		return lowerBoundBandwidth;
	}
	public void setLowerBoundBandwidth(double lowerBoundBandwidth) {
		this.lowerBoundBandwidth = lowerBoundBandwidth;
	}
	
}
