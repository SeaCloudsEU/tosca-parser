package seaclouds.utils.toscamodel;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 16/04/2015.
 */
public interface IToscaEnvironment {
    public INamedEntity getNamedEntity(String entityName);

    public ITypeStruct newNamedType(String name,ISchemaDefinition schema);
    public INodeType newNodeType(String name,ISchemaDefinition properties, Map<String,Object> attributes);

    public ISchemaDefinition newSchema(String description, INamedEntity derivedFrom);

    public Iterable<INamedEntity> getTopology();
    public Iterable<INodeType> getSubtypesOf(INodeType parentType);
    public Iterable<ITypeStruct> getSubtypesOf(ITypeStruct parentType);

    /**
     *
     * @param input a reader containing the string to parse as tosca
     * @param sharedTypes types imported this way will not be written to the output file
     */
    public void readFile(Reader input, boolean sharedTypes);

    public void writeFile(Writer output);
}
