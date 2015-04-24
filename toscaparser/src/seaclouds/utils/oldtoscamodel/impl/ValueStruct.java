package seaclouds.utils.oldtoscamodel.impl;

import seaclouds.utils.oldtoscamodel.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by pq on 08/04/2015.
 */
public class ValueStruct extends HashMap<String,IValue> implements IValueStruct,InvocationHandler {
    private final TypeStruct type;
    public ValueStruct(TypeStruct type){
        this.type = type;
        for (Entry<String,? extends  IProperty> e : type.getProperties().entrySet()){
            this.put(e.getKey(), e.getValue().getDefaultValue());
        }
        ProxyInfo p = new ProxyInfo();
        p.proxy = this;
        this.representationCache.put(IValueStruct.class,p);
        this.representationCache.put(null,p);
    }
    @Override
    public ITypeStruct getType() {
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
            // check if representation corresponds to type, that is all methods are either getter or setter of properties specified in the struct type
            final Method[] methods = representation.getMethods();
            res = new ProxyInfo();
            for (Method m :methods) {
                final String methodName = m.getName();
                final String operation = methodName.substring(0, 3);
                final String propertyName = methodName.substring(3,4).toLowerCase()  + methodName.substring(4);
                if(operation.equals("get") && m.getParameterCount() == 0 || operation.equals("set") && m.getParameterCount() == 1 && IValueStruct.class.isAssignableFrom(m.getParameterTypes()[0]) )
                    res.put(m,propertyName);
                else
                    return null;
            }
            res.proxy = (IValueStruct) Proxy.newProxyInstance(representation.getClassLoader(),new Class[]{representation},this);
            representationCache.put(representation,res);

        }
        return res.proxy;

    }

    public IValueStruct getDefaultRepresentation() {
        Class<? extends  IValueStruct> representation = type.getRepresentation();
        return this.getRepresentation(representation);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        List<Method> methods = Arrays.asList(IValueStruct.class.getMethods());
        if(methods.contains(method)) {
            Object retval = method.invoke(this, args);
            return retval;
        }

        ProxyInfo p = representationCache.get(method.getDeclaringClass());
        String propname = p.get(method);
        IValue retval = null;
        switch (args.length) {
            case 0:
                retval = getProperty(propname);
                if (retval instanceof IValueStruct) {
                    IValueStruct r = (IValueStruct) retval;
                    r = r.getRepresentation(IValueStruct.class);
                    if (r instanceof ValueStruct){
                        retval = ((ValueStruct) r).getDefaultRepresentation();
                    }
                }
                break;
            case 1:
                IValue arg = (IValue)args[0];
                if(arg instanceof IValueStruct)
                    arg = ((IValueStruct) arg).getRepresentation(IValueStruct.class);
                setProperty(propname,arg);
                break;
            default:
                break;
        }
        return retval;

    }

}
