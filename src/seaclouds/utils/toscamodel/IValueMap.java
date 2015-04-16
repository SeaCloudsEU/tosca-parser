package seaclouds.utils.toscamodel;


import java.util.Map;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueMap extends IValue {
    public Map<String,? extends IValue> get();
    public IType valueType();
}
