/*
 * Copyright 2015 Universita' di Pisa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(!(obj instanceof ValueScalarUnit)) return false;
            ValueScalarUnit o = (ValueScalarUnit) obj;
            return this.scalar == o.scalar && this.unit.equals(o.unit);
        }

        @Override
        public int hashCode() {
            return Double.hashCode(this.scalar) * unit.hashCode();
        }
    }
}
