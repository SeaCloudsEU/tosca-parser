package seaclouds.utils.toscamodel.impl;

import seaclouds.utils.toscamodel.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by pq on 08/04/2015.
 */
public class ValueStruct extends HashMap<String,IValue> implements IValueStruct,InvocationHandler {
    private final ITypeStruct type;
    public ValueStruct(ITypeStruct type){
        this.type = type;
        for (IProperty p : type.getProperties()){
            this.put(p.getName(),p.getDefaultValue());
        }
    }
    @Override
    public IType getType() {
        return type;
    }

    @Override
    public IValue getProperty(String propertyName) {
        return this.get(propertyName);
    }

    @Override
    public void setProperty(String propertyName, IValue value) {
        if(value.getType().equals(this.get(propertyName).getType()))
            this.replace(propertyName,value);
        // todo: else throw exception
    }

    class ProxyInfo extends HashMap<Method,String>{
        public IValueStruct proxy;
    };
    Map<Class<? extends IValueStruct>,ProxyInfo> representationCache = new HashMap<Class<? extends IValueStruct>, ProxyInfo>();
    @Override
    public IValueStruct getRepresentation(Class<? extends IValueStruct> representation) {
        ProxyInfo res = representationCache.get(representation);
        if(res == null) {
            // 1. check if representation corresponds to type
            final Method[] methods = representation.getMethods();
            res = new ProxyInfo();
            for (Method m :methods) {
                final String methodName = m.getName();
                final String operation = methodName.substring(0, 3);
                final String propertyName = methodName.substring(3,4).toLowerCase()  + methodName.substring(4);
                if(operation == "get" && m.getParameterCount() == 0 || operation == "set" && m.getParameterCount() == 1 && IValueStruct.class.isAssignableFrom(m.getParameterTypes()[0]) )
                    res.put(m,propertyName);
                else
                    return null;
            }
            res.proxy = (IValueStruct) Proxy.newProxyInstance(representation.getClassLoader(),new Class[]{representation},this);
            representationCache.put(representation,res);

        }
        return res.proxy;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String methodName = method.getName();
        if(methodName == "getRepresentation")
        {
            Class<? extends IValueStruct> c = (Class<? extends IValueStruct>) args[0];
            if (c.isInstance(proxy))
                return proxy;
        }
        List<Method> methods = Arrays.asList(IValueStruct.class.getMethods());
        if(methods.contains(method)) {
            Object retval = method.invoke(this, args);
            return retval;
        }
        final String operation = methodName.substring(0, 3);
        final String propertyName = methodName.substring(3,4).toLowerCase() +  methodName.substring(4);
        if(operation == "get")
            return this.getProperty(propertyName);
        if(operation == "set")
            this.setProperty(propertyName, (IValue) args[0]);
        return null;
    }

}
