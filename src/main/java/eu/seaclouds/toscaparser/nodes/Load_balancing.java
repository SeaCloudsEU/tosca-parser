package eu.seaclouds.toscaparser.nodes;

public class Load_balancing extends Networking {

private boolean hasLoadBalancing;

public Load_balancing(){
	super();
}

public Load_balancing(boolean hasLoadBalancing) {
	super();
	this.hasLoadBalancing = hasLoadBalancing;
}

public boolean isHasLoadBalancing() {
	return hasLoadBalancing;
}

public void setHasLoadBalancing(boolean hasLoadBalancing) {
	this.hasLoadBalancing = hasLoadBalancing;
}

@Override
public String toString(){return ""+hasLoadBalancing;}
	
}
