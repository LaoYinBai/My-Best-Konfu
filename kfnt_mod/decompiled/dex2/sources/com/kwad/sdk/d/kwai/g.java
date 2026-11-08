package com.kwad.sdk.d.kwai;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.c;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.z;
import com.kwad.sdk.widget.KSFrameLayout;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends b implements com.kwad.sdk.widget.d {

    @NonNull
    protected Context a;
    protected AdInfo b;
    private c c;
    private AdTemplate d;
    private KSFrameLayout e;
    private KSFrameLayout f;
    private Dialog g;
    private List<Integer> i;
    private com.kwad.sdk.core.video.videoview.b j;

    @NonNull
    private KsAdVideoPlayConfig k;
    private ImageView l;
    private com.kwad.sdk.core.download.a.b m;
    private com.kwad.sdk.d.a.c n;

    @Nullable
    private boolean h = false;
    private final a.InterfaceC0047a o = new a.InterfaceC0047a() { // from class: com.kwad.sdk.d.kwai.g.2
        @Override // com.kwad.sdk.core.video.videoview.a.InterfaceC0047a
        public void a(int i, z.a aVar) {
            int i2;
            int i3 = 2;
            boolean z = false;
            switch (i) {
                case 1:
                    i2 = 13;
                    break;
                case 2:
                    i2 = 82;
                    break;
                case 3:
                    i2 = 83;
                    i3 = 1;
                    z = true;
                    break;
                default:
                    i2 = 108;
                    break;
            }
            p.a aVar2 = new p.a();
            aVar2.g = aVar;
            aVar2.b = i2;
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(g.this.j)).a(g.this.d).a(g.this.m).a(i3).a(z).c(true).a(aVar2).a(new a.b() { // from class: com.kwad.sdk.d.kwai.g.2.1
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    g.this.f();
                }
            }));
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.i == null || this.i.isEmpty()) {
            return;
        }
        Iterator<Integer> it = this.i.iterator();
        while (it.hasNext()) {
            if (iCeil >= it.next().intValue()) {
                com.kwad.sdk.core.report.a.b(this.d, iCeil, null);
                it.remove();
                return;
            }
        }
    }

    private void e() {
        ImageView imageView;
        int i;
        String strA;
        this.h = this.k.isVideoSoundEnable();
        String strA2 = com.kwad.sdk.core.response.a.a.aa(this.b).a();
        if (TextUtils.isEmpty(strA2)) {
            imageView = this.l;
            i = 8;
        } else {
            this.l.setImageDrawable(null);
            KSImageLoader.loadImage(this.l, strA2, this.d);
            imageView = this.l;
            i = 0;
        }
        imageView.setVisibility(i);
        int iC = com.kwad.sdk.core.config.b.C();
        if (iC < 0) {
            File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(com.kwad.sdk.core.response.a.a.a(this.b));
            strA = (fileB == null || !fileB.exists()) ? null : fileB.getAbsolutePath();
        } else {
            strA = iC == 0 ? com.kwad.sdk.core.response.a.a.a(this.b) : com.kwad.sdk.core.videocache.b.a.a(this.a).a(com.kwad.sdk.core.response.a.a.a(this.b));
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        this.j.a(new c.a(this.d).a(strA).b(com.kwad.sdk.core.response.a.f.b(com.kwad.sdk.core.response.a.d.k(this.d))).a(this.d.mVideoPlayerStatus).a(new com.kwad.sdk.contentalliance.detail.video.b(this.d, System.currentTimeMillis())).a(), (Map<String, String>) null);
        this.j.setVideoSoundEnable(this.h);
        this.n.setVideoPlayCallback(new a.b() { // from class: com.kwad.sdk.d.kwai.g.1
            private boolean b = false;

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                g.this.a(j);
                Iterator<a.b> it = g.this.c.h.iterator();
                while (it.hasNext()) {
                    it.next().a(j);
                }
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                com.kwad.sdk.core.report.a.i(g.this.d);
                if (g.this.c.b != null) {
                    g.this.c.b.onVideoPlayStart();
                }
                Iterator<a.b> it = g.this.c.h.iterator();
                while (it.hasNext()) {
                    it.next().b_();
                }
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (!this.b) {
                    this.b = true;
                    com.kwad.sdk.core.report.d.a(g.this.d, System.currentTimeMillis(), 0);
                }
                Iterator<a.b> it = g.this.c.h.iterator();
                while (it.hasNext()) {
                    it.next().e();
                }
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                com.kwad.sdk.core.report.a.j(g.this.d);
                if (g.this.c.b != null) {
                    g.this.c.b.onVideoPlayEnd();
                }
                Iterator<a.b> it = g.this.c.h.iterator();
                while (it.hasNext()) {
                    it.next().f();
                }
            }
        });
        this.j.setController(this.n);
        this.f.setClickable(true);
        new com.kwad.sdk.widget.f(this.f.getContext(), this.f, this);
        this.f.addView(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.c.b != null) {
            this.c.b.onAdClicked();
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() throws Resources.NotFoundException {
        super.a();
        this.c = (c) p();
        this.g = this.c.c;
        this.k = this.c.g;
        this.d = this.c.a;
        this.b = com.kwad.sdk.core.response.a.d.j(this.d);
        this.i = com.kwad.sdk.core.response.a.a.S(this.b);
        this.j = this.c.i;
        this.j.setTag(this.i);
        this.n = new com.kwad.sdk.d.a.c(this.a, this.d, this.j);
        this.n.setDataFlowAutoStart(this.k.isDataFlowAutoStart());
        this.n.setAdClickListener(this.o);
        this.n.j();
        this.m = this.c.d;
        e();
        float dimension = q().getResources().getDimension(R.dimen.ksad_interstitial_card_radius);
        this.f.a(dimension, dimension, 0.0f, 0.0f);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        this.c.a(view, false, 3, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.c.a)) {
            this.c.a(view, false, 3, false);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.e = (KSFrameLayout) m().findViewById(R.id.ksad_container);
        this.f = (KSFrameLayout) m().findViewById(R.id.ksad_video_container);
        this.l = (ImageView) m().findViewById(R.id.ksad_video_first_frame_container);
        this.f.setVisibility(4);
        this.a = q();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
    }
}
