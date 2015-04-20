package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface INodeType extends INamedType, ISchemaDefinition {
    Map<String,IValue> declaredAttributes();
    Map<String,IValue> allAttributes();

    @Override
    INodeType supertype();
}
