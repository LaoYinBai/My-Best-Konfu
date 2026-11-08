package b.a.c;

import b.l;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final List<l> f794a;

    /* renamed from: b, reason: collision with root package name */
    private int f795b = 0;

    /* renamed from: c, reason: collision with root package name */
    private boolean f796c;
    private boolean d;

    public b(List<l> list) {
        this.f794a = list;
    }

    public l a(SSLSocket sSLSocket) throws IOException {
        l lVar;
        int i = this.f795b;
        int size = this.f794a.size();
        while (true) {
            if (i >= size) {
                lVar = null;
                break;
            }
            lVar = this.f794a.get(i);
            if (lVar.a(sSLSocket)) {
                this.f795b = i + 1;
                break;
            }
            i++;
        }
        if (lVar == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.d + ", modes=" + this.f794a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.f796c = b(sSLSocket);
        b.a.a.f750a.a(lVar, sSLSocket, this.d);
        return lVar;
    }

    public boolean a(IOException iOException) {
        this.d = true;
        if (!this.f796c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }

    private boolean b(SSLSocket sSLSocket) {
        for (int i = this.f795b; i < this.f794a.size(); i++) {
            if (this.f794a.get(i).a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
