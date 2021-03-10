package com.utopia.reflecct.core;


import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class RealReflect implements Reflect{
    private final Class<?> mClazz;

    RealReflect(String className) throws ReflectException {
        AssertUtils.checkMemberAccess(className, "ClassName can not be empty!");
        try {
            this.mClazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ReflectException("Class:" + className + " can not be found!", e.getCause());
        }
    }

    RealReflect(Class<?> clazz) {
        this.mClazz = clazz;
    }

    @Override
    public <T> ReflectConstructor<T> constructor(Class<?>... types) throws ReflectException {
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");
        try {
            Constructor<?> constructor = mClazz.getDeclaredConstructor(types);
            return (ReflectConstructor<T>) ReflectConstructor.create(constructor);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    @Override
    public ReflectMethod method(String methodName, Class<?>... parameterTypes) throws ReflectException {
        AssertUtils.checkMemberAccess(methodName, "MethodName can not be empty!");
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");

        try {
            Method method = mClazz.getDeclaredMethod(methodName, parameterTypes);
            return ReflectMethod.create(method);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    @Override
    public ReflectField field(String fieldName) throws ReflectException {
        AssertUtils.checkMemberAccess(fieldName, "FieldName can not be empty!");
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");
        try {
            Field field = mClazz.getDeclaredField(fieldName);
            return ReflectField.create(field);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
