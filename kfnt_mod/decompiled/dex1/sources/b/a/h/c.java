package b.a.h;

import b.aa;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c extends f {

    /* renamed from: a, reason: collision with root package name */
    final Method f937a;

    /* renamed from: b, reason: collision with root package name */
    final Method f938b;

    c(Method method, Method method2) {
        this.f937a = method;
        this.f938b = method2;
    }

    @Override // b.a.h.f
    public void a(SSLSocket sSLSocket, String str, List<aa> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> listA = a(list);
            this.f937a.invoke(sSLParameters, listA.toArray(new String[listA.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw b.a.c.a("unable to set ssl parameters", (Exception) e);
        }
    }

    @Override // b.a.h.f
    @Nullable
    public String a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f938b.invoke(sSLSocket, new Object[0]);
            if (str == null) {
                return null;
            }
            if (str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw b.a.c.a("unable to get selected protocols", (Exception) e);
        }
    }

    @Override // b.a.h.f
    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    public static c b() {
        try {
            return new c(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
