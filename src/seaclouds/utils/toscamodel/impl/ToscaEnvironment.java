package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;

public class ToscaEnvironment implements  IToscaEnvironment,ITypeManager,ITopology {
    HashMap<String,IType> types;
    HashMap<String,INodeType> nodeTypes;
    HashMap<String,Object> topology;
    @Override
    public ITopology getTopology() {
        return this;
    }

    @Override
    public ITypeManager getTypeManager() {
        return this;
    }

    @Override
    public void readToscaFile(Reader input) {
        // todo
    }

    @Override
    public void writePartialToscaFile(Writer output, Iterable<String> identifiers) {
        // todo
    }

    @Override
    public void writeToscaFile(Writer output) {
        // todo
    }

    @Override
    public IToscaEnvironment getEnvironment() {
        return this;
    }

    @Override
    public INodeType getNodeType(String typename) {
        return nodeTypes.get(typename);
    }

    @Override
    public IType getType(String typename) {
        return nodeTypes.get(typename);
    }

};
