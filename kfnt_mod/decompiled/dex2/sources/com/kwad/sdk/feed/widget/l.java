package com.kwad.sdk.feed.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.c;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.AdVideoPlayerViewCache;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.core.webview.jshandler.WebCardVideoPositionHandler;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.c;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.jshandler.v;
import com.kwad.sdk.feed.FeedType;
import com.kwad.sdk.feed.widget.base.RatioFrameLayout;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.utils.z;
import com.kwad.sdk.widget.KSFrameLayout;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l extends com.kwad.sdk.feed.widget.base.a implements View.OnClickListener {
    private static final HashMap<Long, Double> s = new HashMap<>(8);
    private ViewGroup.MarginLayoutParams A;
    private Handler B;
    private p.b C;
    private final a.InterfaceC0047a D;
    private RatioFrameLayout a;
    private KsAdWebView f;

    @Nullable
    private com.kwad.sdk.core.download.a.b g;
    private com.kwad.sdk.core.webview.kwai.g h;
    private com.kwad.sdk.core.webview.a i;
    private int j;
    private double k;
    private List<Integer> l;
    private com.kwad.sdk.core.video.videoview.b m;
    private j n;
    private ImageView o;
    private boolean p;
    private KSFrameLayout q;
    private v r;
    private boolean t;
    private String u;
    private boolean v;
    private com.kwad.sdk.feed.widget.base.a w;
    private KsAdVideoPlayConfig x;
    private a y;
    private int z;

    public interface a {
        void a();
    }

    public l(@NonNull Context context) {
        super(context);
        this.j = -1;
        this.p = false;
        this.v = false;
        this.B = new Handler(Looper.getMainLooper());
        this.C = new p.b() { // from class: com.kwad.sdk.feed.widget.l.12
            @Override // com.kwad.sdk.core.webview.jshandler.p.b
            public void a(p.a aVar) {
                if (l.this.t) {
                    return;
                }
                l.this.j = aVar.a;
                if (l.this.j != 1) {
                    l.this.a(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                    return;
                }
                if (l.this.w != null) {
                    l.this.w.setVisibility(8);
                }
                l.this.f.setVisibility(0);
                com.kwad.sdk.core.report.d.a(l.this.b);
                l.this.B.removeCallbacksAndMessages(null);
                if (l.this.y != null) {
                    l.this.y.a();
                }
            }
        };
        this.D = new a.InterfaceC0047a() { // from class: com.kwad.sdk.feed.widget.l.5
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
                com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(l.this.m)).a(l.this.b).a(l.this.g).a(i3).a(z).c(true).a(aVar2).e(true).a(new a.b() { // from class: com.kwad.sdk.feed.widget.l.5.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                        l.this.l();
                    }
                }));
            }
        };
        this.z = context.getResources().getDisplayMetrics().widthPixels;
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
    public void a(com.kwad.sdk.core.video.videoview.b bVar) {
        if (bVar != null) {
            String strA = com.kwad.sdk.core.response.a.a.a(this.c);
            boolean z = false;
            this.n.setAutoRelease(false);
            AdVideoPlayerViewCache.a().a(strA, this.m);
            FeedType feedTypeFromInt = FeedType.fromInt(this.b.type);
            a.C0035a c0035aA = new a.C0035a(bb.a(this)).a(this.b).a(this.g).a(2);
            if ((feedTypeFromInt == FeedType.FEED_TYPE_TEXT_BELOW || feedTypeFromInt == FeedType.FEED_TYPE_TEXT_ABOVE) && com.kwad.sdk.core.response.a.a.P(this.c)) {
                z = true;
            }
            com.kwad.sdk.core.download.a.a.a(c0035aA.e(z).a(new a.b() { // from class: com.kwad.sdk.feed.widget.l.3
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    l.this.k();
                }
            }));
        }
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        this.r = new v();
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.i, this.g, getClickListener()));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.i, this.g, getClickListener()));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.j(this.i, new j.b() { // from class: com.kwad.sdk.feed.widget.l.7
            @Override // com.kwad.sdk.core.webview.jshandler.j.b
            public void a(j.a aVar) {
                l.this.f.setVisibility(0);
                if (l.this.k == 0.0d) {
                    l.this.k = aVar.a;
                    double d = aVar.a / l.this.z;
                    l.this.a.setRatio((float) d);
                    l.s.put(Long.valueOf(l.this.b.posId), Double.valueOf(d));
                }
            }
        }));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.i));
        gVar.a(new WebCardVideoPositionHandler(new WebCardVideoPositionHandler.a() { // from class: com.kwad.sdk.feed.widget.l.8
            @Override // com.kwad.sdk.core.webview.jshandler.WebCardVideoPositionHandler.a
            public void a(WebCardVideoPositionHandler.VideoPosition videoPosition) {
                if (l.this.c == null || !com.kwad.sdk.core.response.a.a.P(l.this.c)) {
                    return;
                }
                l.this.A = (ViewGroup.MarginLayoutParams) l.this.q.getLayoutParams();
                int iCeil = (int) Math.ceil(l.this.k);
                int i = l.this.z;
                if (videoPosition.widthRation == 0.0d) {
                    videoPosition.widthRation = 0.9200000166893005d;
                    videoPosition.leftMarginRation = 0.03999999910593033d;
                }
                l.this.A.topMargin = (int) (videoPosition.topMarginRation * iCeil);
                double d = i;
                l.this.A.leftMargin = (int) (videoPosition.leftMarginRation * d);
                l.this.A.width = (int) (d * videoPosition.widthRation);
                l.this.A.height = (int) (l.this.A.width * videoPosition.heightWidthRation);
                l.this.q.setRadius(videoPosition.borderRadius);
                l.this.q.setLayoutParams(l.this.A);
                l.this.a(l.this.x);
            }
        }));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.c(this.i, new c.a() { // from class: com.kwad.sdk.feed.widget.l.9
            @Override // com.kwad.sdk.core.webview.jshandler.c.a
            public void a() {
                l.this.B.post(new Runnable() { // from class: com.kwad.sdk.feed.widget.l.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        l.this.m();
                    }
                });
            }
        }));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.i));
        com.kwad.sdk.core.webview.jshandler.d dVar = new com.kwad.sdk.core.webview.jshandler.d(this.i);
        dVar.a(new d.b() { // from class: com.kwad.sdk.feed.widget.l.10
            @Override // com.kwad.sdk.core.webview.jshandler.d.b
            public void a(d.a aVar) {
                aVar.b = 0;
                aVar.a = l.this.z;
                l.this.v = true;
            }
        });
        gVar.a(dVar);
        gVar.a(new com.kwad.sdk.core.webview.jshandler.p(this.C));
        gVar.a(new t(this.i, this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.k(this.i));
        gVar.a(this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.kwad.sdk.core.d.a.a("FeedWebView", "handleWebViewError " + str);
        this.B.removeCallbacksAndMessages(null);
        if (this.t) {
            return;
        }
        if (this.y != null) {
            this.y.a();
        }
        this.y = null;
        this.t = true;
        com.kwad.sdk.core.report.d.c(this.b, com.kwad.sdk.core.response.a.b.k(this.b), str);
        if (this.w == null) {
            this.w = com.kwad.sdk.feed.a.a(getContext(), FeedType.fromInt(this.b.type), com.kwad.sdk.core.response.a.a.R(this.c));
            if (this.w != null) {
                this.w.setMargin(com.kwad.sdk.a.kwai.a.a(getContext(), 16.0f));
                this.a.removeAllViews();
                this.a.setRatio(0.0f);
                this.f.setVisibility(8);
                this.q.setVisibility(8);
            }
            this.a.addView(this.w);
            this.w.a(this.b);
            if (this.w instanceof com.kwad.sdk.feed.widget.a) {
                ((com.kwad.sdk.feed.widget.a) this.w).a(this.x);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(b.a aVar) {
        return aVar.a ? aVar.b : aVar.c == 1;
    }

    private float c(AdTemplate adTemplate) {
        int i = adTemplate.type;
        if (i == 1) {
            return 0.6013f;
        }
        return (i == 2 || i == 3) ? 0.283f : 0.968f;
    }

    @NonNull
    private b.c getClickListener() {
        return new b.c() { // from class: com.kwad.sdk.feed.widget.l.11
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) {
                int i = aVar.c;
                if (aVar.a) {
                    i = aVar.b ? 1 : 2;
                }
                boolean z = com.kwad.sdk.core.response.a.a.P(l.this.c) && (l.this.b.type == FeedType.FEED_TYPE_TEXT_BELOW.getType() || l.this.b.type == FeedType.FEED_TYPE_TEXT_ABOVE.getType());
                p.a aVar2 = new p.a();
                if (aVar.d != null && !TextUtils.isEmpty(aVar.d.a)) {
                    aVar2.h = aVar.d.a;
                }
                com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(l.this)).a(l.this.b).a(l.this.g).a(l.this.a(aVar)).a(i).f(aVar.a).e(z).a(aVar2).c(true).a(new a.b() { // from class: com.kwad.sdk.feed.widget.l.11.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                        if (l.this.d != null) {
                            l.this.d.a();
                        }
                    }
                }));
            }
        };
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void o() {
    }

    private void p() {
        this.i = new com.kwad.sdk.core.webview.a();
        this.i.a(this.b);
        this.i.a = 0;
        this.i.b = null;
        this.i.d = this.a;
        this.i.e = this.f;
        this.i.c = null;
        this.i.h = false;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void q() {
        r();
        be.b(this.f);
        this.f.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.feed.widget.l.6
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
                l.this.v = false;
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
                l.this.a("1");
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
                if (l.this.v) {
                    return;
                }
                l.this.a("2");
            }
        });
        this.h = new com.kwad.sdk.core.webview.kwai.g(this.f);
        a(this.h);
        this.f.addJavascriptInterface(this.h, "KwaiAd");
        this.f.loadUrl(com.kwad.sdk.core.response.a.b.k(this.b));
    }

    private void r() {
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
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
            imageView = this.o;
            i = 8;
        } else {
            this.o.setImageDrawable(null);
            KSImageLoader.loadImage(this.o, strA, this.b);
            imageView = this.o;
            i = 0;
        }
        imageView.setVisibility(i);
        this.l = com.kwad.sdk.core.response.a.a.S(this.c);
        this.m = new com.kwad.sdk.core.video.videoview.b(this.e);
        this.m.setTag(this.l);
        String strA2 = com.kwad.sdk.core.response.a.a.a(this.c);
        if (TextUtils.isEmpty(strA2)) {
            return;
        }
        this.m.a(new c.a(this.b).a(strA2).b(com.kwad.sdk.core.response.a.f.b(com.kwad.sdk.core.response.a.d.k(this.b))).a(this.b.mVideoPlayerStatus).a(new com.kwad.sdk.contentalliance.detail.video.b(this.b, System.currentTimeMillis())).a(), (Map<String, String>) null);
        this.m.setVideoSoundEnable(this.p);
        this.n = new j(this.e, this.b, this.m);
        this.n.setVideoPlayCallback(new a.b() { // from class: com.kwad.sdk.feed.widget.l.13
            private boolean b = false;

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                l.this.a(j);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                com.kwad.sdk.core.report.a.i(l.this.b);
                l.this.r.a(3);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (this.b) {
                    return;
                }
                this.b = true;
                com.kwad.sdk.core.report.d.a(l.this.b, System.currentTimeMillis(), 1);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                com.kwad.sdk.core.report.a.j(l.this.b);
                l.this.r.a(9);
                if (l.this.q != null) {
                    l.this.q.setVisibility(8);
                }
            }
        });
        this.n.setAdClickListener(this.D);
        this.n.setDataFlowAutoStart(ksAdVideoPlayConfig.isDataFlowAutoStart());
        this.m.setController(this.n);
        if (this.q.getTag() != null) {
            this.q.removeView((View) this.q.getTag());
            this.q.setTag(null);
        }
        this.q.addView(this.m);
        this.q.setTag(this.m);
        this.q.setClickable(true);
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.l.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!l.this.m.d()) {
                    l.this.a(l.this.m);
                    return;
                }
                m.b(l.this.b);
                l.this.m.setKsPlayLogParam(com.kwad.sdk.contentalliance.detail.video.b.a(l.this.b));
                l.this.m.a();
            }
        });
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        adTemplate.realShowType = 2;
        super.a(adTemplate);
        if (this.t) {
            if (this.w != null) {
                this.w.a(this.b);
                if (this.w instanceof com.kwad.sdk.feed.widget.a) {
                    ((com.kwad.sdk.feed.widget.a) this.w).a(this.x);
                    return;
                }
                return;
            }
            return;
        }
        if (this.j != 1) {
            b(this.b);
        }
        if (this.u == null || !this.u.equals(adTemplate.mOriginJString)) {
            if (g()) {
                this.f.reload();
            } else {
                d();
            }
        }
        this.u = adTemplate.mOriginJString;
    }

    public void a(@NonNull AdTemplate adTemplate, a aVar) {
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        this.y = aVar;
        adTemplate.realShowType = 2;
        super.a(adTemplate);
        if (this.j != 1) {
            b(this.b);
        }
        if (this.u == null || !this.u.equals(adTemplate.mOriginJString)) {
            if (g()) {
                this.f.reload();
            } else {
                d();
            }
        }
        this.u = adTemplate.mOriginJString;
        this.B.postDelayed(new Runnable() { // from class: com.kwad.sdk.feed.widget.l.1
            @Override // java.lang.Runnable
            public void run() {
                l.this.f.stopLoading();
                l.this.f.setVisibility(8);
                l.this.a(PushConstants.PUSH_TYPE_NOTIFY);
            }
        }, 2500L);
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        m.a(this.b);
    }

    public void b(AdTemplate adTemplate) {
        RatioFrameLayout ratioFrameLayout;
        float fC;
        this.g = new com.kwad.sdk.core.download.a.b(this.b);
        this.g.a((DialogInterface.OnShowListener) this);
        this.g.a((DialogInterface.OnDismissListener) this);
        this.b = adTemplate;
        Double d = s.get(Long.valueOf(this.b.posId));
        if (d != null) {
            this.a.setRatio(d.floatValue());
        } else {
            if (com.kwad.sdk.core.response.a.b.l(this.b) > 0.0f) {
                ratioFrameLayout = this.a;
                fC = com.kwad.sdk.core.response.a.b.l(this.b);
            } else if (this.a.getRatio() == 0.0f) {
                ratioFrameLayout = this.a;
                fC = c(this.b);
            }
            ratioFrameLayout.setRatio(fC);
        }
        o();
        p();
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        this.f = (KsAdWebView) findViewById(R.id.ksad_web_card_webView);
        this.f.setVisibility(4);
        this.a = (RatioFrameLayout) findViewById(R.id.ksad_container);
        this.q = (KSFrameLayout) findViewById(R.id.ksad_video_container);
        this.o = (ImageView) findViewById(R.id.ksad_video_first_frame_container);
    }

    public void d() {
        if (com.kwad.sdk.core.response.a.b.m(this.b)) {
            q();
        } else {
            a(PushConstants.PUSH_TYPE_NOTIFY);
        }
    }

    protected boolean g() {
        return this.j == 1;
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected int getLayoutId() {
        return R.layout.ksad_feed_webview;
    }

    public void h() {
        this.B.removeCallbacksAndMessages(null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        ViewGroup viewGroup;
        super.onWindowFocusChanged(z);
        if (!z || this.m == null || (viewGroup = (ViewGroup) this.m.getParent()) == this.q) {
            return;
        }
        viewGroup.removeView(this.m);
        if (this.q.getTag() != null) {
            this.q.removeView((View) this.q.getTag());
            this.q.setTag(null);
        }
        this.q.addView(this.m);
        this.q.setTag(this.m);
        String strA = com.kwad.sdk.core.response.a.a.a(this.c);
        this.m.setVideoSoundEnable(this.p);
        if (this.x != null) {
            this.n.setDataFlowAutoStart(this.x.isDataFlowAutoStart());
        }
        this.n.setVideoPlayCallback(new a.b() { // from class: com.kwad.sdk.feed.widget.l.4
            private boolean b = false;

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                l.this.a(j);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                com.kwad.sdk.core.report.a.i(l.this.b);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (this.b) {
                    return;
                }
                this.b = true;
                com.kwad.sdk.core.report.d.a(l.this.b, System.currentTimeMillis(), 1);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                com.kwad.sdk.core.report.a.j(l.this.b);
                if (l.this.q != null) {
                    l.this.q.setVisibility(8);
                }
            }
        });
        this.n.setAdClickListener(this.D);
        this.n.getAdTemplate().mAdWebVideoPageShowing = false;
        this.n.p();
        this.n.setAutoRelease(true);
        AdVideoPlayerViewCache.a().a(strA);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void setMargin(int i) {
    }

    public void setVideoPlayConfig(KsAdVideoPlayConfig ksAdVideoPlayConfig) {
        this.x = ksAdVideoPlayConfig;
        if (this.m != null) {
            this.m.setVideoSoundEnable(ksAdVideoPlayConfig.isVideoSoundEnable());
        }
        if (this.n != null) {
            this.n.setDataFlowAutoStart(ksAdVideoPlayConfig.isDataFlowAutoStart());
        }
    }

    public void setWidth(int i) {
        this.z = i;
    }
}
