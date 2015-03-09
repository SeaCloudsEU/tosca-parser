package eu.seaclouds.toscaparser.nodes;

public class StopPauseTerminate extends Operation {

	private boolean hasStop;
	private boolean hasPause;
	private boolean hasTerminate;
	
	
	
	
	
	public StopPauseTerminate() {
		super();
	}
	
	
	public StopPauseTerminate(boolean hasStop, boolean hasPause,
			boolean hasTerminate) {
		super();
		this.hasStop = hasStop;
		this.hasPause = hasPause;
		this.hasTerminate = hasTerminate;
	}
	
	
	public boolean getHasStop() {
		return hasStop;
	}
	public void setHasStop(boolean hasStop) {
		this.hasStop = hasStop;
	}
	public boolean getHasPause() {
		return hasPause;
	}
	public void setHasPause(boolean hasPause) {
		this.hasPause = hasPause;
	}
	public boolean getHasTerminate() {
		return hasTerminate;
	}
	public void setHasTerminate(boolean hasTerminate) {
		this.hasTerminate = hasTerminate;
	}
	
	
}
