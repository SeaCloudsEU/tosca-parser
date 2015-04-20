package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.basictypes.ITypeString;
import seaclouds.utils.toscamodel.ITypeStruct;
import seaclouds.utils.toscamodel.basictypes.IValueString;

/**
 * Created by pq on 20/04/2015.
 */
class FactoryBasicTypes {
    //TODO: copy stuff from basicTypeFactory
    static class ValueString implements  IValueString {

        public ValueString(String value) {
            this.value = value;
        }

        @Override
        public ITypeString type() {
            return string;
        }

        String value;
    }

    static ITypeString string = new ITypeString() {
        @Override
        public IValueString instantiate(Object value) {
            return instantiate(value.toString());
        }

        @Override
        public IValueString instantiate(String value) {
            return new ValueString(value);
        }

        @Override
        public String name() {
            return "string";
        }
    };

}
