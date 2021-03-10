package com.utopia.reflecct.utils;


public class AssertUtils {
    public static <T> void checkMemberAccess(T t, String message) {
        if (t == null) {
            throw new ReflectException(String.valueOf(message));
        }
    }

    public static void checkMemberAccess(String t, String message) {
        if (t == null || "".equals(t.trim())) {
            throw new ReflectException(String.valueOf(message));
        }
    }
}
