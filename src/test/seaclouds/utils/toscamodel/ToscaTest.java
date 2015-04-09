package seaclouds.utils.toscamodel;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ToscaTest {
    private static final Logger logger = Logger.getLogger("logger");


    @Test
    public void Test1(){
        assertNotNull(Tosca.createEnvironment());
    }

}