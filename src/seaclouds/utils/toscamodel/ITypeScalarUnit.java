package seaclouds.utils.toscamodel;

/**
 * Created by pq on 16/04/2015.
 */
public interface ITypeScalarUnit extends INamedType {
    public IValueScalarUnit instantiate(float scalar,String unit);
}
