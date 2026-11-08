package com.sigmob.sdk.splash;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.a.b;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.models.AdStatus;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.mta.PointEntitySigmobError;
import com.sigmob.sdk.splash.f;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class g implements b.a, f.a {
    private final LoadAdRequest a;
    private final WeakReference<Activity> c;
    private Handler d;
    private f e;
    private Context f;
    private b g;
    private int h;
    private AdStatus i;
    private SplashAdView j;
    private BaseAdUnit k;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private final Runnable b = new Runnable() { // from class: com.sigmob.sdk.splash.g.1
        @Override // java.lang.Runnable
        public void run() {
            if (g.this.d != null) {
                g.this.d.removeCallbacksAndMessages(null);
                if (g.this.j == null) {
                    return;
                }
                if (g.this.h <= 0) {
                    g.this.j.setDuration(0);
                    g.this.a((String) null, PointCategory.COMPLETE, g.this.k, g.this.a.getPlacementId());
                    return;
                }
                g.this.j.setDuration(g.this.h);
                g.d(g.this);
                if (g.this.n != 2 && g.this.o > 0 && ((g.this.m - g.this.h) * 100) / g.this.m >= g.this.o) {
                    SigmobLog.e("performAdClick: " + (((g.this.m - g.this.h) * 100) / g.this.m));
                    g.this.j.d();
                }
                g.this.d.postDelayed(g.this.b, 1000L);
            }
        }
    };

    g(Activity activity, LoadAdRequest loadAdRequest, b bVar) {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT >= 23 && (rootWindowInsets = activity.getWindow().getDecorView().getRootWindowInsets()) != null) {
            com.sigmob.sdk.common.a.n().a(rootWindowInsets);
        }
        this.f = activity.getApplicationContext();
        this.c = new WeakReference<>(activity);
        this.i = AdStatus.AdStatusNone;
        this.d = new Handler(Looper.getMainLooper());
        this.a = loadAdRequest;
        this.g = bVar;
        this.e = new f(this);
        if (1 == com.sigmob.sdk.common.a.n().Q().intValue()) {
            return;
        }
        h();
    }

    private void a(final int i, final String str, final String str2, int i2, String str3) {
        t.a(PointCategory.SERVER_ERROR, i2, str3, (BaseAdUnit) null, new t.a() { // from class: com.sigmob.sdk.splash.g.2
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmobError) {
                    PointEntitySigmobError pointEntitySigmobError = (PointEntitySigmobError) obj;
                    pointEntitySigmobError.setRequest_id(str2);
                    pointEntitySigmobError.setPlacement_id(str);
                    pointEntitySigmobError.setAdtype(String.valueOf(i));
                }
            }
        });
    }

    private void a(int i, String str, boolean z) {
        this.i = AdStatus.AdStatusNone;
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
        if (!j() && this.j != null) {
            this.j.a();
        }
        if (this.g != null) {
            if (z) {
                this.g.onSplashAdLoadFail(i, str);
            } else {
                this.g.onSplashAdFailToPresent(i, str);
            }
            this.g = null;
        }
        if (this.e != null) {
            this.e.c(this.k);
        }
        com.sigmob.sdk.base.common.d.f();
        com.sigmob.sdk.base.common.d.g();
    }

    private void a(String str, BaseAdUnit baseAdUnit, final String str2, final int i, String str3) {
        t.a(str, i, str3, baseAdUnit, new t.a() { // from class: com.sigmob.sdk.splash.g.3
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmobError) {
                    PointEntitySigmobError pointEntitySigmobError = (PointEntitySigmobError) obj;
                    pointEntitySigmobError.setPlacement_id(str2);
                    pointEntitySigmobError.setAdtype(String.valueOf(2));
                    if (i == SigmobError.REQUEST_AD_SLOT_NOT_MATCH__AD_TYPE.getErrorCode()) {
                        pointEntitySigmobError.setUrl(g.this.k.getMaterial().image_src);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, BaseAdUnit baseAdUnit, String str3) {
        a(str, str2, baseAdUnit, str3, (String) null);
    }

    private void a(String str, String str2, BaseAdUnit baseAdUnit, final String str3, final String str4) {
        t.a(str2, str, baseAdUnit, new t.a() { // from class: com.sigmob.sdk.splash.g.4
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setPlacement_id(str3);
                    pointEntitySigmob.setAdtype(String.valueOf(2));
                    pointEntitySigmob.setRequest_id(str4);
                }
            }
        });
    }

    private boolean a(Context context, BaseAdUnit baseAdUnit) {
        if (baseAdUnit == null) {
            return false;
        }
        this.j = new SplashAdView(context);
        if (this.j == null) {
            return false;
        }
        this.j.b();
        this.j.setShowAppLogo(this.l != 0);
        return this.j.a(baseAdUnit);
    }

    static /* synthetic */ int d(g gVar) {
        int i = gVar.h;
        gVar.h = i - 1;
        return i;
    }

    private Activity i() {
        if (this.c != null) {
            return this.c.get();
        }
        return null;
    }

    private boolean j() {
        Map<String, Object> options = this.a.getOptions();
        if (options == null || options.get(LoadAdRequest.SPLASH_DISABLE_AD_HIDE) == null) {
            return false;
        }
        try {
            return ((Boolean) options.get(LoadAdRequest.SPLASH_DISABLE_AD_HIDE)).booleanValue();
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return false;
        }
    }

    private void k() {
        if (this.e != null) {
            if (this.e.e != null) {
                this.e.e.b(this.f, 0, this.k);
            }
            this.e.c(this.k);
        }
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
        if (!j() && this.j != null) {
            this.j.a();
        }
        this.g = null;
        this.j = null;
        com.sigmob.sdk.base.common.d.f();
        com.sigmob.sdk.base.common.d.g();
    }

    @Override // com.sigmob.sdk.base.common.k.c
    public void a() {
    }

    @Override // com.sigmob.sdk.base.a.b.a
    public void a(int i, String str, String str2, LoadAdRequest loadAdRequest) {
        a(Constants.FAIL, PointCategory.RESPOND, (BaseAdUnit) null, this.a.getPlacementId(), str2);
        a(PointCategory.REQUEST, (BaseAdUnit) null, loadAdRequest.getPlacementId(), i, str);
        if (i != SigmobError.ERROR_SIGMOB_NETWORK.getErrorCode()) {
            a(loadAdRequest.getAdType(), loadAdRequest.getPlacementId(), str2, i, str);
        }
        a(i, str, true);
    }

    public void a(Activity activity) {
        if (this.j == null || activity != i()) {
            return;
        }
        this.j.e();
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    public void a(ViewGroup viewGroup) {
        if (viewGroup == null || this.f == null) {
            c();
            return;
        }
        viewGroup.removeAllViews();
        if (!a(viewGroup.getContext(), this.k)) {
            c();
            return;
        }
        viewGroup.addView(this.j, new FrameLayout.LayoutParams(-1, -1));
        this.e.a(this.f, this.k, (Bundle) null);
        this.h = this.e.e.d();
        this.n = this.e.e.f();
        this.o = this.e.e.g();
        this.j.setDuration(this.h);
        if (this.j.c()) {
            return;
        }
        c();
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void a(BaseAdUnit baseAdUnit) {
        SigmobLog.d(" onInterstitialLoadstart");
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void a(BaseAdUnit baseAdUnit, String str) {
        a(SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD.getErrorCode(), str, true);
        a(PointCategory.LOAD, baseAdUnit, (String) null, SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD.getErrorCode(), str);
    }

    @Override // com.sigmob.sdk.base.a.b.a
    public void a(List<BaseAdUnit> list, LoadAdRequest loadAdRequest) {
        if (list == null || list.size() <= 0) {
            a(Constants.FAIL, PointCategory.RESPOND, (BaseAdUnit) null, this.a.getPlacementId(), this.a.getRequestId());
            a(PointCategory.REQUEST, (BaseAdUnit) null, loadAdRequest.getPlacementId(), SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getErrorCode(), SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getMessage());
            a(SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getErrorCode(), SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getMessage(), true);
            return;
        }
        BaseAdUnit baseAdUnit = list.get(0);
        a("1", PointCategory.RESPOND, baseAdUnit, this.a.getPlacementId());
        this.k = baseAdUnit;
        if (this.e == null || this.f == null) {
            return;
        }
        this.e.a(this.f, (Map<String, Object>) null, baseAdUnit);
    }

    protected void a(boolean z) throws Throwable {
        String str;
        if (z) {
            this.a.setRequest_scene_type(com.sigmob.sdk.rewardVideoAd.a.SplashCloseRequest.a().intValue());
            str = PointCategory.PLAY;
        } else {
            this.i = AdStatus.AdStatusLoading;
            Object objD = com.sigmob.sdk.common.utils.d.d(com.sigmob.sdk.common.utils.d.e());
            if (objD instanceof BaseAdUnit) {
                BaseAdUnit baseAdUnit = (BaseAdUnit) objD;
                if (baseAdUnit.getAd() != null) {
                    com.sigmob.sdk.common.utils.d.b(com.sigmob.sdk.common.utils.d.e());
                    this.k = baseAdUnit;
                    if (g() && this.e != null && this.f != null) {
                        this.e.a(this.f, (Map<String, Object>) null, this.k);
                        return;
                    }
                }
            }
            this.a.setRequest_scene_type(com.sigmob.sdk.rewardVideoAd.a.NormalRequest.a().intValue());
            str = PointCategory.INIT;
        }
        a(str, PointCategory.REQUEST, (BaseAdUnit) null, this.a.getPlacementId());
        com.sigmob.sdk.base.a.b.a(SDKConfig.sharedInstance().getAdsUrl(), this.a, this);
    }

    @Override // com.sigmob.sdk.base.common.k.c
    public void b() {
        BaseBroadcastReceiver.a(this.f, this.k.getUuid(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
    }

    public void b(Activity activity) {
        if (this.j == null || activity != i()) {
            return;
        }
        this.j.f();
        if (this.d == null) {
            this.d = new Handler(Looper.getMainLooper());
        }
        this.d.post(this.b);
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void b(BaseAdUnit baseAdUnit) throws Throwable {
        SigmobLog.d(" onInterstitialLoaded");
        if (this.i == AdStatus.AdStatusClose) {
            SigmobLog.d(" next load");
            com.sigmob.sdk.common.utils.d.a(baseAdUnit, com.sigmob.sdk.common.utils.d.e());
        }
        if (this.i != AdStatus.AdStatusLoading) {
            return;
        }
        if (!(1 == com.sigmob.sdk.common.a.n().Q().intValue())) {
            h();
            return;
        }
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
        }
        this.i = AdStatus.AdStatusReady;
        if (this.g != null) {
            this.g.onSplashAdLoadSuccess();
        }
    }

    @Override // com.sigmob.sdk.splash.f.a
    public void c() {
        a(PointCategory.PLAY, this.k, (String) null, SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_RESOURCE.getErrorCode(), "");
        a(SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_RESOURCE.getErrorCode(), SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_RESOURCE.getMessage(), false);
        k();
    }

    public void c(Activity activity) {
        Context context = this.f;
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void c(BaseAdUnit baseAdUnit) {
        this.i = AdStatus.AdStatusPlaying;
        com.sigmob.sdk.base.common.d.b(baseAdUnit);
        if (this.j.getDuration() > 0 && this.j.getDuration() < this.h) {
            this.h = this.j.getDuration();
        }
        if (this.j != null) {
            this.j.setDuration(this.h);
            this.j.setVisibility(0);
        }
        if (this.g != null) {
            this.g.onSplashAdSuccessPresentScreen();
        }
        if (this.d == null) {
            this.d = new Handler(Looper.getMainLooper());
        }
        this.m = this.h;
        this.d.post(this.b);
    }

    @Override // com.sigmob.sdk.splash.f.a
    public void d() {
        if (this.e != null && this.e.e != null) {
            this.e.e.c(this.f, 0, this.k);
        }
        this.j.setDuration(0);
        if (this.g != null) {
            this.g.onSplashAdSkip();
        }
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void d(BaseAdUnit baseAdUnit) {
        this.i = AdStatus.AdStatusClick;
        if (this.g != null) {
            this.g.onSplashAdClicked();
        }
    }

    @Override // com.sigmob.sdk.splash.f.a
    public void e() {
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void e(BaseAdUnit baseAdUnit) throws Throwable {
        if (this.g != null) {
            this.g.onSplashClosed();
        }
        k();
        this.i = AdStatus.AdStatusClose;
        com.sigmob.sdk.base.common.d.c(baseAdUnit);
        a(true);
    }

    @Override // com.sigmob.sdk.splash.f.a
    public void f() {
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean g() {
        /*
            r8 = this;
            r0 = 0
            com.sigmob.sdk.common.models.AdStatus r1 = r8.i     // Catch: java.lang.Exception -> L4e
            com.sigmob.sdk.common.models.AdStatus r2 = com.sigmob.sdk.common.models.AdStatus.AdStatusReady     // Catch: java.lang.Exception -> L4e
            if (r1 == r2) goto Lc
            com.sigmob.sdk.base.models.BaseAdUnit r1 = r8.k     // Catch: java.lang.Exception -> L4e
            if (r1 != 0) goto Lc
            return r0
        Lc:
            java.io.File r1 = new java.io.File     // Catch: java.lang.Exception -> L4e
            com.sigmob.sdk.base.models.BaseAdUnit r2 = r8.k     // Catch: java.lang.Exception -> L4e
            java.lang.String r2 = r2.getSplashFilePath()     // Catch: java.lang.Exception -> L4e
            r1.<init>(r2)     // Catch: java.lang.Exception -> L4e
            boolean r1 = r1.canRead()     // Catch: java.lang.Exception -> L4e
            com.sigmob.sdk.base.models.BaseAdUnit r2 = r8.k     // Catch: java.lang.Exception -> L4e
            com.sigmob.sdk.common.models.sigdsp.pb.Ad r2 = r2.getAd()     // Catch: java.lang.Exception -> L4e
            java.lang.Integer r2 = r2.expired_time     // Catch: java.lang.Exception -> L4e
            int r2 = r2.intValue()     // Catch: java.lang.Exception -> L4e
            r3 = 1
            if (r2 == 0) goto L47
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L4e
            com.sigmob.sdk.base.models.BaseAdUnit r2 = r8.k     // Catch: java.lang.Exception -> L4e
            long r6 = r2.getCreate_time()     // Catch: java.lang.Exception -> L4e
            r2 = 0
            long r4 = r4 - r6
            com.sigmob.sdk.base.models.BaseAdUnit r2 = r8.k     // Catch: java.lang.Exception -> L4e
            java.lang.Integer r2 = r2.getAdExpiredTime()     // Catch: java.lang.Exception -> L4e
            int r2 = r2.intValue()     // Catch: java.lang.Exception -> L4e
            long r6 = (long) r2
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L47
            r2 = 1
            goto L48
        L47:
            r2 = 0
        L48:
            if (r1 == 0) goto L4d
            if (r2 != 0) goto L4d
            r0 = 1
        L4d:
            return r0
        L4e:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.sigmob.logger.SigmobLog.e(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.splash.g.g():boolean");
    }

    public void h() {
        a(PointCategory.PLAY, this.k, (String) null, SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_ORIENTATION.getErrorCode(), "");
        a(SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_ORIENTATION.getErrorCode(), SigmobError.ERROR_SIGMOB_SPLASH_UNSUPPORT_ORIENTATION.getMessage(), true);
        k();
    }
}
