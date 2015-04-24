package seaclouds.utils.toscamodel.impl;

import com.google.common.collect.Maps;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.events.*;
import org.yaml.snakeyaml.nodes.Tag;
import seaclouds.utils.toscamodel.*;
import org.yaml.snakeyaml.emitter.Emitter;
import seaclouds.utils.toscamodel.basictypes.*;
import seaclouds.utils.toscamodel.basictypes.impl.TypeString;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by pq on 22/04/2015.
 */
public class ToscaEmitter {
    public final DumperOptions options = new DumperOptions();
    public void WriteDocument(Writer target,IToscaEnvironment environment) throws IOException {
        StringWriter sw = new StringWriter();
        Emitter e = new Emitter(sw, options);
        e.emit(new StreamStartEvent(null,null));
        EmitDocument(e, environment);
        e.emit(new StreamEndEvent(null,null));
        target.write(sw.toString().replaceAll("! ", "").replaceAll("\"",""));
    }

    private void WriteScalarValue(Emitter e, Object scalar) throws  IOException {
        e.emit(new ScalarEvent(null, null, new ImplicitTuple(true, false), scalar.toString(), null, null, '\0'));
    }
    private void StartMap(Emitter e) throws IOException {
        e.emit(new MappingStartEvent(null, null, true, null, null, false));
    }
    private void EndMap(Emitter e) throws IOException {
        e.emit(new MappingEndEvent(null, null));
    }

    private void WriteStructValue(Emitter e, Map<String,IValue> struct) throws IOException {
        StartMap(e);
        for (Map.Entry<String, IValue> entry : struct.entrySet()) {
            if(entry.getValue() == null)
                continue;
            WriteScalarValue(e, entry.getKey());
            WriteValue(e, entry.getValue());
        }
        EndMap(e);
    }
    private void WriteProperty(Emitter e, IProperty p) throws  IOException {
        StartMap(e);
        WriteScalarValue(e, "type");
        if(p.type() instanceof INamedEntity)
        {
            WriteScalarValue(e,((INamedEntity) p.type()).name());
        } else if (p.type() instanceof  ICoercedType){
            WriteScalarValue(e,((INamedEntity)((ICoercedType) p.type()).baseType()).name());
            WriteScalarValue(e,"constraints");
            StartSequence(e);
            for (IConstraint constraint : ((ICoercedType) p.type()).getConstraints()) {
                WriteScalarValue(e,constraint);
            }
            EndSequence(e);
        } else {
            throw new NotImplementedException();
        }
        EndMap(e);
    }

    private void WriteProperties(Emitter e, Map<String,IProperty> struct) throws IOException {
        StartMap(e);
        for (Map.Entry<String,IProperty> entry : struct.entrySet()) {
            WriteScalarValue(e, entry.getKey());
            WriteProperty(e, entry.getValue());
        }
        EndMap(e);
    }

    private void WriteListValue(Emitter e, List<IValue> list) throws  IOException {
        StartSequence(e);
        for (IValue iValue : list) {
            WriteValue(e, iValue);
        }
        EndSequence(e);

    }

    private void EndSequence(Emitter e) throws IOException {
        e.emit(new SequenceEndEvent(null, null));
    }

    private void StartSequence(Emitter e) throws IOException {
        e.emit(new SequenceStartEvent(null,null,false,null,null,false));
    }

    private void WriteValue(Emitter e, IValue value) throws  IOException{
        if(value instanceof IValueString) {
            WriteScalarValue(e,((IValueString) value).get());
        } else if (value instanceof IValueInteger) {
            WriteScalarValue(e,((IValueInteger) value).get());
        } else if (value instanceof IValueFloat){
            WriteScalarValue(e, ((IValueFloat) value).get());
        } else if (value instanceof IValueBoolean) {
            WriteScalarValue(e, ((IValueBoolean) value).get());
        } else if (value instanceof IValueList) {
            WriteListValue(e,((IValueList) value).get());
        } else if (value instanceof IValueStruct) {
            WriteStructValue(e, ((IValueStruct) value).get());
        } else if (value instanceof IValueMap) {
            WriteStructValue(e,((IValueMap) value).get());
        } else if (value instanceof  IValueScalarUnit) {
            WriteScalarValue(e,Double.toString(((IValueScalarUnit) value).get()) + " " + ((IValueScalarUnit) value).unit());
        } else {
            throw new NotImplementedException();
        }
    }
    private void WriteNodeTemplate(Emitter e, INodeTemplate t) throws  IOException {
        StartMap(e);
        if (t.description() != null && t.description().length() > 0) {
            WriteScalarValue(e, "description");
            WriteScalarValue(e, t.description());
        }
        if (t.baseType() != null) {
            WriteScalarValue(e, "type");
            WriteScalarValue(e, ((INamedEntity) t.baseType()).name());
        }
        if (!t.declaredProperties().isEmpty()) {
            WriteScalarValue(e, "properties");
            WriteProperties(e, t.declaredProperties());
        }
        if (!t.declaredAttributes().isEmpty()) {
            WriteScalarValue(e, "attributes");
            WriteStructValue(e, t.declaredAttributes());
        }
        EndMap(e);
    }
    private void WriteNodeType(Emitter e, INodeType t) throws  IOException {
        StartMap(e);
        if(t.description() != null && t.description().length() > 0) {
            WriteScalarValue(e,"description");
            WriteScalarValue(e,t.description());
        }
        if(t.baseType() != null)
        {
            WriteScalarValue(e,"derived_from");
            WriteScalarValue(e,((INamedEntity)t.baseType()).name());
        }
        if(!t.declaredProperties().isEmpty()) {
            WriteScalarValue(e, "properties");
            WriteProperties(e, t.declaredProperties());
        }
        if(!t.declaredAttributes().isEmpty()) {
            WriteScalarValue(e,"attributes");
            WriteStructValue(e,t.declaredAttributes());
        }
        EndMap(e);
    }

    private void EmitDocument(Emitter e, IToscaEnvironment environment) throws IOException {
        //e.emit(new DocumentStartEvent(null,null,options.isExplicitStart(),options.getVersion(),options.getTags()));
        e.emit(new DocumentStartEvent(null,null,false,options.getVersion(),options.getTags()));
        StartMap(e);
            WriteScalarValue(e, "tosca_definitions_version"); WriteScalarValue(e,"tosca_simple_yaml_1_0_0");
            //e.emit(new ScalarEvent(null,null,new ImplicitTuple(true,false),"description",null,null,'\0'));
            //e.emit(new ScalarEvent(null,null,new ImplicitTuple(true,false),"some description",null,null,'\0'));
            //e.emit(new ScalarEvent(null,null,new ImplicitTuple(true,false),"type_definitions",null,null,'\0'));
            //e.emit(new MappingStartEvent(null,null,true,null,null,false));
            //e.emit(new ScalarEvent(null,null,new ImplicitTuple(true,false),"tosca_definitions_version",null,null,'\0'));
            //e.emit(new ScalarEvent(null,null,new ImplicitTuple(true,false),"tosca_simple_yaml_1_0_0",null,null,'\0'));
            //e.emit(new MappingEndEvent(null,null));
            WriteScalarValue(e, "node_types"); StartMap(e);
                for (INodeType nodeType : environment.getNodeTypesDerivingFrom((INodeType) environment.getNamedEntity("tosca.nodes.Root"))) {
                    WriteScalarValue(e,((INamedEntity)nodeType).name());
                    WriteNodeType(e,nodeType);
                }
            EndMap(e);
            WriteScalarValue(e, "topology_template"); StartMap(e);
                WriteScalarValue(e, "node_templates"); StartMap(e);
                    for (INodeTemplate template : environment.getNodeTemplatesOfType((INodeType) environment.getNamedEntity("tosca.nodes.Root"))) {
                        WriteScalarValue(e,((INamedEntity)template).name());
                        WriteNodeTemplate(e,template);
                    }
                EndMap(e);
                if(environment instanceof ToscaEnvironment && ((ToscaEnvironment) environment).relationshipTemplate != null)
                {
                    WriteScalarValue(e, "relationship_templates");
                    WriteDummyRelationshipTemplate(e,((ToscaEnvironment) environment).relationshipTemplate);
                }
            EndMap(e);
        EndMap(e);
        e.emit(new DocumentEndEvent(null, null, options.isExplicitEnd()));
    }

    private void DumpAny(Emitter e, Object o) throws IOException {
        if(o instanceof Map)
        {
            StartMap(e);
            for(Map.Entry<String,Object> entry: ((Map<String,Object>) o).entrySet()) {
                WriteScalarValue(e,entry.getKey());
                DumpAny(e,entry.getValue());
            }
            EndMap(e);

        } else if (o instanceof List) {
            StartSequence(e);
            for(Object object : (List)o ) {
                DumpAny(e,object);
            }
            EndSequence(e);
        } else  {
            WriteScalarValue(e,o);
        }
    }
    private void WriteDummyRelationshipTemplate(Emitter e, Object relationshipTemplate) throws IOException {
        DumpAny(e,relationshipTemplate);
    }


}
