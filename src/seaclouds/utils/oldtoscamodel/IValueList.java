package seaclouds.utils.oldtoscamodel;

import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueList extends IValue,List<IValue> {
    public IType getValueType();
}
