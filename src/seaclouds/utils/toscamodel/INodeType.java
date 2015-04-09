package seaclouds.utils.toscamodel;

import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 19/03/2015.
 */
public interface INodeType extends  IType {
    public INodeType getSupertype();
    public String getName();
    public String getDescription();

    public Set<IProperty> getProperties();
    public Map<String,IValue> getAttributes();
}
