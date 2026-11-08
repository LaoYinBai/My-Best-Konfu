package b.a.h;

import b.aa;
import b.z;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final f f947a = b();

    /* renamed from: b, reason: collision with root package name */
    private static final Logger f948b = Logger.getLogger(z.class.getName());

    /* renamed from: c, reason: collision with root package name */
    public static final int f949c = 4;
    public static final int d = 5;

    @Nullable
    public String a(SSLSocket sSLSocket) {
        return null;
    }

    public void a(SSLSocket sSLSocket, String str, List<aa> list) {
    }

    public void b(SSLSocket sSLSocket) {
    }

    public boolean b(String str) {
        return true;
    }

    public String d() {
        return "OkHttp";
    }

    public static f c() {
        return f947a;
    }

    protected X509TrustManager a(SSLSocketFactory sSLSocketFactory) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Object objA = a(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (objA == null) {
                return null;
            }
            return (X509TrustManager) a(objA, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(int i, String str, Throwable th) {
        f948b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public Object a(String str) {
        if (f948b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        a(5, str, (Throwable) obj);
    }

    public static List<String> a(List<aa> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            aa aaVar = list.get(i);
            if (aaVar != aa.HTTP_1_0) {
                arrayList.add(aaVar.toString());
            }
        }
        return arrayList;
    }

    public b.a.j.c a(X509TrustManager x509TrustManager) {
        return new b.a.j.a(b(x509TrustManager));
    }

    public b.a.j.c b(SSLSocketFactory sSLSocketFactory) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        X509TrustManager x509TrustManagerA = a(sSLSocketFactory);
        if (x509TrustManagerA == null) {
            throw new IllegalStateException("Unable to extract the trust manager on " + c() + ", sslSocketFactory is " + sSLSocketFactory.getClass());
        }
        return a(x509TrustManagerA);
    }

    public static boolean e() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    private static f b() {
        f fVarB;
        f fVarA = a.a();
        if (fVarA != null) {
            return fVarA;
        }
        if (e() && (fVarB = b.b()) != null) {
            return fVarB;
        }
        c cVarB = c.b();
        if (cVarB != null) {
            return cVarB;
        }
        f fVarB2 = d.b();
        return fVarB2 != null ? fVarB2 : new f();
    }

    static byte[] b(List<aa> list) {
        c.c cVar = new c.c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            aa aaVar = list.get(i);
            if (aaVar != aa.HTTP_1_0) {
                cVar.m(aaVar.toString().length());
                cVar.b(aaVar.toString());
            }
        }
        return cVar.x();
    }

    static <T> T a(Object obj, Class<T> cls, String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Object objA;
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null && cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (objA = a(obj, (Class<Object>) Object.class, "delegate")) == null) {
            return null;
        }
        return (T) a(objA, cls, str);
    }

    public SSLContext e_() {
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No TLS provider", e);
        }
    }

    public b.a.j.f b(X509TrustManager x509TrustManager) {
        return new b.a.j.b(x509TrustManager.getAcceptedIssuers());
    }
}
