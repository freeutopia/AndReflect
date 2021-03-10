package com.utopia.reflecct.handle;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Constructor;

public final class ConstructorHandle {
    private final Constructor constructor;

    private ConstructorHandle(Constructor constructor) {
        this.constructor = constructor;
    }

    public static <T> ConstructorHandle create(Constructor<T> constructor) {
        constructor.setAccessible(true);
        return new ConstructorHandle(constructor);
    }

    public static ConstructorHandle createEmpty() {
        return new ConstructorHandle(null);
    }

    public Constructor obtain() {
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