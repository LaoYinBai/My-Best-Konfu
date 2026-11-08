package com.kwad.sdk.draw.a;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.imageloader.KSImageLoader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.draw.kwai.a {
    private ImageView b;
    private com.kwad.sdk.core.response.model.c c;
    private com.kwad.sdk.contentalliance.detail.video.d d = new e() { // from class: com.kwad.sdk.draw.a.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            if (b.this.b.getVisibility() == 0) {
                b.this.b.setVisibility(8);
            }
        }
    };
    private Runnable e = new Runnable() { // from class: com.kwad.sdk.draw.a.b.2
        @Override // java.lang.Runnable
        public void run() {
            int width = b.this.m().getWidth();
            ViewGroup.LayoutParams layoutParams = b.this.b.getLayoutParams();
            int iC = b.this.c.c();
            int iB = b.this.c.b();
            if (iC == 0 || iB == 0 || iB > iC) {
                layoutParams.width = -1;
                layoutParams.height = -1;
                b.this.b.setLayoutParams(layoutParams);
            } else {
                layoutParams.width = width;
                layoutParams.height = (int) ((iB / (iC * 1.0f)) * width);
                b.this.b.setLayoutParams(layoutParams);
                b.this.b.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            b.this.b.setImageDrawable(null);
            KSImageLoader.loadImage(b.this.b, b.this.c.a(), b.this.a.c);
        }
    };

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = com.kwad.sdk.core.response.a.a.aa(com.kwad.sdk.core.response.a.d.j(this.a.c));
        if (TextUtils.isEmpty(this.c.a())) {
            return;
        }
        m().post(this.e);
        this.b.setVisibility(0);
        this.a.e.a(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        m().removeCallbacks(this.e);
        this.a.e.b(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ImageView) a(R.id.ksad_video_first_frame);
    }
}
