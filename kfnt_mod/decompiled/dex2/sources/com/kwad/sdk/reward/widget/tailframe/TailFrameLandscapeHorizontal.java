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
public class TailFrameLandscapeHorizontal extends LinearLayout implements d {
    private ImageView a;
    private TailFrameBarAppLandscape b;
    private TailFrameBarH5Landscape c;
    private a d;
    private AdTemplate e;
    private AdInfo f;
    private JSONObject g;
    private b h;
    private TextProgressBar i;
    private KsLogoView j;
    private com.kwad.sdk.reward.a k;

    public TailFrameLandscapeHorizontal(Context context) {
        this(context, null);
    }

    public TailFrameLandscapeHorizontal(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TailFrameLandscapeHorizontal(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void a(View view, final boolean z) {
        int i = z ? 1 : 153;
        if (com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.k.a(getContext(), i, 1);
        } else {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.e).a(this.h).a(view == this.i).a(view == this.i ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeHorizontal.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (TailFrameLandscapeHorizontal.this.d != null) {
                        TailFrameLandscapeHorizontal.this.d.a(z);
                    }
                }
            }));
        }
    }

    private void b() {
        inflate(getContext(), R.layout.ksad_video_tf_view_landscape_horizontal, this);
        this.a = (ImageView) findViewById(R.id.ksad_video_thumb_img);
        this.j = (KsLogoView) findViewById(R.id.ksad_video_tf_logo);
    }

    private void c() {
        if (!com.kwad.sdk.core.response.a.a.B(this.f) && !com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.c = (TailFrameBarH5Landscape) findViewById(R.id.ksad_video_h5_tail_frame);
            this.c.setModel(this.e);
            this.c.setVisibility(0);
            return;
        }
        this.b = (TailFrameBarAppLandscape) findViewById(R.id.ksad_video_app_tail_frame);
        this.b.setModel(this.e);
        this.b.setVisibility(0);
        this.i = this.b.getTextProgressBar();
        if (!com.kwad.sdk.core.response.a.d.v(this.e)) {
            d();
        }
        new f(this.i, this);
    }

    private void d() {
        this.h = new b(this.e, this.g, new c() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeHorizontal.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                super.a(i);
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.b(i), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFrameLandscapeHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.a(TailFrameLandscapeHorizontal.this.e), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFrameLandscapeHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.m(TailFrameLandscapeHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                TailFrameLandscapeHorizontal.this.b.a(com.kwad.sdk.core.response.a.d.j(TailFrameLandscapeHorizontal.this.e));
                TailFrameLandscapeHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.a(i), i);
            }
        });
    }

    private void e() {
        setOnClickListener(null);
        this.h = null;
    }

    public void a() {
        if (this.b != null) {
            this.b.b();
            this.b.setVisibility(8);
        }
        if (this.c != null) {
            this.c.a();
            this.c.setVisibility(8);
        }
        e();
    }

    public void a(@NonNull AdTemplate adTemplate, JSONObject jSONObject, a aVar) {
        this.e = adTemplate;
        this.f = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.g = jSONObject;
        this.d = aVar;
        this.j.a(this.e);
        KSImageLoader.loadImage(this.a, com.kwad.sdk.core.response.a.a.h(this.f), this.e);
        c();
        setClickable(true);
        new f(this, this);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.e)) {
            a(view, false);
        }
    }

    public void setCallerContext(com.kwad.sdk.reward.a aVar) {
        this.k = aVar;
    }
}
