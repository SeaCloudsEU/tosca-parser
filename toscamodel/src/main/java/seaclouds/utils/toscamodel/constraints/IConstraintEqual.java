package seaclouds.utils.toscamodel.constraints;

import seaclouds.utils.toscamodel.IConstraint;
import seaclouds.utils.toscamodel.IValue;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintEqual extends IConstraint {
    public IValue getRefValue();
}
