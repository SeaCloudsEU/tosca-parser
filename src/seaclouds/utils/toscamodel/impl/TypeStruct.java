package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeStruct extends TypeBinding implements ITypeStruct {
    final SchemaDefinition schemaDefinition;
    final ToscaEnvironment environment;
    final String name;

    public TypeStruct( ToscaEnvironment environment, SchemaDefinition schemaDefinition, String name) {
        TypeStruct bt = (TypeStruct)schemaDefinition.baseType();
        assert(bt.environment.equals(environment));

        this.schemaDefinition = schemaDefinition;
        this.environment = environment;
        this.name = name;
    }

    @Override
    public IValueStruct instantiate(Object value) {
        if(value instanceof Map )
        {
            Map<String,Object> cast = Collections.checkedMap((Map)value,String.class,Object.class);
            return instantiate(cast);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public IValueStruct instantiate(Map<String, Object> value) {
        Map<String,IValue> values = new HashMap<>();
        value.forEach((k,v)-> {
            IProperty p = allProperties().get(k);
            values.put(k,p.type().instantiate(v));
        });
        return new SchemaValue(this,values);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isCompatible(ISchemaDefinition otherType) {
        return schemaDefinition.isCompatible(otherType);
    }

    @Override
    public boolean equals(Object obj) {
        return schemaDefinition.equals(obj)
                && obj instanceof TypeStruct && ((TypeStruct) obj).name.equals(name);
    }

    @Override
    public String description() {
        return schemaDefinition.description();
    }

    @Override
    public ITypeStruct baseType() {
        return (ITypeStruct)schemaDefinition.baseType();
    }

    @Override
    public Set<IConstraint> allConstraints() {
        return schemaDefinition.allConstraints();
    }

    @Override
    public Set<IConstraint> declaredConstraints() {
        return schemaDefinition.declaredConstraints();
    }

    @Override
    public Map<String, IProperty> allProperties() {
        return schemaDefinition.allProperties();
    }

    @Override
    public Map<String, IProperty> declaredProperties() {
        return schemaDefinition.declaredProperties();
    }
}
