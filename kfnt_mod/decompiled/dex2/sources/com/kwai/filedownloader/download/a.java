package com.kwai.filedownloader.download;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    final long a;
    final long b;
    final long c;
    final long d;

    a(long j, long j2, long j3, long j4) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = j4;
    }

    public String toString() {
        return com.kwai.filedownloader.e.f.a("range[%d, %d) current offset[%d]", Long.valueOf(this.a), Long.valueOf(this.c), Long.valueOf(this.b));
    }
}
