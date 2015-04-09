package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pq on 08/04/2015.
 */
class TypeStruct implements ITypeStruct {
    private final String name;
    private final String description;
    private final Set<Property> properties;
    private final ITypeStruct supertype;
    private Class<? extends  IValueStruct> representation;

    public Class<? extends IValueStruct> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Class<? extends IValueStruct> representation) {
        //todo: check whether representation matches (copy from ValueStruct.java)
        this.representation = representation;
    }

    public TypeStruct(String tname, String description,ITypeStruct parentType, Collection<Property> prop) {
        name = tname;
        this.description = description;
        supertype = parentType;
        properties = new HashSet<Property>();
        properties.addAll(prop);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ITypeStruct getSupertype() {
        return supertype;
    }

    @Override
    public Set<IProperty> getProperties() {
        Set<IProperty> result = new HashSet<IProperty>();
        result.addAll(properties);
        return result;
    }
}
