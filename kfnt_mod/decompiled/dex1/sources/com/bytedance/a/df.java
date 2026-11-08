package com.bytedance.a;

import android.content.Context;
import com.bytedance.a.y;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class df implements bm {

    /* renamed from: a, reason: collision with root package name */
    public static Object f3495a;

    /* renamed from: b, reason: collision with root package name */
    public static Class<?> f3496b;

    /* renamed from: c, reason: collision with root package name */
    public static Method f3497c;

    static {
        try {
            f3496b = Class.forName("com.android.id.impl.IdProviderImpl");
            f3495a = f3496b.newInstance();
            f3497c = f3496b.getMethod("getOAID", Context.class);
        } catch (Exception e) {
            String str = bh.f3405b;
            StringBuilder sbA = a.a("Api#static reflect exception! ");
            sbA.append(e.getMessage());
            String string = sbA.toString();
            y yVar = t.f3539b;
            if (yVar == null || t.f3538a > 6) {
                return;
            }
            ((y.a) yVar).c(str, string, null);
        }
    }

    public static boolean a() {
        return (f3496b == null || f3495a == null || f3497c == null) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    @Override // com.bytedance.a.bm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.a.bm.a a(android.content.Context r7) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r6 = this;
            r0 = 0
            com.bytedance.a.bm$a r1 = new com.bytedance.a.bm$a     // Catch: java.lang.Exception -> L21
            r1.<init>()     // Catch: java.lang.Exception -> L21
            java.lang.reflect.Method r2 = com.bytedance.a.df.f3497c     // Catch: java.lang.Exception -> L21
            java.lang.Object r3 = com.bytedance.a.df.f3495a     // Catch: java.lang.Exception -> L21
            if (r3 == 0) goto L1d
            if (r2 == 0) goto L1d
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L1d
            r5 = 0
            r4[r5] = r7     // Catch: java.lang.Exception -> L1d
            java.lang.Object r7 = r2.invoke(r3, r4)     // Catch: java.lang.Exception -> L1d
            if (r7 == 0) goto L1d
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L1d
            goto L1e
        L1d:
            r7 = r0
        L1e:
            r1.f3415b = r7     // Catch: java.lang.Exception -> L21
            return r1
        L21:
            r7 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.df.a(android.content.Context):com.bytedance.a.bm$a");
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        return (f3496b == null || f3495a == null || f3497c == null) ? false : true;
    }
}
