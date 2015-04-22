package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.Collections;
import java.util.Map;

/**
 * Created by pq on 21/04/2015.
 */
public class NodeRoot extends NamedNodeType {
    final static private NodeRoot instance = new NodeRoot();
    public static NodeRoot instance() {
        return instance;
    }
    private NodeRoot() {
        super("tosca.nodes.Root", new NodeType(null,"",Collections.emptyMap(),Collections.emptyMap()));
        this.hidden = true;
    }

    @Override
    public boolean derivesFrom(ISchemaDefinition nodeType) {
        return this.equals(nodeType);
    }
}
