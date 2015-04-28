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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (! (obj instanceof ValueBoolean)) return false;
            ValueBoolean o = (ValueBoolean) obj;
            return this.value == o.value;
        }

        @Override
        public int hashCode() {
            return Boolean.hashCode(value);
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
