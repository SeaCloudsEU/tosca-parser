package seaclouds.utils.toscamodel;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by pq on 16/04/2015.
 */
public class ToscaTest {
    private static final Logger logger = Logger.getLogger("logger");

    @Test
    void Test1(){
        //workflow to write a Tosca file
        IToscaEnvironment env = Tosca.newEnvironment();
        //load SeaClouds TOSCA types (such as seaclouds.nodes.compute) from file, or generate them with a procedure
        try {
            FileReader inputFile = new FileReader("seaclouds_types.yaml");
            env.readFile(inputFile, true);
        } catch (FileNotFoundException e) {
            return;
        }
        //get the node type
        INodeType seacloudsNodesCompute = (INodeType) env.getNamedEntity("seaclouds.nodes.compute");
        ITypeScalarUnit scalarUnit = (ITypeScalarUnit) env.getNamedEntity("scalar-unit");

        //for each cloud offering:
        {
            //create an extension type
            String name = "amazon.ec2.somenode";
            Map<String, Object> attributes = new HashMap<>();
            //no need to extend properties or constraints, so we could use "null" here
            ISchemaDefinition schema = env.newSchema("",seacloudsNodesCompute);
            //fill in the map
            attributes.put("cpus", 4);
            attributes.put("filesystem", "ntfs");
            Map<String,Object> network = new HashMap<>();
            IValueScalarUnit bandwidth = scalarUnit.instantiate(4,"MB");
            network.put("bandwidth",bandwidth);
            network.put("numberofips",2);
            attributes.put("network", network);
            env.newNodeType(name, schema, attributes);
        }

        //write the environment into a new file
        Writer outputFile = null;
        env.writeFile(outputFile);

    }
}
