package com.kwad.sdk.reward.presenter.platdetail.kwai;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g implements View.OnClickListener {
    private TextView b;
    private AdTemplate c;
    private com.kwad.sdk.core.download.a.b d;
    private long e;
    private com.kwad.sdk.contentalliance.detail.video.d f = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (j2 >= b.this.e) {
                b.this.e();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.b.getVisibility() == 0) {
            return;
        }
        String strE = com.kwad.sdk.core.response.a.c.e(this.c);
        if (TextUtils.isEmpty(strE)) {
            return;
        }
        this.b.setText(strE);
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
        i();
    }

    private void i() {
        com.kwad.sdk.core.report.a.c(this.c, 18, this.a.e);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = this.a.g;
        this.d = this.a.l;
        this.e = com.kwad.sdk.core.response.a.c.d(this.c);
        this.a.k.a(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.k.b(this.f);
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (TextView) a(R.id.ksad_detail_call_btn);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            this.a.a(view.getContext(), 40, 2);
        }
    }
}
