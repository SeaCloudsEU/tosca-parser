package eu.seaclouds.toscaparser.nodes;

public class Region extends Property {

	private String continent;
	private String country;
	private String region;
	
	
	
	
	
	
	public Region() {
		super();
	}
	
	public Region(String continent, String country, String region) {
		super();
		this.continent = continent;
		this.country = country;
		this.region = region;
	}
	
	
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
	
	
}
