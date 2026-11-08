package com.huawei.updatesdk.a.a.a;

import android.os.Build;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c {
    private static final String[] a = {"TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"};
    private static final String[] b = {"TEA", "SHA0", "MD2", "MD4", "RIPEMD", "aNULL", "eNULL", "RC4", "DES", "DESX", "DES40", "RC2", SameMD5.TAG, "ANON", "NULL", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_RSA", "SSL_RSA", "TLS_DH_anon_WITH_AES_256_CBC_SHA"};
    private static final String[] c = {"TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", "TLS_PSK_WITH_AES_128_CBC_SHA", "TLS_PSK_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA"};

    public static SSLContext a() throws NoSuchAlgorithmException {
        return SSLContext.getInstance(Build.VERSION.SDK_INT >= 29 ? "TLSv1.3" : Build.VERSION.SDK_INT >= 16 ? "TLSv1.2" : "TLS");
    }

    public static void a(SSLSocket sSLSocket) {
        String str;
        if (sSLSocket == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            sSLSocket.setEnabledProtocols(new String[]{"TLSv1.3", "TLSv1.2"});
        }
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 29) {
            str = "TLSv1.2";
        } else if (Build.VERSION.SDK_INT >= 16) {
            return;
        } else {
            str = "TLSv1";
        }
        sSLSocket.setEnabledProtocols(new String[]{str});
    }

    private static boolean a(SSLSocket sSLSocket, ArrayList<String> arrayList) {
        if (sSLSocket != null && arrayList != null) {
            String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            ArrayList arrayList2 = new ArrayList();
            for (String str : enabledCipherSuites) {
                String upperCase = str.toUpperCase(Locale.ENGLISH);
                Iterator<String> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (upperCase.equals(it.next())) {
                        arrayList2.add(str);
                        break;
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                sSLSocket.setEnabledCipherSuites((String[]) arrayList2.toArray(new String[arrayList2.size()]));
                return true;
            }
        }
        return false;
    }

    public static void b(SSLSocket sSLSocket) {
        if (sSLSocket == null) {
            return;
        }
        a(sSLSocket);
        c(sSLSocket);
    }

    private static void b(SSLSocket sSLSocket, ArrayList<String> arrayList) {
        boolean z;
        if (sSLSocket == null || arrayList == null) {
            return;
        }
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        ArrayList arrayList2 = new ArrayList();
        for (String str : enabledCipherSuites) {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            Iterator<String> it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (upperCase.contains(it.next().toUpperCase(Locale.ENGLISH))) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList2.add(str);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        sSLSocket.setEnabledCipherSuites((String[]) arrayList2.toArray(new String[arrayList2.size()]));
    }

    private static void c(SSLSocket sSLSocket) {
        if (sSLSocket == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 20) {
            arrayList.addAll(Arrays.asList(a));
            arrayList2.addAll(Arrays.asList(b));
            arrayList2.addAll(Arrays.asList(c));
        } else {
            arrayList.addAll(Arrays.asList(a));
            arrayList2.addAll(Arrays.asList(b));
            arrayList.addAll(Arrays.asList(c));
        }
        if (a(sSLSocket, arrayList)) {
            return;
        }
        b(sSLSocket, arrayList2);
    }
}
