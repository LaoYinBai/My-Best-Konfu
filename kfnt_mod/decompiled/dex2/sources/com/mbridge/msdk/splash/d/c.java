package com.mbridge.msdk.splash.d;

import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.ad;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.MBSplashLoadListener;
import com.mbridge.msdk.out.MBSplashShowListener;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.ZoomOutTypeEnum;
import com.mbridge.msdk.splash.view.MBSplashPopView;
import com.mbridge.msdk.splash.view.MBSplashView;
import com.mbridge.msdk.splash.view.MBSplashWebview;

/* compiled from: SplashProvider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static String b = "SplashProvider";
    private Activity A;
    private boolean B;
    private CampaignEx C;
    private MBSplashPopView D;
    private String c;
    private String d;
    private MBridgeIds e;
    private long g;
    private com.mbridge.msdk.splash.c.b h;
    private com.mbridge.msdk.splash.c.c i;
    private b j;
    private MBSplashShowListener k;
    private d l;
    private MBSplashView m;
    private MBSplashWebview n;
    private ViewGroup o;
    private View p;
    private com.mbridge.msdk.b.d q;
    private boolean r;
    private ViewGroup s;
    private com.mbridge.msdk.b.c t;
    private boolean u;
    private int f = 5;
    private int v = 1;
    private int w = o.w(com.mbridge.msdk.foundation.controller.a.f().j());
    private int x = o.x(com.mbridge.msdk.foundation.controller.a.f().j());
    private Object y = new Object();
    private Object z = new Object();
    public boolean a = false;
    private boolean E = false;

    public final void a(ViewGroup viewGroup) {
        this.s = viewGroup;
    }

    public final boolean a() {
        return this.r;
    }

    public final void a(boolean z) {
        this.r = z;
    }

    public final void a(int i) {
        this.v = i;
    }

    public c(Activity activity, String str, String str2) {
        this.d = str;
        this.c = str2;
        this.e = new MBridgeIds(str, str2);
        this.A = activity;
        if (this.i == null) {
            if (activity != null) {
                this.i = new com.mbridge.msdk.splash.c.c(activity, this.d, this.c);
            } else {
                this.i = new com.mbridge.msdk.splash.c.c(com.mbridge.msdk.foundation.controller.a.f().j(), this.d, this.c);
            }
        }
        if (this.n == null) {
            if (activity != null) {
                this.n = new MBSplashWebview(activity);
            } else {
                this.n = new MBSplashWebview(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            this.n.setWebViewClient(new com.mbridge.msdk.splash.view.a(this.c, this.i.a()));
        }
        if (this.m == null) {
            if (activity != null) {
                this.m = new MBSplashView(activity);
            } else {
                this.m = new MBSplashView(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            this.m.setSplashWebview(this.n);
        }
        if (this.t == null) {
            this.t = new com.mbridge.msdk.b.c();
        }
        this.t.a(com.mbridge.msdk.foundation.controller.a.f().j(), com.mbridge.msdk.foundation.controller.a.f().k(), com.mbridge.msdk.foundation.controller.a.f().l(), this.c);
    }

    public final void a(long j) {
        this.g = j;
    }

    public final long b() {
        return this.g;
    }

    public final boolean c() {
        return this.u;
    }

    public final void a(MBSplashLoadListener mBSplashLoadListener) {
        if (this.j == null) {
            this.j = new b(this, this.e);
        }
        this.j.a(mBSplashLoadListener);
    }

    public final void a(MBSplashShowListener mBSplashShowListener) {
        this.k = mBSplashShowListener;
    }

    public final void a(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            if (this.j != null) {
                this.j.a("token is null or empty", 1);
                return;
            }
            return;
        }
        a(str, 1);
    }

    public final void b(String str) throws Throwable {
        a(str, 1);
    }

    public final void a(String str, ViewGroup viewGroup) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            if (this.j != null) {
                this.j.a("token is null or empty", 1);
                return;
            }
            return;
        }
        b(str, viewGroup);
    }

    public final void b(String str, ViewGroup viewGroup) throws Throwable {
        this.o = viewGroup;
        if (this.m != null) {
            this.m.setDevContainer(viewGroup);
        }
        a(str, 2);
    }

    public final boolean c(String str) {
        return com.mbridge.msdk.splash.c.a.a(this.m, this.d, this.c, str, this.u, this.f, false, true) != null;
    }

    public final void b(boolean z) {
        this.u = z;
    }

    public final void b(int i) {
        this.f = i;
    }

    public final int d() {
        return this.f;
    }

    public final void a(int i, int i2) {
        b(i2, i);
    }

    public final String e() {
        return !this.a ? this.h != null ? this.h.a() : "" : this.i != null ? this.i.b() : "";
    }

    public final void c(String str, ViewGroup viewGroup) {
        if (TextUtils.isEmpty(str)) {
            if (this.k != null) {
                this.k.onShowFailed(this.e, "token is null or empty");
                return;
            }
            return;
        }
        d(str, viewGroup);
    }

    public final void d(String str, ViewGroup viewGroup) {
        this.o = viewGroup;
        if (this.m != null) {
            this.m.setDevContainer(viewGroup);
        }
        CampaignEx campaignExA = com.mbridge.msdk.splash.c.a.a(this.m, this.d, this.c, str, this.u, this.f, true, false);
        if (campaignExA != null) {
            if (this.q == null) {
                this.q = com.mbridge.msdk.b.b.a().d(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            }
            this.l = new d(this, this.k, this.q.e(), campaignExA);
            s.a(b, "show start");
            if (this.w == 0 || this.x == 0) {
                if (this.l != null) {
                    this.l.a(this.e, "width or height is 0  or width or height is too small");
                    return;
                }
                return;
            } else {
                if (this.f < 2 || this.f > 10) {
                    if (this.l != null) {
                        this.l.a(this.e, "countDownTime must in 2 - 10 ,but now is " + this.f);
                        return;
                    }
                    return;
                }
                a(campaignExA, this.q.q(), false);
                return;
            }
        }
        if (this.k != null) {
            this.k.onShowFailed(this.e, "campaignEx is vali");
        }
    }

    final void a(CampaignEx campaignEx, int i, boolean z) {
        if (campaignEx != null && z) {
            if (this.q == null) {
                this.q = com.mbridge.msdk.b.b.a().d(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            }
            this.l = new d(this, this.k, this.q.e(), campaignEx);
        }
        this.C = campaignEx;
        if (this.o == null) {
            if (this.l != null) {
                this.l.a(this.e, "container is null");
            }
        } else {
            if (this.i == null) {
                this.i = new com.mbridge.msdk.splash.c.c(this.o.getContext(), this.d, this.c);
            }
            b(campaignEx, i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final CampaignEx campaignEx, final int i, final boolean z) {
        if (!com.mbridge.msdk.splash.c.a.a(this.m, campaignEx)) {
            if (i > 0) {
                this.i.a.postDelayed(new Runnable() { // from class: com.mbridge.msdk.splash.d.c.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.this.b(campaignEx, i - 1, z);
                    }
                }, 1L);
                return;
            } else {
                if (this.l != null) {
                    this.l.a(this.e, "campaignEx is not ready");
                    return;
                }
                return;
            }
        }
        d(true);
        ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
        layoutParams.height = -1;
        layoutParams.width = -1;
        this.o.setLayoutParams(layoutParams);
        this.o.removeAllViews();
        this.i.a(this.f);
        this.i.a(this.s);
        this.i.a(this.l);
        s.d(b, "start show process");
        if (this.o != null) {
            this.o.removeAllViews();
            ad.a(this.m);
            this.o.addView(this.m);
        }
        this.i.a(this.u);
        this.i.a(campaignEx, this.m);
    }

    private void a(String str, int i) throws Throwable {
        synchronized (this.y) {
            if (this.r) {
                if (this.j != null) {
                    this.j.a("current unit is loading", i);
                    this.r = true;
                }
                return;
            }
            this.r = true;
            if (this.f < 2 || this.f > 10) {
                if (this.j != null) {
                    this.j.a("countDownTime must in 2 - 10 ,but now is " + this.f, i);
                    return;
                }
                return;
            }
            if (this.w == 0 || this.x == 0) {
                if (this.j != null) {
                    this.j.a("width or height is 0  or width or height is too small", i);
                    return;
                }
                return;
            }
            this.m.clearResState();
            this.q = com.mbridge.msdk.b.b.a().d(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            if (this.h == null) {
                this.h = new com.mbridge.msdk.splash.c.b(this.d, this.c, this.g * 1000);
            }
            if (this.j != null) {
                this.j.a(str);
                this.h.a(this.j);
            }
            this.m.resetLoadState();
            this.h.a(this.f);
            this.h.a(this.m);
            this.h.a(this.q);
            this.h.a(this.w, this.x);
            this.h.a(this.u);
            this.h.a(str, i);
        }
    }

    public final void a(View view, RelativeLayout.LayoutParams layoutParams) {
        this.p = view;
        if (view != null) {
            b(layoutParams.width, layoutParams.height);
            if (this.m != null) {
                this.m.setIconVg(view, layoutParams);
            }
        }
    }

    public final void c(boolean z) {
        this.B = z;
    }

    public final ViewGroup a(ZoomOutTypeEnum zoomOutTypeEnum) {
        this.D = new MBSplashPopView(com.mbridge.msdk.foundation.controller.a.f().j(), new MBSplashPopView.a(this.d, this.c, zoomOutTypeEnum.getIndex(), this.C), this.l);
        return this.D;
    }

    public final void f() {
        this.E = true;
        if (this.D != null) {
            this.D.startCountDown();
        }
    }

    public final void g() {
        this.E = false;
        if (this.k != null) {
            this.k.onZoomOutPlayFinish(new MBridgeIds(this.d, this.c));
        }
        if (this.D != null) {
            this.D.release();
        }
    }

    private void b(int i, int i2) {
        int iW = o.w(com.mbridge.msdk.foundation.controller.a.f().j());
        int iX = o.x(com.mbridge.msdk.foundation.controller.a.f().j());
        if (this.v == 1) {
            if (iX >= i2 * 4) {
                this.x = iX - i2;
                this.w = iW;
                return;
            } else {
                this.x = 0;
                this.w = 0;
                return;
            }
        }
        if (this.v == 2) {
            if (iW >= i * 4) {
                this.w = iW - i;
                this.x = iX;
            } else {
                this.x = 0;
                this.w = 0;
            }
        }
    }

    public final void h() {
        if (this.i != null) {
            this.i.d();
        }
        if (this.D == null || !this.E) {
            return;
        }
        this.D.reStartCountDown();
    }

    public final void d(final boolean z) {
        if (this.n != null) {
            this.n.setOnTouchListener(new View.OnTouchListener() { // from class: com.mbridge.msdk.splash.d.c.2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return !z;
                }
            });
        }
        if (this.m != null) {
            this.m.setOnTouchListener(new View.OnTouchListener() { // from class: com.mbridge.msdk.splash.d.c.3
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return !z;
                }
            });
        }
    }

    public final void i() {
        if (this.i != null) {
            this.i.e();
        }
        if (this.D == null || !this.E) {
            return;
        }
        this.D.pauseCountDown();
    }

    public final void j() {
        this.C = null;
        if (this.k != null) {
            this.k = null;
        }
        if (this.j != null) {
            this.j = null;
        }
        if (this.l != null) {
            this.l = null;
        }
        if (this.h != null) {
            this.h.b();
        }
        if (this.i != null) {
            this.i.c();
        }
        if (this.A != null) {
            this.A = null;
        }
    }
}
