package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;
import seaclouds.utils.toscamodel.basictypes.ITypeScalarUnit;
import seaclouds.utils.toscamodel.basictypes.IValueScalarUnit;
import seaclouds.utils.toscamodel.impl.CoercedType;

import java.util.Collections;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public class TypeScalarUnit implements ITypeScalarUnit {
    static ITypeScalarUnit instance = new TypeScalarUnit();
    public static ITypeScalarUnit instance() { return instance; }

    @Override
    public IValueScalarUnit instantiate(float scalar, String unit) {
        return new ValueScalarUnit(scalar, unit);
    }

    @Override
    public IValueScalarUnit instantiate(Float scalar, String unit) {
        return new ValueScalarUnit(scalar, unit);
    }

    @Override
    public IValueScalarUnit instantiate(double scalar, String unit) {
        return new ValueScalarUnit(scalar, unit);
    }

    @Override
    public IValueScalarUnit instantiate(Double scalar, String unit) {
        return new ValueScalarUnit(scalar, unit);
    }

    @Override
    public IValue instantiate(Object value) {
        //if(value instanceof Double) return instantiate((Double) value);
        //if(value instanceof Float) return instantiate((Float) value);
        if(value instanceof IValueScalarUnit) return (IValueScalarUnit) value;
        if(value instanceof String) {
            String[] res = ((String) value).split(" ", 2);
            return instantiate(Integer.valueOf(res[0]),res[1]);
        }
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

    class ValueScalarUnit implements IValueScalarUnit{
        String unit;
        double scalar;

        public ValueScalarUnit(double scalar, String unit){
            this.unit = unit;
            this.scalar = scalar;
        }

        public ValueScalarUnit(Double scalar, String unit){
            this(scalar.doubleValue(), unit);
        }

        public ValueScalarUnit(Float scalar, String unit){
            this(scalar.doubleValue(), unit);
        }

        @Override
        public double get() {
            return this.scalar;
        }

        @Override
        public String unit() {
            return this.unit;
        }

        @Override
        public IType type() {
            return instance;
        }
    }
}
