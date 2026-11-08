package com.kwad.sdk.reward.widget.tailframe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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
import com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitHorizontal;
import com.kwad.sdk.reward.widget.tailframe.h5bar.TailFrameBarH5PortraitHorizontal;
import com.kwad.sdk.widget.KsLogoView;
import com.kwad.sdk.widget.d;
import com.kwad.sdk.widget.f;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TailFramePortraitHorizontal extends LinearLayout implements d {
    private ImageView a;
    private TailFrameBarAppPortraitHorizontal b;
    private TailFrameBarH5PortraitHorizontal c;
    private a d;
    private AdTemplate e;
    private AdInfo f;
    private JSONObject g;
    private b h;
    private TextProgressBar i;
    private KsLogoView j;
    private com.kwad.sdk.reward.a k;
    private View l;
    private View m;

    public TailFramePortraitHorizontal(Context context) {
        this(context, null);
    }

    public TailFramePortraitHorizontal(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TailFramePortraitHorizontal(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void a(View view, final boolean z) {
        if (com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.k.a(getContext(), z ? 1 : 153, 1);
        } else {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.e).a(this.h).a(view == this.i).a(view == this.i ? 1 : 2).a(new a.b() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFramePortraitHorizontal.2
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    if (TailFramePortraitHorizontal.this.d != null) {
                        TailFramePortraitHorizontal.this.d.a(z);
                    }
                }
            }));
        }
    }

    private void b() {
        this.l = inflate(getContext(), R.layout.ksad_video_tf_view_portrait_horizontal, this);
        this.a = (ImageView) findViewById(R.id.ksad_video_thumb_img);
        this.j = (KsLogoView) findViewById(R.id.ksad_video_tf_logo);
        this.m = findViewById(R.id.video_cover);
    }

    private void c() {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = com.kwad.sdk.core.response.a.a.J(this.f);
        int i = materialFeatureJ.width;
        int i2 = materialFeatureJ.height;
        int iC = com.kwad.sdk.a.kwai.a.c(getContext());
        int i3 = (int) (iC * (i2 / i));
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        layoutParams.width = iC;
        layoutParams.height = i3;
        KSImageLoader.loadImage(this.a, materialFeatureJ.coverUrl, this.e);
    }

    private void d() {
        if (!com.kwad.sdk.core.response.a.a.B(this.f) && !com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.c = (TailFrameBarH5PortraitHorizontal) findViewById(R.id.ksad_video_h5_tail_frame);
            this.c.setModel(this.e);
            this.c.setVisibility(0);
            return;
        }
        this.b = (TailFrameBarAppPortraitHorizontal) findViewById(R.id.ksad_video_app_tail_frame);
        this.b.a(this.e);
        this.b.setVisibility(0);
        this.i = this.b.getTextProgressBar();
        if (!com.kwad.sdk.core.response.a.d.v(this.e)) {
            e();
        }
        new f(this.i, this);
    }

    private void e() {
        this.h = new b(this.e, this.g, new c() { // from class: com.kwad.sdk.reward.widget.tailframe.TailFramePortraitHorizontal.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.b(i), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFramePortraitHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.a(TailFramePortraitHorizontal.this.e), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.A(TailFramePortraitHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.m(TailFramePortraitHorizontal.this.f), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                TailFramePortraitHorizontal.this.b.a(TailFramePortraitHorizontal.this.f);
                TailFramePortraitHorizontal.this.i.a(com.kwad.sdk.core.response.a.a.a(i), i);
            }
        });
    }

    private void f() {
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
        f();
    }

    public void a(@NonNull AdTemplate adTemplate, JSONObject jSONObject, a aVar) {
        this.e = adTemplate;
        if (com.kwad.sdk.core.response.a.d.v(this.e)) {
            this.m.setVisibility(8);
            this.j.setVisibility(8);
            this.l.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ksad_translucent));
        }
        this.f = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.g = jSONObject;
        this.d = aVar;
        this.j.a(this.e);
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
        if (com.kwad.sdk.core.response.a.c.g(this.e)) {
            a(view, false);
        }
    }

    public void setCallerContext(com.kwad.sdk.reward.a aVar) {
        this.k = aVar;
    }
}
