package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;

public class ToscaEnvironment implements  IToscaEnvironment {

    private final TypeManager typeManager = new TypeManager(this);
    //private final ToscaTopology topology = new ToscaTopology(this);


    @Override
    public void readFile(Reader input, boolean sharedTypes) {
        final Parser parser = new Parser(this,sharedTypes);
        parser.Parse(input);
    }

    @Override
    public void writeFile(Writer output) {
        ToscaEmitter emitter = new ToscaEmitter();
        try {
            emitter.WriteDocument(output, this);
        } catch (IOException e) {
            throw new RuntimeException("zomg!");
        }

    }

    @Override
    public INamedEntity getNamedEntity(String entityName) {
        INamedEntity ret = null;
        // todo
//        if (ret == null)
//            ret = topology.getNodeTemplate(entityName);
        if (ret == null)
            ret = (INamedEntity)typeManager.getNodeTemplate(entityName);
        if (ret == null)
            ret = (INamedEntity)typeManager.getNodeType(entityName);
        if (ret == null)
            ret = (INamedEntity)typeManager.getType(entityName);

        return ret;
    }


    @Override
    public Iterable<INodeTemplate> getNodeTemplatesOfType(INodeType rootType) {
        //return topology.getNodeTemplatesOfType(INodeType rootType);
        return typeManager.getNodeTemplatesOfType(rootType);
    }

    @Override
    public Iterable<INodeType> getNodeTypesDerivingFrom(INodeType rootType) {
        return typeManager.getNodeTypesDerivingFrom(rootType);
    }

    @Override
    public Iterable<ITypeStruct> getTypesDerivingFrom(ITypeStruct rootType) {

        return typeManager.getTypesDerivingFrom(rootType);
    }

    @Override
    public INamedEntity registerType(String entityName, IType t) {
        return typeManager.registerType(entityName,t);
    }

    @Override
    public INamedEntity registerNodeType(String entityName, INodeType t) {
        return typeManager.registerNodeType(entityName,t);
    }

    @Override
    public INamedEntity registerNodeTemplate(String entityName, INodeTemplate t) {
        return typeManager.registerNodeTemplate(entityName, t);
    }

    @Override
    public INodeTemplate newTemplate(INodeType type) {
        return new NodeTemplate((NamedNodeType)type,"", Collections.emptyMap(),Collections.emptyMap());
    }
};
