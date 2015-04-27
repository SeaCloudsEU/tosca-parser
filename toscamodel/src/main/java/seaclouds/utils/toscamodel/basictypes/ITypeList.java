package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface ITypeList extends IType {
    IType valueSchema();

    @Override
    IValueList instantiate(Object value);

    public IValueList instantiate(List value);
}
