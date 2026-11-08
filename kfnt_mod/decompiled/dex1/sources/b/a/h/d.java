package b.a.h;

import b.aa;
import com.bytedance.sdk.account.execute.ApiRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d extends f {

    /* renamed from: a, reason: collision with root package name */
    private final Method f939a;

    /* renamed from: b, reason: collision with root package name */
    private final Method f940b;
    private final Method e;
    private final Class<?> f;
    private final Class<?> g;

    d(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f939a = method;
        this.f940b = method2;
        this.e = method3;
        this.f = cls;
        this.g = cls2;
    }

    @Override // b.a.h.f
    public void a(SSLSocket sSLSocket, String str, List<aa> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            this.f939a.invoke(null, sSLSocket, Proxy.newProxyInstance(f.class.getClassLoader(), new Class[]{this.f, this.g}, new a(a(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw b.a.c.a("unable to set alpn", (Exception) e);
        }
    }

    @Override // b.a.h.f
    public void b(SSLSocket sSLSocket) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            this.e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw b.a.c.a("unable to remove alpn", (Exception) e);
        }
    }

    @Override // b.a.h.f
    @Nullable
    public String a(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.f940b.invoke(null, sSLSocket));
            if (!aVar.f941a && aVar.f942b == null) {
                f.c().a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            }
            if (aVar.f941a) {
                return null;
            }
            return aVar.f942b;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw b.a.c.a("unable to get selected protocol", (Exception) e);
        }
    }

    public static f b() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new d(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod(ApiRequest.METHOD_GET, SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    private static class a implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        boolean f941a;

        /* renamed from: b, reason: collision with root package name */
        String f942b;

        /* renamed from: c, reason: collision with root package name */
        private final List<String> f943c;

        a(List<String> list) {
            this.f943c = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = b.a.c.f789b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f941a = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f943c;
            }
            if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                List list = (List) objArr[0];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (this.f943c.contains(list.get(i))) {
                        String str = (String) list.get(i);
                        this.f942b = str;
                        return str;
                    }
                }
                String str2 = this.f943c.get(0);
                this.f942b = str2;
                return str2;
            }
            if ((name.equals("protocolSelected") || name.equals("selected")) && objArr.length == 1) {
                this.f942b = (String) objArr[0];
                return null;
            }
            return method.invoke(this, objArr);
        }
    }
}
