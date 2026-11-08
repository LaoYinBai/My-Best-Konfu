package com.kwad.sdk.feed.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.c;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.AdVideoPlayerViewCache;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.core.view.ScaleAnimSeekBar;
import com.kwad.sdk.feed.widget.base.RatioFrameLayout;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.aw;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.bd;
import com.kwad.sdk.utils.z;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k extends com.kwad.sdk.feed.widget.base.a implements NestedScrollingChild {
    private boolean A;
    private View.OnClickListener B;
    private KsAdVideoPlayConfig C;
    private a D;
    private View E;
    private com.kwad.sdk.core.download.a.b F;
    private Runnable G;
    private bd H;
    private RatioFrameLayout a;
    private ImageView f;
    private List<Integer> g;
    private TextView h;
    private TextView i;
    private TextView j;
    private ScaleAnimSeekBar k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private ViewGroup o;
    private com.kwad.sdk.core.video.videoview.b p;
    private ViewGroup q;
    private TextView r;
    private ViewGroup s;
    private ViewGroup t;
    private j u;
    private int v;
    private boolean w;
    private boolean x;
    private long y;
    private NestedScrollingChildHelper z;

    public interface a {
        void a();

        void b();
    }

    public k(@NonNull Context context) {
        super(context);
        this.v = 100;
        this.G = new Runnable() { // from class: com.kwad.sdk.feed.widget.k.1
            @Override // java.lang.Runnable
            public void run() {
                k.this.o.setVisibility(8);
                k.this.n.setVisibility(8);
                if (k.this.u != null) {
                    k.this.u.a(true);
                }
            }
        };
        this.H = new bd(this.G);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.g == null || this.g.isEmpty()) {
            return;
        }
        Iterator<Integer> it = this.g.iterator();
        while (it.hasNext()) {
            if (iCeil >= it.next().intValue()) {
                com.kwad.sdk.core.report.a.b(this.b, iCeil, null);
                it.remove();
                return;
            }
        }
    }

    private void a(com.kwad.sdk.core.video.videoview.a aVar, final com.kwad.sdk.core.video.videoview.b bVar) {
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.k.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (k.this.o.getVisibility() != 8) {
                    if (k.this.getHandler() != null) {
                        k.this.getHandler().removeCallbacks(k.this.G);
                        if (k.this.A) {
                            return;
                        }
                        k.this.getHandler().postDelayed(k.this.H, 1000L);
                        return;
                    }
                    return;
                }
                if (!k.this.p.i()) {
                    k.this.o.setVisibility(0);
                    k.this.n.setVisibility(0);
                    if (k.this.u != null) {
                        k.this.u.a(false);
                    }
                    if (k.this.getHandler() == null) {
                        return;
                    }
                } else if (k.this.v != 101) {
                    if (k.this.B != null) {
                        k.this.B.onClick(view);
                        return;
                    }
                    return;
                } else {
                    k.this.o.setVisibility(0);
                    k.this.n.setVisibility(8);
                    if (k.this.u != null) {
                        k.this.u.a(false);
                    }
                    if (k.this.getHandler() == null) {
                        return;
                    }
                }
                k.this.getHandler().removeCallbacks(k.this.G);
                k.this.getHandler().postDelayed(k.this.H, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        });
        aVar.setVideoPlayCallback(new a.b() { // from class: com.kwad.sdk.feed.widget.k.3
            private boolean c = false;

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                k.this.a(j);
                float duration = (j * 100.0f) / bVar.getDuration();
                if (!k.this.A) {
                    k.this.k.setProgress((int) duration);
                    k.this.i.setText(aw.a(j));
                }
                k.this.h.setText(aw.a(bVar.getDuration()));
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                com.kwad.sdk.core.report.a.i(k.this.b);
                k.this.h.setText(aw.a(bVar.getDuration()));
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (this.c) {
                    return;
                }
                this.c = true;
                com.kwad.sdk.core.report.d.a(k.this.b, System.currentTimeMillis(), 1);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                com.kwad.sdk.core.report.a.j(k.this.b);
                k.this.o.setVisibility(8);
                k.this.n.setVisibility(8);
                k.this.k.setProgress((int) 100.0f);
                k.this.i.setText(aw.a(bVar.getDuration()));
            }
        });
        this.u.setAdClickListener(new a.InterfaceC0047a() { // from class: com.kwad.sdk.feed.widget.k.4
            @Override // com.kwad.sdk.core.video.videoview.a.InterfaceC0047a
            public void a(int i, z.a aVar2) {
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
                p.a aVar3 = new p.a();
                aVar3.g = aVar2;
                aVar3.b = i2;
                com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(k.this.s)).a(k.this.b).a(k.this.F).a(i3).a(z).c(true).a(aVar3).a(new a.b() { // from class: com.kwad.sdk.feed.widget.k.4.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                        k.this.l();
                    }
                }));
            }
        });
        this.k.setOnSeekBarChangeListener(new ScaleAnimSeekBar.a() { // from class: com.kwad.sdk.feed.widget.k.5
            @Override // com.kwad.sdk.core.view.ScaleAnimSeekBar.a
            public void a(ScaleAnimSeekBar scaleAnimSeekBar) {
                k.this.k.a(true);
                k.this.getHandler().removeCallbacks(k.this.H);
                k.this.A = true;
            }

            @Override // com.kwad.sdk.core.view.ScaleAnimSeekBar.a
            public void a(ScaleAnimSeekBar scaleAnimSeekBar, int i, boolean z) {
                if (z) {
                    k.this.k.a(true);
                    k.this.getHandler().removeCallbacks(k.this.H);
                    k.this.A = true;
                    k.this.i.setText(aw.a((int) ((bVar.getDuration() * scaleAnimSeekBar.getProgress()) / scaleAnimSeekBar.getMaxProgress())));
                }
            }

            @Override // com.kwad.sdk.core.view.ScaleAnimSeekBar.a
            public void b(ScaleAnimSeekBar scaleAnimSeekBar) {
                k.this.k.a(false);
                bVar.a((int) ((bVar.getDuration() * scaleAnimSeekBar.getProgress()) / scaleAnimSeekBar.getMaxProgress()));
                if (k.this.getHandler() != null) {
                    k.this.getHandler().removeCallbacks(k.this.H);
                    k.this.getHandler().postDelayed(k.this.H, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
                k.this.A = false;
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.k.6
            /* JADX WARN: Removed duplicated region for block: B:14:0x009b  */
            /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.view.View r4) {
                /*
                    r3 = this;
                    com.kwad.sdk.core.video.videoview.b r4 = r2
                    boolean r4 = r4.h()
                    if (r4 != 0) goto L58
                    com.kwad.sdk.core.video.videoview.b r4 = r2
                    boolean r4 = r4.d()
                    if (r4 == 0) goto L11
                    goto L58
                L11:
                    com.kwad.sdk.core.video.videoview.b r4 = r2
                    boolean r4 = r4.g()
                    if (r4 == 0) goto L93
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    com.kwad.sdk.feed.widget.j r4 = com.kwad.sdk.feed.widget.k.c(r4)
                    r4.n()
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.widget.ImageView r4 = com.kwad.sdk.feed.widget.k.b(r4)
                    r0 = 0
                    r4.setVisibility(r0)
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.widget.ImageView r4 = com.kwad.sdk.feed.widget.k.b(r4)
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    android.content.Context r0 = r0.getContext()
                    android.content.res.Resources r0 = r0.getResources()
                    int r1 = com.kwad.sdk.R.drawable.ksad_video_player_play_center
                    android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
                    r4.setImageDrawable(r0)
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.widget.ImageView r4 = com.kwad.sdk.feed.widget.k.t(r4)
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    android.content.Context r0 = r0.getContext()
                    android.content.res.Resources r0 = r0.getResources()
                    int r1 = com.kwad.sdk.R.drawable.ksad_video_player_play_btn
                    goto L8c
                L58:
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    com.kwad.sdk.feed.widget.j r4 = com.kwad.sdk.feed.widget.k.c(r4)
                    r4.o()
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.widget.ImageView r4 = com.kwad.sdk.feed.widget.k.t(r4)
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    android.content.Context r0 = r0.getContext()
                    android.content.res.Resources r0 = r0.getResources()
                    int r1 = com.kwad.sdk.R.drawable.ksad_video_player_pause_btn
                    android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
                    r4.setImageDrawable(r0)
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.widget.ImageView r4 = com.kwad.sdk.feed.widget.k.b(r4)
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    android.content.Context r0 = r0.getContext()
                    android.content.res.Resources r0 = r0.getResources()
                    int r1 = com.kwad.sdk.R.drawable.ksad_video_player_pause_center
                L8c:
                    android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
                    r4.setImageDrawable(r0)
                L93:
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.os.Handler r4 = r4.getHandler()
                    if (r4 == 0) goto Lbb
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.os.Handler r4 = r4.getHandler()
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    com.kwad.sdk.utils.bd r0 = com.kwad.sdk.feed.widget.k.f(r0)
                    r4.removeCallbacks(r0)
                    com.kwad.sdk.feed.widget.k r4 = com.kwad.sdk.feed.widget.k.this
                    android.os.Handler r4 = r4.getHandler()
                    com.kwad.sdk.feed.widget.k r0 = com.kwad.sdk.feed.widget.k.this
                    com.kwad.sdk.utils.bd r0 = com.kwad.sdk.feed.widget.k.f(r0)
                    r1 = 5000(0x1388, double:2.4703E-320)
                    r4.postDelayed(r0, r1)
                Lbb:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.feed.widget.k.AnonymousClass6.onClick(android.view.View):void");
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.k.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (k.this.p.h() || k.this.p.d()) {
                    k.this.g();
                } else if (k.this.p.g()) {
                    k.this.h();
                }
                if (k.this.getHandler() != null) {
                    k.this.getHandler().removeCallbacks(k.this.H);
                    k.this.getHandler().postDelayed(k.this.H, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.k.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                k.this.p();
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.feed.widget.k.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (k.this.v == 100) {
                    k.this.o();
                } else if (k.this.v == 101) {
                    k.this.p();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.u.o();
        this.l.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ksad_video_player_pause_btn));
        this.n.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ksad_video_player_pause_center));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.u.n();
        this.n.setVisibility(0);
        this.n.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ksad_video_player_play_center));
        this.l.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ksad_video_player_play_btn));
    }

    private boolean i() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime - this.y;
        if (j > 888) {
            this.y = jElapsedRealtime;
        }
        return j > 888;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.D != null) {
            this.D.a();
        }
        if ((this.v == 100) && i()) {
            this.q.setVisibility(0);
            this.r.setText(this.c.adBaseInfo.adDescription);
            boolean z = com.kwad.sdk.core.response.a.a.f(this.c) > com.kwad.sdk.core.response.a.a.e(this.c);
            this.w = af.a(getContext());
            this.x = af.e(getContext());
            if (z) {
                af.d(getContext());
            } else {
                af.c(getContext());
            }
            ViewGroup viewGroup = (ViewGroup) this.s.getParent();
            this.t = viewGroup;
            if (viewGroup != null) {
                viewGroup.removeView(this.s);
                this.E = new View(this.s.getContext());
                this.E.setLayoutParams(new ViewGroup.LayoutParams(this.s.getWidth(), this.s.getHeight()));
                viewGroup.addView(this.E);
            }
            af.a(getContext(), false);
            Context contextA = bb.a(this);
            if (contextA instanceof Activity) {
                ViewGroup viewGroup2 = (ViewGroup) ((Activity) contextA).getWindow().getDecorView();
                this.s.setLayoutParams(new ViewGroup.LayoutParams(viewGroup2.getWidth(), viewGroup2.getHeight()));
                if (z && viewGroup2.getWidth() != 0) {
                    this.a.setRatio(viewGroup2.getHeight() / viewGroup2.getWidth());
                }
                viewGroup2.addView(this.s, new FrameLayout.LayoutParams(-1, -1));
                setUIWithStateAndMode(101);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if ((this.v == 101) & i()) {
            this.q.setVisibility(8);
            if (this.w) {
                af.a(getContext());
            } else {
                af.b(getContext());
            }
            if (this.x) {
                af.d(getContext());
            } else {
                af.c(getContext());
            }
            af.a(getContext(), true);
            ViewGroup viewGroup = (ViewGroup) this.s.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.s);
            }
            this.s.setLayoutParams(new ViewGroup.LayoutParams(this.t.getWidth(), this.t.getHeight()));
            this.a.setRatio(0.56f);
            if (this.E != null) {
                this.t.removeView(this.E);
                this.E = null;
            }
            this.t.addView(this.s, new FrameLayout.LayoutParams(-1, -2));
            this.s.requestLayout();
            setUIWithStateAndMode(100);
        }
        if (this.D != null) {
            this.D.b();
        }
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout, com.kwad.sdk.widget.j
    public void a(View view) {
        super.a(view);
    }

    public void a(@NonNull KsAdVideoPlayConfig ksAdVideoPlayConfig, com.kwad.sdk.core.download.a.b bVar) {
        this.F = bVar;
        com.kwad.sdk.core.response.model.c cVarAa = com.kwad.sdk.core.response.a.a.aa(this.c);
        this.C = ksAdVideoPlayConfig;
        String strA = cVarAa.a();
        if (TextUtils.isEmpty(strA)) {
            this.f.setVisibility(8);
        } else {
            this.f.setImageDrawable(null);
            KSImageLoader.loadImage(this.f, strA, this.b);
            this.f.setVisibility(0);
        }
        this.g = com.kwad.sdk.core.response.a.a.S(this.c);
        String strA2 = com.kwad.sdk.core.response.a.a.a(this.c);
        if (TextUtils.isEmpty(strA2)) {
            return;
        }
        this.p = AdVideoPlayerViewCache.a().b(strA2);
        if (this.p == null) {
            this.p = new com.kwad.sdk.core.video.videoview.b(getContext());
            com.kwad.sdk.core.response.a.a.l(this.c);
            this.p.a(new c.a(this.b).a(this.b.mVideoPlayerStatus).a(com.kwad.sdk.core.response.a.d.m(this.b)).b(com.kwad.sdk.core.response.a.f.b(com.kwad.sdk.core.response.a.d.k(this.b))).a(new com.kwad.sdk.contentalliance.detail.video.b(this.b, System.currentTimeMillis())).a(), (Map<String, String>) null);
            this.p.setVideoSoundEnable(ksAdVideoPlayConfig.isVideoSoundEnable());
            this.u = new j(this.e, this.b, this.p);
            this.u.setDataFlowAutoStart(ksAdVideoPlayConfig.isDataFlowAutoStart());
            this.p.setController(this.u);
            this.u.setAutoRelease(false);
        } else {
            if (this.p.getTag() != null) {
                try {
                    this.g = (List) this.p.getTag();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.u = (j) this.p.getController();
            this.u.setAutoRelease(false);
            this.u.getAdTemplate().mAdWebVideoPageShowing = true;
        }
        this.p.setVideoSoundEnable(ksAdVideoPlayConfig.isVideoSoundEnable());
        if (this.p.getParent() != null) {
            ((ViewGroup) this.p.getParent()).removeView(this.p);
        }
        if (this.a.getTag() != null) {
            this.a.removeView((View) this.a.getTag());
            this.a.setTag(null);
        }
        this.a.addView(this.p);
        this.a.setTag(this.p);
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        if (this.c.adConversionInfo.h5Type == 1) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.j.setText(this.c.adBaseInfo.adDescription);
        }
        a(this.u, this.p);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        super.a(adTemplate);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        this.z = new NestedScrollingChildHelper(this);
        this.a = (RatioFrameLayout) findViewById(R.id.ksad_video_container);
        this.a.setRatio(0.56f);
        this.f = (ImageView) findViewById(R.id.ksad_video_first_frame_container);
        this.j = (TextView) findViewById(R.id.ksad_video_text_below);
        this.k = (ScaleAnimSeekBar) findViewById(R.id.ksad_web_video_seek_bar);
        this.k.setMaxProgress(100);
        this.k.setMinProgress(0);
        this.h = (TextView) findViewById(R.id.ksad_video_control_play_total);
        this.i = (TextView) findViewById(R.id.ksad_video_control_play_duration);
        this.l = (ImageView) findViewById(R.id.ksad_video_control_play_button);
        this.n = (ImageView) findViewById(R.id.ksad_video_control_play_status);
        this.m = (ImageView) findViewById(R.id.ksad_video_control_fullscreen);
        this.o = (ViewGroup) findViewById(R.id.ksad_video_control_container);
        this.s = (ViewGroup) findViewById(R.id.ksad_feed_video_container);
        this.q = (ViewGroup) findViewById(R.id.ksad_video_control_fullscreen_container);
        this.r = (TextView) findViewById(R.id.ksad_video_control_fullscreen_title);
    }

    public boolean d() {
        if (this.v != 101) {
            return false;
        }
        p();
        return true;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.z.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.z.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.z.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.z.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.core.j.d
    public void e() {
        super.e();
        if (this.p == null || this.p.g()) {
            return;
        }
        g();
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.H);
            getHandler().postDelayed(this.H, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }
    }

    @Override // com.kwad.sdk.feed.widget.base.a, com.kwad.sdk.core.j.d
    public void f() {
        super.f();
        if (this.p != null) {
            h();
            if (getHandler() != null) {
                getHandler().removeCallbacks(this.H);
                getHandler().postDelayed(this.H, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        }
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected int getLayoutId() {
        return R.layout.ksad_feed_video;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.z.hasNestedScrollingParent();
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.z.isNestedScrollingEnabled();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.z.setNestedScrollingEnabled(z);
    }

    public void setOnEndBtnClickListener(View.OnClickListener onClickListener) {
        this.B = onClickListener;
    }

    protected void setUIWithStateAndMode(int i) {
        ImageView imageView;
        Resources resources;
        int i2;
        if (i == 101) {
            imageView = this.m;
            resources = getContext().getResources();
            i2 = R.drawable.ksad_video_player_exit_fullscreen_btn;
        } else {
            imageView = this.m;
            resources = getContext().getResources();
            i2 = R.drawable.ksad_video_player_fullscreen_btn;
        }
        imageView.setImageDrawable(resources.getDrawable(i2));
        this.v = i;
    }

    public void setWindowFullScreenListener(a aVar) {
        this.D = aVar;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.z.startNestedScroll(i);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.z.stopNestedScroll();
    }
}
