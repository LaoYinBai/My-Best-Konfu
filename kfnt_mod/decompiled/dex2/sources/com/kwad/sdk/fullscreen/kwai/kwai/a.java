package com.kwad.sdk.fullscreen.kwai.kwai;

import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private TextView b;
    private com.kwad.sdk.contentalliance.detail.video.d c = new e() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.a.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            a.this.b((int) (((j - j2) / 1000.0f) + 0.5f));
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.b.setText(String.valueOf(i));
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b.setText(String.valueOf(com.kwad.sdk.core.response.a.a.b(com.kwad.sdk.core.response.a.d.j(this.a.g))));
        this.b.setVisibility(0);
        this.a.k.a(this.c);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.k.b(this.c);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (TextView) a(R.id.ksad_video_count_down);
    }
}
