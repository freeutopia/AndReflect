package com.utopia.reflecct.utils;

import android.os.Build;

import com.utopia.reflecct.Reflect;
import com.utopia.reflecct.handle.MethodHandle;

import java.lang.reflect.Method;

public class AndroidUtils {

    public static void fitAndroidP() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return;
        }

        try {
            Reflect reflect = Reflect.on(Class.class);
            MethodHandle forName = reflect.method("forName", String.class);
            MethodHandle getDeclaredMethod = reflect.method("getDeclaredMethod", String.class, Class[].class);

            Class<?> vmRuntimeClass = (Class<?>) forName.call(null, "dalvik.system.VMRuntime");
            Method getRuntime = (Method) getDeclaredMethod.call(vmRuntimeClass, "getRuntime", null);
            MethodHandle getRuntimeMethod = MethodHandle.create(getRuntime);
            getDeclaredMethod.call(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            getRuntimeMethod.call(null);
        } catch (Exception ignored) {

        }
    }
}
