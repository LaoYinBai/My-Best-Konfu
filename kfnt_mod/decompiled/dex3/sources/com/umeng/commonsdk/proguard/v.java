package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class v extends ByteArrayOutputStream {
    public v(int i) {
        super(i);
    }

    public v() {
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
