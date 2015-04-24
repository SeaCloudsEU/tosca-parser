package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public interface ITypeRange extends INamedEntity, IType {

    @Override
    IValueRange instantiate(Object value);

    IValueRange instantiate(Object lowerBound, Object upperBound);

    IValueRange instantiate(int lowerBound, int upperBound);

    IValueRange instantiate(Integer lowerBound, Integer upperBound);

    public IValueRange instantiate(String lowerBound, String upperBound);

    @Override default
    String name() {
        return "range";
    }
}
