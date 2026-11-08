package com.kwad.sdk.draw.view.playend;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.api.KsDrawAd;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.a.a;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.draw.view.DrawDownloadProgressBar;
import com.kwad.sdk.reward.widget.AppScoreView;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DrawVideoTailFrame extends FrameLayout implements View.OnClickListener {
    private AdBaseFrameLayout a;
    private AdTemplate b;
    private AdInfo c;

    @Nullable
    private b d;

    @Nullable
    private KsAppDownloadListener e;
    private ImageView f;
    private ViewGroup g;
    private ImageView h;
    private TextView i;
    private AppScoreView j;
    private TextView k;
    private TextView l;
    private DrawDownloadProgressBar m;
    private ViewGroup n;
    private TextView o;
    private TextView p;
    private KsDrawAd.AdInteractionListener q;
    private KsLogoView r;

    public DrawVideoTailFrame(@NonNull Context context) {
        super(context);
        a(context);
    }

    public DrawVideoTailFrame(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DrawVideoTailFrame(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_draw_video_tailframe, this);
        this.f = (ImageView) findViewById(R.id.ksad_video_cover);
        this.g = (ViewGroup) findViewById(R.id.ksad_app_container);
        this.h = (ImageView) findViewById(R.id.ksad_app_icon);
        this.i = (TextView) findViewById(R.id.ksad_app_name);
        this.j = (AppScoreView) findViewById(R.id.ksad_app_score);
        this.k = (TextView) findViewById(R.id.ksad_app_download_count);
        this.l = (TextView) findViewById(R.id.ksad_app_ad_desc);
        this.m = (DrawDownloadProgressBar) findViewById(R.id.ksad_app_download_btn);
        this.m.setTextSize(15);
        this.n = (ViewGroup) findViewById(R.id.ksad_h5_container);
        this.o = (TextView) findViewById(R.id.ksad_h5_ad_desc);
        this.p = (TextView) findViewById(R.id.ksad_h5_open_btn);
        this.r = (KsLogoView) findViewById(R.id.ksad_draw_tailframe_logo);
    }

    private KsAppDownloadListener getAppDownloadListener() {
        return new c() { // from class: com.kwad.sdk.draw.view.playend.DrawVideoTailFrame.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                super.a(i);
                DrawVideoTailFrame.this.m.a(a.a(), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                DrawVideoTailFrame.this.m.a(a.A(DrawVideoTailFrame.this.c), DrawVideoTailFrame.this.m.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                DrawVideoTailFrame.this.m.a(a.a(DrawVideoTailFrame.this.b), DrawVideoTailFrame.this.m.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                DrawVideoTailFrame.this.m.a(a.A(DrawVideoTailFrame.this.c), DrawVideoTailFrame.this.m.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                DrawVideoTailFrame.this.m.a(a.m(DrawVideoTailFrame.this.c), DrawVideoTailFrame.this.m.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                DrawVideoTailFrame.this.m.a(i + "%", i);
            }
        };
    }

    public void a() {
        if (this.d != null) {
            if (this.e != null) {
                this.d.c(this.e);
            } else {
                this.e = getAppDownloadListener();
                this.d.a(this.e);
            }
        }
    }

    public void a(AdTemplate adTemplate) {
        this.b = adTemplate;
        this.c = d.j(adTemplate);
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = a.J(this.c);
        String str = materialFeatureJ.coverUrl;
        this.r.a(adTemplate);
        if (!TextUtils.isEmpty(str)) {
            int i = materialFeatureJ.width;
            int i2 = materialFeatureJ.height;
            if (i > 0 && i > i2) {
                int iC = com.kwad.sdk.a.kwai.a.c(getContext());
                if (getWidth() != 0) {
                    iC = getWidth();
                }
                int i3 = (int) (iC * (i2 / i));
                ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
                layoutParams.width = iC;
                layoutParams.height = i3;
            }
            KSImageLoader.loadImage(this.f, str, this.b);
        }
        if (a.B(this.c)) {
            KSImageLoader.loadAppIcon(this.h, a.au(this.c), this.b, 11);
            this.i.setText(a.t(this.c));
            float fX = a.x(this.c);
            if (fX >= 3.0f) {
                this.j.setScore(fX);
                this.j.setVisibility(0);
            }
            this.k.setText(a.w(this.c));
            this.l.setText(a.s(this.c));
            this.g.setVisibility(0);
            this.n.setVisibility(8);
        } else {
            this.o.setText(a.s(this.c));
            this.p.setText(a.A(this.c));
            this.g.setVisibility(8);
            this.n.setVisibility(0);
        }
        this.m.setOnClickListener(this);
        setOnClickListener(this);
    }

    public void b() {
        if (this.d == null || this.e == null) {
            return;
        }
        this.d.b(this.e);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(getContext()).a(this.b).a(this.d).a(view == this.m ? 1 : 2).a(view == this.m).a(new a.b() { // from class: com.kwad.sdk.draw.view.playend.DrawVideoTailFrame.2
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                if (DrawVideoTailFrame.this.q != null) {
                    DrawVideoTailFrame.this.q.onAdClicked();
                }
                com.kwad.sdk.core.report.a.a(DrawVideoTailFrame.this.b, 2, DrawVideoTailFrame.this.a.getTouchCoords());
            }
        }));
    }

    public void setAdBaseFrameLayout(AdBaseFrameLayout adBaseFrameLayout) {
        this.a = adBaseFrameLayout;
    }

    public void setAdInteractionListener(KsDrawAd.AdInteractionListener adInteractionListener) {
        this.q = adInteractionListener;
    }

    public void setApkDownloadHelper(@Nullable b bVar) {
        this.d = bVar;
    }
}
