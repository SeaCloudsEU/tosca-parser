package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.basictypes.ITypeString;
import seaclouds.utils.toscamodel.basictypes.IValueString;
import seaclouds.utils.toscamodel.impl.CoercedType;

import java.util.Collections;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeString implements ITypeString {
    static ITypeString instance = new TypeString();
    public static ITypeString instance() { return  instance; }
    class ValueString implements IValueString {
        final String value;

        public ValueString(String value) {
            this.value = value;
        }

        @Override
        public String get() {
            return value;
        }

        @Override
        public IType type() {
            return instance;
        }
    }

    @Override
    public IValueString instantiate(Object value) {
        if(value instanceof String) return instantiate((String) value);
        if(value instanceof IValueString) return (IValueString)value;
        throw new IllegalArgumentException();
    }

    @Override
    public IValueString instantiate(String value) {
        return new ValueString(value);
    }

    public IValueString instantiate(IValueString value) {
        return value;
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return new CoercedType(this, Collections.singleton(constraint));
    }

    @Override
    public boolean derivesFrom(IType parent) {
        return this == parent;
    }
}
