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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
class Property implements IProperty {
    final IType type;
    final IValue defaultValue;

    @Override
    public String toString() {
        return type.toString() + "=" +(defaultValue == null?"?":defaultValue.toString());
    }

    public Property(IType type, Object defaultValue) {
        this.type = type;
        this.defaultValue = defaultValue==null?null:type.instantiate(defaultValue);
    }

    public Property(IProperty property) {
        this.type = property.type();
        this.defaultValue = property.defaultValue();
    }

    @Override
    public IType type() {
        return this.type;
    }

    @Override
    public IValue defaultValue() {
        return this.defaultValue;
    }

}
