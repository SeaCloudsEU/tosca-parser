package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.EBasicTypeInfo;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.ITypeBasic;
import seaclouds.utils.toscamodel.IValueBoolean;

/**
 * Created by pq on 05/04/15.
 */
public class ValueBoolean implements IValueBoolean {
    boolean value;

    public ValueBoolean(boolean v) {
        value = v;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new ITypeBasic() {
            @Override
            public EBasicTypeInfo getBasicType() {
                return EBasicTypeInfo.TOSCABoolean;
            }
        };
    }
}
