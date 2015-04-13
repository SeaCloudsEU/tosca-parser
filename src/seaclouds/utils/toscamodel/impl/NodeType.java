package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.Collection;

/**
 * Created by pq on 09/04/2015.
 */
public class NodeType extends TypeStruct implements INodeType {
    final ValueStruct attributes;
    public NodeType(String tname, String description,INodeType parentType, Collection<? extends IProperty> prop,ValueStruct attributes) {
        super(tname, description, parentType, prop);
        this.attributes = attributes;
    }
    @Override
    public INodeType getSupertype() {
        return (INodeType) super.getSupertype();
    }

    @Override
    public ValueStruct getAttributes() {
        return attributes;
    }
}
