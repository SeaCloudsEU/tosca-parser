package eu.seaclouds.toscaparser.algorithmTest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import eu.seaclouds.toscaparser.nodes.Compute;
import eu.seaclouds.toscaparser.nodes.Load_balancing;
import eu.seaclouds.toscaparser.nodes.Number_of_ipv4;
import eu.seaclouds.toscaparser.nodes.Operation;
import eu.seaclouds.toscaparser.nodes.Outbound_bandwidth;
import eu.seaclouds.toscaparser.nodes.Property;
import eu.seaclouds.toscaparser.nodes.Region;
import eu.seaclouds.toscaparser.nodes.Scaling_horizontal;
import eu.seaclouds.toscaparser.nodes.Scaling_vertical;
import eu.seaclouds.toscaparser.nodes.StopPauseTerminate;
import eu.seaclouds.toscaparser.nodes.Storage_Filesystem;
import eu.seaclouds.toscaparser.nodes.Storage_type;

public class ToscaNodeComputeTest {

	static Logger log;
	private static final String yamlfilename = "/target/computeFull.yaml";
	private static final String yamlfilenamelist = "/target/computeFullList.yaml";
	private static final String yamlfilenamemap = "/target/computeFullMap.yaml";
	
	
	private String stringyaml;
	
	@BeforeClass
	public void createSingleComputeObjectYAML() {
		
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(ToscaNodeComputeTest.class);
		log.info("Starting TEST CreateStructure");

	}

	


	@Test
	public void testWriteReadCompute(){
		
		stringyaml = createyaml();
		Compute comp= readyaml(stringyaml);
		
		Assert.assertTrue(comp!=null, "Readyaml returned null");
		
		
		
	}




	private Compute readyaml(String stringyaml2) {
		// TODO Auto-generated method stub
		return null;
	}




	private String createyaml() {
		
			//Writing objects
				log.info("STARTING TEST Creating Compute Object into a YAML");

				HashMap<String,Operation> operations = new HashMap<String,Operation>(); 
				operations.put(Scaling_horizontal.class.getCanonicalName(), new Scaling_horizontal(true));
				// We omit the scaling vertical info to see what happens
				//Scaling_vertical sv = new Scaling_vertical(false);
				
				operations.put(StopPauseTerminate.class.getCanonicalName(),new StopPauseTerminate(false,true,true));
				
				ArrayList<Property> properties = new ArrayList<Property>();
				
				properties.add(new Load_balancing(true));
				properties.add(new Outbound_bandwidth(5.0,10.0,2.0));
				properties.add(new Number_of_ipv4(5));
				
				properties.add(new Storage_type("MyStorageType"));
				properties.add(new Storage_Filesystem("My Storage File System (This string has white spaces to see what happens)"));
				properties.add(new Region("Europe","Ireland",""));
				
				
				Compute comp1 = new Compute("firstCompute",2,properties,operations);
				
				Yaml parser = new Yaml();
				stringyaml = parser.dump(comp1);
				
				final String dir = System.getProperty("user.dir");
				log.debug("Saving files in folder = " + dir);
				
				saveFile(dir+yamlfilename, stringyaml);
				
				log.info("STARTING TEST Creating Compute Object into a YAML");
				return stringyaml;
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
