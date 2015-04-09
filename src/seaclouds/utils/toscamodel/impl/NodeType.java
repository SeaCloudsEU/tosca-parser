package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INodeType;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IValue;

import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 09/04/2015.
 */
public class NodeType implements INodeType {
    final TypeStruct properties;
    final ValueStruct attributes;
    final NodeType supertype;
    public NodeType(NodeType supertype, TypeStruct properties, ValueStruct attributes) {
        assert properties.getSupertype().equals(attributes.getType());
        assert supertype.properties.equals(properties.getSupertype());
        this.supertype = supertype;
        this.properties = properties;
        this.attributes = attributes;
    }
    @Override
    public INodeType getSupertype() {
        return supertype;
    }

    @Override
    public String getName() {
        return properties.getName();
    }

    @Override
    public String getDescription() {
        return properties.getDescription();
    }

    @Override
    public Set<IProperty> getProperties() {
        return properties.getProperties();
    }

    @Override
    public Map<String, IValue> getAttributes() {
        return attributes;
    }
}
