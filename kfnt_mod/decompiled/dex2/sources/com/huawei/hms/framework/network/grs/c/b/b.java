package com.huawei.hms.framework.network.grs.c.b;

import android.os.Build;
import com.huawei.hms.framework.common.Logger;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private static final String[] a = {"TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"};
    private static final String[] b = {"TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"};
    private static final String[] c = {"TLS_RSA", "TEA", "SHA0", "MD2", "MD4", "RIPEMD", "NULL", "RC4", "DES", "DESX", "DES40", "RC2", SameMD5.TAG, "ANON", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"};
    private static final String[] d = {"TEA", "SHA0", "MD2", "MD4", "RIPEMD", "NULL", "RC4", "DES", "DESX", "DES40", "RC2", SameMD5.TAG, "ANON", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"};
    private static final String[] e = {"CBC", "TEA", "SHA0", "MD2", "MD4", "RIPEMD", "NULL", "RC4", "DES", "DESX", "DES40", "RC2", SameMD5.TAG, "ANON", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"};

    public static SSLContext a() {
        return SSLContext.getInstance(Build.VERSION.SDK_INT >= 29 ? "TLSv1.3" : Build.VERSION.SDK_INT >= 16 ? "TLSv1.2" : "TLS");
    }

    public static void a(SSLSocket sSLSocket) {
        if (sSLSocket == null || b(sSLSocket)) {
            return;
        }
        c(sSLSocket);
    }

    private static boolean a(SSLSocket sSLSocket, boolean z) {
        if (sSLSocket == null) {
            return false;
        }
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        ArrayList arrayList = new ArrayList();
        String[] strArr = z ? Build.VERSION.SDK_INT > 19 ? e : d : c;
        int length = enabledCipherSuites.length;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= length) {
                break;
            }
            String str = enabledCipherSuites[i];
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = false;
                    break;
                }
                if (upperCase.contains(strArr[i2].toUpperCase(Locale.ENGLISH))) {
                    break;
                }
                i2++;
            }
            if (!z2) {
                arrayList.add(str);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(javax.net.ssl.SSLSocket r11) {
        /*
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            java.lang.String[] r1 = r11.getEnabledCipherSuites()
            java.lang.String r2 = "SSLUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "before filter algriom: "
            r3.append(r4)
            java.util.List r4 = java.util.Arrays.asList(r1)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.huawei.hms.framework.common.Logger.v(r2, r3)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String[] r3 = com.huawei.hms.framework.network.grs.c.b.b.b
            java.util.List r3 = java.util.Arrays.asList(r3)
            java.lang.String[] r4 = com.huawei.hms.framework.network.grs.c.b.b.a
            java.util.List r4 = java.util.Arrays.asList(r4)
            int r5 = r1.length
            r6 = 0
        L35:
            if (r6 >= r5) goto L59
            r7 = r1[r6]
            java.util.Locale r8 = java.util.Locale.ENGLISH
            java.lang.String r8 = r7.toUpperCase(r8)
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 19
            if (r9 <= r10) goto L4f
            boolean r8 = r3.contains(r8)
            if (r8 == 0) goto L56
        L4b:
            r2.add(r7)
            goto L56
        L4f:
            boolean r8 = r4.contains(r8)
            if (r8 == 0) goto L56
            goto L4b
        L56:
            int r6 = r6 + 1
            goto L35
        L59:
            java.lang.String r1 = "SSLUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "after filter algriom: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            com.huawei.hms.framework.common.Logger.v(r1, r3)
            boolean r1 = r2.isEmpty()
            if (r1 != 0) goto L86
            int r0 = r2.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r2.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r11.setEnabledCipherSuites(r0)
            r11 = 1
            return r11
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.c.b.b.b(javax.net.ssl.SSLSocket):boolean");
    }

    public static boolean c(SSLSocket sSLSocket) {
        return a(sSLSocket, false);
    }

    public static void d(SSLSocket sSLSocket) {
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

    public static void e(SSLSocket sSLSocket) {
        if (sSLSocket == null) {
            return;
        }
        d(sSLSocket);
        a(sSLSocket);
        Logger.v("SSLUtil", "current platform level is{%d},protocols is{%s} and safeCipherSuites is{%s}", Integer.valueOf(Build.VERSION.SDK_INT), Arrays.toString(sSLSocket.getEnabledProtocols()), Arrays.toString(sSLSocket.getEnabledCipherSuites()));
    }
}
