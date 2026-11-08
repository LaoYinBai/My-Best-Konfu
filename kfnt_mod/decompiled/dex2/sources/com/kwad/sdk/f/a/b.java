package com.kwad.sdk.f.a;

import android.text.TextUtils;
import android.widget.ImageView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.f.kwai.a {
    private ImageView b;
    private com.kwad.sdk.core.response.model.c c;
    private com.kwad.sdk.contentalliance.detail.video.d d = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            if (b.this.b.getVisibility() == 0) {
                b.this.b.setVisibility(8);
            }
        }
    };
    private Runnable e = new Runnable() { // from class: com.kwad.sdk.f.a.b.2
        @Override // java.lang.Runnable
        public void run() {
            com.kwad.sdk.a.kwai.a.a(b.this.b, b.this.c.c(), b.this.c.b());
            b.this.b.setImageDrawable(null);
            KSImageLoader.loadImage(b.this.b, b.this.c.a(), b.this.a.d);
        }
    };

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = com.kwad.sdk.core.response.a.a.aa(com.kwad.sdk.core.response.a.d.j(this.a.d));
        if (TextUtils.isEmpty(this.c.a())) {
            return;
        }
        m().post(this.e);
        this.b.setVisibility(0);
        this.a.f.a(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        m().removeCallbacks(this.e);
        this.a.f.b(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ImageView) a(R.id.ksad_video_first_frame);
    }
}
