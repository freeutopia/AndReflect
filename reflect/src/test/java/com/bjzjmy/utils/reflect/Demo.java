package com.bjzjmy.utils.reflect;


public class Demo {
    private String name = "123";
    public int age = 10;
    static char sex = 0;

    private Demo() {
    }

    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static void show(){
        System.out.println("我调用了show方法！");
    }

    private void show1(){
        System.out.println("我调用了show1方法！");
    }

    public void show2(){
        System.out.println("我调用了show2方法！");
    }
}
