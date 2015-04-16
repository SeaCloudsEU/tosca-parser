package seaclouds.utils.toscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintInRange extends IConstraint {
    public IValue minValue();
    public IValue maxValue();
}
