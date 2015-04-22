import seaclouds.utils.toscamodel.*;

import java.io.Console;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.MalformedParametersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pq on 21/04/2015.
 */
public class Mein {
    static Logger logger = Logger.getLogger("logger");

    public static void main(String[] parameters) throws  Exception {
        IToscaEnvironment env = Tosca.newEnvironment();
        Reader toscaFile = new FileReader("C:\\Users\\pq\\Downloads\\cloud_offerings_iaas.yaml.txt.yaml");
        env.readFile(toscaFile, false);
        Console c = System.console();
        for (ITypeStruct nodeType : env.getTypesDerivingFrom((ITypeStruct) env.getNamedEntity("emptyStruct"))) {
            logger.info(((INamedEntity)nodeType).name());
            logger.info(((INamedEntity)nodeType.baseType()).name());
            logger.info(nodeType.declaredProperties().toString());
            logger.info(nodeType.allProperties().toString());
        }
        for (INodeType nodeType : env.getNodeTypesDerivingFrom((INodeType) env.getNamedEntity("tosca.nodes.Root"))) {
            logger.info(((INamedEntity)nodeType).name());
            logger.info(((INamedEntity)nodeType.baseType()).name());
            logger.info(nodeType.declaredProperties().toString());
            logger.info(nodeType.declaredAttributes().toString());
            logger.info(nodeType.allProperties().toString());
            logger.info(nodeType.allAttributes().toString());
        }
        for (INodeTemplate nodeType : env.getNodeTemplatesOfType((INodeType) env.getNamedEntity("seaclouds.nodes.Compute"))) {
            logger.info(((INamedEntity) nodeType).name());
            logger.info(((INamedEntity) nodeType.baseType()).name());
            logger.info(nodeType.declaredProperties().toString());
            logger.info(nodeType.declaredAttributes().toString());
            logger.info(nodeType.allProperties().toString());
            logger.info(nodeType.allAttributes().toString());
        }
        for (INodeTemplate nodeType : env.getNodeTemplatesOfType((INodeType) env.getNamedEntity("tosca.nodes.Compute"))) {
            logger.info(((INamedEntity) nodeType).name());
            logger.info(((INamedEntity) nodeType.baseType()).name());
            logger.info(nodeType.declaredProperties().toString());
            logger.info(nodeType.declaredAttributes().toString());
            logger.info(nodeType.allProperties().toString());
            logger.info(nodeType.allAttributes().toString());
        }
        return;

    }
}
