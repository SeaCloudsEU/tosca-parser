package seaclouds.utils.toscamodel;

/**
 * Created by pq on 15/04/2015.
 */
public interface ITypeInteger extends INamedType {
    @Override
    IValueInteger instantiate(Object value);

    IValueInteger instantiate(Integer value);

    IValueInteger instantiate(int value);
}
