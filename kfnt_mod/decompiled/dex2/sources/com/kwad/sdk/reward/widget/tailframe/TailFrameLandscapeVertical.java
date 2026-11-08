package com.kwad.sdk.reward.widget.tailframe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppLandscape;
import com.kwad.sdk.reward.widget.tailframe.h5bar.TailFrameBarH5Landscape;
import com.kwad.sdk.widget.KsLogoView;
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TailFrameLandscapeVertical extends LinearLayout implements d {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private TailFrameBarAppLandscape d;
    private TailFrameBarH5Landscape e;
    private a f;
    private AdTemplate g;
    private AdInfo h;
    private JSONObject i;
    private b j;
    private TextProgressBar k;
    private KsLogoView l;
    private com.kwad.sdk.reward.a m;
    private View n;

    public TailFrameLandscapeVertical(Context context) {
        this(context, null);
    }

    public TailFrameLandscapeVertical(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TailFrameLandscapeVertical(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void a(View view, final boolean z) {
        if (com.kwad.sdk.core.response.a.d.v(this.g)) {
            this.m.a(getContext(), z ? 1 : 153, 1);
        } else {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.g).a(this.j).a(view == this.k).a(view == this.k ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeVertical.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (TailFrameLandscapeVertical.this.f != null) {
                        TailFrameLandscapeVertical.this.f.a(z);
                    }
                }
            }));
        }
    }

    private void b() {
        inflate(getContext(), R.layout.ksad_video_tf_view_landscape_vertical, this);
        this.a = (ImageView) findViewById(R.id.ksad_video_thumb_left);
        this.b = (ImageView) findViewById(R.id.ksad_video_thumb_mid);
        this.c = (ImageView) findViewById(R.id.ksad_video_thumb_right);
        this.l = (KsLogoView) findViewById(R.id.ksad_video_tf_logo);
        this.n = findViewById(R.id.ksad_video_thumb_container);
    }

    private void c() {
        KSImageLoader.loadImage(this.a, com.kwad.sdk.core.response.a.a.h(this.h), this.g);
        KSImageLoader.loadImage(this.b, com.kwad.sdk.core.response.a.a.h(this.h), this.g);
        KSImageLoader.loadImage(this.c, com.kwad.sdk.core.response.a.a.h(this.h), this.g);
    }

    private void d() {
        if (!com.kwad.sdk.core.response.a.a.B(this.h) && !com.kwad.sdk.core.response.a.d.v(this.g)) {
            this.e = (TailFrameBarH5Landscape) findViewById(R.id.ksad_video_h5_tail_frame);
            this.e.setModel(this.g);
            this.e.setVisibility(0);
            return;
        }
        this.d = (TailFrameBarAppLandscape) findViewById(R.id.ksad_video_app_tail_frame);
        this.d.setModel(this.g);
        this.d.setVisibility(0);
        this.k = this.d.getTextProgressBar();
        if (!com.kwad.sdk.core.response.a.d.v(this.g)) {
            e();
        }
        new f(this.k, this);
    }

    private void e() {
        this.j = new b(this.g, this.i, new c() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeVertical.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.a(i), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.A(TailFrameLandscapeVertical.this.h), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.a(TailFrameLandscapeVertical.this.g), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.A(TailFrameLandscapeVertical.this.h), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.m(TailFrameLandscapeVertical.this.h), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                TailFrameLandscapeVertical.this.d.a(TailFrameLandscapeVertical.this.h);
                TailFrameLandscapeVertical.this.k.a(com.kwad.sdk.core.response.a.a.a(i), i);
            }
        });
    }

    private void f() {
        setOnClickListener(null);
        this.j = null;
    }

    public void a() {
        if (this.d != null) {
            this.d.b();
            this.d.setVisibility(8);
        }
        if (this.e != null) {
            this.e.a();
            this.e.setVisibility(8);
        }
        f();
    }

    public void a(@NonNull AdTemplate adTemplate, JSONObject jSONObject, a aVar) {
        this.g = adTemplate;
        this.h = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.i = jSONObject;
        this.f = aVar;
        this.l.a(this.g);
        c();
        d();
        setClickable(true);
        new f(this, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.g)) {
            a(view, false);
        }
    }

    public void setCallerContext(com.kwad.sdk.reward.a aVar) {
        this.m = aVar;
    }
}
