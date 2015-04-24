package seaclouds.utils.toscamodel;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.emitter.Emitter;
import org.yaml.snakeyaml.events.Event;
import seaclouds.utils.toscamodel.*;
import seaclouds.utils.toscamodel.impl.ToscaEmitter;

import java.io.*;
import java.lang.reflect.MalformedParametersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pq on 21/04/2015.
 */
public class Main {
    static Logger logger = Logger.getLogger("logger");

    public static void main(String[] parameters) throws  Exception {
        IToscaEnvironment env = Tosca.newEnvironment();
        Reader toscaFile = new FileReader("C:\\Users\\pq\\Downloads\\test2-aam.yaml");
        env.readFile(toscaFile,false);
        StringWriter target = new StringWriter();
        //Yaml yaml = new Yaml();
        //DumperOptions options = new DumperOptions();
        //options.setDefaultScalarStyle(DumperOptions.ScalarStyle.SINGLE_QUOTED);

        //Emitter emitter = new Emitter(target,options);
        //for (Event e : yaml.parse(toscaFile))
        //{
        //    emitter.emit(e);
        //}
        ToscaEmitter e = new ToscaEmitter();
        e.WriteDocument(target, env);
        String out = target.toString();
        System.out.println(out);
        if(true)
            return;
        env.readFile(toscaFile, false);
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
