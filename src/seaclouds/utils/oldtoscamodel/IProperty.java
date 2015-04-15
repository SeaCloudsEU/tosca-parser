package seaclouds.utils.oldtoscamodel;

import java.util.Set;

/**
 * Created by pq on 19/03/2015.
 */
public interface IProperty {
    public String getName();

    public IType getType();
    public IValue getDefaultValue();
    public Set<IConstraint> getConstraints();
}

