package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.INamedEntity;
import seaclouds.utils.toscamodel.IProperty;

import java.util.Map;

/**
 * Created by pq on 20/04/2015.
 */
public class NamedNodeTemplate extends NodeTemplate implements INamedEntity {
    private String name;

    public NamedNodeTemplate(String name,NodeTemplate unnamedVersion) {
        super((NodeType)unnamedVersion.baseType,unnamedVersion.description, unnamedVersion.declaredProperties(), unnamedVersion.attributes);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }


    @Override
    public String toString() {
        return name();
    }

    public void rename(String newEntityName) {
        name = newEntityName;
    }
}
