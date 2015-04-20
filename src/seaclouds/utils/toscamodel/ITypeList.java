package seaclouds.utils.toscamodel;

import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeList extends IType {
    INamedEntity valueSchema();

    @Override
    IValueList instantiate(Object value);

    public IValueList instantiate(List value);
}
