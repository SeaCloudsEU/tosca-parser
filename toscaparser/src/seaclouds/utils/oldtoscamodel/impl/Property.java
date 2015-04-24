package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pq on 08/04/2015.
 */
public class Property implements IProperty {
    private final String name;
    private final IType type;
    private final IValue defaultValue;
    private final Set<IConstraint> constraints;
    public Property(String n, IType t, IValue dv, Collection<? extends  IConstraint> ct){
        name = n;
        type = t;
        defaultValue = dv;
        constraints = new HashSet<IConstraint>();
        if (ct!= null)
            constraints.addAll(ct);
    }
    public Property(IProperty copyFrom) {
        name = copyFrom.getName();
        type = copyFrom.getType();
        //todo : check if default value matches type and constraints
        defaultValue = copyFrom.getDefaultValue();
        constraints = new HashSet<IConstraint>();
        constraints.addAll(copyFrom.getConstraints());
    }
    public Property addConstraint(String newName, IConstraint ct) {
        Collection<IConstraint> cl = Collections.singleton(ct);
        cl.addAll(constraints);
        return new Property(newName,type,defaultValue, cl);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public IType getType() {
        return type;
    }

    @Override
    public IValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Set<IConstraint> getConstraints() {
        return constraints;
    }
};
