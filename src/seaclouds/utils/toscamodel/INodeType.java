package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface INodeType extends ISchemaDefinition {
    Map<String,IValue> declaredAttributes();
    Map<String,IValue> allAttributes();

    @Override
    INodeType addProperty(String propName, IType propType, Object defaultValue);

    @Override
    INodeType changeDescription(String newDescription);

    @Override
    INodeType baseType();
}
