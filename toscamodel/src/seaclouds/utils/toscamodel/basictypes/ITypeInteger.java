package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by pq on 15/04/2015.
 */
public interface ITypeInteger extends INamedEntity, IType {
    @Override
    IValueInteger instantiate(Object value);

    IValueInteger instantiate(Integer value);

    IValueInteger instantiate(int value);

    @Override default
    String name() {
        return "integer";
    }
}
