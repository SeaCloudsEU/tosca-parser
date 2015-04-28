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

package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by johnnyfreak on 21/04/15.
 */
public interface ITypeRange extends INamedEntity, IType {

    @Override
    IValueRange instantiate(Object value);

    IValueRange instantiate(Object lowerBound, Object upperBound);

    IValueRange instantiate(int lowerBound, int upperBound);

    IValueRange instantiate(Integer lowerBound, Integer upperBound);

    public IValueRange instantiate(String lowerBound, String upperBound);

    @Override default
    String name() {
        return "range";
    }
}
