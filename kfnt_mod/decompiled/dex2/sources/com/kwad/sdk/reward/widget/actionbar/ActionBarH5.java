package com.kwad.sdk.reward.widget.actionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.a.c;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ActionBarH5 extends FrameLayout implements d {
    private TextView a;
    private TextView b;
    private AdTemplate c;
    private a d;

    public interface a {
        void a(boolean z);
    }

    public ActionBarH5(Context context) {
        super(context);
        a(context);
    }

    public ActionBarH5(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ActionBarH5(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_video_actionbar_h5, this);
        this.a = (TextView) findViewById(R.id.ksad_h5_ad_desc);
        this.b = (TextView) findViewById(R.id.ksad_h5_open_btn);
    }

    private void a(View view, final boolean z) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.c).a(new a.b() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarH5.1
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                if (ActionBarH5.this.d != null) {
                    ActionBarH5.this.d.a(z);
                }
            }
        }));
    }

    public void a(@NonNull AdTemplate adTemplate, a aVar) {
        this.c = adTemplate;
        this.d = aVar;
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.c);
        this.a.setText(com.kwad.sdk.core.response.a.a.s(adInfoJ));
        this.b.setText(com.kwad.sdk.core.response.a.a.A(adInfoJ));
        setClickable(true);
        new f(this, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (c.g(this.c)) {
            a(view, false);
        }
    }
}
