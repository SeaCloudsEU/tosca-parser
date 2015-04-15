package seaclouds.utils.oldtoscamodel;

import java.io.Reader;
import java.io.Writer;

public interface IToscaEnvironment {
    /// reads a tosca file and adds the information in it into the environment.
    // multiple files can be read in order to resolve dependencies between types
    // files are required to be read in order, that is, when a file is read all supertypes
    // required should have been already created
    public void readToscaFile(Reader input);
    // writes all types and topology on a file
    public void writeToscaFile(Writer output);
    // writes only selected identifiers into a file
    //public void writePartialToscaFile(Writer output,Iterable<IType> types, Iterable<INodeType> nodeTypes, Iterable<INodeTemplate> nodeTemplates);
    public void writePartialToscaFile(Writer output,Iterable<String> identifiers);
    public ITypeManager getTypeManager();
    public ITopology getTopology();
}
