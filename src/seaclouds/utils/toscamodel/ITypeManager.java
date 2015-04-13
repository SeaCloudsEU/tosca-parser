package seaclouds.utils.toscamodel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeManager {
    public IToscaEnvironment getEnvironment();
    public IType getType(String typename);
    public IType createNewType(String typename, String description,ITypeStruct parentType,  Collection<? extends  IProperty> schema);
    public IType createNewNodeType(String typename, String description,String parentType,  Collection<? extends  IProperty> schema, IValueStruct attributes);
    public INodeType getNodeType(String typename);
    public void bindTypeToInterface(Class<? extends IValueStruct> interfaceType,String toscaTypeName );
}
