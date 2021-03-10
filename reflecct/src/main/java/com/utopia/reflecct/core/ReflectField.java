package com.utopia.reflecct.core;

import com.utopia.reflecct.utils.AssertUtils;
import com.utopia.reflecct.utils.ReflectException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public final class ReflectField {
    private final Field field;

    private ReflectField(Field field) {
        this.field = field;
    }

    static ReflectField create(Field field) {
        field.setAccessible(true);
        return new ReflectField(field);
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
        AssertUtils.checkMemberAccess(field,"Field can not be null!");

        if (target == null && (field.getModifiers() & Modifier.STATIC) == 0) {
            throw new ReflectException("field is not static!");
        }

        try {
            return field.get(target);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}