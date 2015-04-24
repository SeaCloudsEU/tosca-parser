package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.*;

import java.util.*;

/**
 * Created by pq on 08/04/2015.
 */
class TypeStruct implements ITypeStruct {
    private final String name;
    private final String description;
    private final Map<String,Property> properties;
    private final ITypeStruct supertype;
    private Class<? extends  IValueStruct> representation;

    public Class<? extends IValueStruct> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Class<? extends IValueStruct> representation) {
        //todo: check whether representation matches (copy from ValueStruct.java)
        this.representation = representation;
    }

    public TypeStruct(String tname, String description,ITypeStruct parentType, Collection<? extends IProperty> prop) {
        name = tname;
        this.description = description;
        supertype = parentType;
        properties = new HashMap<String, Property>();
        for (IProperty p : prop)
        properties.put(p.getName(), new Property(p));
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
    public Map<String,? extends IProperty> getProperties() {
        return properties;
    }
}
