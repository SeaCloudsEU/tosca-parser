package seaclouds.utils.toscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintInRange extends IConstraint {
    public Value minValue();
    public Value maxValue();
}
