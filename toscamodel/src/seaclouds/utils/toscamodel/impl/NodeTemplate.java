package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NodeTemplate extends NodeValue implements INodeTemplate{
    public NodeTemplate(NodeType baseType, String description, Map<String, IProperty> properties,Map<String,? extends Object> attributes) {
        super(baseType, description, properties, attributes);
    }

    @Override
    public INodeTemplate addProperty(String propName, IType propType, Object defaultValue) {
        return new NodeTemplate((NodeType)baseType,description,extendSchema(propName, propType, defaultValue),attributes);
    }

    @Override
    public INodeTemplate changeDescription(String newDescription) {
        return new NodeTemplate((NodeType)baseType,newDescription,declaredProperties,attributes);
    }
}
