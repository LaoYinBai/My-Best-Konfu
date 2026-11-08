package com.huawei.a.a.b.f;

import android.content.Context;
import java.io.Closeable;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements X509TrustManager {
    protected List<X509TrustManager> a = new ArrayList();

    public d(Context context) throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        if (context == null) {
            throw new IllegalArgumentException("context is null,can not read CA");
        }
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            KeyStore keyStore = KeyStore.getInstance("bks");
            inputStreamOpen = context.getAssets().open("updatesdkcas.bks");
            try {
                inputStreamOpen.reset();
                keyStore.load(inputStreamOpen, "".toCharArray());
                trustManagerFactory.init(keyStore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                for (int i = 0; i < trustManagers.length; i++) {
                    if (trustManagers[i] instanceof X509TrustManager) {
                        this.a.add((X509TrustManager) trustManagers[i]);
                    }
                }
                if (this.a.isEmpty()) {
                    throw new CertificateException("X509TrustManager is empty");
                }
                com.huawei.a.a.b.k.d.a((Closeable) inputStreamOpen);
            } catch (Throwable th2) {
                th = th2;
                com.huawei.a.a.b.k.d.a((Closeable) inputStreamOpen);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpen = null;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (this.a.isEmpty()) {
            throw new CertificateException("checkClientTrusted CertificateException");
        }
        this.a.get(0).checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (this.a.isEmpty()) {
            throw new CertificateException("checkServerTrusted CertificateException");
        }
        this.a.get(0).checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<X509TrustManager> it = this.a.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(it.next().getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e) {
            com.huawei.a.a.b.e.a.d("SecureX509TrustManager", "getAcceptedIssuers exception : " + e.getMessage());
            return new X509Certificate[0];
        }
    }
}
