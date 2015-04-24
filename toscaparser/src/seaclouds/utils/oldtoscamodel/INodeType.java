package seaclouds.utils.oldtoscamodel;

/**
 * Created by pq on 19/03/2015.
 */
public interface INodeType extends  ITypeStruct {
    @Override
    INodeType getSupertype();

    public String getName();
    public String getDescription();

    public IValueStruct getAttributes();
}
