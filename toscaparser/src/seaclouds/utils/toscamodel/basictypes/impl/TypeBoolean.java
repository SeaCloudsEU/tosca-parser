package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;
import seaclouds.utils.toscamodel.basictypes.ITypeBoolean;
import seaclouds.utils.toscamodel.basictypes.IValueBoolean;
import seaclouds.utils.toscamodel.impl.CoercedType;

import java.util.Collections;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public class TypeBoolean implements ITypeBoolean {
    static ITypeBoolean instance = new TypeBoolean();
    public static ITypeBoolean instance() { return  instance; }

    class ValueBoolean implements IValueBoolean{
        boolean value;

        public ValueBoolean(boolean value) { this.value = value; }
        public ValueBoolean(Boolean value) { this(value.booleanValue()); }
        public ValueBoolean(String value)  { this(Boolean.valueOf(value)); }

        @Override
        public boolean get() {
            return value;
        }

        @Override
        public IType type() {
            return instance;
        }
    }

    @Override
    public IValueBoolean instantiate(Object value) {
        if (value instanceof IValueBoolean)
            return (IValueBoolean)value;
        else if (value instanceof Boolean)
            return instantiate((Boolean) value);
        else if (value instanceof String)
            return instantiate(Boolean.valueOf((String)value));
        else throw new IllegalArgumentException();
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return new CoercedType(this, Collections.singleton(constraint));
    }

    @Override
    public boolean derivesFrom(IType parent) {
        return this == parent;
    }

    @Override
    public IValueBoolean instantiate(boolean value) {
        return new ValueBoolean(value);
    }

    @Override
    public IValueBoolean instantiate(Boolean value) {
        return new ValueBoolean(value);
    }

    @Override
    public IValueBoolean instantiate(String value) {
        return new ValueBoolean(value);
    }
}
