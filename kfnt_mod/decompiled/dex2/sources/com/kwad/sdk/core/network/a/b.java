package com.kwad.sdk.core.network.a;

import android.os.SystemClock;
import com.kwad.sdk.core.report.d;
import com.kwad.sdk.internal.api.SceneImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private long a;
    private long b;
    private long c;
    private long d;
    private String e;
    private SceneImpl f;
    private String g = "";

    public void a() {
        this.a = SystemClock.uptimeMillis();
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(String str, SceneImpl sceneImpl) {
        this.e = str;
        this.f = sceneImpl;
    }

    public void b() {
        this.b = SystemClock.uptimeMillis();
    }

    public void c() {
        this.c = SystemClock.uptimeMillis();
    }

    public void d() {
        this.d = SystemClock.uptimeMillis();
    }

    public void e() {
        a aVar = new a();
        aVar.g = this.e;
        long jUptimeMillis = SystemClock.uptimeMillis();
        aVar.a = this.a != 0 ? jUptimeMillis - this.a : 0L;
        aVar.b = (this.b == 0 || this.a == 0) ? 0L : this.b - this.a;
        aVar.c = (this.c == 0 || this.b == 0) ? 0L : this.c - this.b;
        aVar.d = (this.d == 0 || this.c == 0) ? 0L : this.d - this.c;
        aVar.e = this.d != 0 ? jUptimeMillis - this.d : 0L;
        aVar.f = this.g;
        d.a(this.f, aVar);
        com.kwad.sdk.core.d.a.a("NetworkMonitorRecorder", aVar.toString());
    }
}
