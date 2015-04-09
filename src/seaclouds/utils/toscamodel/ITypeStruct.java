package seaclouds.utils.toscamodel;

import java.util.Set;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeStruct extends IType {
    public String getName();
    public String getDescription();
    public ITypeStruct getSupertype();
    public Set<IProperty> getProperties();
}
