package com.utopia.reflecct.core;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public final class ReflectMethod {
    private final Method method;

    private ReflectMethod(Method method) {
        this.method = method;
    }

    static ReflectMethod create(Method method) {
        method.setAccessible(true);//关闭安全检查，可以提升访问权限和提升反射效率
        return new ReflectMethod(method);
    }

    public Method obtain() {
        return method;
    }

    public Object invoke(Object obj, Object... args) throws ReflectException{
        AssertUtils.checkMemberAccess(method,"Method can not be null!");

        if (obj == null && (method.getModifiers() & Modifier.STATIC) == 0){
            throw new ReflectException("Invoker can not be null!");
        }

        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage());
        }
    }
}
