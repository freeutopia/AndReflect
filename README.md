# AndroidReflect


##基本使用方法
***
### 1. Reflect接口

```
public interface Reflect {
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
```



### 2. 创建一个Reflect
```
//直接传入Class方式构建
Reflect reflect = Reflect.on(String.class);
//传入类名方式构建(不建议这么做)
Reflect reflect = Reflect.on("java.lang.String");
```

```
//构造器相关用法
Object instance = reflect.constructor().newInstance();//根据无参构造参数生成对象实例
Object instance = reflect.constructor(String.class).newInstance("test");//根据有参构造参数生成对象实例
```

```
//字段相关用法
FieldHandle hashField = reflect.field("hash",Object.class);

int hash = (int)hashField.get(instance);//FieldHandle提供了get方法，用于获取属性值。
hashField.set(instance,123);//FieldHandle提供了set方法，用于设置属性值。
```

```
//方法相关用法
MethodHandle equalsMethod = reflect.method("equals", Object.class);
bool isEqual = (bool)methodHandle.call(instance,"test");//调用普通方法
methodHandle.call(null,"test");//调用类方法
```


### 3. 完整示例


```
String testStr = "hello world !";
char[] value = (char[]) Reflect.on(testStr.getClass()).field("value").get(testStr);
System.out.println(testStr);//输出hello world !
value[0] = 'H';
System.out.println(testStr);//输出Hello world !
```
