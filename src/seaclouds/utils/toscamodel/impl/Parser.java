package seaclouds.utils.toscamodel.impl;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.events.*;
import seaclouds.utils.toscamodel.*;

import java.io.Reader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.logging.Logger;

/**
 * Created by pq on 13/04/2015.
 */
public final class Parser {
    private class ParseError extends  RuntimeException {};
    static final Logger logger = Logger.getLogger("logger");

    ToscaEnvironment env;
    Yaml yaml = new Yaml();
    Parser(ToscaEnvironment env){
        this.env = env;
    }

    public void Parse(Reader input) {
        Iterator<Event> it = yaml.parse(input).iterator();
        Event e = it.next();
        Expect(e.is(Event.ID.StreamStart));

        while (it.hasNext()) {
            e = it.next();
            if (e.is(Event.ID.DocumentStart))
            {
                ParseDocument(it);
            } else {
                Expect(e.is(Event.ID.StreamEnd));
                Expect(!it.hasNext());
            }
        }
    }

    private void Expect(boolean guard) {
        if (!guard)
            throw new ParseError();
    }
    private String GetString(Event e) {
        Expect(e.is(Event.ID.Scalar));
        Expect(e instanceof ScalarEvent);
        return ((ScalarEvent)e).getValue();
    }
    private void ParseMapping(Iterator<Event> it, BiConsumer<String,Event> fn) {
        Event e = it.next();
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

    //////////

    private void ParseDocument(Iterator<Event> it) {
        ParseMapping(it, (key, value) -> {
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
                    ParseNodeTypes(it);
                    break;
                default:
                    throw new ParseError();
            }
        });
    }

    private void ParseNodeTypes(Iterator<Event>it){
        ParseMapping(it,(nodeTypeName,mapHead)->{
            ParseNodeType(it,nodeTypeName);
        });
    }
    private void ParseNodeType (Iterator<Event>it,String typeName) {
        final String[] parentTypeName = {null};
        final String[] description = {null};
        final Map<String,Property> properties = new HashMap<String,Property>();
        final ValueStruct[] attributes = {null};
        ParseMapping(it, (key, value) -> {
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
                    Expect(value.is(Event.ID.MappingStart));
                    ParseMapping(it, (propname, ms) -> {
                        // TODO: add case for inline properties (needed?)
                        Expect(ms.is(Event.ID.MappingStart));
                        ParseProperty(it,propname,properties);
                    });
                    break;
                case "capabilities":
                    // TODO
                    break;
                case "requirements":
                    Expect(value.is(Event.ID.SequenceStart));
                    break;
                default:
                    throw new ParseError();
            }
        });
        // TODO check not null
        INodeType parentType = env.getTypeManager().getNodeType(parentTypeName[0]);
        NodeType newType =  new NodeType(typeName, description[0],parentType,properties.values(),attributes[0]);
    }
    private void ParseProperty(Iterator<Event> it,String propname,Map<String,Property> properties) {
        final String[] typeName = {null};
        final IValue[] defaultValue = {null};
        final Collection<? extends IConstraint> constraints = new ArrayList<IConstraint>();
        ParseMapping(it,(key,value)->{
            switch (key) {
                case "type":
                    typeName[0] = GetString(value);
                    break;
                // TODO: implement constraints
                default:
                    throw new ParseError();
            }
        });
        IType t = env.getTypeManager().getType(typeName[0]);
        // TODO: check not null
        Property p = new Property(propname,t,defaultValue[0],constraints);
        properties.put(propname, p);
    }
}
