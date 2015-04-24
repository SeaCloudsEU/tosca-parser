package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pq on 05/04/15.
 */
public class BasicTypeFactory {
    static Map<EBasicTypeInfo,ITypeBasic> basicTypesCache = new HashMap<EBasicTypeInfo, ITypeBasic>();

    public static ITypeBasic getBasicType(String typeName) {
        switch (typeName) {
            case "string":
                return getBasicType(EBasicTypeInfo.TOSCAString);
            //TODO : other types
            default:
                return null;
        }
    }
    public static ITypeBasic getBasicType(final EBasicTypeInfo typeInfo) {
        ITypeBasic t = basicTypesCache.get(typeInfo);
        if (t == null) {
            t = new ITypeBasic() {
                @Override
                public int hashCode() {
                    return ITypeBasic.class.hashCode() * typeInfo.hashCode();
                }

                @Override
                public boolean equals(Object obj) {
                    if (obj == null ) return false;
                    if (!(obj instanceof ITypeBasic)) return false;
                    ITypeBasic other = (ITypeBasic) obj;
                    return  other.getBasicType().equals(typeInfo);
                }

                @Override
                public EBasicTypeInfo getBasicType() {
                    return typeInfo;
                }
            };
            basicTypesCache.put(typeInfo, t);
        }
        return t;
    }
    static Map<IType,ITypeList> listTypeCache = new HashMap<IType, ITypeList>();
    static ITypeList getListType(final IType innerType) {
        ITypeList t = listTypeCache.get(innerType);
        if (t == null) {
            t = new ITypeList() {
                @Override
                public int hashCode() {
                    return ITypeList.class.hashCode() * innerType.hashCode();
                }

                @Override
                public boolean equals(Object obj) {
                    if (obj == null ) return false;
                    if (!(obj instanceof ITypeList)) return false;
                    ITypeList other = (ITypeList) obj;
                    return  other.getValueType().equals(innerType);
                }
               @Override
               public IType getValueType() {
                   return innerType;
               }
            };
            listTypeCache.put(innerType,t);
        }
        return t;
    }

    static Map<IType,ITypeMap> mapTypeCache = new HashMap<IType, ITypeMap>();
    static ITypeMap getMapType(final IType innerType) {
        ITypeMap t = mapTypeCache.get(innerType);
        if (t == null) {
            t = new ITypeMap() {
                @Override
                public int hashCode() {
                    return ITypeList.class.hashCode() * innerType.hashCode();
                }

                @Override
                public boolean equals(Object obj) {
                    if (obj == null ) return false;
                    if (!(obj instanceof ITypeList)) return false;
                    ITypeList other = (ITypeList) obj;
                    return  other.getValueType().equals(innerType);
                }
                @Override
                public IType getValueType() {
                    return innerType;
                }
            };
            mapTypeCache.put(innerType,t);
        }
        return t;
    }
}
