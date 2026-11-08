package com.kwad.sdk.feed.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.c;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.AdVideoPlayerViewCache;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.feed.widget.base.RatioFrameLayout;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.utils.z;
import com.kwad.sdk.widget.DownloadProgressView;
import com.kwad.sdk.widget.KsLogoView;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends com.kwad.sdk.feed.widget.base.a implements View.OnClickListener {
    a.b a;
    private TextView f;
    private RatioFrameLayout g;
    private ImageView h;
    private KsLogoView i;
    private com.kwad.sdk.core.download.a.b j;
    private ImageView k;
    private List<Integer> l;
    private com.kwad.sdk.core.video.videoview.b m;
    private j n;
    private DownloadProgressView o;
    private boolean p;
    private volatile boolean q;
    private com.kwad.sdk.widget.k r;
    private final a.InterfaceC0047a s;

    public a(@NonNull Context context) {
        super(context);
        this.p = false;
        this.q = false;
        this.r = new com.kwad.sdk.widget.k() { // from class: com.kwad.sdk.feed.widget.a.1
            @Override // com.kwad.sdk.widget.k
            public void a() {
                m.c(a.this.b);
            }
        };
        this.a = new a.b() { // from class: com.kwad.sdk.feed.widget.a.2
            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                a.this.a(j);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                a.this.q = false;
                com.kwad.sdk.core.report.a.i(a.this.b);
                if (a.this.m == null || a.this.m.getParent() != a.this.g) {
                    return;
                }
                a.this.m.setVideoSoundEnable(a.this.p);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (a.this.q) {
                    return;
                }
                a.this.q = true;
                com.kwad.sdk.core.report.d.a(a.this.b, System.currentTimeMillis(), 1);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                com.kwad.sdk.core.report.a.j(a.this.b);
            }
        };
        this.s = new a.InterfaceC0047a() { // from class: com.kwad.sdk.feed.widget.a.3
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
                com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(a.this.m)).a(a.this.b).a(a.this.j).a(i3).a(z).c(true).e(true).a(aVar2).a(new a.b() { // from class: com.kwad.sdk.feed.widget.a.3.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                        a.this.l();
                    }
                }));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.l == null || this.l.isEmpty()) {
            return;
        }
        Iterator<Integer> it = this.l.iterator();
        while (it.hasNext()) {
            if (iCeil >= it.next().intValue()) {
                com.kwad.sdk.core.report.a.b(this.b, iCeil, null);
                it.remove();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.kwad.sdk.core.video.videoview.b bVar, boolean z) {
        if (bVar != null) {
            String strA = com.kwad.sdk.core.response.a.a.a(this.c);
            this.n.setAutoRelease(false);
            int i = z ? 1 : 2;
            AdVideoPlayerViewCache.a().a(strA, this.m);
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(this)).a(this.b).a(this.j).a(z).a(i).e(true).a(new a.b() { // from class: com.kwad.sdk.feed.widget.a.5
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    a.this.k();
                }
            }));
        }
    }

    private void d() {
        findViewById(R.id.ksad_ad_h5_container).setVisibility(0);
        findViewById(R.id.ksad_ad_download_container).setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.ksad_h5_desc);
        TextView textView2 = (TextView) findViewById(R.id.ksad_h5_open_btn);
        textView.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        textView2.setText(com.kwad.sdk.core.response.a.a.A(this.c));
        findViewById(R.id.ksad_h5_open_cover).setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    private void g() {
        findViewById(R.id.ksad_ad_download_container).setVisibility(0);
        findViewById(R.id.ksad_ad_h5_container).setVisibility(8);
        ImageView imageView = (ImageView) findViewById(R.id.ksad_app_icon);
        TextView textView = (TextView) findViewById(R.id.ksad_app_title);
        TextView textView2 = (TextView) findViewById(R.id.ksad_app_desc);
        textView.setText(com.kwad.sdk.core.response.a.a.t(this.c));
        imageView.setImageResource(R.drawable.ksad_default_app_icon);
        KSImageLoader.loadAppIcon(imageView, com.kwad.sdk.core.response.a.a.au(this.c), this.b, 8);
        textView2.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        this.o.a(this.b);
        this.o.setOnClickListener(this);
        this.j = new com.kwad.sdk.core.download.a.b(this.b, null, this.o.getAppDownloadListener());
        this.j.c(this.o.getAppDownloadListener());
        this.j.a((DialogInterface.OnShowListener) this);
        this.j.a((DialogInterface.OnDismissListener) this);
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.widget.KSFrameLayout
    protected void a() {
        super.a();
    }

    public void a(@NonNull KsAdVideoPlayConfig ksAdVideoPlayConfig) {
        ImageView imageView;
        int i;
        this.p = ksAdVideoPlayConfig.isVideoSoundEnable();
        String strA = com.kwad.sdk.core.response.a.a.aa(this.c).a();
        if (TextUtils.isEmpty(strA)) {
            imageView = this.k;
            i = 8;
        } else {
            this.k.setImageDrawable(null);
            KSImageLoader.loadImage(this.k, strA, this.b);
            imageView = this.k;
            i = 0;
        }
        imageView.setVisibility(i);
        this.l = com.kwad.sdk.core.response.a.a.S(this.c);
        this.m = new com.kwad.sdk.core.video.videoview.b(this.e);
        this.m.setVisibleListener(this.r);
        this.m.setTag(this.l);
        String strA2 = com.kwad.sdk.core.response.a.a.a(this.c);
        if (TextUtils.isEmpty(strA2)) {
            return;
        }
        this.m.a(new c.a(this.b).a(strA2).b(com.kwad.sdk.core.response.a.f.b(com.kwad.sdk.core.response.a.d.k(this.b))).a(this.b.mVideoPlayerStatus).a(com.kwad.sdk.contentalliance.detail.video.b.a(this.b)).a(), (Map<String, String>) null);
        this.m.setVideoSoundEnable(this.p);
        this.n = new j(this.e, this.b, this.m);
        this.n.setVideoPlayCallback(this.a);
        this.n.setAdClickListener(this.s);
        this.n.setDataFlowAutoStart(ksAdVideoPlayConfig.isDataFlowAutoStart());
        this.m.setController(this.n);
        if (this.g.getTag() != null) {
            this.g.removeView((View) this.g.getTag());
            this.g.setTag(null);
        }
        this.g.addView(this.m);
        this.g.setTag(this.m);
        this.g.setClickable(true);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.a.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!a.this.m.d()) {
                    a.this.a(a.this.m, false);
                    return;
                }
                m.b(a.this.b);
                a.this.m.setKsPlayLogParam(com.kwad.sdk.contentalliance.detail.video.b.a(a.this.b));
                a.this.m.a();
            }
        });
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        super.a(adTemplate);
        this.f.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        this.i.a(adTemplate);
        if (com.kwad.sdk.core.response.a.a.B(this.c)) {
            g();
        } else {
            d();
        }
        this.h.setOnClickListener(this);
        setOnClickListener(this);
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        m.a(this.b);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        this.f = (TextView) findViewById(R.id.ksad_ad_desc);
        this.g = (RatioFrameLayout) findViewById(R.id.ksad_video_container);
        this.g.setRatio(0.56f);
        this.h = (ImageView) findViewById(R.id.ksad_ad_dislike);
        this.k = (ImageView) findViewById(R.id.ksad_video_first_frame_container);
        this.i = (KsLogoView) findViewById(R.id.ksad_ad_dislike_logo);
        this.o = (DownloadProgressView) findViewById(R.id.ksad_app_download_btn);
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.core.j.d
    public void e() {
        super.e();
        if (this.p) {
            com.kwad.sdk.utils.b.a(getContext()).a(false);
            if (com.kwad.sdk.utils.b.a(getContext()).a()) {
                this.p = false;
                this.m.setVideoSoundEnable(this.p);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.h) {
            m();
        } else {
            a(this.m, view == this.o);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || this.m == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) this.m.getParent();
        if (this.m.getParent() != this.g) {
            viewGroup.removeView(this.m);
            if (this.g.getTag() != null) {
                this.g.removeView((View) this.g.getTag());
                this.g.setTag(null);
            }
            this.g.addView(this.m);
            this.g.setTag(this.m);
            String strA = com.kwad.sdk.core.response.a.a.a(this.c);
            this.m.setVideoSoundEnable(this.p);
            this.n.setVideoPlayCallback(this.a);
            this.n.setAdClickListener(this.s);
            this.n.getAdTemplate().mAdWebVideoPageShowing = false;
            this.n.p();
            this.n.setAutoRelease(true);
            AdVideoPlayerViewCache.a().a(strA);
        }
    }
}
