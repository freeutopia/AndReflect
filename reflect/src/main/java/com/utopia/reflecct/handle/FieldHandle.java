package com.utopia.reflecct.handle;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public final class FieldHandle {
    private final Field field;

    private FieldHandle(Field field) {
        this.field = field;
    }

    public static FieldHandle create(Field field) {
        field.setAccessible(true);
        return new FieldHandle(field);
    }

    public static FieldHandle createEmpty() {
        return new FieldHandle(null);
    }

    public Field obtain() {
        return field;
    }

    public void set(Object target, Object value) throws ReflectException{
        AssertUtils.checkMemberAccess(field,"Field can not be null!");

        if (target == null && (field.getModifiers() & Modifier.STATIC) == 0) {
            throw new ReflectException("field is not static!");
        }

        try {
            field.set(target, value);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public Object get(Object target) throws ReflectException{
        try {
            //target == null代表访问我的是static属性
            //getModifiers是获取属性修饰符，具体枚举值请参见：java.lang.reflect.Modifier类
            if (target == null && (field.getModifiers() & Modifier.STATIC) == 0) {
                throw new ReflectException("field is not static!");
            }

            return field.get(target);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}