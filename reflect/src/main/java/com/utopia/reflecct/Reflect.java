package com.utopia.reflecct;

import com.utopia.reflecct.handle.ConstructorHandle;
import com.utopia.reflecct.handle.FieldHandle;
import com.utopia.reflecct.handle.MethodHandle;
import com.utopia.reflecct.interfaces.IReflect;
import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

public class Reflect implements IReflect {
    private final IReflect mReflect;

    private Reflect(IReflect reflect) {
        this.mReflect = reflect;
    }

    public static Reflect on(String className) throws ReflectException {
        return new Reflect(new RealReflect(className));
    }

    public static Reflect on(Class<?> clazz){
        return new Reflect(new RealReflect(clazz));
    }

    @Override
    public ConstructorHandle constructor(Class<?>... types) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.constructor(types);
    }

    @Override
    public MethodHandle method(String methodName, Class<?>... parameterTypes) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.method(methodName,parameterTypes);
    }

    @Override
    public FieldHandle field(String fieldName) throws ReflectException {
        AssertUtils.checkMemberAccess(mReflect, "请先执行ReflectProxy.on()方法!");
        return mReflect.field(fieldName);
    }
}
