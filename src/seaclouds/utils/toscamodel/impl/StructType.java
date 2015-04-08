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
class StructType implements ITypeStruct {
    private final String name;
    private final Set<Property> properties;
    private final ITypeStruct supertype;
    private Class<? extends  IValueStruct> representation;

    public Class<? extends IValueStruct> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Class<? extends IValueStruct> representation) {
        //todo check representation
        this.representation = representation;
    }

    public IValueStruct coerceValue(ValueStruct v) {
        if (v.getType() != this)
            return null;
        if (representation == null)
            return v;
        Proxy.newProxyInstance(representation.getClassLoader(), new Class[]{representation}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        representation.
        v.get();
    }

    public StructType(String tname, ITypeStruct parentType, Collection<Property> prop) {
        name = tname;
        supertype = parentType;
        properties = new HashSet<Property>();
        properties.addAll(prop);
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
