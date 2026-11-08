package com.mbridge.msdk.foundation.same.net;

import android.os.Handler;
import com.mbridge.msdk.foundation.tools.s;
import java.util.concurrent.Executor;

/* compiled from: ExecutorDelivery.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements c {
    private final String a = d.class.getSimpleName();
    private final Executor b;

    public d(final Handler handler) {
        this.b = new Executor() { // from class: com.mbridge.msdk.foundation.same.net.d.1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void a(i<?> iVar, k<?> kVar) {
        s.b(this.a, "postResponse response=" + kVar.a);
        if (this.b != null) {
            this.b.execute(new a(iVar, kVar));
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void a(i<?> iVar, com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.b(this.a, "postError error=" + aVar.a);
        if (this.b != null) {
            this.b.execute(new a(iVar, k.a(aVar)));
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void a(final i<?> iVar) {
        s.b(this.a, "postFinish request=" + iVar.b());
        if (this.b != null) {
            this.b.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.d.2
                @Override // java.lang.Runnable
                public final void run() {
                    iVar.l();
                }
            });
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void b(final i<?> iVar) {
        s.b(this.a, "postCancel request=" + iVar.b());
        if (this.b != null) {
            this.b.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.d.3
                @Override // java.lang.Runnable
                public final void run() {
                    iVar.j();
                }
            });
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void c(final i<?> iVar) {
        s.b(this.a, "postPreExecute request=" + iVar.b());
        if (this.b != null) {
            this.b.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.d.4
                @Override // java.lang.Runnable
                public final void run() {
                    iVar.k();
                }
            });
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void d(final i<?> iVar) {
        s.b(this.a, "postNetworking request=" + iVar.b());
        if (this.b != null) {
            this.b.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.d.5
                @Override // java.lang.Runnable
                public final void run() {
                    iVar.m();
                }
            });
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.c
    public final void a(final i<?> iVar, final long j, final long j2) {
        s.b(this.a, "postDownloadProgress downloadedSize=" + j2 + "/ fileSize=" + j);
        if (this.b != null) {
            this.b.execute(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.d.6
                @Override // java.lang.Runnable
                public final void run() {
                    iVar.a(j, j2);
                }
            });
        }
    }

    /* compiled from: ExecutorDelivery.java */
    private class a implements Runnable {
        private final i b;
        private final k c;

        public a(i iVar, k kVar) {
            this.b = iVar;
            this.c = kVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b.c()) {
                this.b.a("canceled-at-delivery");
                this.b.j();
                return;
            }
            if (this.c.b == null) {
                this.b.a(this.c);
            } else {
                this.b.a(this.c.b);
            }
            this.b.a("done");
            this.b.l();
        }
    }
}
