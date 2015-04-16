package seaclouds.utils.toscamodel;


import java.util.Map;

/**
 * Created by pq on 08/04/2015.
 */
public interface IValueStruct extends IValue {
    @Override
    IType type();

    public Map<String,IValue> get();
}
