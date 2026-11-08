package com.huawei.a.a.b.f;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultLoadControl;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {

    private static class a extends Exception {
        a(String str) {
            super(str);
        }
    }

    public static c a(String str, byte[] bArr, Map<String, String> map) {
        return a(str, bArr, map, "POST");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v16, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.io.Closeable, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.Closeable] */
    public static c a(String str, byte[] bArr, Map<String, String> map, String str2) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return new c(-100, "");
        }
        int i = -102;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                str = a((String) str, bArr.length, (Map<String, String>) map, str2);
                try {
                    if (str == 0) {
                        c cVar = new c(-101, "");
                        com.huawei.a.a.b.k.d.a((Closeable) null);
                        com.huawei.a.a.b.k.d.a((Closeable) null);
                        if (str != 0) {
                            com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                        }
                        return cVar;
                    }
                    map = str.getOutputStream();
                    try {
                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(map);
                        try {
                            try {
                                bufferedOutputStream2.write(bArr);
                                bufferedOutputStream2.flush();
                                int responseCode = str.getResponseCode();
                                try {
                                    c cVar2 = new c(responseCode, b(str));
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream2);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar2;
                                } catch (SecurityException unused) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "SecurityException with HttpClient. Please check INTERNET permission.");
                                    c cVar3 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar3;
                                } catch (ConnectException unused2) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "Network is unreachable or Connection refused");
                                    c cVar4 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar4;
                                } catch (UnknownHostException unused3) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "No address associated with hostname or No network");
                                    c cVar5 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar5;
                                } catch (SSLHandshakeException unused4) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "Chain validation failed,Certificate expired");
                                    c cVar6 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar6;
                                } catch (SSLPeerUnverifiedException unused5) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "Certificate has not been verified,Request is restricted!");
                                    c cVar7 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar7;
                                } catch (IOException unused6) {
                                    i = responseCode;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    com.huawei.a.a.b.e.a.c("HttpClient", "events PostRequest(byte[]): IOException occurred.");
                                    c cVar8 = new c(i, "");
                                    com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                    com.huawei.a.a.b.k.d.a((Closeable) map);
                                    if (str != 0) {
                                        com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                    }
                                    return cVar8;
                                }
                            } catch (a unused7) {
                                bufferedOutputStream = bufferedOutputStream2;
                                com.huawei.a.a.b.e.a.c("HttpClient", "PostRequest(byte[]): No ssl socket factory set!");
                                c cVar9 = new c(-101, "");
                                com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                com.huawei.a.a.b.k.d.a((Closeable) map);
                                if (str != 0) {
                                    com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                }
                                return cVar9;
                            } catch (Throwable th) {
                                th = th;
                                bufferedOutputStream = bufferedOutputStream2;
                                com.huawei.a.a.b.k.d.a((Closeable) bufferedOutputStream);
                                com.huawei.a.a.b.k.d.a((Closeable) map);
                                if (str != 0) {
                                    com.huawei.a.a.b.k.d.a((HttpURLConnection) str);
                                }
                                throw th;
                            }
                        } catch (SecurityException unused8) {
                        } catch (ConnectException unused9) {
                        } catch (UnknownHostException unused10) {
                        } catch (SSLHandshakeException unused11) {
                        } catch (SSLPeerUnverifiedException unused12) {
                        } catch (IOException unused13) {
                        }
                    } catch (a unused14) {
                    } catch (ConnectException unused15) {
                    } catch (IOException unused16) {
                    } catch (SecurityException unused17) {
                    } catch (UnknownHostException unused18) {
                    } catch (SSLHandshakeException unused19) {
                    } catch (SSLPeerUnverifiedException unused20) {
                    }
                } catch (a unused21) {
                    map = 0;
                } catch (ConnectException unused22) {
                    map = 0;
                } catch (UnknownHostException unused23) {
                    map = 0;
                } catch (SSLHandshakeException unused24) {
                    map = 0;
                } catch (SSLPeerUnverifiedException unused25) {
                    map = 0;
                } catch (IOException unused26) {
                    map = 0;
                } catch (SecurityException unused27) {
                    map = 0;
                } catch (Throwable th2) {
                    th = th2;
                    map = 0;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (a unused28) {
            str = 0;
            map = 0;
        } catch (SecurityException unused29) {
            str = 0;
            map = 0;
        } catch (ConnectException unused30) {
            str = 0;
            map = 0;
        } catch (UnknownHostException unused31) {
            str = 0;
            map = 0;
        } catch (SSLHandshakeException unused32) {
            str = 0;
            map = 0;
        } catch (SSLPeerUnverifiedException unused33) {
            str = 0;
            map = 0;
        } catch (IOException unused34) {
            str = 0;
            map = 0;
        } catch (Throwable th4) {
            th = th4;
            str = 0;
            map = 0;
        }
    }

    private static HttpURLConnection a(String str, int i, Map<String, String> map, String str2) throws a, ProtocolException {
        if (TextUtils.isEmpty(str)) {
            com.huawei.a.a.b.e.a.d("HttpClient", "CreateConnection: invalid urlPath.");
            return null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        a(httpURLConnection);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
        httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(i));
        httpURLConnection.setRequestProperty("Connection", CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        if (map != null && map.size() >= 1) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null && !TextUtils.isEmpty(key)) {
                    httpURLConnection.setRequestProperty(key, entry.getValue());
                }
            }
        }
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection) throws a {
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SocketFactory socketFactoryA = com.huawei.a.a.b.f.a.a(com.huawei.a.a.a.b.j());
            if (socketFactoryA == null || !(socketFactoryA instanceof SSLSocketFactory)) {
                throw new a("No ssl socket factory set");
            }
            httpsURLConnection.setSSLSocketFactory((SSLSocketFactory) socketFactoryA);
            httpsURLConnection.setHostnameVerifier(new StrictHostnameVerifier());
        }
    }

    private static String b(HttpURLConnection httpURLConnection) throws Throwable {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException unused) {
        }
        try {
            String strA = com.huawei.a.a.b.k.d.a(inputStream);
            com.huawei.a.a.b.k.d.a((Closeable) inputStream);
            return strA;
        } catch (IOException unused2) {
            inputStream2 = inputStream;
            com.huawei.a.a.b.e.a.c("HttpClient", "When Response Content From Connection inputStream operation exception! " + httpURLConnection.getResponseCode());
            com.huawei.a.a.b.k.d.a((Closeable) inputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            com.huawei.a.a.b.k.d.a((Closeable) inputStream2);
            throw th;
        }
    }
}
