package seaclouds.utils.toscamodel;

/**
 * Created by pq on 16/04/2015.
 */
public interface IType {
    IValue instantiate(Object value);
    IType coerce(IConstraint constraint);
    IProperty makeProperty(Object defaultValue);
    boolean derivesFrom(IType parent);
}
