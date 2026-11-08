package com.ss.android.c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements b {
    private final RandomAccessFile a;

    public a(File file) throws FileNotFoundException {
        this.a = new RandomAccessFile(file, "r");
    }

    @Override // com.ss.android.c.b
    public long a() throws IOException {
        return this.a.length();
    }

    @Override // com.ss.android.c.b
    public int a(byte[] bArr, int i, int i2) throws IOException {
        return this.a.read(bArr, i, i2);
    }

    @Override // com.ss.android.c.b
    public void a(long j, long j2) throws IOException {
        this.a.seek(j);
    }

    @Override // com.ss.android.c.b
    public void b() throws IOException {
        this.a.close();
    }
}
