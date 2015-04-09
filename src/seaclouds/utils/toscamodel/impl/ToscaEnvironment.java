package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.Reader;
import java.io.Writer;
import java.util.*;

public class ToscaEnvironment implements  IToscaEnvironment,ITypeManager,ITopology {
    final Map<String,TypeStruct> structTypes = new HashMap<String, TypeStruct>();
    final Map<String,INodeType> nodeTypes = new HashMap<String, INodeType>();

    @Override
    public IType createNewType(String typename,String description, ITypeStruct parentType, Collection< ? extends IProperty> schema) {

        Collection<Property> c = new ArrayList<Property>();
        for (IProperty p : schema)
            c.add(new Property(p));

        return new TypeStruct(typename,description,parentType,c);
    }

    @Override
    public void bindTypeToInterface(Class<? extends  IValueStruct> interfaceType, String toscaTypeName) {
        TypeStruct s = structTypes.get(toscaTypeName);
        if(s != null)
            s.setRepresentation(interfaceType);
    }

    @Override
    public ITopology getTopology() {
        return this;
    }

    @Override
    public ITypeManager getTypeManager() {
        return this;
    }

    @Override
    public void readToscaFile(Reader input) {
        // todo
    }

    @Override
    public void writePartialToscaFile(Writer output, Iterable<String> identifiers) {
        // todo
    }

    @Override
    public void writeToscaFile(Writer output) {
        // todo
    }

    @Override
    public IToscaEnvironment getEnvironment() {
        return this;
    }

    @Override
    public INodeType getNodeType(String typename) {
        return nodeTypes.get(typename);
    }

    @Override
    public IType getType(String typename) {
        return nodeTypes.get(typename);
    }

};
