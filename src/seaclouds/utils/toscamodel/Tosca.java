package seaclouds.utils.toscamodel;

import seaclouds.utils.toscamodel.impl.ToscaEnvironment;
/**
 * Created by pq on 16/04/2015.
 */
public class Tosca {
    public static IToscaEnvironment newEnvironment() {
        return new ToscaEnvironment();
    }
}
