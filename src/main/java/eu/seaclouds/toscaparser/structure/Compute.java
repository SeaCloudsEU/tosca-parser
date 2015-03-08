package eu.seaclouds.toscaparser.structure;

public class Compute {

	private String message;
	
	private int times;

	public Compute(String message, int times) {
		this.message = message;
		this.times = times;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	

	
	
}
