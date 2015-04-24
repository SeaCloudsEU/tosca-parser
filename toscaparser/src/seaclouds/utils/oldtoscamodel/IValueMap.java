package seaclouds.utils.oldtoscamodel;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueMap extends IValue,Map<String,IValue> {
    public IType getValueType();
}
