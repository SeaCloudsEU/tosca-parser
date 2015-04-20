package seaclouds.utils.toscamodel.impl;

import com.google.common.collect.Maps;
import seaclouds.utils.toscamodel.*;

import java.util.*;

/**
 * Created by pq on 20/04/2015.
 */
abstract public class SchemaDefinition implements ISchemaDefinition {
    final String description;
    final ISchemaDefinition baseType;
    final Map<String,IProperty> allProperties;
    final Map<String,IProperty> declaredProperties;
    SchemaDefinition(ISchemaDefinition baseType,String description, Map<String,IProperty> properties ){
        assert(baseType == null || baseType instanceof INamedEntity);

        this.baseType = baseType;
        this.description = description;
        Map<String,IProperty> ap = new HashMap<>();
        Map<String,IProperty> dp = new HashMap<>();
        if(baseType != null)
            ap.putAll(baseType.allProperties());
        properties.forEach((n, p) -> dp.put(n, new Property(p)));
        ap.putAll(dp);

        this.declaredProperties = Collections.unmodifiableMap(dp);
        this.allProperties = Collections.unmodifiableMap(ap);

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

    protected Map<String,IProperty> extendSchema(String pname,IType ptype, Object defaultValue) {
        Map<String,IProperty> newSchema = new HashMap<>();
        newSchema.putAll(declaredProperties());
        newSchema.put(pname,new Property(ptype,defaultValue));
        return newSchema;
    }

    protected Map<String,IValue> valueConvert(Map<String,? extends Object> value) {

        Map<String,IValue> vmap = new HashMap<>();
        for(Map.Entry<String,? extends Object> e : value.entrySet()) {
            IProperty p = allProperties.get(e.getKey());
            vmap.put(e.getKey(), p.type().instantiate(e.getValue()));
        }
        return vmap;
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
