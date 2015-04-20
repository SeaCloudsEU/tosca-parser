package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.INodeType;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IType;

import java.util.Collections;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedNodeType extends  NodeType implements INamedEntity {
    final String name;

    public NamedNodeType(String name, NodeType unnamedNodeType) {
        super((NodeType)unnamedNodeType.baseType, unnamedNodeType.description,unnamedNodeType.declaredProperties ,unnamedNodeType.attributes);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public INodeType addProperty(String propName, IType propType, Object defaultValue) {
        return new NodeType(this,description, Collections.singletonMap(propName,new Property(propType, defaultValue)),Collections.emptyMap());
    }

    @Override
    public INodeType changeDescription(String newDescription) {
        return new NodeType((NodeType)baseType,newDescription,declaredProperties,attributes);
    }
}
