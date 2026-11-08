package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2606a = "msp";

    /* renamed from: b, reason: collision with root package name */
    private static final String f2607b = "application/octet-stream;binary/octet-stream";

    /* renamed from: c, reason: collision with root package name */
    private static final CookieManager f2608c = new CookieManager();

    /* renamed from: com.alipay.sdk.net.a$a, reason: collision with other inner class name */
    public static final class C0079a {

        /* renamed from: a, reason: collision with root package name */
        public final String f2609a;

        /* renamed from: b, reason: collision with root package name */
        public final byte[] f2610b;

        /* renamed from: c, reason: collision with root package name */
        public final Map<String, String> f2611c;

        public C0079a(String str, Map<String, String> map, byte[] bArr) {
            this.f2609a = str;
            this.f2610b = bArr;
            this.f2611c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s headers=%s>", this.f2609a, this.f2611c);
        }
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public final Map<String, List<String>> f2612a;

        /* renamed from: b, reason: collision with root package name */
        public final String f2613b;

        /* renamed from: c, reason: collision with root package name */
        public final byte[] f2614c;

        public b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.f2612a = map;
            this.f2613b = str;
            this.f2614c = bArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v24, types: [java.io.BufferedOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.alipay.sdk.net.a.b a(android.content.Context r12, com.alipay.sdk.net.a.C0079a r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.a.a(android.content.Context, com.alipay.sdk.net.a$a):com.alipay.sdk.net.a$b");
    }

    private static Proxy a(Context context) {
        String strC = c(context);
        if (strC != null && !strC.contains("wap")) {
            return null;
        }
        try {
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return null;
            }
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static NetworkInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String c(Context context) {
        try {
            NetworkInfo networkInfoB = b(context);
            return (networkInfoB == null || !networkInfoB.isAvailable()) ? com.baidu.mobads.sdk.internal.a.f3089a : networkInfoB.getType() == 1 ? OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR : networkInfoB.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return com.baidu.mobads.sdk.internal.a.f3089a;
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, bArr.length);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
