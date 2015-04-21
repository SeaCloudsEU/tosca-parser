package seaclouds.utils.toscamodel.impl;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.events.*;
import seaclouds.utils.toscamodel.*;

import java.io.Reader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Created by pq on 13/04/2015.
 */
public final class Parser {
    private class ParseError extends  RuntimeException {};
    final boolean loadAsShared;
    static final Logger logger = Logger.getLogger("logger");

    IToscaEnvironment env;
    Yaml yaml = new Yaml();
    Parser(IToscaEnvironment env){
        this.env = env;
        this.loadAsShared = false;
    }

    Parser(IToscaEnvironment env, boolean sharedTypes) {
        this.env = env;
        this.loadAsShared = sharedTypes;
    }

    public void Parse(Reader input) {
        Iterator<Event> it = yaml.parse(input).iterator();
        Event e = it.next();
        Expect(e.is(Event.ID.StreamStart));

        while (it.hasNext()) {
            e = it.next();
            if (e.is(Event.ID.DocumentStart))
            {
                ParseDocument(e,it);
            } else {
                Expect(e.is(Event.ID.StreamEnd));
                Expect(!it.hasNext());
            }
        }
    }

    ///////////////////////////////////////////////

    private void Expect(boolean guard) {
        if (!guard)
            throw new ParseError();
    }
    private String GetString(Event e) {
        Expect(e.is(Event.ID.Scalar));
        Expect(e instanceof ScalarEvent);
        return ((ScalarEvent)e).getValue();
    }
    private void ParseMapping(Event e,Iterator<Event> it, BiConsumer<String,Event> fn) {
        Expect(e.is(Event.ID.MappingStart));
        while (it.hasNext())
        {
            e = it.next();
            if(e.is(Event.ID.MappingEnd)) {
                return;
            } else if (e.is(Event.ID.Scalar) && e instanceof ScalarEvent && it.hasNext()) {
                String key = ((ScalarEvent) e).getValue();
                Event value = it.next();
                fn.accept(key.intern(),value);
            } else {
                throw new ParseError();
            }
        }
        throw new ParseError();
    }

    // Accepts: a list block
    // executes the consumer for each element of the list.
    private void ParseSequence(Event e, Iterator<Event> it, Consumer<Event> fn) {
        Expect(e.is(Event.ID.SequenceStart));
        while(it.hasNext()) {
            e = it.next();
            fn.accept(e);
        }
    }

    // Accepts: any YAML
    // does nothing but eating all events until the current element parsing is completed.
    // To be used for non implemented/ignored stuff
    private void Skip(Event e, Iterator<Event> it) {
        if (e.is(Event.ID.Scalar)) {

        } else if (e.is(Event.ID.SequenceStart)) {
            ParseSequence(e, it, m -> Skip(m, it) );
        } else if (e.is(Event.ID.MappingStart)){
            ParseMapping(e,it,(k,v)->Skip(v,it));
        }

    }

    private Object ParseAny(Event e, Iterator<Event> it) {
        if (e.is(Event.ID.Scalar)) {
            return ScalarParse(e);
        } else if (e.is(Event.ID.SequenceStart)) {
            List res = new ArrayList<>();
            ParseSequence(e, it, m -> {
                res.add(ParseAny(m,it));
            } );
            return res;
        } else if (e.is(Event.ID.MappingStart)){
            Map<String,Object> res = new HashMap<>();
            ParseMapping(e,it,(k,v)->{
                res.put(k, ParseAny(v,it));
            });
            return res;
        }
        return null;

    }

    private String ScalarParse(Event e) {
        // TODO not implemented
        return ((ScalarEvent)e).getValue();
    }

    /////////////////////////////////////////------////////

    private void ParseDocument(Event e,Iterator<Event> it) {
        Expect(e.is(Event.ID.DocumentStart));
        e = it.next();
        ParseMapping(e, it, (key, value) -> {
            switch (key) {
                case "tosca_definitions_version":
                    String s = GetString(value);
                    Expect(s.equals("tosca_simple_yaml_1_0_0"));
                    break;
                case "description":
                    Expect(value.is(Event.ID.Scalar));
                    Expect(value instanceof ScalarEvent);
                    break;
                case "node_types":
                    Expect(value.is(Event.ID.MappingStart));
                    ParseNodeTypes(value, it);
                    break;
                default:
                    throw new ParseError();
            }
        });
    }

    private void ParseNodeTypes(Event e,Iterator<Event>it){
        ParseMapping(e, it, (nodeTypeName, mapHead) -> {
            ParseNodeType(mapHead, it, nodeTypeName);
        });
    }
    private void ParseNodeTemplate (Event e, Iterator<Event>it, String templateName) {
        final String[] parentTypeName = {null};
        final String[] description = {null};
        final Map<String,Property> properties = new HashMap<>();
        final Map<String,Object> attributes = new HashMap<>();
        ParseMapping(e,it, (key, value) -> {
            switch (key) {
                case "type":
                    Expect(parentTypeName[0] == null);
                    parentTypeName[0] = GetString(value);
                    break;
                case "description":
                    Expect(description[0] == null);
                    description[0] = GetString(value);
                    break;
                case "properties":
                    //Expect(value.is(Event.ID.MappingStart));
                    ParseMapping(value, it, (propname, ms) -> {
                        // TODO: add case for inline properties (needed?)
                        Expect(ms.is(Event.ID.MappingStart));
                        ParseProperty(ms,it,propname,properties);
                    });
                    break;
                case "capabilities":
                    // TODO
                    Skip(e,it);
                    break;
                case "requirements":
                    //Expect(value.is(Event.ID.SequenceStart));
                    ParseSequence(value,it,event->{
                        ParseRequirement(event,it);
                    });
                    break;
                default:
                    throw new ParseError();
            }
        });
        INodeType parentType;
        if(parentTypeName[0] == null)
            parentType = null;
        else {
            INamedEntity pt = env.getNamedEntity(parentTypeName[0]);
            if(pt == null || !(pt instanceof INodeType))
                throw new ParseError();
            parentType = (INodeType)pt;
        }
        INodeTemplate newTemplate = env.newTemplate(parentType);
        for(Map.Entry<String,? extends  IProperty> entry : properties.entrySet()) {
            parentType = parentType.addProperty(entry.getKey(),entry.getValue().type(),entry.getValue().defaultValue());
        }
        env.registerNodeTemplate(templateName, newTemplate);
    }
    private void ParseNodeType (Event e, Iterator<Event>it, String typeName) {
        final String[] parentTypeName = {null};
        final String[] description = {null};
        final Map<String,Property> properties = new HashMap<>();
        final Map<String,Object> attributes = new HashMap<>();
        ParseMapping(e,it, (key, value) -> {
            switch (key) {
                case "derived_from":
                    Expect(parentTypeName[0] == null);
                    parentTypeName[0] = GetString(value);
                    break;
                case "description":
                    Expect(description[0] == null);
                    description[0] = GetString(value);
                    break;
                case "properties":
                    //Expect(value.is(Event.ID.MappingStart));
                    ParseMapping(value, it, (propname, ms) -> {
                        // TODO: add case for inline properties (needed?)
                        ParseProperty(ms,it,propname,properties);
                    });
                    break;
                case "attributes":
                    //Expect(value.is(Event.ID.MappingStart));
                    ParseMapping(value, it, (propname, ms) -> {
                        // TODO: add case for inline properties (needed?)
                        ParseAttribute(ms, it, propname, attributes);
                    });
                    break;
                case "capabilities":
                    // TODO
                    Skip(e,it);
                    break;
                case "requirements":
                    //Expect(value.is(Event.ID.SequenceStart));
                    ParseSequence(value,it,event->{
                        ParseRequirement(event,it);
                    });
                    break;
                default:
                    throw new ParseError();
            }
        });
        INodeType parentType;
        if(parentTypeName[0] == null)
            parentType = null;
        else {
            INamedEntity pt = env.getNamedEntity(parentTypeName[0]);
            if(pt == null || !(pt instanceof INodeType))
                throw new ParseError();
            parentType = (INodeType)pt;
        }
        INodeType newType = parentType;
        for(Map.Entry<String,? extends  IProperty> entry : properties.entrySet()) {
            parentType = parentType.addProperty(entry.getKey(),entry.getValue().type(),entry.getValue().defaultValue());
        }
        env.registerNodeType(typeName, newType);
    }

    private void ParseAttribute(Event e, Iterator<Event> it, String propname, Map<String, Object> attributes) {
        final String[] typeName = {null};
        final Object[] defaultValue = {null};
        ParseMapping(e,it,(key,value)->{
            switch (key) {
                case "type":
            }
        });
    }


    private void ParseProperty(Event e, Iterator<Event> it,String propname,Map<String,Property> properties) {
        final String[] typeName = {null};
        final Object[] defaultValue = {null};
        final Collection<IConstraint> constraints = new ArrayList<IConstraint>();
        ParseMapping(e,it,(key,value)->{
            switch (key) {
                case "type":
                    typeName[0] = GetString(value);
                    break;
                case "constraints":
                    // TODO: implement constraints
                    Skip(e,it);
                    break;
                default:
                    throw new ParseError();
            }
        });
        IType t = (IType)env.getNamedEntity(typeName[0]);
        for(IConstraint c : constraints) {
            t  = t.coerce(c);
        }
        Property p = new Property(t,defaultValue[0]);
        properties.put(propname, p);
    }
    private  void ParseRequirement(Event e, Iterator<Event> it) {
        ParseMapping(e,it,(key,value)->{
            Skip(value,it);
            // TODO: requirements not implemented
        });
    }
}
