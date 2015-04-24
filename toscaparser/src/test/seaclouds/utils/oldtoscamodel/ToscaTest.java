package seaclouds.utils.oldtoscamodel;

import org.junit.Test;
import seaclouds.utils.oldtoscamodel.impl.ValueFactory;
import seaclouds.utils.oldtoscamodel.impl.ValueStruct;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ToscaTest {
    private static final Logger logger = Logger.getLogger("logger");


    @Test
    public void Test1(){
        assertNotNull(Tosca.createEnvironment());
    }

    class PlatformAttributes implements IValueStruct {
        @Override
        public ITypeStruct getType() {
            return null;// il tipo per seaclouds.nodes.platform
        }

        @Override
        public IValue getProperty(String propertyName) {
            return null; //
        }

        @Override
        public void setProperty(String propertyName, IValue value) {

        }

        @Override
        public IValueStruct getRepresentation(Class<? extends IValueStruct> representation) {
            if (representation.isInstance(this) ) return this; else return null;
        }
    }


    public interface NodeNetworkInfo extends IValueStruct {
        IValueScalarUnit getOutputBandwidth();
        IValueScalarUnit getInboundBandwidth();
        IValueStruct getMyComplexType();
        IValueBoolean getLoadBalancing();
        IValueInteger getNumberOfIpv4();
    }
    public interface SeacloudsNodesCompute extends IValueStruct {
        NodeNetworkInfo getNetworking();
        void setNetworking(NodeNetworkInfo info);
        IValueString getStorageType();
        void setCpus(int numberofcpus);
        void setCpus(IValue numberofcpus);
        void setPapayaDb(boolean supportedPapayaDb);
        void setScalingHorizontal(String scalingMode);
    }

    public void Test2() {
        List<IValue> properties = new ArrayList<>();
        SeacloudPlatform p = new ....;


        p.setServiceName("meh");
        p.setBandwidthPricing(new ValueStruct() {
        })


        IToscaEnvironment env = Tosca.createEnvironment();
        env.getTypeManager().bindTypeToInterface(NodeNetworkInfo.class, "seaclouds.types.NodeNetworkInfo");
        env.getTypeManager().bindTypeToInterface(SeacloudsNodesCompute.class, "seaclouds.nodes.Compute");
        INodeType snc = (INodeType) env.getTypeManager().getNodeType("seaclouds.nodes.compute");
        INodeType cloudOffering;
        SeacloudsNodesCompute cloudRequirement;
        if(cloudOffering.getAttributes() instanceof SeacloudsNodesCompute) {
            SeacloudsNodesCompute cloudOfferingAttributes = (SeacloudsNodesCompute) cloudOffering.getAttributes();
            for (String propertyName : snc.getProperties().keySet()) {
                IValue p1 = cloudOfferingAttributes.getProperty(propertyName);
                IValue p2 = cloudRequirement.getProperty(propertyName);
                if (MatchMaker.getComparator(propertyName).compare(p1,p2))
                    reject(cloudOffering);
            }
            accept(cloudOffering);
        }

        env.getTypeManager().getNodeType("myNodeType");
        MyNodeType v = (MyNodeType) t.getAttributes();
        t.getSupertype().equals(env.getTypeManager().getNodeType("seaclouds.nodes.compute"));
        getAttributes() .getProperty("networking");


        INodeType snc = (INodeType) env.getTypeManager().getNodeType("seaclouds.nodes.compute");

        IValueStruct attributes = snc.getNewValue();
        attributes.setProperty("cpus", ValueFactory.newInteger(3));
        attributes.setProperty("scaling_horizontal", ValueFactory.newString("auto"));

        SeacloudsNodesCompute attributes = (SeacloudsNodesCompute)snc.getNewValue();
        attributes.setCpus(3);
        attributes.setCpus(ValueFactory.newInteger(2));
        attributes.setScalingHorizontal("auto");
        attributes.setProperty("cpus",ValueFactory.newInteger(4) );
        attributes.setNetworking();

        env.getTypeManager().createNewNodeType("seaclouds.nodes.Platform.GoogleAppEngine", "description", "seaclouds.nodes.Compute", properties, attributes);
    }
}