package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public interface ITypeFloat extends INamedEntity, IType {
        @Override
        IValueFloat instantiate(Object value);

        IValueFloat instantiate(Float value);

        IValueFloat instantiate(float value);

        IValueFloat instantiate(double value);

        IValueFloat instantiate(Double value);

        @Override default
        String name() {
            return "float";
        }
}
