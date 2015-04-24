package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INodeType;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.ISchemaDefinition;
import seaclouds.utils.toscamodel.IValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public abstract class NodeValue extends SchemaDefinition {
    final Map<String,IValue> attributes;
    final Map<String,IValue> allAttributes;

    public NodeValue(NodeType baseType, String description, Map<String, IProperty> properties, Map<String, ? extends Object> attributes) {
        super(baseType, description, properties);
        this.allAttributes = new HashMap<>();
        if(baseType!= null) {
            this.allAttributes.putAll(baseType.allAttributes());
            this.attributes = baseType.valueConvert(attributes);
        } else {
            this.attributes = new HashMap<>();
        }
        this.allAttributes.putAll(this.attributes);
    }

    public Map<String, IValue> declaredAttributes() {
        return attributes;
    }

    public Map<String, IValue> allAttributes() {
        return allAttributes;
    }

    @Override
    public INodeType baseType() {

        return (INodeType)super.baseType();
    }

    @Override
    public boolean derivesFrom(ISchemaDefinition nodeType) {
        return this.equals(nodeType) || (baseType!= null && baseType().derivesFrom(nodeType));
    }
}
