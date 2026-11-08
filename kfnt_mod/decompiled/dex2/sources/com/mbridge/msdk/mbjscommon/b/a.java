package com.mbridge.msdk.mbjscommon.b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: Hack.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static InterfaceC0119a a;

    /* compiled from: Hack.java */
    /* renamed from: com.mbridge.msdk.mbjscommon.b.a$a, reason: collision with other inner class name */
    public interface InterfaceC0119a {
        boolean a(b.C0120a c0120a);
    }

    /* compiled from: Hack.java */
    public static abstract class b {

        /* compiled from: Hack.java */
        /* renamed from: com.mbridge.msdk.mbjscommon.b.a$b$a, reason: collision with other inner class name */
        public static class C0120a extends Throwable {
            private Class<?> a;
            private String b;

            public C0120a(String str) {
                super(str);
            }

            public C0120a(Exception exc) {
                super(exc);
            }

            @Override // java.lang.Throwable
            public final String toString() {
                if (getCause() == null) {
                    return super.toString();
                }
                return getClass().getName() + ": " + getCause();
            }

            public final void a(Class<?> cls) {
                this.a = cls;
            }

            public final void a(String str) {
                this.b = str;
            }
        }
    }

    /* compiled from: Hack.java */
    public static class d {
        protected final Method a;

        public final Object a(Object obj, Object... objArr) throws IllegalArgumentException, InvocationTargetException {
            try {
                return this.a.invoke(obj, objArr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }

        d(Class<?> cls, String str, Class<?>[] clsArr, int i) throws NoSuchMethodException, SecurityException, b.C0120a {
            Method declaredMethod = null;
            if (cls == null) {
                return;
            }
            while (true) {
                if (cls == Object.class) {
                    break;
                }
                try {
                    declaredMethod = cls.getDeclaredMethod(str, clsArr);
                    break;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e2) {
                    try {
                        try {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            b.C0120a c0120a = new b.C0120a(e3);
                            c0120a.a(cls);
                            c0120a.a(str);
                            a.b(c0120a);
                        }
                    } finally {
                        this.a = declaredMethod;
                    }
                }
                cls = cls.getSuperclass();
            }
            if (i > 0 && (declaredMethod.getModifiers() & i) != i) {
                a.b(new b.C0120a(declaredMethod + " does not match modifiers: " + i));
            }
            declaredMethod.setAccessible(true);
        }

        public final Method a() {
            return this.a;
        }
    }

    /* compiled from: Hack.java */
    public static class c<C> {
        protected Class<C> a;

        public final d a(String str, Class<?>... clsArr) throws b.C0120a {
            return new d(this.a, str, clsArr, 0);
        }

        public c(Class<C> cls) {
            this.a = cls;
        }
    }

    public static <T> c<T> a(ClassLoader classLoader, String str) throws b.C0120a {
        try {
            return new c<>(classLoader.loadClass(str));
        } catch (Exception e) {
            b(new b.C0120a(e));
            return new c<>(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(b.C0120a c0120a) throws b.C0120a {
        if (a == null || !a.a(c0120a)) {
            throw c0120a;
        }
    }
}
