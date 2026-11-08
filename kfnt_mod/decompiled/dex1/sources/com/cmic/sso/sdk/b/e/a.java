package com.cmic.sso.sdk.b.e;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private X509Certificate f5438a;

    /* renamed from: b, reason: collision with root package name */
    private SSLContext f5439b;

    private void a(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        if (this.f5438a == null) {
            ByteArrayInputStream byteArrayInputStream2 = null;
            try {
                try {
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
                    } catch (CertificateException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                    byteArrayInputStream = byteArrayInputStream2;
                }
                try {
                    this.f5438a = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
                    byteArrayInputStream.close();
                } catch (CertificateException e2) {
                    e = e2;
                    byteArrayInputStream2 = byteArrayInputStream;
                    e.printStackTrace();
                    if (byteArrayInputStream2 != null) {
                        byteArrayInputStream2.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public a(String str) throws Throwable {
        a(str);
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("cert", this.f5438a);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            this.f5439b = SSLContext.getInstance("SSL");
            this.f5439b.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SSLContext a() {
        return this.f5439b;
    }
}
