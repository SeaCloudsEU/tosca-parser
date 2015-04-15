package seaclouds.utils.oldtoscamodel;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeStruct extends IType {
    public String getName();
    public String getDescription();
    public ITypeStruct getSupertype();
    public Map<String,? extends IProperty> getProperties();
}
