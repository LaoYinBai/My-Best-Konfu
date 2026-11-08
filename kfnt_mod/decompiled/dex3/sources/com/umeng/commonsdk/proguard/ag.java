package com.umeng.commonsdk.proguard;

import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ag implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public ag(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public ag(byte b) {
        this(b, false);
    }

    public ag(byte b, String str) {
        this.b = b;
        this.a = true;
        this.c = str;
        this.d = false;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == 12;
    }

    public boolean d() {
        return this.b == 15 || this.b == 13 || this.b == 14;
    }

    public boolean e() {
        return this.d;
    }
}
