package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pq on 13/04/2015.
 */
class TypeManager {
    private ToscaEnvironment toscaEnvironment;

    final Map<String, INamedType> basicTypes = new HashMap<>();
    final Map<String, TypeStruct> structTypes = new HashMap<String, TypeStruct>();
    final Map<String, INodeType> nodeTypes = new HashMap<String, INodeType>();

    public TypeManager(ToscaEnvironment toscaEnvironment) {
        this.toscaEnvironment = toscaEnvironment;
    }

    public SchemaDefinition newSchema(String description, INamedEntity derivedFrom) {
        assert(derivedFrom instanceof ISchemaDefinition);
        return new SchemaDefinition(derivedFrom,)
    }
    public ITypeStruct createNewType(String typename, ISchemaDefinition schema) {

        Collection<IProperty> c = new ArrayList<IProperty>();

        TypeStruct newType = new TypeStruct(typename, description, parentType, schema);
        structTypes.put(typename, newType);
        return newType;
    }

    public ITypeStruct createNewNodeType(String typename, String description, String parentTypeName, Collection<? extends IProperty> schema, IValueStruct attributes) {

        Collection<Property> c = new ArrayList<Property>();
        for (IProperty p : schema)
            c.add(new Property(p));

        NodeType parentType = (NodeType) this.getNodeType(parentTypeName);
        ValueStruct v = new ValueStruct(parentType);
        for (Map.Entry<String, ? extends IProperty> e : attributes.getType().getProperties().entrySet()) {
            IValue pval = attributes.getProperty(e.getKey());
            if (pval != null && e.getValue().getDefaultValue() != null && e.getValue().getDefaultValue() != pval)
                v.setProperty(e.getKey(), pval);
        }
        NodeType newType = new NodeType(typename, description, parentType, schema, v);
        nodeTypes.put(typename, newType);
        return newType;
    }

    public void bindTypeToInterface(Class<? extends IValueStruct> interfaceType, String toscaTypeName) {
        TypeStruct s = structTypes.get(toscaTypeName);
        if (s != null)
            s.setRepresentation(interfaceType);
    }

    public INodeType getNodeType(String typename) {
        return nodeTypes.get(typename);
    }

    public INamedType getType(String typename) {
        // TODO: check if someone was assuming this method to always return a struct type
        INamedType ret = structTypes.get(typename);
        if(ret == null)
            ret = basicTypes.get(typename);
        return ret;
    }
}
