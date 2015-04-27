package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INodeType;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IType;

import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NodeType extends NodeValue implements INodeType {
    public NodeType(NodeType baseType, String description, Map<String, IProperty> properties, Map<String, ? extends Object> attributes) {
        super(baseType, description, properties, attributes);
    }

    @Override
    public INodeType addProperty(String propName, IType propType, Object defaultValue) {
        return new NodeType((NodeType)baseType,description,extendSchema(propName, propType, defaultValue),attributes);
    }

    @Override
    public INodeType changeDescription(String newDescription) {
        return new NodeType((NodeType)baseType,newDescription,declaredProperties,attributes);
    }

}
