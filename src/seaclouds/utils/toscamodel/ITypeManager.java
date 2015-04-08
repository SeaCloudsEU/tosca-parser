package seaclouds.utils.toscamodel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeManager {
    public IToscaEnvironment getEnvironment();
    public IType getType(String typename);
    public IType createNewType(String typename,ITypeStruct parentType,  List<Property> schema);
    public INodeType getNodeType(String typename);
    public void bindTypeToInterface(Type interfaceType,String toscaTypeName );
}
