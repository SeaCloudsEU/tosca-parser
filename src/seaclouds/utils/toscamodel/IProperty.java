package seaclouds.utils.toscamodel;


import java.util.Set;

/**
 * Created by pq on 15/04/2015.
 */
public interface IProperty {
    INamedEntity type();
    IValue defaultValue();
    Set<? extends IConstraint> constraints();
}
