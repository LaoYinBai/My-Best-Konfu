package com.kwad.sdk.contentalliance.detail.kwai.kwai;

import android.os.SystemClock;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private volatile boolean a;
    private long b;
    private C0030a c = new C0030a();

    /* renamed from: com.kwad.sdk.contentalliance.detail.kwai.kwai.a$a, reason: collision with other inner class name */
    public static class C0030a {
        private long a = 0;
        private int b = 0;

        public int a() {
            return this.b;
        }

        public void a(long j) {
            this.a += j;
            this.b++;
        }

        public long b() {
            return this.a;
        }
    }

    public void a() {
        if (this.a) {
            return;
        }
        this.a = true;
        this.b = SystemClock.elapsedRealtime();
    }

    public void b() {
        if (this.a) {
            this.c.a(SystemClock.elapsedRealtime() - this.b);
            this.a = false;
        }
    }

    public boolean c() {
        return this.a;
    }

    @NonNull
    public C0030a d() {
        if (this.a) {
            this.c.a(SystemClock.elapsedRealtime() - this.b);
            this.a = false;
        }
        return this.c;
    }

    public long e() {
        return this.b;
    }
}
