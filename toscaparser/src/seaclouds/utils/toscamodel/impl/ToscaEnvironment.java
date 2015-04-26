package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by pq on 05/04/15.
 */

import java.io.*;
import java.util.Collections;

public class ToscaEnvironment implements  IToscaEnvironment {

    public Object relationshipTemplate = null;
    private final TypeManager typeManager = new TypeManager(this);
    //private final ToscaTopology topology = new ToscaTopology(this);
    private static final String relName = "normative_types.yaml";
    private static final String absName = "/seaclouds/utils/toscamodel/impl/normative_types.yaml";

    public ToscaEnvironment() {
        //ResourceBundle bundle = ResourceBundle.getBundle("seaclouds.utils.toscamodel.impl");
        //this.getClass().getResourceAsStream(absName);
        InputStream stream = this.getClass().getResourceAsStream(relName);
        readFile(new InputStreamReader(stream),true);
    }

    @Override
    public void readFile(Reader input, boolean hideTypes) {
        final Parser parser = new Parser(this, hideTypes);
        parser.Parse(input);
    }

    @Override
    public void renameEntity(String entityName, String newEntityName) {
        typeManager.renameEntity(entityName, newEntityName);
    }

    @Override
    public void hideEntity(String entityName) {
        INamedEntity ret = getNamedEntity(entityName);
        if(ret instanceof  NamedStruct)
            ((NamedStruct) ret).hidden = true;
        if(ret instanceof NamedNodeType)
            ((NamedNodeType) ret).hidden = true;
    }

    @Override
    public void unhideEntity(String entityName) {
        INamedEntity ret = getNamedEntity(entityName);
        if(ret instanceof  NamedStruct)
            ((NamedStruct) ret).hidden = false;
        if(ret instanceof NamedNodeType)
            ((NamedNodeType) ret).hidden = false;
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
    public INamedEntity importWithSupertypes(INamedEntity entity) {
        return importToMove(entity);
    }

    private INamedEntity importToMove(INamedEntity entity) {
        INamedEntity res = getNamedEntity(entity.name());
        if (res != null)
            return res;
        if(entity instanceof INodeType) res = typeManager.importNodeType(entity, this);
        else if (entity instanceof  INodeTemplate) res = typeManager.importNodeTemplate(entity, this);
        else if (entity instanceof  ITypeStruct) res = typeManager.importStructType(entity, this);
        else {
            throw new NotImplementedException();
        }
        return res;
    }

    @Override
    public INodeTemplate newTemplate(INodeType type) {
        return new NodeTemplate((NamedNodeType)type,"", Collections.emptyMap(),Collections.emptyMap());
    }
};
