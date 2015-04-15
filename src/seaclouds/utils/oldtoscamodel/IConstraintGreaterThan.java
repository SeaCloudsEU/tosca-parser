package seaclouds.utils.oldtoscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintGreaterThan extends  IConstraint {
    public IValue getRefValue();
    public boolean orEqual();
}
