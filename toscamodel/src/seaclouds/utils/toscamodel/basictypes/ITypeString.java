package seaclouds.utils.toscamodel.basictypes;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IType;

/**
 * Created by pq on 15/04/2015.
 */
public interface ITypeString extends INamedEntity, IType {
    @Override
    IValueString instantiate(Object value);

    IValueString instantiate(String value);

    @Override default
    String name() {
         return "string";
     }
}
