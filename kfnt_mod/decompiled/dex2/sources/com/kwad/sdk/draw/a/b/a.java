package com.kwad.sdk.draw.a.b;

import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.draw.view.playend.DrawVideoTailFrame;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.draw.kwai.a {
    private DrawVideoTailFrame b;

    @Nullable
    private com.kwad.sdk.i.b c;
    private d d = new e() { // from class: com.kwad.sdk.draw.a.b.a.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            super.f();
            if (a.this.c == null || !a.this.c.d()) {
                a.this.e();
            } else {
                a.this.b.setVisibility(8);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.b.a();
        this.b.setVisibility(0);
    }

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = this.a.g;
        this.b.a(this.a.c);
        this.b.setAdBaseFrameLayout(this.a.b);
        this.b.setApkDownloadHelper(this.a.d);
        this.b.setVisibility(8);
        this.b.setAdInteractionListener(this.a.a);
        this.a.e.a(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.e.b(this.d);
        this.b.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (DrawVideoTailFrame) a(R.id.ksad_video_tail_frame);
    }
}
