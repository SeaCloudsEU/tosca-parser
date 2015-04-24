package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 * Represents a complex type with its schema definition.
 */
public interface ITypeStruct extends ISchemaDefinition, IType {
    @Override
    ITypeStruct baseType();

    @Override
    IValueStruct instantiate(Object value);

    IValueStruct instantiate(Map<String,Object> value);

    @Override
    ITypeStruct addProperty(String propName, IType propType,Object defaultValue);

    @Override default
    boolean derivesFrom(IType parent) {
        return parent instanceof ITypeStruct && derivesFrom((ITypeStruct) parent);
    }

    @Override default
    boolean derivesFrom(ISchemaDefinition parent){
        return parent instanceof ITypeStruct && derivesFrom((ITypeStruct) parent);
    }

    boolean derivesFrom(ITypeStruct parent);
}
