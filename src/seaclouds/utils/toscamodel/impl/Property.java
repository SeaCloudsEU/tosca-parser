package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
class Property implements IProperty {
    final IType type;
    final IValue defaultValue;

    public Property(IType type, Object defaultValue) {
        this.type = type;
        this.defaultValue = type.instantiate(defaultValue);
    }

    public Property(IProperty property) {
        this.type = property.type();
        this.defaultValue = property.defaultValue();
    }

    @Override
    public IType type() {
        return this.type;
    }

    @Override
    public IValue defaultValue() {
        return this.defaultValue;
    }

}
