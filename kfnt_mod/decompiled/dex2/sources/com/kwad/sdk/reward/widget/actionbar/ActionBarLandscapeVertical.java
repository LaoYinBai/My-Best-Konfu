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
public class ActionBarLandscapeVertical extends FrameLayout implements d {
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
    private KsLogoView m;
    private AdTemplate n;
    private AdInfo o;
    private a p;
    private b q;
    private KsAppDownloadListener r;

    public interface a {
        void a(boolean z);
    }

    public ActionBarLandscapeVertical(@NonNull Context context) {
        super(context);
        a(context);
    }

    public ActionBarLandscapeVertical(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ActionBarLandscapeVertical(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_video_actionbar_landscape_vertical, this);
        this.a = (ViewGroup) findViewById(R.id.ksad_top_container);
        this.b = (ViewGroup) findViewById(R.id.ksad_top_outer);
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
        this.m = (KsLogoView) findViewById(R.id.ksad_actionbar_logo);
    }

    private KsAppDownloadListener getAppDownloadListener() {
        if (this.r == null) {
            this.r = new c() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarLandscapeVertical.1
                @Override // com.kwad.sdk.core.download.a.c
                public void a(int i) {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.b(i), i);
                    ActionBarLandscapeVertical.this.l.setVisibility(8);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.A(ActionBarLandscapeVertical.this.o), 0);
                    ActionBarLandscapeVertical.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.a(ActionBarLandscapeVertical.this.n), 0);
                    ActionBarLandscapeVertical.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onIdle() {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.A(ActionBarLandscapeVertical.this.o), 0);
                    ActionBarLandscapeVertical.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.m(ActionBarLandscapeVertical.this.o), 0);
                    ActionBarLandscapeVertical.this.l.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onProgressUpdate(int i) {
                    ActionBarLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.a(i), i);
                    ActionBarLandscapeVertical.this.l.setVisibility(8);
                }
            };
        }
        return this.r;
    }

    public void a(View view, final boolean z) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.n).a(this.q).a(view == this.k).a(view == this.l ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarLandscapeVertical.2
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                if (ActionBarLandscapeVertical.this.p != null) {
                    ActionBarLandscapeVertical.this.p.a(z);
                }
            }
        }));
    }

    public void a(@NonNull AdTemplate adTemplate, @Nullable b bVar, @NonNull a aVar, int i) {
        TextView textView;
        String strT;
        this.n = adTemplate;
        this.o = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.m.a(adTemplate);
        if (com.kwad.sdk.core.response.a.a.B(this.o)) {
            this.i.setVisibility(8);
            this.a.setVisibility(0);
            new f(this.a, this);
            textView = this.h;
            strT = com.kwad.sdk.core.response.a.a.t(this.o);
        } else {
            this.i.setVisibility(0);
            this.a.setVisibility(8);
            new f(this.b, this);
            textView = this.i;
            strT = this.o.adBaseInfo.productName;
        }
        textView.setText(strT);
        this.p = aVar;
        this.q = bVar;
        KSImageLoader.loadAppIcon(this.c, com.kwad.sdk.core.response.a.a.au(this.o), adTemplate, 16);
        float fX = com.kwad.sdk.core.response.a.a.x(this.o);
        if (fX >= 3.0f) {
            this.d.setScore(fX);
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        String strW = com.kwad.sdk.core.response.a.a.w(this.o);
        if (!TextUtils.isEmpty(strW)) {
            this.e.setText(strW);
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.width = i;
        this.f.setLayoutParams(layoutParams);
        this.j.setText(com.kwad.sdk.core.response.a.a.s(this.o));
        this.k.a(com.kwad.sdk.core.response.a.a.A(this.o), this.k.getMax());
        this.l.setVisibility(8);
        if (this.q != null) {
            this.q.a(getAppDownloadListener());
        }
        new f(this.g, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.n)) {
            a(view, false);
        }
    }
}
