package com.taobao.accs.utl;

import java.io.ByteArrayOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class r extends ByteArrayOutputStream {
    public r(int i) {
        super(i);
    }

    public r() {
    }

    public r a(byte b) {
        write(b);
        return this;
    }

    public r a(short s) {
        write(s >> 8);
        write(s);
        return this;
    }
}
