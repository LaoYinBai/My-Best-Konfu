package com.sigmob.volley.toolbox;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class i {
    private final int a;
    private final List<com.sigmob.volley.e> b;
    private final int c;
    private final InputStream d;

    public i(int i, List<com.sigmob.volley.e> list) {
        this(i, list, -1, null);
    }

    public i(int i, List<com.sigmob.volley.e> list, int i2, InputStream inputStream) {
        this.a = i;
        this.b = list;
        this.c = i2;
        this.d = inputStream;
    }

    public final int a() {
        return this.a;
    }

    public final List<com.sigmob.volley.e> b() {
        return Collections.unmodifiableList(this.b);
    }

    public final int c() {
        return this.c;
    }

    public final InputStream d() {
        return this.d;
    }
}
