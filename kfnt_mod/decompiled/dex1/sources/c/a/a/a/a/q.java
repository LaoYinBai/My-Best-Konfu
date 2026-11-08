package c.a.a.a.a;

import android.content.ServiceConnection;
import com.bytedance.JProtect;
import com.bytedance.sdk.account.execute.ApiRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class q implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    private Object f2112a;

    public q(Object obj) {
        this.f2112a = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
    
        r5 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object a(java.lang.Object r5) {
        /*
            r0 = 0
            java.lang.Class r1 = r5.getClass()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r2 = "mDispatcher"
            java.lang.reflect.Field r1 = c.a.a.a.a.al.a(r1, r2)     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r5 = r1.get(r5)     // Catch: java.lang.Throwable -> L4d
            java.lang.Class r1 = r5.getClass()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r2 = "get"
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> L4d
            java.lang.reflect.Method r1 = c.a.a.a.a.ao.a(r1, r2, r4)     // Catch: java.lang.Throwable -> L4d
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r5 = r1.invoke(r5, r2)     // Catch: java.lang.Throwable -> L4d
            java.lang.Class r1 = r5.getClass()     // Catch: java.lang.Throwable -> L48
            java.lang.String r2 = "getServiceConnection"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> L48
            java.lang.reflect.Method r1 = c.a.a.a.a.ao.a(r1, r2, r4)     // Catch: java.lang.Throwable -> L48
            if (r1 == 0) goto L37
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L48
            java.lang.Object r5 = r1.invoke(r5, r2)     // Catch: java.lang.Throwable -> L48
            goto L49
        L37:
            java.lang.Class r1 = r5.getClass()     // Catch: java.lang.Throwable -> L48
            java.lang.String r2 = "mConnection"
            java.lang.reflect.Field r1 = c.a.a.a.a.al.a(r1, r2)     // Catch: java.lang.Throwable -> L48
            if (r1 == 0) goto L48
            java.lang.Object r5 = r1.get(r5)     // Catch: java.lang.Throwable -> L48
            goto L49
        L48:
            r5 = r0
        L49:
            b(r5)     // Catch: java.lang.Throwable -> L4d
            return r5
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.q.a(java.lang.Object):java.lang.Object");
    }

    public static Object a(Object obj, Object obj2) {
        try {
            Object obj3 = al.a(obj.getClass(), "mDispatcher").get(obj);
            Object objInvoke = ao.a(obj3.getClass(), ApiRequest.METHOD_GET, new Class[0]).invoke(obj3, new Object[0]);
            al.a(objInvoke.getClass(), "mConnection").set(objInvoke, obj2);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void b(Object obj) {
        for (int i = 0; i < obj.getClass().getInterfaces().length; i++) {
            try {
            } catch (Throwable unused) {
                return;
            }
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (((name.hashCode() == -1772511108 && name.equals("asBinder")) ? (char) 0 : (char) 65535) == 0) {
            try {
                Object objA = a(this.f2112a);
                if (objA != null) {
                    a(this.f2112a, Proxy.newProxyInstance(ServiceConnection.class.getClassLoader(), new Class[]{ServiceConnection.class}, new n(objA)));
                }
            } catch (Throwable unused) {
            }
        }
        return method.invoke(this.f2112a, objArr);
    }
}
