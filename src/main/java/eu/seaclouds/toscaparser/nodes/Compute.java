package eu.seaclouds.toscaparser.nodes;

import java.util.HashMap;
import java.util.List;

public class Compute {

	private String message="noStart";
	private int times=1;
	
	private List<Property> properties;
	private HashMap<String,Operation> operations;
	

	public Compute(){}
	

	public Compute(String message, int times, List<Property> properties ,HashMap<String,Operation> operations) {
		this.message = message;
		this.times = times;
		this.properties=properties;
		this.operations=operations;
	}
	
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
	
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public HashMap<String,Operation> getOperations() {
		return operations;
	}
	public void setOperations(HashMap<String,Operation> operations) {
		this.operations = operations;
	}
	
	
	
	
	
	
	@Override
	public String toString(){
		String output="";
		for(int i=0; i<times; i++){
			output+=message;
		}
		
		if((properties!=null)&&(operations!=null)){
			return output+"PROPERTIES:"+properties.toString()+"__OPERATIONS:"+operations.toString();
		}
		else{
			return output;
			}
	}
	
	
	

	

	
	
}
