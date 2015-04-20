package seaclouds.utils.toscamodel.basictypes;


import seaclouds.utils.toscamodel.IValue;

/**
 * Created by pq on 19/03/2015.
 */
public interface IValueScalarUnit extends IValue {
    public double get();
    public String unit();
}
