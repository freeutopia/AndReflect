package com.utopia.reflecct.handle;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public final class MethodHandle {
    private final Method method;

    private MethodHandle(Method method) {
        this.method = method;
    }

    public static MethodHandle create(Method method) {
        method.setAccessible(true);//关闭安全检查，可以提升访问权限和提升反射效率
        return new MethodHandle(method);
    }

    public static MethodHandle createEmpty() {
        return new MethodHandle(null);
    }

    public Method obtain() {
        return method;
    }

    public Object call(Object obj, Object... args) throws ReflectException{
        try {
            //target == null代表访问我的是static方法
            //getModifiers是获取方法修饰符，具体枚举值请参见：java.lang.reflect.Modifier类
            if (obj == null && (method.getModifiers() & Modifier.STATIC) == 0){
                throw new ReflectException("Invoker can not be null!");
            }

            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage());
        }
    }
}
