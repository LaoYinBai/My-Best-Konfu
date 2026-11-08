package com.bytedance.pangle.d;

import com.bytedance.pangle.util.MethodUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f4546a = 0;

        /* renamed from: b, reason: collision with root package name */
        public static int f4547b = 1;

        /* renamed from: c, reason: collision with root package name */
        public static int f4548c = 3;
    }

    public static String a() {
        try {
            return (String) MethodUtils.invokeStaticMethod(Class.forName("dalvik.system.VMRuntime"), "getCurrentInstructionSet", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
