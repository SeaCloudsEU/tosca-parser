package seaclouds.utils.toscamodel.impl;

import com.google.common.collect.Maps;
import seaclouds.utils.toscamodel.*;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeStruct  extends  SchemaDefinition implements ITypeStruct {
    public TypeStruct(ITypeStruct baseType, String description, Map<String, IProperty> properties) {
        super(baseType, description, properties);
    }
    public TypeStruct(ISchemaDefinition copyFrom) {
        super(copyFrom.baseType(),copyFrom.description(),copyFrom.allProperties());
    }

    @Override
    public IValueStruct instantiate(Object value) {
        return null;
    }

    public IValueStruct instantiate(IValueStruct v) {
        if(v.type().equals(this))
            return v;
        else if (v.type().isCompatible(this)) {
            return instantiate(v.get());
        } else
            return null;
    }
    @Override
    public IValueStruct instantiate(Map<String, Object> value) {
        Map<String,IValue> vmap = new HashMap<>();
        for(Map.Entry<String,Object> e: value.entrySet())
        {
            IProperty p = allProperties.get(e.getKey());
            vmap.put(e.getKey(), p.type().instantiate(e.getValue()));
        }
        return new SchemaValue(this,vmap);
    }

    @Override
    public IType coerce(IConstraint constraint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IProperty makeProperty(Object defaultValue) {
        return new Property(this,defaultValue);
    }

    @Override
    public boolean derivesFrom(IType parent) {
        return this.equals(parent) || ((ITypeStruct)baseType).derivesFrom(parent);
    }

    @Override
    public ITypeStruct baseType() {
        return (ITypeStruct) super.baseType();
    }

    @Override
    public ITypeStruct addProperty(String propName, IType propType,Object propValue) {
        return new TypeStruct(baseType(),description,extendSchema(propName,propType,propValue));
    }

    @Override
    public ISchemaDefinition changeDescription(String newDescription) {
        return new TypeStruct(baseType(),newDescription,declaredProperties());
    }
}
