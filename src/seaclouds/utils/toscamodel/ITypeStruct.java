package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 * Represents a complex type with its schema definition.
 */
public interface ITypeStruct extends INamedType, ISchemaDefinition {
    @Override
    ITypeStruct baseType();

    @Override
    IValueStruct instantiate(Object value);

    IValueStruct instantiate(Map<String,Object> value);
}
