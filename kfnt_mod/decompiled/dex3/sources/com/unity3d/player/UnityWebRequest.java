package com.unity3d.player;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.security.cert.CertPathValidatorException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class UnityWebRequest implements Runnable {
    private static final HostnameVerifier m;
    private long a;
    private String b;
    private String c;
    private Map d;
    private boolean e;
    private int f;
    private long g;
    private long h;
    private boolean i;
    private boolean j;
    private Thread k;
    private boolean l;

    static {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
        m = new HostnameVerifier() { // from class: com.unity3d.player.UnityWebRequest.1
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
    }

    UnityWebRequest(long j, String str, Map map, String str2, boolean z, int i) {
        this.a = j;
        this.b = str2;
        this.c = str;
        this.d = map;
        this.e = z;
        this.f = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean aborted() {
        return this.l;
    }

    static void clearCookieCache(String str, String str2) {
        CookieStore cookieStore;
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler == null || !(cookieHandler instanceof CookieManager) || (cookieStore = ((CookieManager) cookieHandler).getCookieStore()) == null) {
            return;
        }
        if (str == null) {
            cookieStore.removeAll();
            return;
        }
        try {
            URI uri = new URI(null, str, str2, null);
            List<HttpCookie> list = cookieStore.get(uri);
            if (list != null) {
                Iterator<HttpCookie> it = list.iterator();
                while (it.hasNext()) {
                    cookieStore.remove(uri, it.next());
                }
            }
        } catch (URISyntaxException unused) {
            g.Log(6, String.format("UnityWebRequest: failed to parse URI %s", str));
        }
    }

    private static native void contentLengthCallback(long j, int i);

    private static native boolean downloadCallback(long j, ByteBuffer byteBuffer, int i);

    private static native void errorCallback(long j, int i, String str);

    private boolean hasTimedOut() {
        return this.f > 0 && System.currentTimeMillis() - this.g >= ((long) this.f);
    }

    private static native void headerCallback(long j, String str, String str2);

    private static native void responseCodeCallback(long j, int i);

    /* JADX WARN: Code restructure failed: missing block: B:130:0x0270, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0273, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void runSafe() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 701
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityWebRequest.runSafe():void");
    }

    private static native int uploadCallback(long j, ByteBuffer byteBuffer);

    private static native boolean validateCertificateCallback(long j, byte[] bArr);

    public void abort() {
        try {
            synchronized (this) {
                this.l = true;
                if (this.k != null) {
                    this.k.interrupt();
                }
            }
        } catch (Exception e) {
            g.Log(6, "UnityWebRequest: abort threw an exception " + e.getMessage());
        }
    }

    protected void badProtocolCallback(String str) {
        g.Log(6, String.format("UnityWebRequest: badProtocolCallback with error=%s url=%s", str, this.b));
        errorCallback(this.a, 4, str);
    }

    protected void contentLengthCallback(int i) {
        contentLengthCallback(this.a, i);
    }

    protected boolean downloadCallback(ByteBuffer byteBuffer, int i) {
        return downloadCallback(this.a, byteBuffer, i);
    }

    protected void errorCallback(String str) {
        g.Log(6, String.format("UnityWebRequest: errorCallback with error=%s url=%s", str, this.b));
        errorCallback(this.a, 2, str);
    }

    protected void headerCallback(String str, String str2) {
        headerCallback(this.a, str, str2);
    }

    protected void headerCallback(Map map) {
        if (map == null || map.size() == 0) {
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str == null) {
                str = "Status";
            }
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                headerCallback(str, (String) it.next());
            }
        }
    }

    protected void malformattedUrlCallback(String str) {
        g.Log(6, String.format("UnityWebRequest: malformattedUrlCallback with error=%s url=%s", str, this.b));
        errorCallback(this.a, 5, str);
    }

    protected void responseCodeCallback(int i) {
        responseCodeCallback(this.a, i);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                synchronized (this) {
                    this.k = Thread.currentThread();
                }
                runSafe();
                synchronized (this) {
                    this.k = null;
                }
            } catch (Exception e) {
                errorCallback(e.toString());
                synchronized (this) {
                    this.k = null;
                }
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.k = null;
                throw th;
            }
        }
    }

    void setupTransferSettings(long j, boolean z, boolean z2) {
        this.h = j;
        this.i = z;
        this.j = z2;
    }

    protected void sslCannotConnectCallback(SSLException sSLException) {
        int i;
        String string = sSLException.toString();
        g.Log(6, String.format("UnityWebRequest: sslCannotConnectCallback with error=%s url=%s", string, this.b));
        SSLException cause = sSLException;
        while (true) {
            if (cause == null) {
                i = 16;
                break;
            } else if (cause instanceof SSLKeyException) {
                i = 23;
                break;
            } else if ((cause instanceof SSLPeerUnverifiedException) || (cause instanceof CertPathValidatorException)) {
                break;
            } else {
                cause = cause.getCause();
            }
        }
        i = 25;
        errorCallback(this.a, i, string);
    }

    protected void unknownHostCallback(String str) {
        g.Log(6, String.format("UnityWebRequest: unknownHostCallback with error=%s url=%s", str, this.b));
        errorCallback(this.a, 7, str);
    }

    protected int uploadCallback(ByteBuffer byteBuffer) {
        return uploadCallback(this.a, byteBuffer);
    }

    protected boolean validateCertificateCallback(byte[] bArr) {
        return validateCertificateCallback(this.a, bArr);
    }
}
