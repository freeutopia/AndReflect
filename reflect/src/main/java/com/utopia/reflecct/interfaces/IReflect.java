package com.utopia.reflecct.interfaces;

import com.utopia.reflecct.handle.ConstructorHandle;
import com.utopia.reflecct.handle.FieldHandle;
import com.utopia.reflecct.handle.MethodHandle;
import com.utopia.reflecct.utils.ReflectException;


public interface IReflect {
    /**
     * 获取构造方法句柄
     */
    ConstructorHandle constructor(Class<?>... types) throws ReflectException;

    /**
     * 获取成员方法或者类方法句柄
     */
    MethodHandle method(String methodName, Class<?>... parameterTypes) throws ReflectException;


    /**
     * 获取成员变量或者类变量句柄
     */
    FieldHandle field(String fieldName) throws ReflectException;
}
