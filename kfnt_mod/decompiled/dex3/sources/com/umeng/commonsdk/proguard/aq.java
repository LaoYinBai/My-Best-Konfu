package com.umeng.commonsdk.proguard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class aq {
    public final String a;
    public final byte b;
    public final int c;

    public aq() {
        this("", (byte) 0, 0);
    }

    public aq(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + ((int) this.b) + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof aq) {
            return a((aq) obj);
        }
        return false;
    }

    public boolean a(aq aqVar) {
        return this.a.equals(aqVar.a) && this.b == aqVar.b && this.c == aqVar.c;
    }
}
