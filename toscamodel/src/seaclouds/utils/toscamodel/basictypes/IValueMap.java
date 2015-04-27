package seaclouds.utils.toscamodel.basictypes;


import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;

import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueMap extends IValue {
    public Map<String,IValue> get();
    public IType valueType();
}
