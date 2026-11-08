package com.kwad.sdk.h.a;

import android.app.Activity;
import android.os.SystemClock;
import com.kwad.sdk.core.report.d;
import com.kwad.sdk.utils.ax;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private String a;
    private long b;
    private long c;
    private long d;
    private boolean e;

    public void a() {
        b bVar = new b();
        bVar.a = this.a;
        bVar.c = this.b != 0 ? this.c - this.b : 0L;
        bVar.d = this.c != 0 ? this.d - this.c : 0L;
        bVar.b = this.b != 0 ? this.d - this.b : 0L;
        d.a(bVar);
        com.kwad.sdk.core.d.a.a("PageMonitor", bVar.toString());
    }

    public void a(long j) {
        this.b = j;
        this.c = SystemClock.uptimeMillis();
    }

    public void a(Activity activity) {
        if (this.e) {
            return;
        }
        this.e = true;
        ax.a(new Runnable() { // from class: com.kwad.sdk.h.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.d = SystemClock.uptimeMillis();
                a.this.a();
            }
        });
    }

    public void a(String str) {
        this.a = str;
    }
}
