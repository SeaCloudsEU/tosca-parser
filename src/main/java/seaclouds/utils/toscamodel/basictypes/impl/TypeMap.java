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

import com.google.common.collect.Maps;
import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;
import seaclouds.utils.toscamodel.basictypes.ITypeMap;
import seaclouds.utils.toscamodel.basictypes.IValueList;
import seaclouds.utils.toscamodel.basictypes.IValueMap;

import java.util.*;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeMap implements ITypeMap {
    static Map<IType,ITypeMap> instances = new HashMap<>();
    static public ITypeMap instance(IType t) {
        ITypeMap m = instances.get(t);
        if(m == null) {
            m = new TypeMap(t);
            instances.put(t,m);
        }
        return m;
    }
    class ValueMap implements IValueMap {
        final Map<String,IValue> value;

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(!(obj instanceof IValueMap))
                return false;
            return valueType().equals(((IValueMap) obj).valueType()) &&
                    get().equals(((IValueMap) obj).get());
        }

        public ValueMap(Map<String,IValue> value) {
            this.value = value;
        }

        @Override
        public Map<String,IValue> get() {
            return value;
        }

        @Override
        public IType valueType() {
            return valueSchema;
        }

        @Override
        public ITypeMap type() {
            return TypeMap.this;//instances.get(valueSchema);
        }
    }
    final IType valueSchema;

    public TypeMap(IType valueSchema) {
        this.valueSchema = valueSchema;
    }

    @Override
    public IType valueSchema() {
        return valueSchema;
    }

    @Override
    public IValueMap instantiate(Object value) {
        if(value instanceof Map)
            return instantiate((Map<String,Object>)value);
        return null;
    }

    @Override
    public IValueMap instantiate(Map<String,Object> value) {
        Map<String,IValue> v = new HashMap<>();
        v.putAll(Maps.transformValues(value,i->valueSchema.instantiate(i)));
        return new ValueMap(v);
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return null;
    }

    @Override
    public boolean derivesFrom(IType parent) {
        return false;
    }
}
