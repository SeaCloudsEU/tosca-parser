package seaclouds.utils.toscamodel;

import java.util.Set;

/**
 * Created by pq on 20/04/2015.
 */
public interface ICoercedType extends IType {
    public Set<IConstraint> getConstraints();
    public Set<IConstraint> getAllConstraints();
    public IType baseType();
}
