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
import java.util.Map;

/**
 * Created by pq on 21/04/2015.
 */
public class NodeRoot extends NamedNodeType {
    final static private NodeRoot instance = new NodeRoot();
    public static NodeRoot instance() {
        return instance;
    }
    private NodeRoot() {
        super("tosca.nodes.Root", new NodeType(null,"",Collections.emptyMap(),Collections.emptyMap()));
        this.hidden = true;
    }

    @Override
    public boolean derivesFrom(ISchemaDefinition nodeType) {
        return this.equals(nodeType);
    }
}
