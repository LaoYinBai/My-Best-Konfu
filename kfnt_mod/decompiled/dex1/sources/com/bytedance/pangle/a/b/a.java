package com.bytedance.pangle.a.b;

import com.bytedance.pangle.log.ZeusLogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static Method f4517a;

    /* renamed from: b, reason: collision with root package name */
    private static Method f4518b;

    /* renamed from: c, reason: collision with root package name */
    private static Method f4519c;
    private static Method d;

    static {
        try {
            f4517a = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            f4518b = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            f4519c = Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class);
            d = Class.class.getDeclaredMethod("forName", String.class);
        } catch (Throwable th) {
            ZeusLogger.e(ZeusLogger.TAG_INIT, "DoubleReflectorinit failed", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Field a(java.lang.Class<?> r10, java.lang.String r11) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.pangle.a.b.a.f4517a
            r1 = 0
            if (r0 == 0) goto L45
            r0 = 0
            r2 = 1
            java.lang.reflect.Method r3 = com.bytedance.pangle.a.b.a.f4517a     // Catch: java.lang.Throwable -> L20
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L20
            r4[r0] = r11     // Catch: java.lang.Throwable -> L20
            java.lang.Object r3 = r3.invoke(r10, r4)     // Catch: java.lang.Throwable -> L20
            java.lang.reflect.Field r3 = (java.lang.reflect.Field) r3     // Catch: java.lang.Throwable -> L20
            if (r3 == 0) goto L1e
            r3.setAccessible(r2)     // Catch: java.lang.Throwable -> L19
            goto L1e
        L19:
            r1 = move-exception
            r9 = r3
            r3 = r1
            r1 = r9
            goto L21
        L1e:
            r1 = r3
            goto L45
        L20:
            r3 = move-exception
        L21:
            java.lang.String r4 = "Zeus"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "DoubleReflector"
            r5.<init>(r6)
            java.lang.String r6 = "getField %s#%s failed !!!"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.String r8 = r10.getName()
            r7[r0] = r8
            r7[r2] = r11
            java.lang.String r0 = java.lang.String.format(r6, r7)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            com.bytedance.pangle.log.ZeusLogger.w(r4, r0, r3)
        L45:
            if (r1 == 0) goto L48
            return r1
        L48:
            java.lang.reflect.Field r10 = com.bytedance.pangle.a.a.a.a(r10, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.a.b.a.a(java.lang.Class, java.lang.String):java.lang.reflect.Field");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Method a(java.lang.Class<?> r10, java.lang.String r11, java.lang.Class<?>... r12) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.pangle.a.b.a.f4518b
            r1 = 0
            if (r0 == 0) goto L47
            r0 = 0
            r2 = 2
            r3 = 1
            java.lang.reflect.Method r4 = com.bytedance.pangle.a.b.a.f4518b     // Catch: java.lang.Throwable -> L23
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L23
            r5[r0] = r11     // Catch: java.lang.Throwable -> L23
            r5[r3] = r12     // Catch: java.lang.Throwable -> L23
            java.lang.Object r4 = r4.invoke(r10, r5)     // Catch: java.lang.Throwable -> L23
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4     // Catch: java.lang.Throwable -> L23
            if (r4 == 0) goto L21
            r4.setAccessible(r3)     // Catch: java.lang.Throwable -> L1c
            goto L21
        L1c:
            r1 = move-exception
            r9 = r4
            r4 = r1
            r1 = r9
            goto L24
        L21:
            r1 = r4
            goto L47
        L23:
            r4 = move-exception
        L24:
            java.lang.String r5 = "Zeus"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "DoubleReflector"
            r6.<init>(r7)
            java.lang.String r7 = "getMethod %s#%s failed !!!"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r8 = r10.getName()
            r2[r0] = r8
            r2[r3] = r11
            java.lang.String r0 = java.lang.String.format(r7, r2)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            com.bytedance.pangle.log.ZeusLogger.w(r5, r0, r4)
        L47:
            if (r1 == 0) goto L4a
            return r1
        L4a:
            java.lang.reflect.Method r10 = com.bytedance.pangle.a.a.a.a(r10, r11, r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.a.b.a.a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        Constructor constructor;
        if (f4519c != null) {
            try {
                constructor = (Constructor) f4519c.invoke(cls, clsArr);
            } catch (Throwable th) {
                ZeusLogger.w(ZeusLogger.TAG, "DoubleReflector" + String.format("getConstructor %s<init>%s failed !!!", cls.getName(), 1), th);
            }
        } else {
            constructor = null;
        }
        return constructor != null ? constructor : com.bytedance.pangle.a.a.a.a(cls, clsArr);
    }
}
