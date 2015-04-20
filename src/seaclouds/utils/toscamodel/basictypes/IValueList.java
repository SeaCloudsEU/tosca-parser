package seaclouds.utils.toscamodel.basictypes;


import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;

import java.util.List;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueList extends IValue {
    public List<IValue> get();
    public IType valueType();
}
