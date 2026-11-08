package com.kwad.sdk.core.download;

import android.content.Context;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.network.m;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.export.proxy.AdDownloadProxy;
import com.kwad.sdk.utils.aq;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final String a = aq.a(KsAdSDKImpl.get().getContext()) + "/downloadFileSync/.temp";
    private static ExecutorService b = com.kwad.sdk.core.i.b.i();

    public static class a implements c {
        final OutputStream a;

        public a(File file, boolean z) {
            this.a = new FileOutputStream(file, z);
        }

        @Override // com.kwad.sdk.core.download.d.c
        public void a(int i, Map<String, List<String>> map) {
        }

        @Override // com.kwad.sdk.core.download.d.c
        public void a(long j) {
        }

        @Override // com.kwad.sdk.core.download.d.c
        public void a(byte[] bArr, int i, int i2) throws IOException {
            this.a.write(bArr, i, i2);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.a.close();
        }
    }

    public interface b {
        boolean a(int i, int i2, Object obj);
    }

    public interface c extends Closeable {
        void a(int i, Map<String, List<String>> map);

        void a(long j);

        void a(byte[] bArr, int i, int i2);
    }

    public static URLConnection a(String str, int i, int i2, boolean z, boolean z2) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        try {
            URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
            m.a(uRLConnectionOpenConnection);
            uRLConnectionOpenConnection.setRequestProperty("Accept-Language", "zh-CN");
            if (i > 0) {
                uRLConnectionOpenConnection.setConnectTimeout(i);
            }
            if (i2 > 0) {
                uRLConnectionOpenConnection.setReadTimeout(i2);
            }
            uRLConnectionOpenConnection.setUseCaches(z);
            uRLConnectionOpenConnection.setDoInput(true);
            uRLConnectionOpenConnection.setRequestProperty("Connection", "keep-alive");
            uRLConnectionOpenConnection.setRequestProperty("Charset", C.UTF8_NAME);
            return uRLConnectionOpenConnection;
        } catch (MalformedURLException e) {
            com.kwad.sdk.core.d.a.a(e);
            return null;
        }
    }

    public static void a(Context context, AdInfo adInfo) {
        a(context, adInfo, false);
    }

    public static void a(Context context, AdInfo adInfo, boolean z) {
        AdDownloadProxy proxyForDownload = KsAdSDKImpl.get().getProxyForDownload();
        if (proxyForDownload != null) {
            DownloadParams downloadParamsTransform = DownloadParams.transform(adInfo);
            downloadParamsTransform.requestInstallPermission = z;
            proxyForDownload.startDownload(context, downloadParamsTransform.mDownloadid, downloadParamsTransform);
        }
    }

    public static void a(Context context, String str) {
        AdDownloadProxy proxyForDownload = KsAdSDKImpl.get().getProxyForDownload();
        if (proxyForDownload != null) {
            proxyForDownload.pauseDownload(context, str, null);
        }
    }

    private static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean a(String str, File file, b bVar, int i) {
        a aVar;
        a aVar2 = null;
        try {
            aVar = new a(file, false);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zA = a(str, (String) null, aVar, bVar, i);
            a(aVar);
            return zA;
        } catch (Throwable th2) {
            th = th2;
            aVar2 = aVar;
            a(aVar2);
            throw th;
        }
    }

    public static boolean a(String str, String str2, c cVar, b bVar, int i) {
        return a(str, str2, cVar, bVar, i, -1L, -1L, false);
    }

    public static boolean a(String str, String str2, c cVar, b bVar, int i, long j, long j2, boolean z) throws Throwable {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) a(str, 10000, i > 0 ? i : 120000, false, true);
            try {
                if (httpURLConnection == null) {
                    throw new IOException("Fail to createUrlConnection");
                }
                boolean zA = a(httpURLConnection, str2, cVar, bVar, i, j, j2, z);
                a(cVar);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return zA;
            } catch (Throwable th) {
                th = th;
                a(cVar);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01a5 A[Catch: all -> 0x01cf, TryCatch #10 {all -> 0x01cf, blocks: (B:101:0x01a1, B:103:0x01a5, B:105:0x01ce, B:104:0x01a8), top: B:123:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01a8 A[Catch: all -> 0x01cf, TryCatch #10 {all -> 0x01cf, blocks: (B:101:0x01a1, B:103:0x01a5, B:105:0x01ce, B:104:0x01a8), top: B:123:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x016b A[EDGE_INSN: B:135:0x016b->B:78:0x016b BREAK  A[LOOP:0: B:73:0x0159->B:137:0x0159], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003c A[Catch: Throwable -> 0x0069, all -> 0x0198, TRY_ENTER, TryCatch #9 {Throwable -> 0x0069, blocks: (B:15:0x003c, B:17:0x004e, B:9:0x0026), top: B:121:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a8 A[Catch: all -> 0x018e, Throwable -> 0x0193, TRY_ENTER, TryCatch #8 {all -> 0x018e, blocks: (B:26:0x0092, B:34:0x00b1, B:33:0x00a8), top: B:119:0x0092 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0151 A[Catch: all -> 0x0186, Throwable -> 0x0189, TryCatch #15 {all -> 0x0186, Throwable -> 0x0189, blocks: (B:69:0x0145, B:71:0x0151, B:72:0x0155, B:73:0x0159, B:75:0x015f, B:77:0x0166, B:79:0x016d), top: B:129:0x0145 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015f A[Catch: all -> 0x0186, Throwable -> 0x0189, TryCatch #15 {all -> 0x0186, Throwable -> 0x0189, blocks: (B:69:0x0145, B:71:0x0151, B:72:0x0155, B:73:0x0159, B:75:0x015f, B:77:0x0166, B:79:0x016d), top: B:129:0x0145 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x016d A[Catch: all -> 0x0186, Throwable -> 0x0189, TRY_LEAVE, TryCatch #15 {all -> 0x0186, Throwable -> 0x0189, blocks: (B:69:0x0145, B:71:0x0151, B:72:0x0155, B:73:0x0159, B:75:0x015f, B:77:0x0166, B:79:0x016d), top: B:129:0x0145 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0181  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.net.HttpURLConnection r17, java.lang.String r18, com.kwad.sdk.core.download.d.c r19, com.kwad.sdk.core.download.d.b r20, int r21, long r22, long r24, boolean r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 487
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.download.d.a(java.net.HttpURLConnection, java.lang.String, com.kwad.sdk.core.download.d$c, com.kwad.sdk.core.download.d$b, int, long, long, boolean):boolean");
    }
}
