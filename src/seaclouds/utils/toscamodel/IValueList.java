package seaclouds.utils.toscamodel;

import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueList extends IValue,List<IValue> {
    public IType getValueType();
}
