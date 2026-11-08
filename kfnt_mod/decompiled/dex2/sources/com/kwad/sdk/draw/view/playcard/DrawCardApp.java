package com.kwad.sdk.draw.view.playcard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.draw.view.DrawDownloadProgressBar;
import com.kwad.sdk.reward.widget.AppScoreView;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DrawCardApp extends FrameLayout implements View.OnClickListener {
    private AdTemplate a;
    private AdInfo b;
    private b c;
    private KsAppDownloadListener d;
    private a e;
    private int f;
    private ImageView g;
    private ImageView h;
    private TextView i;
    private ViewGroup j;
    private AppScoreView k;
    private TextView l;
    private TextView m;
    private KsLogoView n;
    private DrawDownloadProgressBar o;
    private ValueAnimator p;

    public interface a {
        void a();

        void b();
    }

    public DrawCardApp(Context context) {
        super(context);
        a(context);
    }

    public DrawCardApp(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DrawCardApp(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(int i, int i2) {
        d();
        this.p = az.a(this, i, i2);
        this.p.setInterpolator(new DecelerateInterpolator(2.0f));
        this.p.setDuration(300L);
        this.p.start();
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_draw_card_app, this);
        this.g = (ImageView) findViewById(R.id.ksad_card_app_close);
        this.h = (ImageView) findViewById(R.id.ksad_card_app_icon);
        this.i = (TextView) findViewById(R.id.ksad_card_app_name);
        this.j = (ViewGroup) findViewById(R.id.ksad_card_app_score_container);
        this.k = (AppScoreView) findViewById(R.id.ksad_card_app_score);
        this.l = (TextView) findViewById(R.id.ksad_card_app_download_count);
        this.m = (TextView) findViewById(R.id.ksad_card_app_desc);
        this.n = (KsLogoView) findViewById(R.id.ksad_card_logo);
        this.o = (DrawDownloadProgressBar) findViewById(R.id.ksad_card_app_download_btn);
        this.o.setTextSize(16);
        this.f = com.kwad.sdk.a.kwai.a.a(context, 156.0f);
    }

    private void d() {
        if (this.p != null) {
            this.p.removeAllListeners();
            this.p.cancel();
        }
    }

    private KsAppDownloadListener getAppDownloadListener() {
        if (this.d == null) {
            this.d = new c() { // from class: com.kwad.sdk.draw.view.playcard.DrawCardApp.1
                @Override // com.kwad.sdk.core.download.a.c
                public void a(int i) {
                    super.a(i);
                    DrawCardApp.this.o.a(com.kwad.sdk.core.response.a.a.a(), i);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    DrawCardApp.this.o.a(com.kwad.sdk.core.response.a.a.A(DrawCardApp.this.b), DrawCardApp.this.o.getMax());
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    DrawCardApp.this.o.a(com.kwad.sdk.core.response.a.a.a(DrawCardApp.this.a), DrawCardApp.this.o.getMax());
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onIdle() {
                    DrawCardApp.this.o.a(com.kwad.sdk.core.response.a.a.A(DrawCardApp.this.b), DrawCardApp.this.o.getMax());
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    DrawCardApp.this.o.a(com.kwad.sdk.core.response.a.a.m(DrawCardApp.this.b), DrawCardApp.this.o.getMax());
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onProgressUpdate(int i) {
                    DrawCardApp.this.o.a(i + "%", i);
                }
            };
        }
        return this.d;
    }

    public void a() {
        d();
        this.c = null;
    }

    public void a(@NonNull AdTemplate adTemplate, a aVar) {
        this.a = adTemplate;
        this.b = d.j(this.a);
        this.e = aVar;
        this.c = new b(this.a, getAppDownloadListener());
        KSImageLoader.loadAppIcon(this.h, com.kwad.sdk.core.response.a.a.au(this.b), adTemplate, 11);
        this.i.setText(com.kwad.sdk.core.response.a.a.t(this.b));
        String strW = com.kwad.sdk.core.response.a.a.w(this.b);
        float fX = com.kwad.sdk.core.response.a.a.x(this.b);
        boolean z = fX >= 3.0f;
        if (z) {
            this.k.setScore(fX);
            this.k.setVisibility(0);
        }
        boolean z2 = !TextUtils.isEmpty(strW);
        if (z2) {
            this.l.setText(strW);
            this.l.setVisibility(0);
        }
        if (z || z2) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(8);
        }
        this.n.a(this.a);
        this.m.setText(com.kwad.sdk.core.response.a.a.s(this.b));
        this.g.setOnClickListener(this);
        this.o.setOnClickListener(this);
        setOnClickListener(this);
    }

    public void b() {
        a(0, this.f);
    }

    public void c() {
        a(this.f, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.g) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(getContext()).a(this.a).a(this.c).a(view == this.o).a(view == this.o ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.draw.view.playcard.DrawCardApp.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (DrawCardApp.this.e != null) {
                        DrawCardApp.this.e.b();
                    }
                }
            }));
            return;
        }
        c();
        if (this.e != null) {
            this.e.a();
        }
    }
}
