package com.kwad.sdk.core.videocache.kwai;

import com.kwad.sdk.core.videocache.ProxyCacheException;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwad.sdk.core.videocache.a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) throws ProxyCacheException {
        File file2;
        try {
            if (aVar == null) {
                throw new NullPointerException();
            }
            this.b = aVar;
            d.a(file.getParentFile());
            boolean zExists = file.exists();
            if (zExists) {
                file2 = file;
            } else {
                file2 = new File(file.getParentFile(), file.getName() + ".download");
            }
            this.a = file2;
            this.c = new RandomAccessFile(this.a, zExists ? CampaignEx.JSON_KEY_AD_R : "rw");
        } catch (IOException e) {
            throw new ProxyCacheException("Error using file " + file + " as disc cache", e);
        }
    }

    private boolean a(File file) {
        return file.getName().endsWith(".download");
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized int a(byte[] bArr, long j, int i) {
        try {
            this.c.seek(j);
        } catch (IOException e) {
            throw new ProxyCacheException(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(a()), Integer.valueOf(bArr.length)), e);
        }
        return this.c.read(bArr, 0, i);
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized long a() {
        try {
        } catch (IOException e) {
            throw new ProxyCacheException("Error reading length of file " + this.a, e);
        }
        return (int) this.c.length();
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized void a(byte[] bArr, int i) {
        try {
            if (d()) {
                throw new ProxyCacheException("Error append cache: cache file " + this.a + " is completed!");
            }
            this.c.seek(a());
            this.c.write(bArr, 0, i);
        } catch (IOException e) {
            throw new ProxyCacheException(String.format("Error writing %d bytes to %s from buffer with size %d", Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)), e);
        }
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized void b() {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (IOException e) {
            throw new ProxyCacheException("Error closing file " + this.a, e);
        }
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized void c() {
        if (d()) {
            return;
        }
        b();
        File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
        if (!this.a.renameTo(file)) {
            throw new ProxyCacheException("Error renaming file " + this.a + " to " + file + " for completion!");
        }
        this.a = file;
        try {
            this.c = new RandomAccessFile(this.a, CampaignEx.JSON_KEY_AD_R);
            this.b.a(this.a);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening " + this.a + " as disc cache", e);
        }
    }

    @Override // com.kwad.sdk.core.videocache.a
    public synchronized boolean d() {
        return !a(this.a);
    }
}
