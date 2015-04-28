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

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(!(obj instanceof ValueString)) return false;
            return this.value.equals(((ValueString) obj).value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
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
