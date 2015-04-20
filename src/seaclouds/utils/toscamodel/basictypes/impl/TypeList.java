package seaclouds.utils.toscamodel.basictypes.impl;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IType;
import seaclouds.utils.toscamodel.basictypes.ITypeList;
import seaclouds.utils.toscamodel.basictypes.IValueList;

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

        return ;
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
