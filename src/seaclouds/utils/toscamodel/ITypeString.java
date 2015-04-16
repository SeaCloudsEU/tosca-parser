package seaclouds.utils.toscamodel;

/**
 * Created by pq on 15/04/2015.
 */
public interface ITypeString extends INamedType {
    @Override
    IValueString instantiate(Object value);

    IValueString instantiate(String value);
}
