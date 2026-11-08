package com.kwad.sdk.draw.view.playcard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DrawCardH5 extends FrameLayout implements View.OnClickListener {
    private Context a;
    private AdTemplate b;
    private a c;
    private int d;
    private ImageView e;
    private TextView f;
    private TextView g;
    private KsLogoView h;
    private ValueAnimator i;

    public interface a {
        void a();

        void b();
    }

    public DrawCardH5(Context context) {
        super(context);
        a(context);
    }

    public DrawCardH5(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DrawCardH5(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(int i, int i2) {
        d();
        this.i = az.a(this, i, i2);
        this.i.setInterpolator(new DecelerateInterpolator(2.0f));
        this.i.setDuration(300L);
        this.i.start();
    }

    private void a(Context context) {
        this.a = context;
        inflate(context, R.layout.ksad_draw_card_h5, this);
        this.e = (ImageView) findViewById(R.id.ksad_card_close);
        this.f = (TextView) findViewById(R.id.ksad_card_ad_desc);
        this.g = (TextView) findViewById(R.id.ksad_card_h5_open_btn);
        this.h = (KsLogoView) findViewById(R.id.ksad_draw_h5_logo);
    }

    private void d() {
        if (this.i != null) {
            this.i.removeAllListeners();
            this.i.cancel();
        }
    }

    public void a() {
        d();
    }

    public void a(@NonNull AdTemplate adTemplate, a aVar) {
        this.b = adTemplate;
        AdInfo adInfoJ = d.j(this.b);
        this.c = aVar;
        this.f.setText(com.kwad.sdk.core.response.a.a.s(adInfoJ));
        this.g.setText(com.kwad.sdk.core.response.a.a.A(adInfoJ));
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.a(adTemplate);
        setOnClickListener(this);
        this.f.measure(View.MeasureSpec.makeMeasureSpec((av.n(this.a) - (com.kwad.sdk.a.kwai.a.a(this.a, 16.0f) * 2)) - (com.kwad.sdk.a.kwai.a.a(this.a, 10.0f) * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.d = com.kwad.sdk.a.kwai.a.a(this.a, 100.0f) + this.f.getMeasuredHeight();
    }

    public void b() {
        a(0, this.d);
    }

    public void c() {
        a(this.d, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.e) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(getContext()).a(this.b).a(new a.b() { // from class: com.kwad.sdk.draw.view.playcard.DrawCardH5.1
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (DrawCardH5.this.c != null) {
                        DrawCardH5.this.c.b();
                    }
                }
            }));
            return;
        }
        c();
        if (this.c != null) {
            this.c.a();
        }
    }
}
