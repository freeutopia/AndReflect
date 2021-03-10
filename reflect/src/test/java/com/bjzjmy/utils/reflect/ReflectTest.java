package com.bjzjmy.utils.reflect;

import com.utopia.reflecct.Reflect;

import org.junit.jupiter.api.Test;


public class ReflectTest {

    @Test
    public void on() {

        String testStr = "hello world !";
        char[] value = (char[]) Reflect.on(testStr.getClass()).field("value").get(testStr);
        System.out.println(testStr);
        value[0] = 'e';
        System.out.println(testStr);
    }


    @Test
    public void constructor() {
        Object object = Reflect.on("java.lang.String").constructor(String.class).newInstance("hello world");
        //Assert.assertEquals(object,"hello world");
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
            Reflect on = Reflect.on("com.bjzjmy.utils.reflect.Demo");
            Object demo = on.constructor().newInstance();
            on.method("show2").call(demo);
        }
        System.out.println("执行方法耗时："+(System.currentTimeMillis()-startTime)+"ms");
    }
}