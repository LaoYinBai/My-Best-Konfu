package com.xiaomi.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class jm extends jp {
    protected InputStream a;

    /* renamed from: a, reason: collision with other field name */
    protected OutputStream f816a;

    protected jm() {
        this.a = null;
        this.f816a = null;
    }

    public jm(OutputStream outputStream) {
        this.a = null;
        this.f816a = null;
        this.f816a = outputStream;
    }

    @Override // com.xiaomi.push.jp
    public int a(byte[] bArr, int i, int i2) throws IOException, jq {
        if (this.a == null) {
            throw new jq(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = this.a.read(bArr, i, i2);
            if (i3 >= 0) {
                return i3;
            }
            throw new jq(4);
        } catch (IOException e) {
            throw new jq(0, e);
        }
    }

    @Override // com.xiaomi.push.jp
    /* renamed from: a, reason: collision with other method in class */
    public void mo542a(byte[] bArr, int i, int i2) throws IOException, jq {
        if (this.f816a == null) {
            throw new jq(1, "Cannot write to null outputStream");
        }
        try {
            this.f816a.write(bArr, i, i2);
        } catch (IOException e) {
            throw new jq(0, e);
        }
    }
}
