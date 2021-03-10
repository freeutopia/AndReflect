package com.utopia.reflecct.core;

import com.utopia.reflecct.utils.ReflectException;


public interface Reflect {
    /**
     * 反射构造方法
     */
    <T> ReflectConstructor<T> constructor(Class<?>... types) throws ReflectException;

    /**
     * 反射方法
     */
    ReflectMethod method(String methodName, Class<?>... parameterTypes) throws ReflectException;


    /**
     * 反射属性
     */
    ReflectField field(String fieldName) throws ReflectException;
}
