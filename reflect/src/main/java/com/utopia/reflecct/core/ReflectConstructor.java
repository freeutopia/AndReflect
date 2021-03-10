package com.utopia.reflecct.core;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ReflectConstructor<T> {
    private final Constructor<T> constructor;

    private ReflectConstructor(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    static <T> ReflectConstructor<T> create(Constructor<T> constructor) {
        constructor.setAccessible(true);
        return new ReflectConstructor<T>(constructor);
    }

    public Constructor<T> obtain() {
        return constructor;
    }

    public Object newInstance(Object... params) throws ReflectException {
        try {
            return constructor.newInstance(params);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}