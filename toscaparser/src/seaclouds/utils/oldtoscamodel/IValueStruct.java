package seaclouds.utils.oldtoscamodel;

/**
 * Created by pq on 08/04/2015.
 */
public interface IValueStruct extends IValue {
    @Override
    ITypeStruct getType();

    public IValue getProperty(String propertyName);
    public void setProperty(String propertyName, IValue value);
    public IValueStruct getRepresentation(Class<? extends IValueStruct> representation);
}
