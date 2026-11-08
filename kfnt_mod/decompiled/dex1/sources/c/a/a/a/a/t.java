package c.a.a.a.a;

import android.os.IBinder;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    IBinder f2118a;

    public t(IBinder iBinder) {
        this.f2118a = iBinder;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    @Override // java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object invoke(java.lang.Object r4, java.lang.reflect.Method r5, java.lang.Object[] r6) {
        /*
            r3 = this;
            java.lang.String r4 = r5.getName()
            java.lang.String r0 = "transact"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L50
            r4 = 0
            r0 = r6[r4]
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r1 = 1
            r2 = r6[r1]
            android.os.Parcel r2 = (android.os.Parcel) r2
            if (r0 == r1) goto L36
            r1 = 9
            if (r0 == r1) goto L21
            goto L50
        L21:
            int r0 = r2.dataSize()     // Catch: java.lang.Throwable -> L4a
            r2.setDataPosition(r4)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r4 = c.a.a.a.a.bo.d     // Catch: java.lang.Throwable -> L4b
            r2.enforceInterface(r4)     // Catch: java.lang.Throwable -> L4b
        L2d:
            java.lang.String r4 = r2.readString()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r1 = c.a.a.a.a.ar.a()     // Catch: java.lang.Throwable -> L4b
            goto L43
        L36:
            int r0 = r2.dataSize()     // Catch: java.lang.Throwable -> L4a
            r2.setDataPosition(r4)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r4 = c.a.a.a.a.bo.d     // Catch: java.lang.Throwable -> L4b
            r2.enforceInterface(r4)     // Catch: java.lang.Throwable -> L4b
            goto L2d
        L43:
            c.a.a.a.a.k.a(r4, r1)     // Catch: java.lang.Throwable -> L4b
            r2.setDataPosition(r0)     // Catch: java.lang.Throwable -> L4b
            goto L50
        L4a:
            r0 = 0
        L4b:
            if (r0 <= 0) goto L50
            r2.setDataPosition(r0)
        L50:
            android.os.IBinder r4 = r3.f2118a
            java.lang.Object r4 = r5.invoke(r4, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.t.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
