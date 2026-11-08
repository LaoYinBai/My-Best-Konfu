package com.kwad.sdk.fullscreen.kwai.kwai;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g implements View.OnClickListener {
    private ImageView b;
    private TextView c;
    private AdTemplate d;
    private com.kwad.sdk.core.download.a.b e;
    private String f;
    private long g;
    private com.kwad.sdk.contentalliance.detail.video.d h = new e() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (j2 >= b.this.g) {
                b.this.e();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.c.getVisibility() == 0) {
            return;
        }
        String strE = com.kwad.sdk.core.response.a.c.e(this.d);
        if (TextUtils.isEmpty(strE)) {
            return;
        }
        this.c.setText(strE);
        this.c.setVisibility(0);
        this.c.setOnClickListener(this);
        i();
    }

    private void i() {
        com.kwad.sdk.core.report.a.c(this.d, 18, this.a.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        com.kwad.sdk.core.report.a.a(this.d, 40, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = this.a.g;
        this.g = com.kwad.sdk.core.response.a.c.d(this.d);
        this.f = com.kwad.sdk.core.response.a.c.e(this.d);
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        this.e = this.a.l;
        this.a.k.a(this.h);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        this.a.k.b(this.h);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.c = (TextView) a(R.id.ksad_detail_call_btn);
        this.b = (ImageView) a(R.id.ksad_skip_icon);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.d).a(this.e).a(false).a(2).a(new a.b() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.b.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    b.this.r();
                }
            }));
        }
    }
}
