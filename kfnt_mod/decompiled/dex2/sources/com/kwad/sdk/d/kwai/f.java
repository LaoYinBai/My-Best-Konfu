package com.kwad.sdk.d.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.utils.ax;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends b implements a.b {
    private static long a = 1000;
    private c b;
    private AdTemplate c;

    @Nullable
    private a d;
    private int e;

    private class a implements Runnable {
        private int b;
        private boolean c;
        private boolean d;

        private a() {
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
        }

        public void a(boolean z) {
            this.d = z;
        }

        public void b(boolean z) {
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.d) {
                return;
            }
            if (!this.c) {
                if (this.b == Integer.MIN_VALUE) {
                    this.b = f.this.e;
                }
                if (this.b < 0) {
                    return;
                }
                com.kwad.sdk.core.d.a.a("InterstitialPlayablePresenter", f.this.toString() + ", this: " + toString() + " PlayableTimerRunnable run : " + this.b);
                f.this.b(this.b);
                this.b = this.b + (-1);
            }
            ax.a(this, null, f.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.b.e == null) {
            return;
        }
        if (i != 0) {
            this.b.e.a(true, i);
        } else {
            if (this.b.d()) {
                return;
            }
            this.b.a(q(), this.c);
            r();
            this.b.a(true, this.b.i);
        }
    }

    private void r() {
        if (this.b.i != null) {
            this.b.i.k();
        }
        this.b.c.dismiss();
        if (this.b.b != null) {
            this.b.b.onAdClosed();
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        com.kwad.sdk.core.d.a.a("InterstitialPlayablePresenter", this + " onBind");
        this.b = (c) p();
        this.c = this.b.a;
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.c);
        long j = adInfoJ.adInsertScreenInfo.autoCloseTime;
        this.e = j > 0 ? (int) Math.min(com.kwad.sdk.core.config.b.a(adInfoJ), j) : com.kwad.sdk.core.config.b.a(adInfoJ);
        if (this.b.e != null) {
            this.b.e.a(true, -1);
        }
        if (!com.kwad.sdk.core.response.a.a.P(adInfoJ)) {
            this.d = new a();
            ax.a(this.d, null, 1000L);
        } else {
            this.e = (int) Math.min(this.e, com.kwad.sdk.core.response.a.a.b(adInfoJ));
            this.d = null;
            this.b.a(this);
        }
    }

    @Override // com.kwad.sdk.core.video.videoview.a.b
    public void a(long j) {
        b(this.e - ((int) (j / 1000)));
    }

    @Override // com.kwad.sdk.core.video.videoview.a.b
    public void b_() {
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        com.kwad.sdk.core.d.a.a("InterstitialPlayablePresenter", this + " onUnbind");
        this.b.b(this);
        if (this.d != null) {
            this.d.a(true);
            ax.b(this.d);
            this.d = null;
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
    }

    @Override // com.kwad.sdk.core.video.videoview.a.b
    public void e() {
    }

    @Override // com.kwad.sdk.core.video.videoview.a.b
    public void f() {
        this.b.a(q(), this.c);
        r();
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void g() {
        super.g();
        if (this.d != null) {
            this.d.b(false);
        }
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void h() {
        super.h();
        if (this.d != null) {
            this.d.b(true);
        }
    }
}
