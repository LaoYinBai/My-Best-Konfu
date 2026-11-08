package com.kwai.filedownloader.kwai;

import com.kwad.sdk.design.provider.ServiceProvider;
import com.kwai.filedownloader.e.c;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.kwai.filedownloader.kwai.b {
    protected URLConnection a;

    public static class a {
        private Proxy a;
        private Integer b;
        private Integer c;
    }

    public static class b implements c.b {
        private final a a;

        public b() {
            this(null);
        }

        public b(a aVar) {
            this.a = aVar;
        }

        @Override // com.kwai.filedownloader.e.c.b
        public com.kwai.filedownloader.kwai.b a(String str) {
            return new c(str, this.a);
        }
    }

    public c(String str, a aVar) {
        this(new URL(str), aVar);
    }

    public c(URL url, a aVar) {
        this.a = (aVar == null || aVar.a == null) ? url.openConnection() : url.openConnection(aVar.a);
        ((com.kwad.sdk.design.kwai.a) ServiceProvider.a(com.kwad.sdk.design.kwai.a.class)).a(this.a);
        if (aVar != null) {
            if (aVar.b != null) {
                this.a.setReadTimeout(aVar.b.intValue());
            }
            if (aVar.c != null) {
                this.a.setConnectTimeout(aVar.c.intValue());
            }
        }
    }

    @Override // com.kwai.filedownloader.kwai.b
    public InputStream a() throws IOException {
        return ((com.kwad.sdk.design.kwai.c) ServiceProvider.a(com.kwad.sdk.design.kwai.c.class)).a(this.a.getInputStream());
    }

    @Override // com.kwai.filedownloader.kwai.b
    public String a(String str) {
        return this.a.getHeaderField(str);
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void a(String str, String str2) {
        this.a.addRequestProperty(str, str2);
    }

    @Override // com.kwai.filedownloader.kwai.b
    public boolean a(String str, long j) {
        return false;
    }

    @Override // com.kwai.filedownloader.kwai.b
    public Map<String, List<String>> b() {
        return this.a.getRequestProperties();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public Map<String, List<String>> c() {
        return this.a.getHeaderFields();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void d() throws IOException {
        this.a.connect();
    }

    @Override // com.kwai.filedownloader.kwai.b
    public int e() {
        if (this.a instanceof HttpURLConnection) {
            return ((HttpURLConnection) this.a).getResponseCode();
        }
        return 0;
    }

    @Override // com.kwai.filedownloader.kwai.b
    public void f() {
    }
}
