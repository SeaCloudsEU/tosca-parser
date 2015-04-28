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

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.ITypeStruct;

import java.util.Collections;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedStruct extends  TypeStruct implements INamedEntity {
    public boolean hidden = false;
    private String name;

    @Override
    public String toString() {
        return name();
    }

    public NamedStruct(String name,ITypeStruct unnamedType) {
        super(unnamedType);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }


    @Override
    public ITypeStruct addProperty(String propName, IType propType, Object propValue) {
        return new TypeStruct(this,description,Collections.singletonMap(propName,new Property(propType,propValue)));
    }

    public void rename(String newEntityName) {
        name = newEntityName;
    }
}
