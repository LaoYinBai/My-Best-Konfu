package b.a.d;

import androidx.appcompat.widget.ActivityChooserView;
import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import b.ac;
import b.ad;
import b.ae;
import b.af;
import b.ag;
import b.r;
import b.v;
import b.w;
import b.z;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j implements w {

    /* renamed from: a, reason: collision with root package name */
    private static final int f830a = 20;

    /* renamed from: b, reason: collision with root package name */
    private final z f831b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f832c;
    private volatile b.a.c.g d;
    private Object e;
    private volatile boolean f;

    public j(z zVar, boolean z) {
        this.f831b = zVar;
        this.f832c = z;
    }

    public void a() throws IOException {
        this.f = true;
        b.a.c.g gVar = this.d;
        if (gVar != null) {
            gVar.f();
        }
    }

    public boolean b() {
        return this.f;
    }

    public void a(Object obj) {
        this.e = obj;
    }

    public b.a.c.g c() {
        return this.d;
    }

    @Override // b.w
    public ae a(w.a aVar) throws IOException {
        ae aeVarA;
        ac acVarA;
        ac acVarA2 = aVar.a();
        g gVar = (g) aVar;
        b.e eVarC = gVar.c();
        r rVarI = gVar.i();
        b.a.c.g gVar2 = new b.a.c.g(this.f831b.q(), a(acVarA2.a()), eVarC, rVarI, this.e);
        this.d = gVar2;
        ae aeVar = null;
        int i = 0;
        while (!this.f) {
            try {
                try {
                    ae aeVarA2 = gVar.a(acVarA2, gVar2, null, null);
                    aeVarA = aeVar != null ? aeVarA2.i().c(aeVar.i().a((af) null).a()).a() : aeVarA2;
                    acVarA = a(aeVarA, gVar2.b());
                } catch (b.a.c.e e) {
                    if (!a(e.a(), gVar2, false, acVarA2)) {
                        throw e.a();
                    }
                } catch (IOException e2) {
                    if (!a(e2, gVar2, !(e2 instanceof b.a.f.a), acVarA2)) {
                        throw e2;
                    }
                }
                if (acVarA == null) {
                    if (!this.f832c) {
                        gVar2.d();
                    }
                    return aeVarA;
                }
                b.a.c.a(aeVarA.h());
                int i2 = i + 1;
                if (i2 > 20) {
                    gVar2.d();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                if (acVarA.d() instanceof l) {
                    gVar2.d();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", aeVarA.c());
                }
                if (!a(aeVarA, acVarA.a())) {
                    gVar2.d();
                    gVar2 = new b.a.c.g(this.f831b.q(), a(acVarA.a()), eVarC, rVarI, this.e);
                    this.d = gVar2;
                } else if (gVar2.a() != null) {
                    throw new IllegalStateException("Closing the body of " + aeVarA + " didn't close its backing stream. Bad interceptor?");
                }
                aeVar = aeVarA;
                acVarA2 = acVarA;
                i = i2;
            } catch (Throwable th) {
                gVar2.a((IOException) null);
                gVar2.d();
                throw th;
            }
        }
        gVar2.d();
        throw new IOException("Canceled");
    }

    private b.a a(v vVar) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierM;
        b.g gVarN;
        if (vVar.d()) {
            SSLSocketFactory sSLSocketFactoryL = this.f831b.l();
            hostnameVerifierM = this.f831b.m();
            sSLSocketFactory = sSLSocketFactoryL;
            gVarN = this.f831b.n();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierM = null;
            gVarN = null;
        }
        return new b.a(vVar.i(), vVar.j(), this.f831b.j(), this.f831b.k(), sSLSocketFactory, hostnameVerifierM, gVarN, this.f831b.p(), this.f831b.e(), this.f831b.v(), this.f831b.w(), this.f831b.f());
    }

    private boolean a(IOException iOException, b.a.c.g gVar, boolean z, ac acVar) throws IOException {
        gVar.a(iOException);
        if (this.f831b.t()) {
            return !(z && (acVar.d() instanceof l)) && a(iOException, z) && gVar.g();
        }
        return false;
    }

    private boolean a(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private ac a(ae aeVar, ag agVar) throws IOException {
        String strB;
        v vVarE;
        Proxy proxyE;
        if (aeVar == null) {
            throw new IllegalStateException();
        }
        int iC = aeVar.c();
        String strB2 = aeVar.a().b();
        switch (iC) {
            case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!strB2.equals("GET") && !strB2.equals(Request.Method.HEAD)) {
                    return null;
                }
                break;
            case 401:
                return this.f831b.o().a(agVar, aeVar);
            case 407:
                if (agVar != null) {
                    proxyE = agVar.b();
                } else {
                    proxyE = this.f831b.e();
                }
                if (proxyE.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.f831b.p().a(agVar, aeVar);
            case 408:
                if (!this.f831b.t() || (aeVar.a().d() instanceof l)) {
                    return null;
                }
                if ((aeVar.m() == null || aeVar.m().c() != 408) && a(aeVar, 0) <= 0) {
                    return aeVar.a();
                }
                return null;
            case 503:
                if ((aeVar.m() == null || aeVar.m().c() != 503) && a(aeVar, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) == 0) {
                    return aeVar.a();
                }
                return null;
            default:
                return null;
        }
        if (!this.f831b.s() || (strB = aeVar.b("Location")) == null || (vVarE = aeVar.a().a().e(strB)) == null) {
            return null;
        }
        if (!vVarE.c().equals(aeVar.a().a().c()) && !this.f831b.r()) {
            return null;
        }
        ac.a aVarF = aeVar.a().f();
        if (f.c(strB2)) {
            boolean zD = f.d(strB2);
            if (f.e(strB2)) {
                aVarF.a("GET", (ad) null);
            } else {
                aVarF.a(strB2, zD ? aeVar.a().d() : null);
            }
            if (!zD) {
                aVarF.b("Transfer-Encoding");
                aVarF.b("Content-Length");
                aVarF.b("Content-Type");
            }
        }
        if (!a(aeVar, vVarE)) {
            aVarF.b(HttpConstant.AUTHORIZATION);
        }
        return aVarF.a(vVarE).d();
    }

    private int a(ae aeVar, int i) {
        String strB = aeVar.b("Retry-After");
        return strB == null ? i : strB.matches("\\d+") ? Integer.valueOf(strB).intValue() : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    private boolean a(ae aeVar, v vVar) {
        v vVarA = aeVar.a().a();
        return vVarA.i().equals(vVar.i()) && vVarA.j() == vVar.j() && vVarA.c().equals(vVar.c());
    }
}
