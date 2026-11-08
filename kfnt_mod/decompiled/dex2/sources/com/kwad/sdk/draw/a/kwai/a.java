package com.kwad.sdk.draw.a.kwai;

import android.view.View;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBasePvFrameLayout;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.widget.k;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.draw.kwai.a {
    private AdTemplate b;
    private AdInfo c;
    private List<Integer> d;
    private boolean e = false;
    private volatile boolean f = false;
    private k g = new k() { // from class: com.kwad.sdk.draw.a.kwai.a.1
        @Override // com.kwad.sdk.widget.k
        public void a() {
            m.c(a.this.b);
        }
    };
    private d h = new e() { // from class: com.kwad.sdk.draw.a.kwai.a.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(int i, int i2) {
            super.a(i, i2);
            if (a.this.a.a != null) {
                try {
                    a.this.a.a.onVideoPlayError();
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            a.this.a(j2);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            a.this.f = false;
            if (!a.this.b.mPvReported && a.this.a.a != null) {
                a.this.a.a.onAdShow();
            }
            if (a.this.a.a != null) {
                try {
                    a.this.a.a.onVideoPlayStart();
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
                a.this.e = false;
            }
            com.kwad.sdk.core.report.a.a(a.this.b, (JSONObject) null);
            com.kwad.sdk.core.report.a.i(a.this.b);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            if (!a.this.e) {
                if (a.this.f) {
                    return;
                }
                a.this.f = true;
                com.kwad.sdk.core.report.d.a(a.this.b, System.currentTimeMillis(), 1);
                return;
            }
            a.this.e = false;
            if (a.this.a.a != null) {
                try {
                    a.this.a.a.onVideoPlayResume();
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void e() {
            super.e();
            if (a.this.a.a != null) {
                try {
                    a.this.a.a.onVideoPlayPause();
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
            }
            a.this.e = true;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            com.kwad.sdk.core.report.a.j(a.this.b);
            if (a.this.a.a != null) {
                try {
                    a.this.a.a.onVideoPlayEnd();
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
            }
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
                com.kwad.sdk.core.report.a.b(this.b, iCeil, null);
                this.d.remove(num);
                return;
            }
        }
    }

    private void a(k kVar) {
        View viewM = m();
        if (viewM instanceof AdBasePvFrameLayout) {
            ((AdBasePvFrameLayout) viewM).setVisibleListener(kVar);
        }
    }

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.c;
        this.c = com.kwad.sdk.core.response.a.d.j(this.b);
        this.d = com.kwad.sdk.core.response.a.a.S(this.c);
        this.a.e.a(this.h);
        a(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.e.b(this.h);
        a((k) null);
    }
}
