package com.huawei.hms.framework.network.grs.c.b;

import android.content.Context;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
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

    public d(Context context, String str) throws Throwable {
        InputStream inputStreamOpen;
        if (context == null || str == null) {
            throw new IllegalArgumentException("context or trust file is null");
        }
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            KeyStore keyStore = KeyStore.getInstance("bks");
            inputStreamOpen = context.getAssets().open(str);
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
                IoUtils.closeSecure(inputStreamOpen);
            } catch (Throwable th) {
                th = th;
                IoUtils.closeSecure(inputStreamOpen);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
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
        } catch (Exception unused) {
            Logger.w("SecureX509TrustManager", "getAcceptedIssuers exception");
            return new X509Certificate[0];
        }
    }
}
