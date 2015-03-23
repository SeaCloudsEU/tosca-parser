package seaclouds.utils.toscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintGreaterThan extends  IConstraint {
    public Value getRefValue();
    public boolean orEqual();
}
