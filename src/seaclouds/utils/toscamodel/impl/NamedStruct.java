package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.ITypeStruct;

import java.util.Collections;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedStruct extends  TypeStruct implements INamedEntity {
    public boolean hidden = false;

    @Override
    public String toString() {
        return name();
    }

    public NamedStruct(String name,ITypeStruct unnamedType) {
        super(unnamedType);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    final String name;

    @Override
    public ITypeStruct addProperty(String propName, IType propType, Object propValue) {
        return new TypeStruct(this,description,Collections.singletonMap(propName,new Property(propType,propValue)));
    }
}
