package com.kwad.sdk.core.diskcache.a;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.diskcache.kwai.a;
import com.kwad.sdk.core.network.k;
import com.kwad.sdk.core.network.m;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static ExecutorService a = com.kwad.sdk.core.i.b.i();

    public static class a {
        public String a;
    }

    static void a(@NonNull final com.kwad.sdk.core.diskcache.kwai.a aVar, @NonNull final String str, @NonNull final String str2) {
        a.execute(new Runnable() { // from class: com.kwad.sdk.core.diskcache.a.c.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                Throwable th;
                OutputStream outputStreamA;
                IOException e;
                OutputStream outputStream = null;
                try {
                    try {
                        a.C0033a c0033aB = aVar.b(str2);
                        if (c0033aB != null) {
                            outputStreamA = c0033aB.a(0);
                            try {
                                if (c.b(str, outputStreamA, new a())) {
                                    c0033aB.a();
                                } else {
                                    c0033aB.b();
                                }
                                aVar.b();
                                outputStream = outputStreamA;
                            } catch (IOException e2) {
                                e = e2;
                                com.kwad.sdk.core.d.a.a(e);
                                com.kwad.sdk.core.d.a.a("FileHelper", "downLoadFileAsync file crash", e);
                                d.a(outputStreamA);
                                return;
                            }
                        }
                        d.a(outputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        d.a((Closeable) null);
                        throw th;
                    }
                } catch (IOException e3) {
                    outputStreamA = null;
                    e = e3;
                } catch (Throwable th3) {
                    th = th3;
                    d.a((Closeable) null);
                    throw th;
                }
            }
        });
    }

    static boolean a(@NonNull com.kwad.sdk.core.diskcache.kwai.a aVar, @NonNull String str, @NonNull String str2, a aVar2) throws Throwable {
        boolean z = false;
        OutputStream outputStream = null;
        try {
            try {
                a.C0033a c0033aB = aVar.b(str2);
                if (c0033aB != null) {
                    OutputStream outputStreamA = c0033aB.a(0);
                    try {
                        if (b(str, outputStreamA, aVar2)) {
                            c0033aB.a();
                            z = true;
                        } else {
                            c0033aB.b();
                        }
                        aVar.b();
                        outputStream = outputStreamA;
                    } catch (IOException e) {
                        e = e;
                        outputStream = outputStreamA;
                        com.kwad.sdk.core.d.a.a(e);
                        com.kwad.sdk.core.d.a.a("FileHelper", "downLoadFileSync file crash", e);
                        aVar2.a = e.getMessage();
                        d.a(outputStream);
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        outputStream = outputStreamA;
                        d.a(outputStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        d.a(outputStream);
        return z;
    }

    public static boolean a(@NonNull File file, @NonNull String str) throws Throwable {
        boolean zB;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            zB = b(str, fileOutputStream, new a());
            d.a(fileOutputStream);
        } catch (IOException e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            com.kwad.sdk.core.d.a.b(e);
            com.kwad.sdk.core.d.a.a("FileHelper", "downLoadFileSync file crash", e);
            d.a(fileOutputStream2);
            zB = false;
            return zB;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            d.a(fileOutputStream2);
            throw th;
        }
        return zB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str, OutputStream outputStream, a aVar) throws Throwable {
        HttpURLConnection httpURLConnection;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                m.a(httpURLConnection);
                httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(120000);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Connection", "keep-alive");
                httpURLConnection.setRequestProperty("Charset", C.UTF8_NAME);
                httpURLConnection.setRequestProperty("User-Agent", k.c());
                httpURLConnection.setRequestProperty("BrowserUa", k.d());
                httpURLConnection.setRequestProperty("SystemUa", k.a());
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream);
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = bufferedInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                    bufferedOutputStream.flush();
                    d.a(bufferedOutputStream);
                    d.a((Closeable) bufferedInputStream);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream2 = bufferedOutputStream;
                    d.a(bufferedOutputStream2);
                    d.a((Closeable) bufferedInputStream);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = null;
            }
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            bufferedInputStream = null;
        }
    }
}
