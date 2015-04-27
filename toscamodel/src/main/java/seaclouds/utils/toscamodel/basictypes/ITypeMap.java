package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeMap extends IType {
    IType valueSchema();

    @Override
    IValueMap instantiate(Object value);

    public IValueMap instantiate(Map<String,Object> value);
}
