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

package seaclouds.utils.toscamodel;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

/**
 * Created by pq on 16/04/2015.
 */
public interface IToscaEnvironment {
    public INamedEntity getNamedEntity(String entityName);

    public INamedEntity registerType(String entityName, IType t);
    public INamedEntity registerNodeType(String entityName, INodeType t);
    public INamedEntity registerNodeTemplate(String entityName, INodeTemplate t);

    /***
     *  Imports a named entity from another environment, with all its supertypes and used data types.
     *  When an entity with the same name is present it is assumed to be consistent and used without importing a new one
     * @param entity the named entity to be imported
     */
    public INamedEntity importWithSupertypes(INamedEntity entity);

    INodeTemplate newTemplate(INodeType type);

    public Iterable<INodeTemplate> getNodeTemplatesOfType(INodeType rootType);
    public Iterable<INodeType> getNodeTypesDerivingFrom(INodeType rootType);
    public Iterable<ITypeStruct> getTypesDerivingFrom(ITypeStruct rootType);

    public void renameEntity(String entityName, String newEntityName);


    public void hideEntity(String entityName);
    public void unhideEntity(String entityName);

    /**
     *
     * @param input a reader containing the string to parse as tosca
     * @param hideTypes if true, types imported this way will not be written to the output file
     */
    public void readFile(Reader input, boolean hideTypes);

    public void writeFile(Writer output);

}
