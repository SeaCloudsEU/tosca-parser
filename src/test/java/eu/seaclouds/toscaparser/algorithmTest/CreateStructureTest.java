package eu.seaclouds.toscaparser.algorithmTest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.junit.Assert; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import eu.seaclouds.toscaparser.nodes.Compute;


public class CreateStructureTest {

	static Logger log;
	private static final String yamlfilename = "/target/compute.yaml";
	
	
	private String stringyaml;
	
	@BeforeClass
	public void createSingleComputeObjectYAML() {
		
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(CreateStructureTest.class);
		
		log.info("Starting TEST CreateStructure");
		HashMap<String,Compute> map = new HashMap<String,Compute>();
		
		Compute comp2 = new Compute("firstCompute",2);
		map.put("firstCompute", comp2);
		
		Yaml parser = new Yaml();
		stringyaml = parser.dump(comp2);
		//stringyaml = parser.dump(map);
		
		final String dir = System.getProperty("user.dir");
		log.debug("Saving files in folder = " + dir);
		
		saveFile(dir+yamlfilename, stringyaml);
		
	}

	


	@Test
	public void testCreationObject(){
		
		
		log.info("Starging testCreatinObject test");

		Constructor constructor = new Constructor(Compute.class);//Compute.class is root
		//TypeDescription computeDescription = new TypeDescription(Compute.class);
		//carDescription.putListPropertyType("properties", Property.class);
		// td.putMapPropertyType("map",Suit.class,Object.class);
		//constructor.addTypeDescription(computeDescription);
		Yaml parser = new Yaml(constructor);
		
		Compute map =  (Compute) parser.load(stringyaml);
	
		log.info("Original content was " + stringyaml + " and map is " + map.toString());
		Assert.assertTrue("The element created from the yaml null. Original content was: " + stringyaml + System.getProperty("line.separator") + " and element is: " + map.toString(), map!=null);
		log.debug("MapToString is:" + map.toString());
	}
	
	
	@Test
	public void Test2objectsInList(){
		
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(CreateStructureTest.class);
		
		log.info("Starting TEST CreateStructure");
		HashMap<String,Compute> map = new HashMap<String,Compute>();
		
		Compute comp2 = new Compute("firstCompute",2);
		map.put("firstCompute", comp2);
		
		Yaml parser = new Yaml();
		stringyaml = parser.dump(comp2);
		//stringyaml = parser.dump(map);
		
		final String dir = System.getProperty("user.dir");
		log.debug("Saving files in folder = " + dir);
		
		saveFile(dir+yamlfilename, stringyaml);
		
		
	}
	
	
	
	private void saveFile(String outputFilename, String dam) {
		PrintWriter out =null;
		try {
			out = new PrintWriter(outputFilename);
			out.println(dam);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally
		{
		    if ( out != null){
				out.close( );
			}
		}
		
	}
	
}
