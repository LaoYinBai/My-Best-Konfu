package com.kwad.sdk.reward.widget.actionbar;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ActionBarAppPortrait extends LinearLayout implements d {
    private ImageView a;
    private TextView b;
    private TextView c;
    private AppScoreView d;
    private TextView e;
    private TextProgressBar f;
    private View g;
    private AdTemplate h;
    private AdInfo i;
    private a j;
    private b k;
    private KsAppDownloadListener l;

    public interface a {
        void a(boolean z);
    }

    public ActionBarAppPortrait(Context context) {
        this(context, null);
    }

    public ActionBarAppPortrait(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionBarAppPortrait(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.ksad_video_actionbar_app_portrait, this);
        this.a = (ImageView) findViewById(R.id.ksad_app_icon);
        this.b = (TextView) findViewById(R.id.ksad_app_title);
        this.c = (TextView) findViewById(R.id.ksad_app_desc);
        this.d = (AppScoreView) findViewById(R.id.ksad_app_score);
        this.e = (TextView) findViewById(R.id.ksad_app_download_count);
        this.f = (TextProgressBar) findViewById(R.id.ksad_app_download_btn);
        this.f.setTextDimen(com.kwad.sdk.a.kwai.a.a(getContext(), 16.0f));
        this.f.setTextColor(-1);
        this.g = findViewById(R.id.ksad_download_bar_cover);
    }

    private void a(View view, final boolean z) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.h).a(this.k).a(view == this.f).a(view == this.g ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarAppPortrait.2
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                if (ActionBarAppPortrait.this.j != null) {
                    ActionBarAppPortrait.this.j.a(z);
                }
            }
        }));
    }

    private void b() {
        String strW = com.kwad.sdk.core.response.a.a.w(this.i);
        boolean z = !TextUtils.isEmpty(strW);
        float fX = com.kwad.sdk.core.response.a.a.x(this.i);
        boolean z2 = fX >= 3.0f;
        if (z && z2) {
            ((LinearLayout.LayoutParams) this.b.getLayoutParams()).bottomMargin = com.kwad.sdk.a.kwai.a.a(getContext(), 1.0f);
            ((LinearLayout.LayoutParams) this.d.getLayoutParams()).bottomMargin = com.kwad.sdk.a.kwai.a.a(getContext(), 1.0f);
            this.e.setText(strW);
            this.e.setVisibility(0);
            this.d.setVisibility(0);
            this.d.setScore(fX);
        } else if (z) {
            this.e.setText(strW);
            this.e.setVisibility(0);
            this.d.setVisibility(8);
        } else {
            if (!z2) {
                this.c.setText(com.kwad.sdk.core.response.a.a.s(this.i));
                this.e.setVisibility(8);
                this.d.setVisibility(8);
                this.c.setVisibility(0);
                return;
            }
            this.e.setVisibility(8);
            this.d.setScore(fX);
            this.d.setVisibility(0);
        }
        this.c.setVisibility(8);
    }

    private KsAppDownloadListener getAppDownloadListener() {
        if (this.l == null) {
            this.l = new c() { // from class: com.kwad.sdk.reward.widget.actionbar.ActionBarAppPortrait.1
                @Override // com.kwad.sdk.core.download.a.c
                public void a(int i) {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.b(i), i);
                    ActionBarAppPortrait.this.g.setVisibility(8);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.A(ActionBarAppPortrait.this.i), 0);
                    ActionBarAppPortrait.this.g.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.a(ActionBarAppPortrait.this.h), 0);
                    ActionBarAppPortrait.this.g.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onIdle() {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.A(ActionBarAppPortrait.this.i), 0);
                    ActionBarAppPortrait.this.g.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.m(ActionBarAppPortrait.this.i), 0);
                    ActionBarAppPortrait.this.g.setVisibility(0);
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onProgressUpdate(int i) {
                    ActionBarAppPortrait.this.f.a(com.kwad.sdk.core.response.a.a.a(i), i);
                    ActionBarAppPortrait.this.g.setVisibility(8);
                }
            };
        }
        return this.l;
    }

    public void a(@NonNull AdTemplate adTemplate, @Nullable b bVar, a aVar) {
        this.h = adTemplate;
        this.i = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.j = aVar;
        this.k = bVar;
        KSImageLoader.loadAppIcon(this.a, com.kwad.sdk.core.response.a.a.au(this.i), adTemplate, 12);
        this.b.setText(com.kwad.sdk.core.response.a.a.at(this.i));
        b();
        this.f.a(com.kwad.sdk.core.response.a.a.A(this.i), 0);
        if (this.k != null) {
            this.k.a(getAppDownloadListener());
        }
        setClickable(true);
        new f(this, this);
        new f(this.g, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.h)) {
            a(view, false);
        }
    }

    public b getApkDownloadHelper() {
        return this.k;
    }
}
