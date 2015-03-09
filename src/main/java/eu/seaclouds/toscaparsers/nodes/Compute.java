package eu.seaclouds.toscaparsers.nodes;

public class Compute {

	private String message="noStart";
	
	private int times=1;

	public Compute(){}
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
	
	@Override
	public String toString(){
		String output="";
		for(int i=0; i<times; i++){
			output+=message;
		}
		
		return output;
	}
	

	
	
}
