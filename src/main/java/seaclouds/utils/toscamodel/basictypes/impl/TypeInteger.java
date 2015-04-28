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

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(!(obj instanceof ValueInteger)) return false;
            return this.value == ((ValueInteger) obj).value;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(value);
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
