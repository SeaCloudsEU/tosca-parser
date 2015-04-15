package seaclouds.utils.oldtoscamodel.impl;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.events.Event;
import seaclouds.utils.oldtoscamodel.*;

/**
 * Created by pq on 05/04/15.
 */

import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

public class ToscaEnvironment implements  IToscaEnvironment {

    private final TypeManager typeManager = new TypeManager(this);
    private final ToscaTopology topology = new ToscaTopology(this);


    @Override
    public ITopology getTopology() {
        return topology;
    }

    @Override
    public ITypeManager getTypeManager() {
        return typeManager;
    }

    @Override
    public void readToscaFile(Reader input) {
        Yaml parser = new Yaml();
        Iterable<Event> parsed = parser.parse(input);
        Iterator<Event> it = parsed.iterator();
        Event e = null;
        while (it.hasNext()){
            e = it.next();
             e.toString();
        }

        // todo
    }

    @Override
    public void writePartialToscaFile(Writer output, Iterable<String> identifiers) {
        // todo
    }

    @Override
    public void writeToscaFile(Writer output) {
        // todo
    }

};
