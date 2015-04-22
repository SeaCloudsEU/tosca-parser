package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface INodeTemplate extends ISchemaDefinition {
    Map<String,IValue> declaredAttributes();
    Map<String,IValue> allAttributes();

    //Map<String,IValue> requirements();

    @Override
    INodeTemplate addProperty(String propName, IType propType, Object defaultValue);

    @Override
    INodeTemplate changeDescription(String newDescription);

    @Override
    INodeType baseType();
}
