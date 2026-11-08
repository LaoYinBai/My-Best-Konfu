package com.sigmob.volley.toolbox;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class n extends ByteArrayOutputStream {
    private final c a;

    public n(c cVar, int i) {
        this.a = cVar;
        this.buf = this.a.a(Math.max(i, ApkDownloadComplianceInterface.INSTALL_BITS));
    }

    private void a(int i) {
        if (this.count + i <= this.buf.length) {
            return;
        }
        byte[] bArrA = this.a.a((this.count + i) * 2);
        System.arraycopy(this.buf, 0, bArrA, 0, this.count);
        this.a.a(this.buf);
        this.buf = bArrA;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.a.a(this.buf);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int i) {
        a(1);
        super.write(i);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        a(i2);
        super.write(bArr, i, i2);
    }
}
