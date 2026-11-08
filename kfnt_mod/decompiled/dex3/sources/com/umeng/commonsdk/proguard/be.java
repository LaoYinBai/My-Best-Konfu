package com.umeng.commonsdk.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class be extends bg {
    protected InputStream a;
    protected OutputStream b;

    @Override // com.umeng.commonsdk.proguard.bg
    public boolean a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void b() throws bh {
    }

    protected be() {
        this.a = null;
        this.b = null;
    }

    public be(InputStream inputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
    }

    public be(OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.b = outputStream;
    }

    public be(InputStream inputStream, OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
        this.b = outputStream;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void c() throws IOException {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public int a(byte[] bArr, int i, int i2) throws bh, IOException {
        if (this.a == null) {
            throw new bh(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = this.a.read(bArr, i, i2);
            if (i3 >= 0) {
                return i3;
            }
            throw new bh(4);
        } catch (IOException e) {
            throw new bh(0, e);
        }
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void b(byte[] bArr, int i, int i2) throws bh, IOException {
        if (this.b == null) {
            throw new bh(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (IOException e) {
            throw new bh(0, e);
        }
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void d() throws bh, IOException {
        if (this.b == null) {
            throw new bh(1, "Cannot flush null outputStream");
        }
        try {
            this.b.flush();
        } catch (IOException e) {
            throw new bh(0, e);
        }
    }
}
