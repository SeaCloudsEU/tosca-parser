package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public interface ITypeBoolean extends INamedEntity, IType {

    @Override
    IValueBoolean instantiate(Object value);

    IValueBoolean instantiate(boolean value);

    IValueBoolean instantiate(Boolean value);

    public IValueBoolean instantiate(String value);

    @Override default
    String name() {
        return "boolean";
    }
}
