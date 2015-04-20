package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

/**
 * Created by pq on 17/04/2015.
 */
class TypeBinding  {
    public Class<?> getBoundInterface() {
        return boundInterface;
    }

    public void setBoundInterface(Class<?> boundInterface) {
        this.boundInterface = boundInterface;
    }

    Class<?> boundInterface;

}
