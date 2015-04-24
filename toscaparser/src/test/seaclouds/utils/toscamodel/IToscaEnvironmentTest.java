package seaclouds.utils.toscamodel;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pq on 21/04/2015.
 */
public class IToscaEnvironmentTest extends TestCase {
    private static final Logger logger = Logger.getLogger("logger");
    IToscaEnvironment env = null;
    public void setUp() throws Exception {
        super.setUp();
        env = Tosca.newEnvironment();
    }

    public void testReadFile() throws  Exception {
        //setUp();
        Reader toscaFile = new FileReader("C:\\Users\\pq\\Downloads\\cloud_offerings_iaas.yaml.txt.yaml");
        env.readFile(toscaFile,false);
        for (INodeType nodeType : env.getNodeTypesDerivingFrom(null)) {
            logger.log(Level.INFO, ((INamedEntity) nodeType).name());
        }
    }

    public void tearDown() throws Exception {

    }

}