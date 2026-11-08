package com.kwad.sdk.reward.widget.actionbar;

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
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.widget.AppScoreView;
import com.kwad.sdk.widget.KsLogoView;
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ActionBarPortraitHorizontal extends FrameLayout implements d {
    private ViewGroup a;
    private ViewGroup b;
    private ImageView c;
    private AppScoreView d;
    private TextView e;
    private View f;
    private ViewGroup g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextProgressBar k;
    private View l;
    private AdTemplate m;
    private AdInfo n;
    private a o;
    private b p;
    private KsAppDownloadListener q;
    private KsLogoView r;

    public interface a {
        void a(boolean z);
    }

    public ActionBarPortraitHorizontal(@NonNull Context context) {
        super(context);
        a(context);
    }

    public ActionBarPortraitHorizontal(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ActionBarPortraitHorizontal(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_video_actionbar_portrait_horizontal, this);
        this.a = (ViewGroup) findViewById(R.id.ksad_top_container);
        this.b = (ViewGroup) findViewById(R.id.ksad_top_container_product);
        this.c = (ImageView) findViewById(R.id.ksad_app_icon);
        this.d = (AppScoreView) findViewById(R.id.ksad_app_score);
        this.e = (TextView) findViewById(R.id.ksad_app_download_count);
        this.f = findViewById(R.id.ksad_video_place_holder);
        this.g = (ViewGroup) findViewById(R.id.ksad_bottom_container);
        this.h = (TextView) findViewById(R.id.ksad_app_name);
        this.i = (TextView) findViewById(R.id.ksad_product_name);
        this.j = (TextView) findViewById(R.id.ksad_app_desc);
        this.k = (TextProgressBar) findViewById(R.id.ksad_app_download_btn);
        this.k.setTextDimen(com.kwad.sdk.a.kwai.a.a(getContext(), 16.0f));
        this.k.setTextColor(-1);
        this.l = findViewById(R.id.ksad_app_download_btn_cover);
        new f(this.l, this);
        this.r = (KsLogoView) findViewById(R.id.ksad_actionbar_logo);
    }

    private KsAppDownloadListener getAppDownloadListener() {
        if (this.q == null) {
            this.q = new c() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarPortraitHorizontal.1
                @Override // com.kwad.sdk.core.download.a.c
                public void a(int i) {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.b(i), i);
                    ActionBarPortraitHorizontal.this.l.setVisibility(8);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.A(ActionBarPortraitHorizontal.this.n), 0);
                    ActionBarPortraitHorizontal.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.a(ActionBarPortraitHorizontal.this.m), 0);
                    ActionBarPortraitHorizontal.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onIdle() {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.A(ActionBarPortraitHorizontal.this.n), 0);
                    ActionBarPortraitHorizontal.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.m(ActionBarPortraitHorizontal.this.n), 0);
                    ActionBarPortraitHorizontal.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onProgressUpdate(int i) {
                    ActionBarPortraitHorizontal.this.k.a(com.kwad.sdk.core.response.a.a.a(i), i);
                    ActionBarPortraitHorizontal.this.l.setVisibility(8);
                }
            };
        }
        return this.q;
    }

    public void a(View view, final boolean z) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.m).a(new a.b() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarPortraitHorizontal.2
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                if (ActionBarPortraitHorizontal.this.o != null) {
                    ActionBarPortraitHorizontal.this.o.a(z);
                }
            }
        }).a(this.p).a(view == this.k).a(view == this.l ? 1 : 2));
    }

    public void a(@NonNull AdTemplate adTemplate, @Nullable b bVar, @NonNull a aVar, int i) {
        this.m = adTemplate;
        this.r.a(adTemplate);
        this.n = com.kwad.sdk.core.response.a.d.j(this.m);
        if (com.kwad.sdk.core.response.a.a.B(this.n)) {
            this.b.setVisibility(8);
            this.a.setVisibility(0);
            new f(this.a, this);
            this.h.setText(com.kwad.sdk.core.response.a.a.t(this.n));
        } else {
            this.b.setVisibility(0);
            this.a.setVisibility(8);
            this.i.setText(this.n.adBaseInfo.productName);
            new f(this.b, this);
        }
        new f(this.k, this);
        this.o = aVar;
        this.p = bVar;
        KSImageLoader.loadAppIcon(this.c, com.kwad.sdk.core.response.a.a.au(this.n), adTemplate, 16);
        float fX = com.kwad.sdk.core.response.a.a.x(this.n);
        if (fX >= 3.0f) {
            this.d.setScore(fX);
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        String strW = com.kwad.sdk.core.response.a.a.w(this.n);
        if (!TextUtils.isEmpty(strW)) {
            this.e.setText(strW);
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.height = i;
        this.f.setLayoutParams(layoutParams);
        this.j.setText(com.kwad.sdk.core.response.a.a.s(this.n));
        this.k.a(com.kwad.sdk.core.response.a.a.A(this.n), this.k.getMax());
        this.l.setVisibility(8);
        if (this.p != null) {
            this.p.a(getAppDownloadListener());
        }
        new f(this.g, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.m)) {
            a(view, false);
        }
    }
}
