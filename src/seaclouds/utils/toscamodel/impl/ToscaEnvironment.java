package seaclouds.utils.toscamodel.impl;
import com.oracle.webservices.internal.api.message.PropertySet;
import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;

public class ToscaEnvironment implements  IToscaEnvironment,ITypeManager,ITopology {
    final Map<String,ITypeStruct> structTypes = new HashMap<String, ITypeStruct>();
    final Map<String,INodeType> nodeTypes = new HashMap<String, INodeType>();

    @Override
    public IType createNewType(String typename, ITypeStruct parentType, Collection< ? extends IProperty> schema) {
        return null;
    }

    @Override
    public void bindTypeToInterface(Type interfaceType, String toscaTypeName) {

    }

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
