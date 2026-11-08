package com.sigmob.sdk.nativead;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.ClickCommon;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.models.PlacementType;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.mraid.CloseableLayout;
import com.sigmob.sdk.mraid.MraidWebView;
import com.sigmob.sdk.mraid.c;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class o extends com.sigmob.sdk.base.common.g {
    private static SigNativeAdVideo h;
    public final long g;
    private ViewAttr i;
    private ViewAttr j;
    private RelativeLayout k;
    private FrameLayout l;
    private Activity m;
    private BaseAdUnit n;
    private b o;
    private com.sigmob.sdk.mraid.c p;
    private View q;
    private s r;
    private APKStatusBroadcastReceiver s;
    private ImageView t;
    private com.sigmob.sdk.base.views.e u;
    private boolean v;
    private boolean w;

    public o(Activity activity, BaseAdUnit baseAdUnit, Bundle bundle, Bundle bundle2, String str, com.sigmob.sdk.base.common.h hVar) {
        super(activity, str, hVar);
        this.g = 300L;
        this.v = false;
        this.m = activity;
        this.n = baseAdUnit;
        this.o = (b) this.n.getAdConfig();
        this.o.a(activity, this.n, (e.b) null);
        this.i = (ViewAttr) bundle.getParcelable("attr");
        h().a(1);
        int iA = com.sigmob.sdk.base.b.a();
        if (iA != 0) {
            k().getTheme().applyStyle(iA, true);
        }
    }

    private void a(Context context) {
        if (this.t != null) {
            return;
        }
        this.t = new ImageView(context);
        this.t.setImageBitmap(com.sigmob.sdk.base.views.d.CLOSE.a());
        this.t.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.t.setImageAlpha(127);
        this.t.setClickable(true);
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.nativead.o.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                o.this.h().onBackPressed();
            }
        });
        int iB = com.sigmob.sdk.common.utils.c.b(20.0f, this.b);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iB, iB);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        int i = iB / 2;
        layoutParams.setMargins(i, i, 0, 0);
        if (this.k != null) {
            this.k.addView(this.t, layoutParams);
        }
    }

    private void a(Context context, k.a aVar) {
        this.s = new APKStatusBroadcastReceiver(aVar, this.n.getUuid());
        this.s.a(this.s, context.getApplicationContext());
    }

    public static void a(SigAdView sigAdView) {
        if (sigAdView instanceof SigNativeAdVideo) {
            h = (SigNativeAdVideo) sigAdView;
        }
    }

    private int n() {
        DisplayMetrics displayMetricsAi = com.sigmob.sdk.common.a.n().ai();
        return Math.min((int) (Math.min(displayMetricsAi.widthPixels, displayMetricsAi.heightPixels) / this.n.getAdPercent()), Math.max(displayMetricsAi.heightPixels, displayMetricsAi.widthPixels) / 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.t != null) {
            this.t.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.t == null) {
            a(k());
        }
        this.t.setVisibility(0);
    }

    private void q() {
        this.w = true;
        if (h == null) {
            h().a();
            return;
        }
        new v(this.l, this.j, this.i, 300L).a();
        this.k.setVisibility(8);
        this.l.postDelayed(new Runnable() { // from class: com.sigmob.sdk.nativead.o.9
            @Override // java.lang.Runnable
            public void run() {
                if (o.h != null) {
                    o.h.setUIStyle(f.PREVIEW);
                }
                o.this.h().a();
            }
        }, 300L);
    }

    public com.sigmob.sdk.base.common.v a() {
        com.sigmob.sdk.base.common.v sessionManager = this.n.getSessionManager();
        if (sessionManager != null) {
            return sessionManager;
        }
        e eVar = new e(this.b.getApplicationContext());
        eVar.a(this.b, this.n);
        return eVar;
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Configuration configuration) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Bundle bundle) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void b() {
        j().removeAllViews();
        h().onSetContentView(j());
        this.n.getClickCommon().click_scene = "template";
        this.k = new RelativeLayout(k());
        this.k.setBackgroundColor(-1);
        this.q = l();
        if (this.p != null) {
            this.p.a(k());
        }
        this.k.addView(this.q, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (h != null) {
            this.r = h.getNativeAdUnit();
            if (this.r != null) {
                layoutParams.setMargins(0, n(), 0, 0);
            }
        }
        j().addView(this.k, layoutParams);
        if (h != null) {
            this.l = new FrameLayout(k());
            h.setBackClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.nativead.o.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    o.this.h().onBackPressed();
                }
            });
            j().addView(this.l, new RelativeLayout.LayoutParams(-1, n()));
            h.getSigVideoAdController().c();
            this.l.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.sigmob.sdk.nativead.o.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    o.this.l.getViewTreeObserver().removeOnPreDrawListener(this);
                    o.h.setUIStyle(f.DETAIL_PAGE);
                    ViewGroup videoContainer = o.h.getVideoContainer();
                    if (videoContainer != null) {
                        com.sigmob.sdk.common.utils.r.a(videoContainer);
                        o.this.l.addView(videoContainer, new FrameLayout.LayoutParams(-1, -1));
                    }
                    o.this.j = new ViewAttr();
                    int[] iArr = new int[2];
                    o.this.l.getLocationOnScreen(iArr);
                    o.this.j.a(iArr[0]);
                    o.this.j.b(0);
                    o.this.j.c(o.this.l.getMeasuredWidth());
                    o.this.j.d(o.this.l.getMeasuredHeight());
                    new v(o.this.l, o.this.i, o.this.j, 300L).a();
                    o.this.i.b(o.this.i.b() - iArr[1]);
                    if (o.this.k != null) {
                        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                        alphaAnimation.setDuration(300L);
                        o.this.k.setAnimation(alphaAnimation);
                        alphaAnimation.start();
                    }
                    return true;
                }
            });
        }
        a(i(), new k.a() { // from class: com.sigmob.sdk.nativead.o.3
            @Override // com.sigmob.sdk.base.common.k.a
            public void a(boolean z) {
                o.this.p.a(z);
            }

            @Override // com.sigmob.sdk.base.common.k.a
            public void b(boolean z) {
                o.this.p.b(z);
            }

            @Override // com.sigmob.sdk.base.common.k.a
            public void c(boolean z) {
            }

            @Override // com.sigmob.sdk.base.common.k.a
            public void d(boolean z) {
                o.this.p.c(z);
            }
        });
        a(IntentActions.ACTION_NATIVE_TEMPLIE_SHOW, 100);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void c() {
        i sigVideoAdController;
        if (h == null || this.w || (sigVideoAdController = h.getSigVideoAdController()) == null) {
            return;
        }
        sigVideoAdController.a();
    }

    @Override // com.sigmob.sdk.base.common.g
    public void d() {
        i sigVideoAdController;
        if (h == null || (sigVideoAdController = h.getSigVideoAdController()) == null) {
            return;
        }
        sigVideoAdController.c();
    }

    @Override // com.sigmob.sdk.base.common.g
    public void e() {
        if (this.s != null) {
            this.s.a(this.s);
            this.s = null;
        }
        if (this.p != null) {
            this.p.k();
            this.p = null;
        }
        if (h != null) {
            h.setBackClickListener(null);
            h = null;
        }
        a(IntentActions.ACTION_NATIVE_TEMPLE_DISMISS);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void f() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public boolean g() {
        if (h != null && h.b()) {
            return false;
        }
        q();
        return false;
    }

    public View l() {
        if (this.p == null) {
            this.p = new com.sigmob.sdk.mraid.c(this.b, this.n, PlacementType.INTERSTITIAL);
        }
        this.p.a(new c.a() { // from class: com.sigmob.sdk.nativead.o.5
            @Override // com.sigmob.sdk.mraid.c.a
            public void a() {
                SigmobLog.d("SigNativeAdLandViewController onEndCardShow()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(float f) {
                SigmobLog.d("SigNativeAdLandViewController onReward()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(int i, int i2, int i3, int i4, CloseableLayout.a aVar, boolean z) {
                SigmobLog.i("SigNativeAdLandViewController onResize()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(View view) {
                SigmobLog.d("SigNativeAdLandViewController onLoaded()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(SigmobError sigmobError) {
                SigmobLog.i("SigNativeAdLandViewController onRenderProcessGone:" + sigmobError.toString());
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(String str) throws UnsupportedEncodingException {
                boolean z;
                SigmobLog.i("SigNativeAdLandViewController onCompanionClick:" + str);
                boolean z2 = true;
                if (TextUtils.isEmpty(str)) {
                    z = true;
                } else {
                    try {
                        o.this.n.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
                        o.this.n.getClickCommon().is_final_click = true;
                        JSONObject jSONObject = new JSONObject(str);
                        int iOptInt = jSONObject.optInt("type");
                        o.this.p.a(String.valueOf(jSONObject.optInt("x")), String.valueOf(jSONObject.optInt("y")));
                        if (iOptInt != 1) {
                            o.this.a().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
                        } else {
                            z2 = false;
                        }
                        z = z2;
                    } catch (Exception unused) {
                        o.this.p.a(Constants.FAIL, Constants.FAIL);
                        o.this.a().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
                    }
                }
                o.this.o.b(o.this.n, o.this.b, (String) null, com.sigmob.sdk.base.a.COMPANION, o.this.p.a(), z);
                o.this.a(IntentActions.ACTION_NATIVE_TEMPLE_CLICK);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(URI uri, com.sigmob.sdk.videoAd.f fVar, String str) throws UnsupportedEncodingException {
                boolean z;
                boolean z2;
                int iOptInt;
                int iOptInt2;
                int iOptInt3;
                boolean zOptBoolean;
                SigmobLog.i("SigNativeAdLandViewController  onOpen:" + uri + "======" + fVar + "=====" + str);
                boolean z3 = false;
                if (TextUtils.isEmpty(str)) {
                    o.this.p.a(Constants.FAIL, Constants.FAIL);
                    z2 = true;
                    z = false;
                } else {
                    try {
                        o.this.n.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
                        o.this.n.getClickCommon().is_final_click = true;
                        JSONObject jSONObject = new JSONObject(str);
                        iOptInt = jSONObject.optInt("type");
                        iOptInt2 = jSONObject.optInt("x");
                        iOptInt3 = jSONObject.optInt("y");
                        zOptBoolean = jSONObject.optBoolean("disable_landing");
                    } catch (Exception unused) {
                    }
                    try {
                        o.this.p.a(String.valueOf(iOptInt2), String.valueOf(iOptInt3));
                        z = zOptBoolean;
                        z2 = iOptInt != 1;
                    } catch (Exception unused2) {
                        z3 = zOptBoolean;
                        o.this.p.a(Constants.FAIL, Constants.FAIL);
                        z = z3;
                        z2 = true;
                        o.this.o.a(o.this.n, o.this.b, uri.toString(), com.sigmob.sdk.base.a.TEMPLATE, o.this.p.a(), z2, z);
                        o.this.a(IntentActions.ACTION_NATIVE_TEMPLE_CLICK);
                    }
                }
                o.this.o.a(o.this.n, o.this.b, uri.toString(), com.sigmob.sdk.base.a.TEMPLATE, o.this.p.a(), z2, z);
                o.this.a(IntentActions.ACTION_NATIVE_TEMPLE_CLICK);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(boolean z) {
                SigmobLog.d("SigNativeAdLandViewController onMute()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b() {
                SigmobLog.d("SigNativeAdLandViewController onShowSkipTime()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b(float f) {
                SigmobLog.d("SigNativeAdLandViewController onSkip()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void c() {
                SigmobLog.i("SigNativeAdLandViewController onFailedToLoad()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void d() {
                SigmobLog.i("SigNativeAdLandViewController onUnload()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void e() {
                SigmobLog.i("SigNativeAdLandViewController onOpenFourElements()");
                if (o.this.u == null) {
                    o.this.u = new com.sigmob.sdk.base.views.e(o.this.k(), o.this.n);
                    o.this.u.a(new e.b() { // from class: com.sigmob.sdk.nativead.o.5.1
                        @Override // com.sigmob.sdk.base.views.e.b
                        public void a() {
                            if (o.this.u != null) {
                                o.this.u.dismiss();
                                o.this.u.c();
                                o.this.u = null;
                                o.this.v = false;
                            }
                            o.this.n.getClickCommon().click_scene = "template";
                            o.this.a().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_CLOSE, 0);
                        }

                        @Override // com.sigmob.sdk.base.views.e.b
                        public void a(String str, String str2) {
                            o.this.n.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
                            o.this.n.getClickCommon().click_scene = "appinfo";
                            o.this.n.getClickCommon().is_final_click = true;
                            if (o.this.o != null) {
                                o.this.o.a(o.this.n, o.this.b, str, com.sigmob.sdk.base.a.COMPANION, str2, true);
                                o.this.a(IntentActions.ACTION_NATIVE_TEMPLE_CLICK);
                            }
                        }

                        @Override // com.sigmob.sdk.base.views.e.b
                        public void b() {
                            o.this.a().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_SHOW, 0);
                        }
                    });
                }
                if (o.this.u == null || !o.this.u.a() || o.this.v) {
                    return;
                }
                o.this.n.getClickCommon().click_area = "appinfo";
                o.this.n.getClickCommon().is_final_click = false;
                o.this.a().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
                o.this.u.show();
                o.this.v = true;
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void f() {
                SigmobLog.i("SigNativeAdLandViewController onClose()");
                o.this.h().onBackPressed();
            }
        });
        if (!TextUtils.isEmpty(this.n.getHtmlUrl())) {
            this.p.b(this.n.getHtmlUrl(), new c.b() { // from class: com.sigmob.sdk.nativead.o.6
                @Override // com.sigmob.sdk.mraid.c.b
                public void a(MraidWebView mraidWebView, com.sigmob.sdk.base.common.p pVar) {
                }
            });
        } else if (!TextUtils.isEmpty(this.n.getHtmlData())) {
            this.p.a(this.n.getHtmlData(), new c.b() { // from class: com.sigmob.sdk.nativead.o.7
                @Override // com.sigmob.sdk.mraid.c.b
                public void a(MraidWebView mraidWebView, com.sigmob.sdk.base.common.p pVar) {
                }
            });
        }
        this.p.a(new c.e() { // from class: com.sigmob.sdk.nativead.o.8
            @Override // com.sigmob.sdk.mraid.c.e
            public void a(boolean z) {
                if (z) {
                    o.this.o();
                } else if (o.h == null) {
                    o.this.p();
                }
            }
        });
        return this.p.o();
    }
}
