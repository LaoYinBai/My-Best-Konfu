package bitter.jnibridge;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class JNIBridge {

    private static class a implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        private Object f1306a = new Object[0];

        /* renamed from: b, reason: collision with root package name */
        private long f1307b;

        /* renamed from: c, reason: collision with root package name */
        private Constructor f1308c;

        public a(long j) {
            this.f1307b = j;
            try {
                this.f1308c = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                this.f1308c.setAccessible(true);
            } catch (NoClassDefFoundError unused) {
                this.f1308c = null;
            } catch (NoSuchMethodException unused2) {
                this.f1308c = null;
            }
        }

        private Object a(Object obj, Method method, Object[] objArr) {
            if (objArr == null) {
                objArr = new Object[0];
            }
            Class<?> declaringClass = method.getDeclaringClass();
            return ((MethodHandles.Lookup) this.f1308c.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        }

        public final void a() {
            synchronized (this.f1306a) {
                this.f1307b = 0L;
            }
        }

        public final void finalize() {
            synchronized (this.f1306a) {
                if (this.f1307b == 0) {
                    return;
                }
                JNIBridge.delete(this.f1307b);
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            synchronized (this.f1306a) {
                if (this.f1307b == 0) {
                    return null;
                }
                try {
                    return JNIBridge.invoke(this.f1307b, method.getDeclaringClass(), method, objArr);
                } catch (NoSuchMethodError e) {
                    if (this.f1308c == null) {
                        System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                        throw e;
                    }
                    if ((method.getModifiers() & 1024) == 0) {
                        return a(obj, method, objArr);
                    }
                    throw e;
                }
            }
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        if (obj != null) {
            ((a) Proxy.getInvocationHandler(obj)).a();
        }
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new a(j));
    }
}
