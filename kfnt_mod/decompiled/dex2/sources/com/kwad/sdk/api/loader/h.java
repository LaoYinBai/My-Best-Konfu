package com.kwad.sdk.api.loader;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.core.TLSConnectionUtils;
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

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static final String a = a(Loader.get().getContext()) + "/apkfileD/dynamic";

    public interface a {
        void a();

        void a(File file);
    }

    public static class b implements d {
        final OutputStream a;

        public b(File file, boolean z) {
            this.a = new FileOutputStream(file, z);
        }

        @Override // com.kwad.sdk.api.loader.h.d
        public void a(int i, Map<String, List<String>> map) {
        }

        @Override // com.kwad.sdk.api.loader.h.d
        public void a(long j) {
        }

        @Override // com.kwad.sdk.api.loader.h.d
        public void a(byte[] bArr, int i, int i2) throws IOException {
            this.a.write(bArr, i, i2);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.a.close();
        }
    }

    public interface c {
        boolean a(int i, int i2, Object obj);
    }

    public interface d extends Closeable {
        void a(int i, Map<String, List<String>> map);

        void a(long j);

        void a(byte[] bArr, int i, int i2);
    }

    @NonNull
    private static String a(Context context) {
        String externalStorageState;
        File externalFilesDir;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError | NullPointerException unused) {
            externalStorageState = "";
        }
        String path = null;
        if ("mounted".equals(externalStorageState) || !Environment.isExternalStorageRemovable()) {
            try {
                externalFilesDir = context.getExternalFilesDir(null);
            } catch (Exception unused2) {
                externalFilesDir = null;
            }
            if (externalFilesDir != null) {
                path = externalFilesDir.getPath();
            }
        }
        if (TextUtils.isEmpty(path)) {
            path = context.getFilesDir().getPath();
        }
        return path + File.separator + "ksadsdk";
    }

    public static URLConnection a(String str, int i, int i2, boolean z, boolean z2) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        try {
            URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
            TLSConnectionUtils.wrapHttpURLConnection(uRLConnectionOpenConnection);
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
        } catch (MalformedURLException unused) {
            return null;
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

    static void a(final String str, String str2, final a aVar) {
        final File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        i.a(new Runnable() { // from class: com.kwad.sdk.api.loader.h.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                try {
                    boolean zA = h.a(str, file, null, 0);
                    if (aVar != null) {
                        if (zA) {
                            aVar.a(file);
                        } else {
                            aVar.a();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            }
        });
    }

    public static boolean a(String str, File file, c cVar, int i) throws Throwable {
        b bVar;
        b bVar2 = null;
        try {
            bVar = new b(file, false);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zA = a(str, (String) null, bVar, cVar, i);
            a(bVar);
            return zA;
        } catch (Throwable th2) {
            th = th2;
            bVar2 = bVar;
            a(bVar2);
            throw th;
        }
    }

    public static boolean a(String str, String str2, d dVar, c cVar, int i) {
        return a(str, str2, dVar, cVar, i, -1L, -1L, false);
    }

    public static boolean a(String str, String str2, d dVar, c cVar, int i, long j, long j2, boolean z) throws Throwable {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) a(str, 10000, i > 0 ? i : 120000, false, true);
            try {
                if (httpURLConnection == null) {
                    throw new IOException("Fail to createUrlConnection");
                }
                boolean zA = a(httpURLConnection, str2, dVar, cVar, i, j, j2, z);
                a(dVar);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return zA;
            } catch (Throwable th) {
                th = th;
                a(dVar);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01a6 A[Catch: all -> 0x01d0, TryCatch #8 {all -> 0x01d0, blocks: (B:109:0x01a2, B:111:0x01a6, B:113:0x01cf, B:112:0x01a9), top: B:127:0x01a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a9 A[Catch: all -> 0x01d0, TryCatch #8 {all -> 0x01d0, blocks: (B:109:0x01a2, B:111:0x01a6, B:113:0x01cf, B:112:0x01a9), top: B:127:0x01a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0169 A[EDGE_INSN: B:146:0x0169->B:82:0x0169 BREAK  A[LOOP:0: B:135:0x0157->B:147:0x0157], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003c A[Catch: Throwable -> 0x0069, all -> 0x019a, TRY_ENTER, TryCatch #7 {Throwable -> 0x0069, blocks: (B:15:0x003c, B:17:0x004e, B:9:0x0026), top: B:125:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0096 A[Catch: all -> 0x0190, Throwable -> 0x0195, TRY_ENTER, TryCatch #12 {all -> 0x0190, blocks: (B:26:0x0080, B:34:0x009f, B:33:0x0096), top: B:131:0x0080 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x013e A[Catch: all -> 0x0189, Throwable -> 0x018c, TryCatch #15 {all -> 0x0189, Throwable -> 0x018c, blocks: (B:69:0x0132, B:71:0x013e, B:72:0x0142, B:74:0x014c), top: B:141:0x0132 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014c A[Catch: all -> 0x0189, Throwable -> 0x018c, TRY_LEAVE, TryCatch #15 {all -> 0x0189, Throwable -> 0x018c, blocks: (B:69:0x0132, B:71:0x013e, B:72:0x0142, B:74:0x014c), top: B:141:0x0132 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x015d A[Catch: all -> 0x0184, Throwable -> 0x0187, TryCatch #18 {all -> 0x0184, Throwable -> 0x0187, blocks: (B:77:0x0157, B:79:0x015d, B:81:0x0164, B:83:0x016b), top: B:135:0x0157 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016b A[Catch: all -> 0x0184, Throwable -> 0x0187, TRY_LEAVE, TryCatch #18 {all -> 0x0184, Throwable -> 0x0187, blocks: (B:77:0x0157, B:79:0x015d, B:81:0x0164, B:83:0x016b), top: B:135:0x0157 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x017f  */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.net.HttpURLConnection r17, java.lang.String r18, com.kwad.sdk.api.loader.h.d r19, com.kwad.sdk.api.loader.h.c r20, int r21, long r22, long r24, boolean r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.api.loader.h.a(java.net.HttpURLConnection, java.lang.String, com.kwad.sdk.api.loader.h$d, com.kwad.sdk.api.loader.h$c, int, long, long, boolean):boolean");
    }
}
