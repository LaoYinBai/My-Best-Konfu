package com.kwai.filedownloader.d;

import com.kwai.filedownloader.e.c;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwai.filedownloader.d.a {
    private final BufferedOutputStream a;
    private final FileDescriptor b;
    private final RandomAccessFile c;

    public static class a implements c.e {
        @Override // com.kwai.filedownloader.e.c.e
        public com.kwai.filedownloader.d.a a(File file) {
            return new b(file);
        }

        @Override // com.kwai.filedownloader.e.c.e
        public boolean a() {
            return true;
        }
    }

    b(File file) {
        this.c = new RandomAccessFile(file, "rw");
        this.b = this.c.getFD();
        this.a = new BufferedOutputStream(new FileOutputStream(this.c.getFD()));
    }

    @Override // com.kwai.filedownloader.d.a
    public void a() throws IOException {
        this.a.flush();
        this.b.sync();
    }

    @Override // com.kwai.filedownloader.d.a
    public void a(long j) throws IOException {
        this.c.seek(j);
    }

    @Override // com.kwai.filedownloader.d.a
    public void a(byte[] bArr, int i, int i2) throws IOException {
        this.a.write(bArr, i, i2);
    }

    @Override // com.kwai.filedownloader.d.a
    public void b() throws IOException {
        this.a.close();
        this.c.close();
    }

    @Override // com.kwai.filedownloader.d.a
    public void b(long j) throws IOException {
        this.c.setLength(j);
    }
}
