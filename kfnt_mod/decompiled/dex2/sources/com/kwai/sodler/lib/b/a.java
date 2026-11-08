package com.kwai.sodler.lib.b;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static String a;
    private static String b;
    private static final Map<String, String> c = new HashMap();

    static {
        c.put("mips", "mips");
        c.put("mips64", "mips64");
        c.put("x86", "x86");
        c.put("x86_64", "x86_64");
        c.put("arm64", "arm64-v8a");
    }

    public static boolean a() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        Boolean bool = null;
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0]);
            declaredMethod2.setAccessible(true);
            bool = (Boolean) declaredMethod2.invoke(objInvoke, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static String b() {
        return a() ? "arm64-v8a" : "armeabi-v7a";
    }
}
