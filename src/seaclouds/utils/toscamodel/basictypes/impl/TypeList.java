package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.IValue;
import seaclouds.utils.toscamodel.basictypes.ITypeList;
import seaclouds.utils.toscamodel.basictypes.IValueList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class TypeList implements ITypeList {
    static Map<IType,ITypeList> instances = new HashMap<>();
    static ITypeList instance(IType t) {
        ITypeList l = instances.get(t);
        if(l == null) {
            l = new TypeList(t);
            instances.put(t,l);
        }
        return l;
    }
    class ValueList implements  IValueList {
        final List<IValue> value;

        public ValueList(List<IValue> value) {
            this.value = value;
        }

        @Override
        public List<IValue> get() {
            return value;
        }

        @Override
        public IType valueType() {
            return valueSchema;
        }

        @Override
        public ITypeList type() {
            return TypeList.this;//instances.get(valueSchema);
        }
    }
    final IType valueSchema;

    public TypeList(IType valueSchema) {
        this.valueSchema = valueSchema;
    }

    @Override
    public IType valueSchema() {
        return valueSchema;
    }

    @Override
    public IValueList instantiate(Object value) {
        return null;
    }

    @Override
    public IValueList instantiate(List value) {
        List<IValue> v = new ArrayList<>();
        for (Object o : value) {
            v.add(valueSchema.instantiate(o));
        }
        return new ValueList(v);
    }

    @Override
    public IType coerce(IConstraint constraint) {
        return null;
    }

    @Override
    public boolean derivesFrom(IType parent) {
        return false;
    }
}
