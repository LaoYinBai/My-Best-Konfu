package com.kwad.sdk.fullscreen.kwai;

import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private ViewGroup b;
    private ViewGroup c;
    private ViewGroup d;
    private f e = new f() { // from class: com.kwad.sdk.fullscreen.kwai.a.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            a.this.e();
        }
    };
    private d f = new e() { // from class: com.kwad.sdk.fullscreen.kwai.a.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            super.f();
            a.this.a.b();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.a.t) {
            return;
        }
        this.b.setVisibility(8);
        this.c.setVisibility(0);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        ViewGroup viewGroup;
        super.a();
        if (this.a.t) {
            this.d.setVisibility(0);
            this.b.setVisibility(8);
            viewGroup = this.c;
        } else {
            this.b.setVisibility(0);
            viewGroup = this.d;
        }
        viewGroup.setVisibility(8);
        this.a.a(this.e);
        this.a.k.a(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.e);
        this.a.k.b(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_play_detail_top_toolbar);
        this.c = (ViewGroup) a(R.id.ksad_play_end_top_toolbar);
        this.d = (ViewGroup) a(R.id.ksad_js_top);
    }
}
