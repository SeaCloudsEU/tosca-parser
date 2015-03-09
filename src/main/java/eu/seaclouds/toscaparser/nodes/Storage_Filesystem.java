package eu.seaclouds.toscaparser.nodes;

public class Storage_Filesystem extends Property {

	private String filesystem;
	
	

	public Storage_Filesystem() {
		super();
	}

	public Storage_Filesystem(String filesystem) {
		super();
		this.filesystem = filesystem;
	}

	public String getFilesystem() {
		return filesystem;
	}

	public void setFilesystem(String filesystem) {
		this.filesystem = filesystem;
	}
	
	
	
}
