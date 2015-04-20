package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.IValue;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueRange extends IValue {
    public int lowerBound();
    public int upperBound();
}
