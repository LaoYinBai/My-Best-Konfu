package com.kwad.sdk.f.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.utils.aw;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.f.kwai.a implements View.OnClickListener {
    private ViewGroup b;
    private ImageView c;
    private TextView d;
    private com.kwad.sdk.contentalliance.detail.video.d e = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.a.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            a.this.e();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            a.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.b.getVisibility() != 0) {
            return;
        }
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        if (!com.ksad.download.c.b.a(this.b.getContext()) || com.ksad.download.c.b.b(this.b.getContext())) {
            this.b.setVisibility(8);
            this.a.d.mVideoPlayerStatus.mVideoPlayerBehavior = 1;
        } else {
            this.d.setText(aw.a(com.kwad.sdk.core.response.a.a.b(com.kwad.sdk.core.response.a.d.j(this.a.d)) * 1000));
            this.c.setOnClickListener(this);
            this.a.d.mVideoPlayerStatus.mVideoPlayerBehavior = 2;
            this.b.setVisibility(0);
        }
        this.a.f.a(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.b(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_data_flow_container);
        this.c = (ImageView) a(R.id.ksad_data_flow_play_btn);
        this.d = (TextView) a(R.id.ksad_data_flow_play_tip);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            this.a.f.f();
        }
    }
}
