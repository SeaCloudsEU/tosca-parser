package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by pq on 16/04/2015.
 */
public interface ITypeScalarUnit extends INamedEntity, IType {

    public IValueScalarUnit instantiate(float scalar,String unit);
    public IValueScalarUnit instantiate(Float scalar,String unit);
    public IValueScalarUnit instantiate(double scalar,String unit);
    public IValueScalarUnit instantiate(Double scalar,String unit);

    @Override default
    String name() { return "scalar-unit"; }
}
