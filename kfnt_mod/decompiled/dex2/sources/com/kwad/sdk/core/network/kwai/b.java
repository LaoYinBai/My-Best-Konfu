package com.kwad.sdk.core.network.kwai;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.network.c;
import com.kwad.sdk.core.network.f;
import com.kwad.sdk.core.network.k;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static String a = "UTF-8";
    public static OkHttpClient b = new OkHttpClient.Builder().connectTimeout(3000, TimeUnit.MILLISECONDS).readTimeout(6000, TimeUnit.MILLISECONDS).connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS)).build();

    public static c a(String str, @Nullable Map<String, String> map) {
        return a(str, map, true);
    }

    public static c a(String str, Map<String, String> map, Map<String, String> map2) {
        c cVar = new c();
        try {
            Request.Builder builderUrl = new Request.Builder().url(str);
            builderUrl.addHeader("User-Agent", k.c());
            builderUrl.addHeader("BrowserUa", k.d());
            builderUrl.addHeader("SystemUa", k.a());
            a(builderUrl, map);
            b(builderUrl, map2);
            Response responseExecute = a().newCall(builderUrl.build()).execute();
            cVar.a = responseExecute.code();
            cVar.b = a(responseExecute);
        } catch (Exception e) {
            a(cVar, e);
        }
        return cVar;
    }

    public static c a(String str, Map<String, String> map, JSONObject jSONObject) {
        c cVar = new c();
        try {
            Request.Builder builderUrl = new Request.Builder().url(str);
            builderUrl.addHeader("User-Agent", k.c());
            builderUrl.addHeader("BrowserUa", k.d());
            builderUrl.addHeader("SystemUa", k.a());
            a(builderUrl, map);
            a(builderUrl, jSONObject);
            Response responseExecute = a().newCall(builderUrl.build()).execute();
            cVar.a = responseExecute.code();
            cVar.b = a(responseExecute);
        } catch (Exception e) {
            a(cVar, e);
        }
        return cVar;
    }

    public static c a(String str, @Nullable Map<String, String> map, boolean z) {
        c cVar = new c();
        try {
            Request.Builder builderUrl = new Request.Builder().url(str);
            builderUrl.addHeader("User-Agent", k.c());
            builderUrl.addHeader("BrowserUa", k.d());
            builderUrl.addHeader("SystemUa", k.a());
            a(builderUrl, map);
            Response responseExecute = a().newCall(builderUrl.build()).execute();
            cVar.a = responseExecute.code();
            cVar.b = z ? a(responseExecute) : "";
        } catch (Exception e) {
            a(cVar, e);
        }
        return cVar;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, C.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            com.kwad.sdk.core.d.a.a(e);
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String a(Response response) throws Throwable {
        InputStream inputStreamByteStream;
        GZIPInputStream gZIPInputStream;
        StringBuilder sb = new StringBuilder();
        GZIPInputStream gZIPInputStream2 = null;
        try {
            inputStreamByteStream = response.body().byteStream();
            try {
                List listHeaders = response.headers("Content-Encoding");
                boolean z = false;
                if (listHeaders != null) {
                    Iterator it = listHeaders.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if ("gzip".equalsIgnoreCase((String) it.next())) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z) {
                    gZIPInputStream = new GZIPInputStream(inputStreamByteStream);
                    gZIPInputStream2 = gZIPInputStream;
                } else {
                    gZIPInputStream = inputStreamByteStream;
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, a), 8);
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                if (gZIPInputStream2 != null) {
                    gZIPInputStream2.close();
                }
                if (inputStreamByteStream != 0) {
                    inputStreamByteStream.close();
                }
                return sb.toString();
            } catch (Throwable th) {
                th = th;
                if (gZIPInputStream2 != null) {
                    gZIPInputStream2.close();
                }
                if (inputStreamByteStream != 0) {
                    inputStreamByteStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamByteStream = 0;
        }
    }

    public static OkHttpClient a() {
        return b;
    }

    private static void a(@NonNull c cVar, Exception exc) {
        f fVar;
        if (exc instanceof SocketTimeoutException) {
            cVar.a = f.a.n;
            fVar = f.a;
        } else {
            cVar.a = f.b.n;
            fVar = f.b;
        }
        cVar.b = fVar.o;
        if (com.kwad.sdk.b.c.booleanValue()) {
            com.kwad.sdk.core.d.a.b(exc);
        }
    }

    private static void a(Request.Builder builder, @Nullable Map<String, String> map) {
        if (builder == null || map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                try {
                    builder.removeHeader(entry.getKey());
                    builder.addHeader(entry.getKey(), entry.getValue());
                } catch (Exception unused) {
                }
            }
        }
    }

    private static void a(Request.Builder builder, JSONObject jSONObject) {
        builder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString()));
    }

    private static void b(Request.Builder builder, Map<String, String> map) {
        FormBody formBodyBuild;
        if (map == null || map.isEmpty()) {
            formBodyBuild = null;
        } else {
            FormBody.Builder builder2 = new FormBody.Builder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry != null) {
                    try {
                        builder2.addEncoded(entry.getKey(), a(entry.getValue()));
                    } catch (Exception unused) {
                    }
                }
            }
            formBodyBuild = builder2.build();
        }
        if (builder == null || formBodyBuild == null) {
            return;
        }
        builder.post(formBodyBuild);
    }
}
