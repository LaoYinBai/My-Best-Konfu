package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12;

import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.c0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.p;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.s;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.z;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j implements t {

    /* renamed from: a, reason: collision with root package name */
    private final v f1541a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f1542b;

    /* renamed from: c, reason: collision with root package name */
    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g f1543c;
    private Object d;
    private volatile boolean e;

    public j(v vVar, boolean z) {
        this.f1541a = vVar;
        this.f1542b = z;
    }

    public boolean a() {
        return this.e;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t
    public a0 a(t.a aVar) throws IOException {
        y yVarA;
        y yVarB = aVar.b();
        g gVar = (g) aVar;
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e eVarE = gVar.e();
        p pVarG = gVar.g();
        this.f1543c = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g(this.f1541a.d(), a(yVarB.g()), eVarE, pVarG, this.d);
        y yVar = yVarB;
        a0 a0VarA = null;
        int i = 0;
        while (!this.e) {
            try {
                try {
                    try {
                        a0 a0VarA2 = gVar.a(yVar, this.f1543c, null, null);
                        a0VarA = a0VarA != null ? a0VarA2.p().c(a0VarA.p().a((b0) null).a()).a() : a0VarA2;
                        yVarA = a(a0VarA);
                    } catch (IOException e) {
                        if (!a(e, !(e instanceof bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.a), yVar)) {
                            throw e;
                        }
                    }
                } catch (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.e e2) {
                    if (!a(e2.a(), false, yVar)) {
                        throw e2.a();
                    }
                }
                if (yVarA == null) {
                    if (!this.f1542b) {
                        this.f1543c.e();
                    }
                    return a0VarA;
                }
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(a0VarA.i());
                int i2 = i + 1;
                if (i2 <= 20) {
                    yVarA.a();
                    if (!a(a0VarA, yVarA.g())) {
                        this.f1543c.e();
                        this.f1543c = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g(this.f1541a.d(), a(yVarA.g()), eVarE, pVarG, this.d);
                    } else if (this.f1543c.a() != null) {
                        throw new IllegalStateException("Closing the body of " + a0VarA + " didn't close its backing stream. Bad interceptor?");
                    }
                    yVar = yVarA;
                    i = i2;
                } else {
                    this.f1543c.e();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
            } catch (Throwable th) {
                this.f1543c.a((IOException) null);
                this.f1543c.e();
                throw th;
            }
        }
        this.f1543c.e();
        throw new IOException("Canceled");
    }

    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a a(s sVar) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierM;
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.g gVarB;
        if (sVar.h()) {
            SSLSocketFactory sSLSocketFactoryX = this.f1541a.x();
            hostnameVerifierM = this.f1541a.m();
            sSLSocketFactory = sSLSocketFactoryX;
            gVarB = this.f1541a.b();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierM = null;
            gVarB = null;
        }
        return new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a(sVar.g(), sVar.j(), this.f1541a.h(), this.f1541a.w(), sSLSocketFactory, hostnameVerifierM, gVarB, this.f1541a.s(), this.f1541a.r(), this.f1541a.q(), this.f1541a.e(), this.f1541a.t());
    }

    private boolean a(IOException iOException, boolean z, y yVar) throws IOException {
        this.f1543c.a(iOException);
        if (!this.f1541a.v()) {
            return false;
        }
        if (z) {
            yVar.a();
        }
        return a(iOException, z) && this.f1543c.c();
    }

    private boolean a(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private y a(a0 a0Var) throws IOException {
        String strB;
        s sVarB;
        Proxy proxyR;
        if (a0Var != null) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c cVarB = this.f1543c.b();
            c0 c0VarA = cVarB != null ? cVarB.a() : null;
            int iK = a0Var.k();
            String strE = a0Var.s().e();
            switch (iK) {
                case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
                case 301:
                case 302:
                case 303:
                    break;
                case 307:
                case 308:
                    if (!strE.equals("GET") && !strE.equals(Request.Method.HEAD)) {
                        return null;
                    }
                    break;
                case 401:
                    return this.f1541a.a().a(c0VarA, a0Var);
                case 407:
                    if (c0VarA != null) {
                        proxyR = c0VarA.b();
                    } else {
                        proxyR = this.f1541a.r();
                    }
                    if (proxyR.type() == Proxy.Type.HTTP) {
                        return this.f1541a.s().a(c0VarA, a0Var);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                case 408:
                    if (!this.f1541a.v()) {
                        return null;
                    }
                    a0Var.s().a();
                    if (a0Var.q() == null || a0Var.q().k() != 408) {
                        return a0Var.s();
                    }
                    return null;
                default:
                    return null;
            }
            if (!this.f1541a.k() || (strB = a0Var.b("Location")) == null || (sVarB = a0Var.s().g().b(strB)) == null) {
                return null;
            }
            if (!sVarB.m().equals(a0Var.s().g().m()) && !this.f1541a.l()) {
                return null;
            }
            y.a aVarF = a0Var.s().f();
            if (f.b(strE)) {
                boolean zD = f.d(strE);
                if (f.c(strE)) {
                    aVarF.a("GET", (z) null);
                } else {
                    aVarF.a(strE, zD ? a0Var.s().a() : null);
                }
                if (!zD) {
                    aVarF.a("Transfer-Encoding");
                    aVarF.a("Content-Length");
                    aVarF.a("Content-Type");
                }
            }
            if (!a(a0Var, sVarB)) {
                aVarF.a(HttpConstant.AUTHORIZATION);
            }
            return aVarF.a(sVarB).a();
        }
        throw new IllegalStateException();
    }

    private boolean a(a0 a0Var, s sVar) {
        s sVarG = a0Var.s().g();
        return sVarG.g().equals(sVar.g()) && sVarG.j() == sVar.j() && sVarG.m().equals(sVar.m());
    }
}
