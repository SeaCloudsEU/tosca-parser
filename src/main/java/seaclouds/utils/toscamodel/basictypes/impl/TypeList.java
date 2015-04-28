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
import seaclouds.utils.toscamodel.basictypes.ITypeList;
import seaclouds.utils.toscamodel.basictypes.IValueList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeList implements ITypeList {
    static Map<IType,ITypeList> instances = new HashMap<>();
    static public ITypeList instance(IType t) {
        ITypeList l = instances.get(t);
        if(l == null) {
            l = new TypeList(t);
            instances.put(t,l);
        }
        return l;
    }
    class ValueList implements  IValueList {
        final List<IValue> value;

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(!(obj instanceof IValueList))
                return false;
            return ((IValueList) obj).valueType().equals(valueType()) &&
                    ((IValueList) obj).get().equals(this.get());
        }

        @Override
        public int hashCode() {
            return get().hashCode();
        }

        public ValueList(List<IValue> value) {
            this.value = value;
        }

        @Override
        public List<IValue> get() {
            return value;
        }

        @Override
        public IType valueType() {
            return valueSchema;
        }

        @Override
        public ITypeList type() {
            return TypeList.this;//instances.get(valueSchema);
        }
    }
    final IType valueSchema;

    public TypeList(IType valueSchema) {
        this.valueSchema = valueSchema;
    }

    @Override
    public IType valueSchema() {
        return valueSchema;
    }

    @Override
    public IValueList instantiate(Object value) {
        return null;
    }

    @Override
    public IValueList instantiate(List value) {
        List<IValue> v = new ArrayList<>();
        for (Object o : value) {
            v.add(valueSchema.instantiate(o));
        }
        return new ValueList(v);
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
