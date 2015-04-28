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

package seaclouds.utils.toscamodel;

import java.util.Map;
import java.util.Set;

/**
 * Created by pq on 16/04/2015.
 */
public interface ISchemaDefinition {
    Map<String,IProperty> declaredProperties();
    Map<String,IProperty> allProperties();

    ISchemaDefinition baseType();

    String description();

    ISchemaDefinition addProperty(String propName,IType propType,Object defaultValue);
    ISchemaDefinition changeDescription(String newDescription);

    boolean derivesFrom(ISchemaDefinition parentSchema);

    /**
     * equals yields true when the schema, name and declaration environment are equal.
     * isCompatible yields true when the properties are compatible
     *
     * @param otherType another struct type
     * @return true if schema are compatible
     */
    boolean isCompatible(ISchemaDefinition otherType);

}
