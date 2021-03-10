package com.utopia.reflecct;


import com.utopia.reflecct.handle.ConstructorHandle;
import com.utopia.reflecct.handle.FieldHandle;
import com.utopia.reflecct.handle.MethodHandle;
import com.utopia.reflecct.interfaces.IReflect;
import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class RealReflect implements IReflect {
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
        AssertUtils.checkMemberAccess(clazz, "Class can not be null!");
        this.mClazz = clazz;
    }
    

    @Override
    public ConstructorHandle constructor(Class<?>... types) throws ReflectException {
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");
        try {
            return ConstructorHandle.create(mClazz.getDeclaredConstructor(types));
        } catch (Exception e) {
            return ConstructorHandle.createEmpty();
        }
    }

    @Override
    public MethodHandle method(String methodName, Class<?>... parameterTypes) throws ReflectException {
        AssertUtils.checkMemberAccess(methodName, "MethodName can not be empty!");
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");

        try {
            return MethodHandle.create(mClazz.getDeclaredMethod(methodName, parameterTypes));
        } catch (Exception e) {
            return MethodHandle.createEmpty();
        }
    }

    @Override
    public FieldHandle field(String fieldName) {
        AssertUtils.checkMemberAccess(mClazz, "Reflect class can not be null!");
        try {
            return FieldHandle.create(mClazz.getDeclaredField(fieldName));
        } catch (Exception e) {
            return FieldHandle.createEmpty();
        }
    }
}
