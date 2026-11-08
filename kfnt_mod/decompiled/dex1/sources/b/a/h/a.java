package b.a.h;

import android.os.Build;
import android.util.Log;
import anet.channel.entity.ConnType;
import b.aa;
import com.bytedance.sdk.account.execute.ApiRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a extends f {

    /* renamed from: a, reason: collision with root package name */
    private static final int f928a = 4000;

    /* renamed from: b, reason: collision with root package name */
    private final Class<?> f929b;
    private final e<Socket> e;
    private final e<Socket> f;
    private final e<Socket> g;
    private final e<Socket> h;
    private final c i = c.a();

    a(Class<?> cls, e<Socket> eVar, e<Socket> eVar2, e<Socket> eVar3, e<Socket> eVar4) {
        this.f929b = cls;
        this.e = eVar;
        this.f = eVar2;
        this.g = eVar3;
        this.h = eVar4;
    }

    @Override // b.a.h.f
    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (!b.a.c.a(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (ClassCastException e2) {
            if (Build.VERSION.SDK_INT == 26) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
            throw e2;
        } catch (SecurityException e3) {
            IOException iOException2 = new IOException("Exception in connect");
            iOException2.initCause(e3);
            throw iOException2;
        }
    }

    @Override // b.a.h.f
    protected X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        Object objA = a(sSLSocketFactory, this.f929b, "sslParameters");
        if (objA == null) {
            try {
                objA = a(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
            } catch (ClassNotFoundException unused) {
                return super.a(sSLSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) a(objA, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager != null ? x509TrustManager : (X509TrustManager) a(objA, X509TrustManager.class, "trustManager");
    }

    @Override // b.a.h.f
    public void a(SSLSocket sSLSocket, String str, List<aa> list) {
        if (str != null) {
            this.e.b(sSLSocket, true);
            this.f.b(sSLSocket, str);
        }
        if (this.h == null || !this.h.a((e<Socket>) sSLSocket)) {
            return;
        }
        this.h.d(sSLSocket, b(list));
    }

    @Override // b.a.h.f
    @Nullable
    public String a(SSLSocket sSLSocket) {
        byte[] bArr;
        if (this.g == null || !this.g.a((e<Socket>) sSLSocket) || (bArr = (byte[]) this.g.d(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, b.a.c.e);
    }

    @Override // b.a.h.f
    public void a(int i, String str, Throwable th) {
        int iMin;
        int i2 = i != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int iIndexOf = str.indexOf(10, i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i3 = iMin;
                }
            }
            i3 = iMin + 1;
        }
    }

    @Override // b.a.h.f
    public Object a(String str) {
        return this.i.a(str);
    }

    @Override // b.a.h.f
    public void a(String str, Object obj) {
        if (this.i.a(obj)) {
            return;
        }
        a(5, str, (Throwable) null);
    }

    @Override // b.a.h.f
    public boolean b(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return a(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.b(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw b.a.c.a("unable to determine cleartext support", e);
        }
    }

    private boolean a(String str, Class<?> cls, Object obj) throws IllegalAccessException, InvocationTargetException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return b(str, cls, obj);
        }
    }

    private boolean b(String str, Class<?> cls, Object obj) throws IllegalAccessException, InvocationTargetException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.b(str);
        }
    }

    private static boolean b() throws ClassNotFoundException {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // b.a.h.f
    public b.a.j.c a(X509TrustManager x509TrustManager) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0021a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.a(x509TrustManager);
        }
    }

    public static f a() {
        Class<?> cls;
        e eVar;
        e eVar2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            e eVar3 = new e(null, "setUseSessionTickets", Boolean.TYPE);
            e eVar4 = new e(null, "setHostname", String.class);
            if (b()) {
                e eVar5 = new e(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                eVar2 = new e(null, "setAlpnProtocols", byte[].class);
                eVar = eVar5;
            } else {
                eVar = null;
                eVar2 = null;
            }
            return new a(cls2, eVar3, eVar4, eVar, eVar2);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    @Override // b.a.h.f
    public b.a.j.f b(X509TrustManager x509TrustManager) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.b(x509TrustManager);
        }
    }

    /* renamed from: b.a.h.a$a, reason: collision with other inner class name */
    static final class C0021a extends b.a.j.c {

        /* renamed from: a, reason: collision with root package name */
        private final Object f930a;

        /* renamed from: b, reason: collision with root package name */
        private final Method f931b;

        public int hashCode() {
            return 0;
        }

        C0021a(Object obj, Method method) {
            this.f930a = obj;
            this.f931b = method;
        }

        @Override // b.a.j.c
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.f931b.invoke(this.f930a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage());
                sSLPeerUnverifiedException.initCause(e2);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0021a;
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private final Method f934a;

        /* renamed from: b, reason: collision with root package name */
        private final Method f935b;

        /* renamed from: c, reason: collision with root package name */
        private final Method f936c;

        c(Method method, Method method2, Method method3) {
            this.f934a = method;
            this.f935b = method2;
            this.f936c = method3;
        }

        Object a(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (this.f934a != null) {
                try {
                    Object objInvoke = this.f934a.invoke(null, new Object[0]);
                    this.f935b.invoke(objInvoke, str);
                    return objInvoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        boolean a(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (obj == null) {
                return false;
            }
            try {
                this.f936c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        static c a() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod(ApiRequest.METHOD_GET, new Class[0]);
                method2 = cls.getMethod(ConnType.PK_OPEN, String.class);
                method = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method = null;
                method2 = null;
            }
            return new c(method3, method2, method);
        }
    }

    static final class b implements b.a.j.f {

        /* renamed from: a, reason: collision with root package name */
        private final X509TrustManager f932a;

        /* renamed from: b, reason: collision with root package name */
        private final Method f933b;

        b(X509TrustManager x509TrustManager, Method method) {
            this.f933b = method;
            this.f932a = x509TrustManager;
        }

        @Override // b.a.j.f
        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f933b.invoke(this.f932a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e) {
                throw b.a.c.a("unable to get issues and signature", (Exception) e);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f932a.equals(bVar.f932a) && this.f933b.equals(bVar.f933b);
        }

        public int hashCode() {
            return this.f932a.hashCode() + (this.f933b.hashCode() * 31);
        }
    }
}
