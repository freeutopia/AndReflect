package com.utopia.reflecct.core;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

public class ReflectProxy implements Reflect{
    private final Reflect mReflect;

    private ReflectProxy(Reflect reflect) {
        this.mReflect = reflect;
    }

    public static ReflectProxy on(String className) throws ReflectException {
        return new ReflectProxy(new RealReflect(className));
    }

    public static ReflectProxy on(Class<?> clazz){
        return new ReflectProxy(new RealReflect(clazz));
    }

    @Override
    public <T> ReflectConstructor<T> constructor(Class<?>... types) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.constructor(types);
    }

    @Override
    public ReflectMethod method(String methodName, Class<?>... parameterTypes) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.method(methodName,parameterTypes);
    }

    @Override
    public ReflectField field(String fieldName) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.field(fieldName);
    }
}
