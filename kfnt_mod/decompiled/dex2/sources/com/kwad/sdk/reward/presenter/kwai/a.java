package com.kwad.sdk.reward.presenter.kwai;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.kwad.sdk.contentalliance.detail.kwai.kwai.a;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.report.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.g;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private AdTemplate b;
    private AdInfo c;
    private List<Integer> d;
    private final com.kwad.sdk.contentalliance.detail.kwai.kwai.a e = new com.kwad.sdk.contentalliance.detail.kwai.kwai.a();
    private long f = 0;
    private Handler g = new Handler(Looper.getMainLooper());
    private boolean h = true;
    private volatile boolean i = false;
    private Runnable j = new Runnable() { // from class: com.kwad.sdk.reward.presenter.kwai.a.1
        @Override // java.lang.Runnable
        public void run() {
            if (a.this.e.c()) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - a.this.e.e();
                int iA = a.this.e.d().a();
                a.this.a.a(jElapsedRealtime, a.this.e.d().b(), iA);
            } else if (a.this.h) {
                a.this.a.a(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, 1);
            }
            d.c(a.this.b);
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d k = new e() { // from class: com.kwad.sdk.reward.presenter.kwai.a.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            a.this.a(j2);
            a.this.f = j2;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            a.this.i = false;
            com.kwad.sdk.core.report.a.a(a.this.b, a.this.a.e);
            com.kwad.sdk.core.report.a.g(a.this.b, a.this.a.e);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            a.this.e.b();
            a.this.h = false;
            if (a.this.i) {
                return;
            }
            a.this.i = true;
            d.a(a.this.b, System.currentTimeMillis(), 1);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void e() {
            super.e();
            a.this.e.b();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            if (!a.this.a.r || !a.this.a.u) {
                com.kwad.sdk.core.report.a.h(a.this.b, a.this.a.e);
            }
            a.this.e.b();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void g() {
            super.g();
            a.this.e.a();
            a.this.g.removeCallbacks(a.this.j);
            a.this.g.postDelayed(a.this.j, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void h() {
            super.h();
            a.this.e.a();
            a.this.g.removeCallbacks(a.this.j);
            a.this.g.postDelayed(a.this.j, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.d == null || this.d.isEmpty()) {
            return;
        }
        for (Integer num : this.d) {
            if (iCeil >= num.intValue()) {
                com.kwad.sdk.core.report.a.b(this.b, iCeil, this.a.e);
                this.d.remove(num);
                return;
            }
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.g;
        this.c = com.kwad.sdk.core.response.a.d.j(this.b);
        this.d = com.kwad.sdk.core.response.a.a.S(this.c);
        this.a.k.a(this.k);
        this.g.postDelayed(this.j, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.g.removeCallbacksAndMessages(null);
        this.a.k.b(this.k);
        a.C0030a c0030aD = this.e.d();
        d.a(this.a.g, this.f, c0030aD.b(), c0030aD.a());
    }
}
