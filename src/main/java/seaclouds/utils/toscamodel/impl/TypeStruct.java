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

package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeStruct  extends  SchemaDefinition implements ITypeStruct {
    public TypeStruct(ITypeStruct baseType, String description, Map<String, IProperty> properties) {
        super(baseType, description, properties);
    }
    public TypeStruct(ISchemaDefinition copyFrom) {
        super(copyFrom.baseType(),copyFrom.description(),copyFrom.allProperties());
    }

    @Override
    public IValueStruct instantiate(Object value) {
        return null;
    }

    public IValueStruct instantiate(IValueStruct v) {
        if(v.type().equals(this))
            return v;
        else if (v.type().isCompatible(this)) {
            return instantiate(v.get());
        } else
            return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ITypeStruct))
            return false;
        return declaredProperties.equals(((ITypeStruct) obj).declaredProperties())
                && ( (((ITypeStruct) obj).baseType() == this.baseType()) || this.baseType().equals(((ITypeStruct) obj).baseType()));
    }

    @Override
    public int hashCode() {
        return allProperties().hashCode();
    }

    @Override
    public IValueStruct instantiate(Map<String, Object> value) {
        return new StructValue(this,valueConvert(value));
    }

    @Override
    public IType coerce(IConstraint constraint) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean derivesFrom(IType parent) {
        return this.equals(parent) || ((ITypeStruct)baseType).derivesFrom(parent);
    }

    @Override
    public ITypeStruct baseType() {
        return (ITypeStruct) super.baseType();
    }

    @Override
    public ITypeStruct addProperty(String propName, IType propType,Object propValue) {
        return new TypeStruct(baseType(),description,extendSchema(propName, propType, propValue));
    }

    @Override
    public ISchemaDefinition changeDescription(String newDescription) {
        return new TypeStruct(baseType(),newDescription,declaredProperties());
    }

    @Override
    public boolean derivesFrom(ITypeStruct parentSchema) {
        return parentSchema.equals(this) || (baseType()!= null && baseType().derivesFrom(parentSchema));
    }
}
