package seaclouds.utils.toscamodel;

public interface IToscaEnvironment {
    /// reads a tosca file and adds it to the content
    public void readToscaFile();
    public void writeToscaFile();
    public ITypeManager getTypeManager();
    public ITopology getTopology();
}
