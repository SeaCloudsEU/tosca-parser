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

import com.google.common.collect.Sets;
import javafx.beans.binding.SetExpression;
import seaclouds.utils.toscamodel.ICoercedType;
import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by pq on 20/04/2015.
 */
public class CoercedType implements ICoercedType {
    final IType baseType;
    final Set<IConstraint> constraints;
    final Set<IConstraint> allConstraints;

    public CoercedType(IType baseType, Set<IConstraint> constraints) {
        this.baseType = baseType;
        this.constraints = new HashSet<>();
        this.allConstraints = new HashSet<>();
        constraints.addAll(constraints);
        if(baseType instanceof ICoercedType)
            allConstraints.addAll(((ICoercedType) baseType).getAllConstraints());
        allConstraints.addAll(constraints);
    }

    @Override
    public Set<IConstraint> getConstraints() {
        return constraints;
    }

    @Override
    public Set<IConstraint> getAllConstraints() {
        return this.allConstraints;
    }

    @Override
    public IType baseType() {
        return baseType;
    }


    @Override
    public IValue instantiate(Object value) {
        IValue v = baseType.instantiate(value);
        boolean constraintsHold = true;
        for (IConstraint constraint : constraints) {
            constraintsHold = constraintsHold && constraint.verify(v);
        }
        return constraintsHold?v:null;
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return new CoercedType(this.baseType, Sets.union(constraints,Collections.singleton(constraint)) );
    }

    @Override
    public boolean derivesFrom(IType parent) {
        if(this.equals(parent))
            return true;
        return baseType.derivesFrom(parent);
    }
}
