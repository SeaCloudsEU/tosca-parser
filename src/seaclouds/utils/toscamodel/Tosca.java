package seaclouds.utils.toscamodel;

import  seaclouds.utils.toscamodel.impl.ToscaEnvironment;

//factory class
public final class Tosca {
	public static IToscaEnvironment createEnvironment() {
        IToscaEnvironment te = new ToscaEnvironment();
        return  te;
    }
}
