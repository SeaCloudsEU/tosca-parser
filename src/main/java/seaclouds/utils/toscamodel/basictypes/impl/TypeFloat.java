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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof ValueFloat)) return false;
            return this.value == ((ValueFloat) obj).value;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
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
