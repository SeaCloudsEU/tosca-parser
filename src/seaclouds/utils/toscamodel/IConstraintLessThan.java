package seaclouds.utils.toscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintLessThan extends IConstraint {
    public IValue getRefValue();
    public boolean orEqual();
}
