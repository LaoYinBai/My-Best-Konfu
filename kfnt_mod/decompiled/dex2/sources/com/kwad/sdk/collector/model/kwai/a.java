package com.kwad.sdk.collector.model.kwai;

import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.collector.model.a {
    private b a;
    private Set<String> b;
    private long c;

    public a(b bVar, Set<String> set, long j) {
        this.a = bVar;
        this.b = set;
        this.c = j;
    }

    public String a() {
        if (this.a == null) {
            return null;
        }
        return com.kwad.sdk.collector.model.c.b(this.a);
    }

    public Set<String> b() {
        return this.b;
    }

    public b c() {
        return this.a;
    }
}
