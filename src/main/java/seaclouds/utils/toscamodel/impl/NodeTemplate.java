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

import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NodeTemplate extends NodeValue implements INodeTemplate{
    public NodeTemplate(NodeType baseType, String description, Map<String, IProperty> properties,Map<String,? extends Object> attributes) {
        super(baseType, description, properties, attributes);
    }

    @Override
    public INodeTemplate addProperty(String propName, IType propType, Object defaultValue) {
        return new NodeTemplate((NodeType)baseType,description,extendSchema(propName, propType, defaultValue),attributes);
    }

    @Override
    public INodeTemplate changeDescription(String newDescription) {
        return new NodeTemplate((NodeType)baseType,newDescription,declaredProperties,attributes);
    }
}
