package anet.channel.util;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private InputStream f601a;

    /* renamed from: b, reason: collision with root package name */
    private long f602b = 0;

    public a(InputStream inputStream) {
        this.f601a = null;
        if (inputStream == null) {
            throw new NullPointerException("input stream cannot be null");
        }
        this.f601a = inputStream;
    }

    public long a() {
        return this.f602b;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        this.f602b++;
        return this.f601a.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f601a.read(bArr, i, i2);
        if (i3 != -1) {
            this.f602b += i3;
        }
        return i3;
    }
}
