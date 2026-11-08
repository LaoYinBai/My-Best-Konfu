package com.kwad.sdk.reward.presenter.a.a;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g implements View.OnClickListener {
    private View b;
    private boolean d;
    private long e;
    private AdTemplate f;
    private AdInfo g;
    private Handler c = new Handler(Looper.getMainLooper());
    private volatile long h = 0;
    private volatile boolean i = false;
    private d j = new e() { // from class: com.kwad.sdk.reward.presenter.a.a.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            super.a(j, j2);
            b.this.h = j2;
            b.this.i = j - j2 < 800;
        }
    };
    private f k = new f() { // from class: com.kwad.sdk.reward.presenter.a.a.b.2
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (b.this.a.x) {
                return;
            }
            long j = b.this.e;
            if (j == 0 || !b.this.d) {
                b.this.e();
            } else {
                b.this.c.postDelayed(new Runnable() { // from class: com.kwad.sdk.reward.presenter.a.a.b.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (b.this.o() == null || b.this.o().isFinishing()) {
                            return;
                        }
                        b.this.e();
                        b.this.b.setAlpha(0.0f);
                        b.this.b.animate().alpha(1.0f).setDuration(500L).start();
                    }
                }, j);
            }
        }
    };

    public b(long j) {
        this.e = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
    }

    private void i() {
        j.a(this.a.h, this.f, j.a(this.f, (String) null, this.a.y, this.a.z), new j.c() { // from class: com.kwad.sdk.reward.presenter.a.a.b.3
            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void a() {
                com.kwad.sdk.core.report.a.c(b.this.f, 149, null);
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void b() {
                com.kwad.sdk.core.report.a.a(b.this.a.g, (int) (b.this.e / 1000), (int) (b.this.h / 1000));
                com.kwad.sdk.core.report.a.f(b.this.f, 151);
                b.this.r();
                b.this.h();
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void c() {
                com.kwad.sdk.core.report.a.f(b.this.f, 150);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.a.b.a(false);
    }

    private void s() {
        this.a.b.e();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.h = 0L;
        this.i = false;
        this.f = this.a.g;
        this.g = com.kwad.sdk.core.response.a.d.j(this.f);
        this.a.a(this.k);
        this.a.k.a(this.j);
    }

    public b b(boolean z) {
        this.d = z;
        return this;
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.k);
        this.c.removeCallbacksAndMessages(null);
        this.a.k.b(this.j);
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = a(R.id.ksad_end_close_btn);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        this.c.removeCallbacksAndMessages(null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            boolean z = false;
            boolean z2 = true;
            if (f() || g()) {
                com.kwad.sdk.core.report.a.a(this.f, 69, (int) (this.e / 1000), (int) (this.h / 1000));
                if (!this.a.v && com.kwad.sdk.core.config.b.R() && !this.a.u) {
                    z = true;
                }
                if (z) {
                    i();
                    return;
                }
            } else {
                long jP = com.kwad.sdk.core.response.a.a.p(this.g);
                if (jP >= 0 && !this.i && this.h < jP) {
                    z2 = false;
                }
                if (z2) {
                    s();
                }
            }
            r();
            h();
        }
    }
}
