package com.ksad.download;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.kwai.filedownloader.e.c;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h implements com.kwai.filedownloader.kwai.b {
    private final OkHttpClient a;
    private final Request.Builder b;
    private Request c;
    private Response d;

    public static class a implements c.b {
        private OkHttpClient a;
        private OkHttpClient.Builder b;

        public a() {
        }

        public a(boolean z) {
            this.b = z ? h.j() : h.i();
        }

        @Override // com.kwai.filedownloader.e.c.b
        public com.kwai.filedownloader.kwai.b a(String str) {
            if (this.a == null) {
                synchronized (a.class) {
                    if (this.a == null) {
                        this.a = this.b != null ? this.b.build() : new OkHttpClient();
                        this.b = null;
                    }
                }
            }
            return new h(str, this.a);
        }
    }

    private h(String str, OkHttpClient okHttpClient) {
        this(new Request.Builder().url(str), okHttpClient);
    }

    private h(Request.Builder builder, OkHttpClient okHttpClient) {
        this.b = builder;
        this.a = okHttpClient;
    }

    private String b(String str) {
        String str2;
        String strA = a("Content-Type");
        String strB = com.ksad.download.c.a.b(str);
        if (TextUtils.isEmpty(strA) || !TextUtils.isEmpty(strB)) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            return System.currentTimeMillis() + ".apk";
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(strA);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        if (TextUtils.isEmpty(extensionFromMimeType)) {
            str2 = ".apk";
        } else {
            str2 = "." + extensionFromMimeType;
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static OkHttpClient.Builder i() {
        return new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS).addInterceptor(new com.ksad.download.a.a()).readTimeout(0L, TimeUnit.MILLISECONDS).connectionPool(new ConnectionPool(6, 60000L, TimeUnit.MILLISECONDS)).retryOnConnectionFailure(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static OkHttpClient.Builder j() {
        return new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS).addInterceptor(new com.ksad.download.a.a()).protocols(Util.immutableList(new Protocol[]{Protocol.HTTP_1_1})).readTimeout(0L, TimeUnit.MILLISECONDS).connectionPool(new ConnectionPool(6, 60000L, TimeUnit.MILLISECONDS)).retryOnConnectionFailure(true);
    }

    @Override // com.kwai.filedownloader.kwai.b
    public InputStream a() {
        if (this.d == null) {
            throw new IllegalStateException("Please invoke #execute first!");
        }
        return com.kwad.sdk.core.h.b.a().b(this.d.body().byteStream());
    }

    @Override // com.kwai.filedownloader.kwai.b
    public String a(String str) {
        String str2;
        if (!"Content-Disposition".equals(str)) {
            if (this.d == null) {
                return null;
            }
            return this.d.header(str);
        }
        try {
        } catch (Exception unused) {
            str2 = "";
        }
        if (!TextUtils.isEmpty(com.kwai.filedownloader.e.f.g(this.d.header(str)))) {
            return this.d.header(str);
        }
        str2 = (String) this.d.request().url().pathSegments().get(r3.size() - 1);
        return "attachment; filename=\"" + b(str2) + "\"";
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void a(String str, String str2) {
        this.b.addHeader(str, str2);
    }

    @Override // com.kwai.filedownloader.kwai.b
    public boolean a(String str, long j) {
        return false;
    }

    @Override // com.kwai.filedownloader.kwai.b
    public Map<String, List<String>> b() {
        if (this.c == null) {
            this.c = this.b.build();
        }
        return this.c.headers().toMultimap();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public Map<String, List<String>> c() {
        if (this.d == null) {
            return null;
        }
        return this.d.headers().toMultimap();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void d() {
        if (this.c == null) {
            this.c = this.b.build();
        }
        this.d = this.a.newCall(this.c).execute();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public int e() {
        if (this.d != null) {
            return this.d.code();
        }
        throw new IllegalStateException("Please invoke #execute first!");
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void f() {
        this.c = null;
        if (this.d != null && this.d.body() != null) {
            this.d.body().close();
        }
        this.d = null;
    }
}
