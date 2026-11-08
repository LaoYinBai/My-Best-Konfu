package com.kwad.sdk.c.kwai;

import android.net.Uri;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private String a;
    private String b;
    private List<String> c;
    private String d;
    private String e;

    public f(String str) {
        Uri uri = Uri.parse(str);
        this.a = uri.getHost();
        this.b = uri.getScheme();
        this.c = uri.getPathSegments();
        this.e = uri.getQueryParameter("sceneId");
        this.d = uri.getPath();
    }

    public String a() {
        return this.a + this.d;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        if (this.c == null || this.c.size() <= 0) {
            return null;
        }
        return this.c.get(this.c.size() - 1);
    }
}
