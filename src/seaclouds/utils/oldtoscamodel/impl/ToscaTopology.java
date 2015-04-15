package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.ITopology;

/**
 * Created by pq on 13/04/2015.
 */
class ToscaTopology implements ITopology {
    private ToscaEnvironment toscaEnvironment;

    public ToscaTopology(ToscaEnvironment toscaEnvironment) {
        this.toscaEnvironment = toscaEnvironment;
    }
}
