package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.ICoercedType;
import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

import java.util.Collections;
import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedCoercedType extends CoercedType implements INamedEntity{
    final String name;

    public NamedCoercedType(String name,ICoercedType baseType) {
        super(baseType.baseType(), baseType.getConstraints());
        this.name = name;
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return new CoercedType(this, Collections.singleton(constraint));
    }

    @Override
    public String name() {
        return name;
    }
}
