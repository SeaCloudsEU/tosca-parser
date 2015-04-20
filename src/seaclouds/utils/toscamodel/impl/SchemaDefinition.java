package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.util.*;

/**
 * Created by pq on 20/04/2015.
 */
public class SchemaDefinition implements ISchemaDefinition {
    class Property implements  IProperty {
        final IType type;
        final IValue defaultValue;
        final Set<IConstraint> constraints;
        public Property(IType type,IValue defaultValue, Set<? extends IConstraint> constraints) {
            assert (defaultValue.type().equals(type));
            this.type = type;
            this.defaultValue = defaultValue;
            this.constraints = new HashSet<IConstraint>();
            this.constraints.addAll(constraints);
        }
        public Property(IProperty property) {
            this.type = property.type();
            this.defaultValue = property.defaultValue();
            this.constraints = new HashSet<>();
            this.constraints.addAll(property.constraints());
        }

        @Override
        public IType type() {
            return this.type;
        }

        @Override
        public IValue defaultValue() {
            return this.defaultValue;
        }

        @Override
        public Set<IConstraint> constraints() {
            return Collections.unmodifiableSet(constraints);
        }
    }
    String description = "";
    final ISchemaDefinition baseType;
    final Set<IConstraint> allConstraints;
    final Set<IConstraint> declaredConstraints;
    final Map<String,IProperty> allProperties;
    final Map<String,IProperty> declaredProperties;
    SchemaDefinition(ISchemaDefinition baseType,Set<IConstraint> constraints, Map<String,IProperty> properties ){
        assert(baseType instanceof INamedEntity);
        this.baseType = baseType;
        Set<IConstraint> ac = new HashSet<>();
        Set<IConstraint> dc = new HashSet<>();
        Map<String,IProperty> ap = new HashMap<>();
        Map<String,IProperty> dp = new HashMap<>();
        ac.addAll(baseType.allConstraints());
        ap.putAll(baseType.allProperties());
        properties.forEach((n, p) -> dp.put(n, new Property(p)));
        dc.addAll(constraints);
        ap.putAll(dp);
        ac.addAll(dc);

        this.declaredProperties = Collections.unmodifiableMap(dp);
        this.allProperties = Collections.unmodifiableMap(ap);
        this.declaredConstraints = Collections.unmodifiableSet(dc);
        this.allConstraints = Collections.unmodifiableSet(ac);

    }

    @Override
    public boolean isCompatible(ISchemaDefinition otherType) {
        // TODO
        return equals(otherType);
    }

    @Override
    public boolean equals(Object obj) {
        // TODO
        return super.equals(obj);
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public ISchemaDefinition baseType() {
        return baseType;
    }

    @Override
    public Set<IConstraint> allConstraints() {
        return allConstraints;
    }

    @Override
    public Set<IConstraint> declaredConstraints() {
        return declaredConstraints;
    }

    @Override
    public Map<String, IProperty> allProperties() {
        return allProperties;
    }

    @Override
    public Map<String, IProperty> declaredProperties() {
        return declaredProperties;
    }
}
