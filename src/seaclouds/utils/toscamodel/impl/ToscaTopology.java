package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.ITopology;

/**
 * Created by pq on 13/04/2015.
 */
class ToscaTopology implements ITopology {
    private ToscaEnvironment toscaEnvironment;

    public ToscaTopology(ToscaEnvironment toscaEnvironment) {
        this.toscaEnvironment = toscaEnvironment;
    }
}
