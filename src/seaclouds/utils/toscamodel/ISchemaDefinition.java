package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface ISchemaDefinition {
    Map<String,IProperty> properties();
    Map<String,IConstraint> constraints();

    ISchemaDefinition supertype();

    String description();

    /**
     * equals yields true when the schema, name and declaration environment are equal.
     * isCompatible yields true when the properties are compatible
     *
     * @param otherType another struct type
     * @return true if schema are compatible
     */
    boolean isCompatible(ISchemaDefinition otherType);
}
