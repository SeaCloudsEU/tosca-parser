package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface INodeType extends INamedEntity, ISchemaDefinition {
    Map<String,IValue> attributes();

    @Override
    INodeType supertype();
}
