package eu.seaclouds.toscaparser.nodes;

public class Number_of_ipv4 extends Networking {

	private int numIP;
	
	public Number_of_ipv4(){
		super();
	}

	public Number_of_ipv4(int numIP) {
		super();
		this.numIP = numIP;
	}

	public int getNumIP() {
		return numIP;
	}

	public void setNumIP(int numIP) {
		this.numIP = numIP;
	}
	
	
}
