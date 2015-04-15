package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.*;

import java.util.*;

/**
 * Created by pq on 05/04/15.
 */
public class ValueFactory {
    static public IValueFloat newFloat(final double v) {
        return new IValueFloat() {
            @Override
            public int hashCode() {
                return Double.hashCode(v);
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof  IValueFloat)) return false;
                return v == ((IValueFloat) obj).getValue();
            }

            @Override
            public double getValue() {
                return v;
            }

            @Override
            public IType getType() {
                return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCAFloat);
            }
        };
    }

    static private IValueBoolean trueValue = new IValueBoolean() {
        @Override
        public boolean getValue() {
            return true;
        }

        @Override
        public IType getType() {
            return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCABoolean);
        }
    };
    static private IValueBoolean falseValue = new IValueBoolean() {
        @Override
        public boolean getValue() {
            return false;
        }

        @Override
        public IType getType() {
            return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCABoolean);
        }
    };

    static public IValueBoolean newBoolean(final boolean v) {
        if (v)
            return trueValue;
        else
            return falseValue;
    }

    static public IValueInteger newInteger(final int v) {
        return new IValueInteger() {
            @Override
            public int hashCode() {
                return Integer.hashCode(v);
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override
            public int getValue() {
                return v;
            }

            @Override
            public IType getType() {
                return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCAInteger);
            }
        };
    }

    static public IValueString newString(final String v){
        return new IValueString() {
            @Override
            public int hashCode() {
                return v.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof  IValueString)) return false;
                return v == ((IValueString) obj).getValue();
            }

            @Override
            public String getValue() {
                return v;
            }

            @Override
            public IType getType() {
                return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCAString);
            }
        };
    }

    static public IValueRange newRange(final int lb, final int ub) {
        return new IValueRange() {
            @Override
            public int hashCode() {
                return Integer.hashCode(lb) * Integer.hashCode(ub);
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof  IValueRange)) return false;
                IValueRange other = (IValueRange) obj;
                return ub == other.getMax() && lb == other.getMin();
            }
            @Override
            public int getMin() {
                return lb;
            }

            @Override
            public int getMax() {
                return ub;
            }

            @Override
            public IType getType() {
                return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCARange);
            }
        };
    }

    static public IValueScalarUnit newUnit(final double v,final EScalarUnits unit) {
        return new IValueScalarUnit() {
            @Override
            public int hashCode() {
                return Double.hashCode(v) * unit.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof  IValueScalarUnit)) return false;
                IValueScalarUnit o = (IValueScalarUnit) obj;
                return (o.getUnit () == unit) && (o.getValue() == v);
            }

            @Override
            public double getValue() {
                return v;
            }

            @Override
            public EScalarUnits getUnit() {
                return unit;
            }

            @Override
            public IType getType() {
                return BasicTypeFactory.getBasicType(EBasicTypeInfo.TOSCAScalarUnit);
            }
        };
    }

    static public IValueList newList(final List<IValue> values){
        IType t = null;
        for  (IValue v : values ) {
            if(t == null)
                t = v.getType();
            if(t != v.getType())
                return null;
        }
        final IType t1 = t;
        final IType listType = BasicTypeFactory.getListType(t);
        return new IValueList() {

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof  IValueList)) return false;
                IValueList o = (IValueList) obj;
                if( o.getType() != listType || o.size()!= values.size() ) return false;
                //todo: rewrite using iterators/arrays, this is n^2
                for(int i = 0; i < values.size(); i++)
                    if (values.get(i) != o.get(i) ) return false;
                return true;
            }

            @Override
            public int hashCode() {
                return t1.hashCode()*values.hashCode();
            }

            @Override
            public IType getValueType() {
                return t1;
            }

            @Override
            public IType getType() {
                return listType;
            }

            @Override
            public int size() {
                return values.size();
            }

            @Override
            public boolean isEmpty() {
                return values.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return values.contains(o);
            }

            @Override
            public Iterator<IValue> iterator() {
                return values.iterator();
            }

            @Override
            public Object[] toArray() {
                return values.toArray();
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return values.toArray(a);
            }

            @Override
            public boolean add(IValue iValue) {
                return values.add(iValue);
            }

            @Override
            public boolean remove(Object o) {
                return values.remove(o);
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return values.containsAll(c);
            }

            @Override
            public boolean addAll(Collection<? extends IValue> c) {
                return values.addAll(c);
            }

            @Override
            public boolean addAll(int index, Collection<? extends IValue> c) {
                return values.addAll(index,c);
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return values.removeAll(c);
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return values.retainAll(c);
            }

            @Override
            public void clear() {
                values.clear();
            }

            @Override
            public IValue get(int index) {
                return values.get(index);
            }

            @Override
            public IValue set(int index, IValue element) {
                return values.set(index,element);
            }

            @Override
            public void add(int index, IValue element) {
                values.add(index,element);
            }

            @Override
            public IValue remove(int index) {
                return values.remove(index);
            }

            @Override
            public int indexOf(Object o) {
                return values.indexOf(o);
            }

            @Override
            public int lastIndexOf(Object o) {
                return values.lastIndexOf(o);
            }

            @Override
            public ListIterator<IValue> listIterator() {
                return values.listIterator();
            }

            @Override
            public ListIterator<IValue> listIterator(int index) {
                return values.listIterator(index);
            }

            @Override
            public List<IValue> subList(int fromIndex, int toIndex) {
                return values.subList(fromIndex,toIndex);
            }
        };
    }
    static public IValueMap newMap(final Map<String,IValue> values) {
        IType t = null;
        for (Map.Entry<String,IValue> e : values.entrySet()) {
            if (t == null)
                t = e.getValue().getType();
            if (t != e.getValue().getType())
                return null;
        }
        final IType t1 = t;
        final IType mapType = BasicTypeFactory.getMapType(t);
        return new IValueMap() {

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof IValueMap)) return false;
                IValueMap o = (IValueMap) obj;
                if (o.getType() != mapType || o.size() != values.size()) return false;
                //todo: rewrite using iterators/arrays, this is n^2
                for (int i = 0; i < values.size(); i++)
                    if (values.get(i) != o.get(i)) return false;
                return true;
            }

            @Override
            public int hashCode() {
                return t1.hashCode() * values.hashCode();
            }

            @Override
            public IType getValueType() {
                return t1;
            }

            @Override
            public IType getType() {
                return mapType;
            }

            @Override
            public Set<Entry<String, IValue>> entrySet() {
                return values.entrySet();
            }

            @Override
            public int size() {
                return values.size();
            }

            @Override
            public boolean isEmpty() {
                return values.isEmpty();
            }

            @Override
            public boolean containsKey(Object key) {
                return values.containsKey(key);
            }

            @Override
            public boolean containsValue(Object value) {
                return values.containsValue(value);
            }

            @Override
            public IValue get(Object key) {
                return values.get(key);
            }

            @Override
            public IValue put(String key, IValue value) {
                return values.put(key,value);
            }

            @Override
            public IValue remove(Object key) {
                return values.remove(key);
            }

            @Override
            public void putAll(Map<? extends String, ? extends IValue> m) {
                values.putAll(m);
            }

            @Override
            public void clear() {
                values.clear();
            }

            @Override
            public Set<String> keySet() {
                return values.keySet();
            }

            @Override
            public Collection<IValue> values() {
                return values.values();
            }
        };
    }

}
