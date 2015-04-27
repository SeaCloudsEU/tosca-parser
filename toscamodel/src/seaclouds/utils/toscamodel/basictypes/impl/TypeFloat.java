package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.basictypes.ITypeFloat;
import seaclouds.utils.toscamodel.basictypes.IValueFloat;
import seaclouds.utils.toscamodel.impl.CoercedType;

import java.util.Collections;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public class TypeFloat implements ITypeFloat{
    static ITypeFloat instance = new TypeFloat();
    public static ITypeFloat instance() { return instance; }

    class ValueFloat implements IValueFloat {
        final double value;

        public ValueFloat(float value) { this.value = value; }
        public ValueFloat(double value) { this.value = value; }
        public ValueFloat(Float value) { this(value.doubleValue()); }
        public ValueFloat(Double value) { this(value.doubleValue()); }

        @Override
        public double get() {
            return this.value;
        }

        @Override
        public IType type() {
            return instance;
        }
    }

    @Override
    public IValueFloat instantiate(Object value) {
        if( value instanceof IValueFloat) return (IValueFloat)value;
        if( value instanceof Float) return instantiate(((Float) value).doubleValue());
        if( value instanceof Double) return instantiate(((Double) value).doubleValue());
        if( value instanceof String) return instantiate((String) value);
        throw new IllegalArgumentException();
    }

    public IValueFloat instantiate(String value){
        return instantiate(Double.valueOf(value));
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
    public IValueFloat instantiate(Float value) {
        return new ValueFloat(value);
    }

    @Override
    public IValueFloat instantiate(float value) {
        return new ValueFloat(value);
    }

    @Override
    public IValueFloat instantiate(double value) {
        return new ValueFloat(value);
    }

    @Override
    public IValueFloat instantiate(Double value) {
        return new ValueFloat(value);
    }
}
