package seaclouds.utils.toscamodel;

import org.junit.Test;
import seaclouds.utils.toscamodel.basictypes.ITypeScalarUnit;
import seaclouds.utils.toscamodel.basictypes.IValueScalarUnit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by pq on 16/04/2015.
 */
public class ToscaTest {
    private static final Logger logger = Logger.getLogger("logger");

    /*
    @Test
    void Test1(){
        //workflow to write a Tosca file with cloud offerings
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

    void Test2(){
        //workflow to read a Tosca file with AAM and compare them with cloud offerings from discoverer
        IToscaEnvironment discoverer = null; //would initialize with a connection to the discoverer
        IToscaEnvironment aam = Tosca.newEnvironment();
        INodeType snc = (INodeType) aam.getNamedEntity("seaclouds.nodes.compute");
        Iterable<INodeTemplate> topology = aam.getNodeTemplatesOfType(snc);
        Map<INodeTemplate,List<INodeType>> matchmaking = new HashMap<>();
        for (INodeTemplate e: topology) {
            INodeType aamType = e.supertype();
            INodeType offeringType =(INodeType) discoverer.getNamedEntity(aamType.name());
            while(offeringType == null)
            {
                aamType = aamType.supertype();
                offeringType = (INodeType) discoverer.getNamedEntity(aamType.name());
            }

            Iterable<INodeType> potentialOfferings = discoverer.getNodeTypesDerivingFrom(offeringType);
            ArrayList<INodeType> validOfferings = new ArrayList<>();
            for (INodeType o : potentialOfferings)
            {
                boolean valid = true;
                for (Map.Entry<String,IProperty> entry : aamType.allProperties().entrySet()) {
                    IValue offeringValue = o.allAttributes().get(entry.getKey());
                    IValue aamValue = o.allAttributes().get(entry.getKey());
                    boolean constraintIsValid = true;
                    for (IConstraint constraint : o.allProperties().get(entry.getKey()).constraints()) {
                       constraintIsValid = constraintIsValid && constraint.verify(offeringValue);
                    }
                    // this should compare using partial ordering
                    //if (!MatchMaker.betterThan(offeringValue,entry.getValue()))
                    if(!constraintIsValid)
                    {
                        valid = false;
                        break;
                    }
                }
                if(valid)
                    validOfferings.add(o);
            }
            matchmaking.put(e,validOfferings);
        }

    }*/
}
