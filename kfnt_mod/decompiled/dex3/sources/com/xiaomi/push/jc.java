package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class jc {
    public final byte a;

    /* renamed from: a, reason: collision with other field name */
    public final String f811a;

    /* renamed from: a, reason: collision with other field name */
    public final short f812a;

    public jc() {
        this("", (byte) 0, (short) 0);
    }

    public jc(String str, byte b, short s) {
        this.f811a = str;
        this.a = b;
        this.f812a = s;
    }

    public String toString() {
        return "<TField name:'" + this.f811a + "' type:" + ((int) this.a) + " field-id:" + ((int) this.f812a) + ">";
    }
}
