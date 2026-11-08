package com.kwad.sdk.reward.widget.tailframe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical;
import com.kwad.sdk.reward.widget.tailframe.h5bar.TailFrameBarH5PortraitVertical;
import com.kwad.sdk.widget.KsLogoView;
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TailFramePortraitVertical extends LinearLayout implements d {
    private ImageView a;
    private TailFrameBarAppPortraitVertical b;
    private TailFrameBarH5PortraitVertical c;
    private a d;
    private AdTemplate e;
    private AdInfo f;
    private JSONObject g;
    private b h;
    private TextProgressBar i;
    private KsLogoView j;
    private com.kwad.sdk.reward.a k;
    private View l;

    public TailFramePortraitVertical(Context context) {
        this(context, null);
    }

    public TailFramePortraitVertical(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TailFramePortraitVertical(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void a(View view, final boolean z) {
        if (com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.k.a(getContext(), z ? 1 : 153, 1);
        } else {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.e).a(this.h).a(view == this.i).a(view == this.i ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFramePortraitVertical.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (TailFramePortraitVertical.this.d != null) {
                        TailFramePortraitVertical.this.d.a(z);
                    }
                }
            }));
        }
    }

    private void b() {
        this.l = inflate(getContext(), R.layout.ksad_video_tf_view_portrait_vertical, this);
        this.a = (ImageView) findViewById(R.id.ksad_video_thumb_img);
        this.j = (KsLogoView) findViewById(R.id.ksad_video_tf_logo);
    }

    private void c() {
        if (!com.kwad.sdk.core.response.a.a.B(this.f) && !com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.c = (TailFrameBarH5PortraitVertical) findViewById(R.id.ksad_video_h5_tail_frame);
            this.c.a(this.e);
            this.c.setVisibility(0);
            return;
        }
        this.b = (TailFrameBarAppPortraitVertical) findViewById(R.id.ksad_video_app_tail_frame);
        this.b.a(this.e);
        this.b.setVisibility(0);
        this.i = this.b.getTextProgressBar();
        if (!com.kwad.sdk.core.response.a.d.v(this.e)) {
            d();
        }
        new f(this.i, this);
    }

    private void d() {
        this.h = new b(this.e, this.g, new c() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFramePortraitVertical.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.b(i), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFramePortraitVertical.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.a(TailFramePortraitVertical.this.e), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFramePortraitVertical.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.m(TailFramePortraitVertical.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                TailFramePortraitVertical.this.b.a(TailFramePortraitVertical.this.f);
                TailFramePortraitVertical.this.i.a(com.kwad.sdk.core.response.a.a.a(i), i);
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
        if (com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.l.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ksad_translucent));
            this.a.setVisibility(8);
            this.j.setVisibility(8);
        }
        this.f = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.g = jSONObject;
        this.d = aVar;
        KSImageLoader.loadImage(this.a, com.kwad.sdk.core.response.a.a.h(this.f), this.e);
        this.j.a(adTemplate);
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
