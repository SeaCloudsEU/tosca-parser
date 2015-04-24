package seaclouds.utils.toscamodel.constraints;

import seaclouds.utils.toscamodel.IConstraint;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintLength extends IConstraint {
    public int minLength();
    public int maxLength();
}
