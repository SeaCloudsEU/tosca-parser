package seaclouds.utils.toscamodel.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import seaclouds.utils.toscamodel.*;

import javax.xml.soap.Node;
import java.util.*;

/**
 * Created by pq on 13/04/2015.
 */
class TypeManager {
    private ToscaEnvironment toscaEnvironment;

    final Map<String, IType> basicTypes = new HashMap<>();
    final Map<String, TypeStruct> structTypes = new HashMap<String, TypeStruct>();
    final Map<String, NodeType> nodeTypes = new HashMap<String, NodeType>();
    final Map<String, NodeTemplate> nodeTemplates = new HashMap<String, NodeTemplate>();

    public TypeManager(ToscaEnvironment toscaEnvironment) {
        this.toscaEnvironment = toscaEnvironment;
    }

    public INodeType getNodeType(String typename) {
        return nodeTypes.get(typename);
    }

    public IType getType(String typename) {
        IType ret = basicTypes.get(typename);
        if (ret == null)
            ret = structTypes.get(typename);
        return ret;
    }


    public Iterable<INodeType> getNodeTypesDerivingFrom(INodeType rootType) {
        return Iterables.transform(
                Iterables.filter(nodeTypes.values(),
                        t -> t.derivesFrom(rootType)),
                t -> (INodeType)t);
    }

    public Iterable<ITypeStruct> getTypesDerivingFrom(ITypeStruct rootType) {
        return Iterables.transform(
                Iterables.filter(structTypes.values(),
                        t -> t.derivesFrom(rootType)),
                t -> (ITypeStruct)t);
    }

    public INamedEntity registerType(String name,IType type) {

        if(type instanceof  ITypeStruct) {
            ITypeStruct struct = (ITypeStruct) type;
            final TypeStruct unnamed;
            if (nodeTypes.containsKey(name))
                return null;
            if (struct instanceof TypeStruct)
                unnamed = (TypeStruct) struct;
            else {
                ITypeStruct parentType = struct.baseType();
                if (parentType != null && !(parentType instanceof TypeStruct))
                    parentType = structTypes.get(((INamedEntity) parentType).name());
                unnamed = new TypeStruct((TypeStruct) parentType, struct.description(), struct.declaredProperties());
            }
            NamedStruct t = new NamedStruct(name, unnamed);
            structTypes.put(name, t);
            return t;
        } else if (type instanceof  ICoercedType) {
            //todo
        }
        return null;
    }

    public INamedEntity registerNodeType(String name,INodeType nodeType) {
        final NodeType unnamed;
        if(nodeTypes.containsKey(name))
            return null;
        if(nodeType instanceof  NodeType)
            unnamed = (NodeType)nodeType;
        else {
            INodeType parentType = nodeType.baseType();
            if(parentType != null && !(parentType instanceof NodeType))
                parentType = nodeTypes.get(((INamedEntity)parentType).name());
            unnamed = new NodeType((NodeType)parentType,nodeType.description(),nodeType.declaredProperties(),nodeType.declaredAttributes());
        }
        NamedNodeType t = new NamedNodeType(name,(NodeType) unnamed);
        nodeTypes.put(name,t);
        return t;
    }

    public INamedEntity registerNodeTemplate(String name, INodeTemplate nodeType) {
        final NodeTemplate unnamed;
        if(nodeTypes.containsKey(name))
            return null;
        if(nodeType instanceof  NodeTemplate)
            unnamed = (NodeTemplate)nodeType;
        else {
            INodeType parentType = nodeType.baseType();
            if(parentType != null && !(parentType instanceof NodeTemplate))
                parentType = nodeTypes.get(((INamedEntity)parentType).name());
            unnamed = new NodeTemplate((NodeType)parentType,nodeType.description(),nodeType.declaredProperties(),nodeType.declaredAttributes());
        }
        NamedNodeTemplate t = new NamedNodeTemplate(name, unnamed);
        nodeTemplates.put(name,t);
        return t;
    }

    public Iterable<INodeTemplate> getNodeTemplatesOfType(INodeType rootType) {
        return Iterables.transform(
                Iterables.filter(nodeTemplates.values(),
                        t -> t.derivesFrom(rootType)),
                t -> (INodeTemplate)t);
    }

    public INodeTemplate getNodeTemplate(String entityName) {
        return nodeTemplates.get(entityName);
    }
}
