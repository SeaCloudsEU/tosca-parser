package seaclouds.utils.toscamodel;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeMap extends INamedType {
    INamedEntity valueType();

    @Override
    IValueMap instantiate(Object value);

    public IValueMap instantiate(Map<String,Object> value);
}
