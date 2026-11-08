package com.kwad.sdk.reward.presenter.a.a;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g implements View.OnClickListener {
    private TextView b;
    private TextView c;

    @NonNull
    private com.kwad.sdk.reward.a.b d;

    @NonNull
    private AdTemplate e;

    @Nullable
    private com.kwad.sdk.core.download.a.b f;
    private f g = new f() { // from class: com.kwad.sdk.reward.presenter.a.a.a.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (a.this.a.x) {
                return;
            }
            a.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        TextView textView;
        String strF = com.kwad.sdk.core.response.a.c.f(this.e);
        if (TextUtils.isEmpty(strF)) {
            return;
        }
        if (this.a.f == 1 || (this.a.r && this.a.s)) {
            this.c.setVisibility(8);
            this.b.setText(strF);
            this.b.setVisibility(0);
            textView = this.b;
        } else {
            this.b.setVisibility(8);
            this.c.setText(strF);
            this.c.setVisibility(0);
            textView = this.c;
        }
        textView.setOnClickListener(this);
        i();
    }

    private void i() {
        com.kwad.sdk.core.report.a.c(this.e, 17, this.a.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        com.kwad.sdk.core.report.a.a(this.e, 39, this.a.j.getTouchCoords(), this.a.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.d.a();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = this.a.b;
        this.e = this.a.g;
        this.f = this.a.l;
        this.a.a(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.g);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (TextView) a(R.id.ksad_end_left_call_btn);
        this.c = (TextView) a(R.id.ksad_end_right_call_btn);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b || view == this.c) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.e).a(this.f).a(false).a(2).a(new a.b() { // from class: com.kwad.sdk.reward.presenter.a.a.a.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    a.this.r();
                    a.this.s();
                }
            }));
        }
    }
}
