/*
 * Copyright 2015 UniversitÓ di Pisa
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
import seaclouds.utils.toscamodel.INodeType;
import seaclouds.utils.toscamodel.IProperty;
import seaclouds.utils.toscamodel.IType;

import java.util.Collections;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedNodeType extends  NodeType implements INamedEntity {
    private String name;
    public boolean hidden = false;

    @Override
    public String toString() {
        return name();
    }

    public NamedNodeType(String name, NodeType unnamedNodeType) {
        super((NodeType)unnamedNodeType.baseType, unnamedNodeType.description,unnamedNodeType.declaredProperties ,unnamedNodeType.attributes);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public INodeType addProperty(String propName, IType propType, Object defaultValue) {
        if(propName == null)
            return new NodeType(this,description,Collections.emptyMap(),Collections.emptyMap());
        return new NodeType(this,description, Collections.singletonMap(propName,new Property(propType, defaultValue)),Collections.emptyMap());
    }

    @Override
    public INodeType changeDescription(String newDescription) {
        return new NodeType((NodeType)baseType,newDescription,declaredProperties,attributes);
    }

    public void rename(String newEntityName) {
        name = newEntityName;
    }
}
