package com.bjzjmy.utils.reflect;

import com.utopia.reflecct.core.Reflect;
import com.utopia.reflecct.core.ReflectProxy;

import org.junit.Assert;
import org.junit.Test;


public class ReflectTest {

    @Test
    public void on() {
        String str = "123456";

        //不建议使用类名的方式反射，耗时过长
        Reflect reflect = ReflectProxy.on("java.lang.String");
        Assert.assertNotNull(reflect);

        Reflect reflect1= ReflectProxy.on(str.getClass());
        Assert.assertNotNull(reflect1);

        Reflect reflect2 = ReflectProxy.on(String.class);
        Assert.assertNotNull(reflect2);

    }


    @Test
    public void constructor() {
        Object object = ReflectProxy.on("java.lang.String").constructor(String.class).newInstance("hello world");
        Assert.assertEquals(object,"hello world");
    }

    @Test
    public void method() {
    }

    @Test
    public void field() {
    }

    @Test
    public void testOn1() {
    }

    @Test
    public void testUseTime() {
        long startTime = System.currentTimeMillis();
        for (int i = 0 ; i < 10 ; i++) {
            ReflectProxy on = ReflectProxy.on("com.bjzjmy.utils.reflect.Demo");
            Object demo = on.constructor().newInstance();
            on.method("show2").invoke(demo);
        }
        System.out.println("执行方法耗时："+(System.currentTimeMillis()-startTime)+"ms");
    }
}