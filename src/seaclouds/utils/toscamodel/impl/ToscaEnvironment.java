package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

class ToscaEnvironment implements  IToscaEnvironment {

    private final TypeManager typeManager = new TypeManager(this);
    //private final ToscaTopology topology = new ToscaTopology(this);


    @Override
    public void readFile(Reader input, boolean sharedTypes) {
        // todo
    }

    @Override
    public void writeFile(Writer output) {
        // todo
    }

    @Override
    public INamedEntity getNamedEntity(String entityName) {
        INamedEntity ret = null;
        // todo
//        if (ret == null)
//            ret = topology.getNodeTemplate(entityName);
        if (ret == null)
            ret = typeManager.getNodeType(entityName);
        if (ret == null)
            ret = typeManager.getType(entityName);

        return null;
    }

    @Override
    public ITypeStruct newNamedType(String name, ISchemaDefinition schema) {
        return typeManager.createNewType(name,"",);
    }

    @Override
    public INodeType newNodeType(String name, ISchemaDefinition properties, Map<String, Object> attributes) {
        return null;
    }

    @Override
    public ISchemaDefinition newSchema(String description, INamedEntity derivedFrom) {
        return null;
    }

    @Override
    public Iterable<INodeTemplate> getNodeTemplatesOfType(INodeType rootType) {
        return null;
    }

    @Override
    public Iterable<INodeType> getNodeTypesDerivingFrom(INodeType rootType) {
        return null;
    }

    @Override
    public Iterable<ITypeStruct> getTypesDerivingFrom(ITypeStruct rootType) {
        return null;
    }
};
