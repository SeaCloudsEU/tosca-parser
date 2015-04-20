package seaclouds.utils.toscamodel;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface IToscaEnvironment {
    public INamedEntity getNamedEntity(String entityName);

    public INamedEntity registerType(String entityName, IType t);
    public INamedEntity registerNodeType(String entityName, INodeType t);
    public INamedEntity registerTemplate(String entityName, INodeTemplate t);

    public Iterable<INodeTemplate> getNodeTemplatesOfType(INodeType rootType);
    public Iterable<INodeType> getNodeTypesDerivingFrom(INodeType rootType);
    public Iterable<ITypeStruct> getTypesDerivingFrom(ITypeStruct rootType);

    /**
     *
     * @param input a reader containing the string to parse as tosca
     * @param sharedTypes types imported this way will not be written to the output file
     */
    public void readFile(Reader input, boolean sharedTypes);

    public void writeFile(Writer output);
}
