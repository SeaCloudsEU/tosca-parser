package eu.seaclouds.toscaparser.nodes;

public class Storage_type extends Property {

	private String type;
	
	

	public Storage_type() {
		super();
	}

	public Storage_type(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){return type;}
		
	
	
	
}
