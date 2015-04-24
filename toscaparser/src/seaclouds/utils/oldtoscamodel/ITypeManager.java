package seaclouds.utils.oldtoscamodel;

import java.util.Collection;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeManager {
    public IToscaEnvironment getEnvironment();
    public IType getType(String typename);
    public INodeType getNodeType(String typename);
    public IType createNewType(String typename, String description,ITypeStruct parentType,  Collection<? extends  IProperty> schema);
    public INodeType createNewNodeType(String typename, String description,String parentType,  Collection<? extends  IProperty> schema, IValueStruct attributes);

    public void bindTypeToInterface(Class<? extends IValueStruct> interfaceType,String toscaTypeName );
}
