package seaclouds.utils.oldtoscamodel;

import  seaclouds.utils.oldtoscamodel.impl.ToscaEnvironment;

//factory class
public final class Tosca {
	public static IToscaEnvironment createEnvironment() {
        IToscaEnvironment te = new ToscaEnvironment();
        return  te;
    }
}
