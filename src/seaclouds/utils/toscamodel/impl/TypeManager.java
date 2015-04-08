package seaclouds.utils.toscamodel.impl;
import seaclouds.utils.toscamodel.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by pq on 05/04/15.
 */
public class TypeManager implements  ITypeManager {


    @Override
    public IType getType(String typename) {
        return null;
    }

    @Override
    public INodeType getNodeType(String typename) {
        return null;
    }

    @Override
    public IToscaEnvironment getEnvironment() {
        return null;
    }

    @Override
    public IType createNewType(String typename, ITypeStruct parentType, List<Property> schema) {
        return null;
    }

    @Override
    public void bindTypeToInterface(Type interfaceType, String toscaTypeName) {

    }
}
