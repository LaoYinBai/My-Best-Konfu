package com.kwai.filedownloader;

import android.os.SystemClock;
import com.kwai.filedownloader.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements s.a, s.b {
    private long a;
    private long b;
    private long c;
    private long d;
    private int e;
    private int f = 1000;

    @Override // com.kwai.filedownloader.s.b
    public void a() {
        this.e = 0;
        this.a = 0L;
    }

    @Override // com.kwai.filedownloader.s.b
    public void a(long j) {
        this.d = SystemClock.uptimeMillis();
        this.c = j;
    }

    @Override // com.kwai.filedownloader.s.a
    public int b() {
        return this.e;
    }

    @Override // com.kwai.filedownloader.s.b
    public void b(long j) {
        if (this.d <= 0) {
            return;
        }
        long j2 = j - this.c;
        this.a = 0L;
        long jUptimeMillis = SystemClock.uptimeMillis() - this.d;
        if (jUptimeMillis > 0) {
            j2 /= jUptimeMillis;
        }
        this.e = (int) j2;
    }

    @Override // com.kwai.filedownloader.s.b
    public void c(long j) {
        if (this.f <= 0) {
            return;
        }
        boolean z = true;
        if (this.a != 0) {
            long jUptimeMillis = SystemClock.uptimeMillis() - this.a;
            if (jUptimeMillis >= this.f || (this.e == 0 && jUptimeMillis > 0)) {
                this.e = (int) ((j - this.b) / jUptimeMillis);
                this.e = Math.max(0, this.e);
            } else {
                z = false;
            }
        }
        if (z) {
            this.b = j;
            this.a = SystemClock.uptimeMillis();
        }
    }
}
