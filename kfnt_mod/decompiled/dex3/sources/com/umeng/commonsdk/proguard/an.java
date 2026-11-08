package com.umeng.commonsdk.proguard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class an {
    public final String a;
    public final byte b;
    public final short c;

    public an() {
        this("", (byte) 0, (short) 0);
    }

    public an(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + ">";
    }

    public boolean a(an anVar) {
        return this.b == anVar.b && this.c == anVar.c;
    }
}
