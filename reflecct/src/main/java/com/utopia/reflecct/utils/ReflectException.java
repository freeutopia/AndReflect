package com.utopia.reflecct.utils;

/**
 * 反射异常封装类
 */
public class ReflectException extends RuntimeException {
    public ReflectException() {
        super();
    }

    public  ReflectException(String detailMessage) {
        super(detailMessage);
    }

    public  ReflectException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ReflectException(Throwable throwable) {
        super(throwable);
    }
}
