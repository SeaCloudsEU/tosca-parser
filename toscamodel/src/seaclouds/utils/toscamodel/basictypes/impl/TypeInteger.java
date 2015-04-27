package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.basictypes.ITypeInteger;
import seaclouds.utils.toscamodel.basictypes.IValueInteger;
import seaclouds.utils.toscamodel.impl.CoercedType;

import java.util.Collections;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public class TypeInteger implements ITypeInteger {
    static ITypeInteger instance = new TypeInteger();
    public static  ITypeInteger instance() { return instance; }

    class ValueInteger implements IValueInteger{
        final int value;

        public ValueInteger(int value){
            this.value = value;
        }

        public ValueInteger(Integer value){
            this(value.intValue());
        }

        public ValueInteger(String value){
            this.value = Integer.valueOf(value);
        }
        @Override
        public int get() {
            return this.value;
        }

        @Override
        public IType type() {
            return instance;
        }
    }

    @Override
    public IValueInteger instantiate(Object value) {
        if(value instanceof IValueInteger) return (IValueInteger)value;
        if(value instanceof Integer) return  instantiate((Integer) value);
        if(value instanceof String) return instantiate((String) value);
        throw new IllegalArgumentException();
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
    public IValueInteger instantiate(Integer value) {
        return new ValueInteger(value);
    }

    public IValueInteger instantiate(String value) {
        return new ValueInteger(value);
    }

    @Override
    public IValueInteger instantiate(int value) {
        return new ValueInteger(value);
    }

    public IValueInteger instantiate(IValueInteger value) { return value; }
}
