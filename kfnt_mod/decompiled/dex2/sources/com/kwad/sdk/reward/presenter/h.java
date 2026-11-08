package com.kwad.sdk.reward.presenter;

import android.view.View;
import com.kwad.sdk.R;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.widget.KsToastView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends com.kwad.sdk.reward.g {
    private KsToastView b;
    private float e;
    private Runnable f;
    private boolean c = false;
    private boolean d = false;
    private boolean g = false;
    private com.kwad.sdk.reward.a.f h = new com.kwad.sdk.reward.a.f() { // from class: com.kwad.sdk.reward.presenter.h.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            h.this.b.setVisibility(8);
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d i = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.h.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            super.a(j, j2);
            if (j < 10000 || !h.this.c || j2 < j * h.this.e) {
                return;
            }
            h.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.b.setVisibility(0);
        this.b.a(3);
        i();
        ax.a(this.f, null, 3000L);
    }

    private void i() {
        this.f = new Runnable() { // from class: com.kwad.sdk.reward.presenter.h.4
            @Override // java.lang.Runnable
            public void run() {
                if (h.this.g) {
                    return;
                }
                if (h.this.b != null) {
                    h.this.b.setVisibility(8);
                }
                com.kwad.sdk.reward.c.a().b();
            }
        };
    }

    private void r() {
        ax.b(this.f);
        this.f = null;
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a.a(this.h);
        this.a.k.a(this.i);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        r();
        this.a.b(this.h);
        this.a.k.b(this.i);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (KsToastView) a(R.id.ksad_toast_view);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.presenter.h.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                h.this.g = true;
                h.this.b.setVisibility(8);
                com.kwad.sdk.reward.c.a().b();
            }
        });
        this.e = com.kwad.sdk.core.config.b.aw();
        this.c = (this.e == 0.0f || this.e == 1.0f) ? false : true;
    }
}
