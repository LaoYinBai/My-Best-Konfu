package b.b.b;

import android.content.Context;
import b.b.b.h;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class z implements p {

    /* renamed from: a, reason: collision with root package name */
    public static Object f1200a;

    /* renamed from: b, reason: collision with root package name */
    public static Class<?> f1201b;

    /* renamed from: c, reason: collision with root package name */
    public static Method f1202c;

    static {
        try {
            f1201b = Class.forName("com.android.id.impl.IdProviderImpl");
            f1200a = f1201b.newInstance();
            f1202c = f1201b.getMethod("getOAID", Context.class);
        } catch (Exception e) {
            String str = o.f1173b;
            StringBuilder sbA = b.a.a.a.a.a("Api#static reflect exception! ");
            sbA.append(e.getMessage());
            String string = sbA.toString();
            h hVar = g.f1165b;
            if (hVar == null || g.f1164a > 6) {
                return;
            }
            ((h.a) hVar).c(str, string, null);
        }
    }

    public static boolean a() {
        return (f1201b == null || f1200a == null || f1202c == null) ? false : true;
    }

    @Override // b.b.b.p
    public boolean b(Context context) {
        return a();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    @Override // b.b.b.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b.b.b.p.a c(android.content.Context r7) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r6 = this;
            r0 = 0
            b.b.b.p$a r1 = new b.b.b.p$a     // Catch: java.lang.Exception -> L21
            r1.<init>()     // Catch: java.lang.Exception -> L21
            java.lang.reflect.Method r2 = b.b.b.z.f1202c     // Catch: java.lang.Exception -> L21
            java.lang.Object r3 = b.b.b.z.f1200a     // Catch: java.lang.Exception -> L21
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
            r1.f1176b = r7     // Catch: java.lang.Exception -> L21
            return r1
        L21:
            r7 = move-exception
            r7.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.b.z.c(android.content.Context):b.b.b.p$a");
    }
}
