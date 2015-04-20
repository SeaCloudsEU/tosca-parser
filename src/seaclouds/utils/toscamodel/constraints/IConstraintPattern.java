package seaclouds.utils.toscamodel.constraints;

import seaclouds.utils.toscamodel.IConstraint;

import java.util.regex.Pattern;

/**
 * Created by pq on 19/03/2015.
 */
public interface IConstraintPattern extends IConstraint {
    public Pattern getPattern();
}
