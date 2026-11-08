package com.ss.union.game.sdk.core.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlideUrl implements Key {
    private static final String a = "@#&=*+-_.,:!?()/~'%;$";
    private final Headers b;
    private final URL c;
    private final String d;
    private String e;
    private URL f;
    private volatile byte[] g;
    private int h;

    public GlideUrl(URL url) {
        this(url, Headers.DEFAULT);
    }

    public GlideUrl(String str) {
        this(str, Headers.DEFAULT);
    }

    public GlideUrl(URL url, Headers headers) {
        this.c = (URL) Preconditions.checkNotNull(url);
        this.d = null;
        this.b = (Headers) Preconditions.checkNotNull(headers);
    }

    public GlideUrl(String str, Headers headers) {
        this.c = null;
        this.d = Preconditions.checkNotEmpty(str);
        this.b = (Headers) Preconditions.checkNotNull(headers);
    }

    public URL toURL() throws MalformedURLException {
        return a();
    }

    private URL a() throws MalformedURLException {
        if (this.f == null) {
            this.f = new URL(b());
        }
        return this.f;
    }

    public String toStringUrl() {
        return b();
    }

    private String b() {
        if (TextUtils.isEmpty(this.e)) {
            String string = this.d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) Preconditions.checkNotNull(this.c)).toString();
            }
            this.e = Uri.encode(string, a);
        }
        return this.e;
    }

    public Map<String, String> getHeaders() {
        return this.b.getHeaders();
    }

    public String getCacheKey() {
        return this.d != null ? this.d : ((URL) Preconditions.checkNotNull(this.c)).toString();
    }

    public String toString() {
        return getCacheKey();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(c());
    }

    private byte[] c() {
        if (this.g == null) {
            this.g = getCacheKey().getBytes(CHARSET);
        }
        return this.g;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (!(obj instanceof GlideUrl)) {
            return false;
        }
        GlideUrl glideUrl = (GlideUrl) obj;
        return getCacheKey().equals(glideUrl.getCacheKey()) && this.b.equals(glideUrl.b);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        if (this.h == 0) {
            this.h = getCacheKey().hashCode();
            this.h = (this.h * 31) + this.b.hashCode();
        }
        return this.h;
    }
}
