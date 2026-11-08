package com.sigmob.sdk.mraid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.p;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.models.PlacementType;
import com.sigmob.sdk.base.views.VideoButtonWidget;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.mraid.CloseableLayout;
import com.sigmob.sdk.mraid.c;
import com.sigmob.sdk.mraid.l;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i extends com.sigmob.sdk.videoAd.b {
    protected p g;
    private final String h;
    private int i;
    private com.sigmob.sdk.videoAd.a j;
    private int k;
    private boolean l;
    private BaseAdUnit m;
    private c n;
    private VideoButtonWidget o;
    private Integer p;
    private boolean q;
    private boolean r;
    private com.sigmob.sdk.base.views.e s;
    private boolean t;

    public i(Activity activity, BaseAdUnit baseAdUnit, Bundle bundle, Bundle bundle2, String str, com.sigmob.sdk.base.common.h hVar) {
        int i;
        super(activity, str, hVar);
        this.r = false;
        this.t = false;
        this.m = baseAdUnit;
        this.h = bundle.getString("ad_scene");
        this.j = (com.sigmob.sdk.videoAd.a) this.m.getAdConfig();
        this.j.a(k(), this.m, (e.b) null);
        this.k = com.sigmob.sdk.common.a.n().ad();
        switch (this.m.getAd().display_orientation.intValue()) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 6;
                break;
            default:
                i = bundle.getInt(Constants.REQUESTED_ORIENTATION, 3);
                break;
        }
        this.i = i;
        h().a(this.i);
        k().requestWindowFeature(1);
        k().getWindow().addFlags(16778240);
        b(bundle);
        j().setBackgroundColor(0);
    }

    private void a(int i, RelativeLayout.LayoutParams layoutParams) {
        int i2;
        int iB = com.sigmob.sdk.common.utils.c.b(10.0f, this.b);
        switch (i) {
            case 1:
            case 2:
                layoutParams.addRule(10);
                i2 = 9;
                break;
            case 3:
            case 4:
                layoutParams.addRule(10);
                i2 = 11;
                break;
            default:
                return;
        }
        layoutParams.addRule(i2);
        layoutParams.setMargins(iB, iB * 2, iB, iB);
    }

    private void a(Context context, int i) {
        if (this.o != null) {
            return;
        }
        this.o = new VideoButtonWidget(context, this.j.f());
        this.o.setVisibility(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.sigmob.sdk.common.utils.c.b(30.0f, context), com.sigmob.sdk.common.utils.c.b(30.0f, context));
        a(this.j.f(), layoutParams);
        j().addView(this.o, layoutParams);
        this.o.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.mraid.i.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    i.this.r = true;
                    i.this.j.b(i.this.i(), i.this.p != null ? i.this.p.intValue() : -1, i.this.m);
                    i.this.h().a();
                }
                return true;
            }
        });
        this.o.a(this.m);
        String strB = this.j.b();
        if (strB != null) {
            this.o.a(strB);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.o != null) {
            this.o.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.o == null) {
            a(this.b, 0);
        }
        this.o.setVisibility(0);
    }

    public View a() {
        String str = this.a;
        l.a aVarA = str != null ? l.a(str) : null;
        this.n = (aVarA == null || aVarA.c() == null) ? new c(this.b, this.m, PlacementType.INTERSTITIAL) : aVarA.c();
        this.n.a(new c.f() { // from class: com.sigmob.sdk.mraid.i.2
            private boolean b;

            @Override // com.sigmob.sdk.mraid.c.f
            public void a() {
                SigmobLog.d("adImpression() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void a(Integer num) {
                i.this.p = num;
                i.this.g.a(i.this.p.intValue(), i.this.j.d());
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void a(Map<String, String> map) {
                try {
                    map.get(AdDownloadModel.JsonKey.ID);
                    String str2 = map.get("url");
                    if (!map.get("playerHandles").equalsIgnoreCase("false")) {
                        i.this.j.b(i.this.m, i.this.b, str2, com.sigmob.sdk.base.a.ENDCARD, i.this.n.a(), true);
                    }
                    i.this.a(com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
                    i.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
                    SigmobLog.d("adClickThru() called" + map.toString());
                } catch (Throwable th) {
                    SigmobLog.e("adClickThru", th);
                }
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void b() {
                if (this.b) {
                    return;
                }
                i.this.a(com.sigmob.sdk.base.common.a.AD_PAUSE);
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void b(Map<String, String> map) {
                i.this.a(IntentActions.ACTION_MRAID_PLAYFAIL);
                i.this.a(com.sigmob.sdk.base.common.a.AD_ERROR);
                SigmobLog.d("adError() called " + map);
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void c() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_PLAYING);
                SigmobLog.d("adPlaying() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void d() {
                this.b = true;
                if (!i.this.l && i.this.m.getAd_type() != 4) {
                    i.this.l = true;
                    i.this.a(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
                    i.this.g.a(com.sigmob.sdk.base.common.a.AD_FINISH, i.this.n.e().intValue());
                }
                i.this.a(com.sigmob.sdk.base.common.a.AD_COMPLETE);
                SigmobLog.d("adVideoComplete() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void e() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_PLAY_QUARTER);
                SigmobLog.d("adVideoFirstQuartile() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void f() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_PLAY_THREE_QUARTERS);
                SigmobLog.d("adVideoThirdQuartile() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void g() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_PLAY_TWO_QUARTERS);
                SigmobLog.d("adVideoMidpoint() called");
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void h() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_VIDEO_START);
                SigmobLog.d("adVideoStart() called");
            }
        });
        this.n.a(new c.a() { // from class: com.sigmob.sdk.mraid.i.3
            @Override // com.sigmob.sdk.mraid.c.a
            public void a() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_SHOW);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(float f) {
                if (i.this.l) {
                    return;
                }
                i.this.l = true;
                i.this.a(com.sigmob.sdk.base.common.a.AD_FINISH);
                i.this.a(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(int i, int i2, int i3, int i4, CloseableLayout.a aVar, boolean z) {
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(View view) {
                i.this.a(com.sigmob.sdk.base.common.a.AD_START);
                SigmobLog.d("onLoaded() called");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(SigmobError sigmobError) {
                SigmobLog.d("Finishing the activity due to a problem: " + sigmobError);
                i.this.a(com.sigmob.sdk.base.common.a.AD_ERROR);
                if (i.this.a != null) {
                    i.this.a(IntentActions.ACTION_INTERSTITIAL_FAIL);
                }
                i.this.f.a();
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(String str2) throws UnsupportedEncodingException {
                boolean z;
                boolean z2 = true;
                if (TextUtils.isEmpty(str2)) {
                    z = true;
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        int iOptInt = jSONObject.optInt("type");
                        i.this.n.a(String.valueOf(jSONObject.optInt("x")), String.valueOf(jSONObject.optInt("y")));
                        if (iOptInt != 1) {
                            i.this.a(com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
                        } else {
                            z2 = false;
                        }
                        z = z2;
                    } catch (Exception unused) {
                        i.this.n.a(Constants.FAIL, Constants.FAIL);
                        i.this.a(com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
                    }
                }
                i.this.j.b(i.this.m, i.this.b, (String) null, com.sigmob.sdk.base.a.COMPANION, i.this.n.a(), z);
                i.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(URI uri, com.sigmob.sdk.videoAd.f fVar, String str2) throws UnsupportedEncodingException {
                boolean z;
                boolean z2;
                int iOptInt;
                int iOptInt2;
                int iOptInt3;
                boolean zOptBoolean;
                boolean z3 = false;
                boolean z4 = true;
                if (TextUtils.isEmpty(str2)) {
                    i.this.n.a(Constants.FAIL, Constants.FAIL);
                    i.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                    z2 = true;
                    z = false;
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        iOptInt = jSONObject.optInt("type");
                        iOptInt2 = jSONObject.optInt("x");
                        iOptInt3 = jSONObject.optInt("y");
                        zOptBoolean = jSONObject.optBoolean("disable_landing");
                    } catch (Exception unused) {
                    }
                    try {
                        i.this.n.a(String.valueOf(iOptInt2), String.valueOf(iOptInt3));
                        if (iOptInt != 1) {
                            i.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                        } else {
                            z4 = false;
                        }
                        z = zOptBoolean;
                        z2 = z4;
                    } catch (Exception unused2) {
                        z3 = zOptBoolean;
                        i.this.n.a(Constants.FAIL, Constants.FAIL);
                        i.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                        z = z3;
                        z2 = true;
                        i.this.j.a(i.this.m, i.this.b, uri.toString(), com.sigmob.sdk.base.a.ENDCARD, i.this.n.a(), z2, z);
                        i.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
                    }
                }
                i.this.j.a(i.this.m, i.this.b, uri.toString(), com.sigmob.sdk.base.a.ENDCARD, i.this.n.a(), z2, z);
                i.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(boolean z) {
                i iVar;
                com.sigmob.sdk.base.common.a aVar;
                if (z) {
                    iVar = i.this;
                    aVar = com.sigmob.sdk.base.common.a.AD_MUTE;
                } else {
                    iVar = i.this;
                    aVar = com.sigmob.sdk.base.common.a.AD_UNMUTE;
                }
                iVar.a(aVar);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b() {
                i.this.a(com.sigmob.sdk.base.common.a.AD_SHOW_SKIP);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b(float f) {
                if (i.this.q) {
                    return;
                }
                i.this.q = true;
                i.this.a(com.sigmob.sdk.base.common.a.AD_SKIP);
                i.this.a(IntentActions.ACTION_REWARDED_VIDEO_SKIP);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void c() {
                SigmobLog.d("MraidActivity failed to load. Finishing the activity");
                if (i.this.a != null) {
                    i.this.a(IntentActions.ACTION_INTERSTITIAL_FAIL);
                }
                i.this.f.a();
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void d() {
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void e() {
                if (i.this.s == null) {
                    i.this.s = new com.sigmob.sdk.base.views.e(i.this.k(), i.this.m);
                    i.this.s.a(new e.b() { // from class: com.sigmob.sdk.mraid.i.3.2
                        @Override // com.sigmob.sdk.base.views.e.b
                        public void a() {
                            if (i.this.s != null) {
                                i.this.s.dismiss();
                                i.this.s.c();
                                i.this.s = null;
                                i.this.t = false;
                            }
                            if (i.this.g != null) {
                                i.this.g.a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_CLOSE, 0);
                            }
                        }

                        @Override // com.sigmob.sdk.base.views.e.b
                        public void a(String str2, String str3) {
                            if (i.this.j != null) {
                                i.this.j.a(i.this.m, i.this.b, str2, com.sigmob.sdk.base.a.COMPANION, str3, true);
                                i.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                                i.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
                            }
                        }

                        @Override // com.sigmob.sdk.base.views.e.b
                        public void b() {
                            if (i.this.g != null) {
                                i.this.g.a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_SHOW, 0);
                            }
                        }
                    });
                }
                if (i.this.s == null || !i.this.s.a() || i.this.t) {
                    return;
                }
                i.this.s.show();
                i.this.t = true;
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void f() {
                i.this.n.a(new ValueCallback<String>() { // from class: com.sigmob.sdk.mraid.i.3.1
                    @Override // android.webkit.ValueCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onReceiveValue(String str2) throws NumberFormatException {
                        if (a.a(str2) != null) {
                            Float fValueOf = Float.valueOf(str2);
                            if (fValueOf.floatValue() < 0.999f && !i.this.q) {
                                i.this.q = true;
                                i.this.a(com.sigmob.sdk.base.common.a.AD_SKIP);
                            }
                            if (i.this.l || i.this.m.getAd_type() == 4 || fValueOf.floatValue() <= i.this.m.getFinishedTime()) {
                                return;
                            }
                            i.this.l = true;
                            i.this.g.a(com.sigmob.sdk.base.common.a.AD_FINISH, i.this.n.e().intValue());
                            i.this.a(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
                        }
                    }
                });
                i.this.a(IntentActions.ACTION_REWARDED_VIDEO_CLOSE);
                i.this.r = true;
                i.this.f.a();
            }
        });
        this.n.a(new c.e() { // from class: com.sigmob.sdk.mraid.i.4
            @Override // com.sigmob.sdk.mraid.c.e
            public void a(boolean z) {
                if (z) {
                    i.this.l();
                } else {
                    i.this.m();
                }
            }
        });
        if (aVarA != null) {
            this.g = aVarA.b();
        } else if (!TextUtils.isEmpty(this.m.getHtmlData())) {
            this.n.a(this.m.getHtmlData(), new c.b() { // from class: com.sigmob.sdk.mraid.i.5
                @Override // com.sigmob.sdk.mraid.c.b
                public void a(MraidWebView mraidWebView, p pVar) {
                    if (pVar != null) {
                        i.this.g = pVar;
                        return;
                    }
                    i.this.g = new p(i.this.b);
                    i.this.g.a(i.this.b, i.this.m, i.this.h);
                }
            });
        } else if (!TextUtils.isEmpty(this.m.getHtmlUrl())) {
            this.n.b(this.m.getHtmlUrl(), new c.b() { // from class: com.sigmob.sdk.mraid.i.6
                @Override // com.sigmob.sdk.mraid.c.b
                public void a(MraidWebView mraidWebView, p pVar) {
                    if (pVar != null) {
                        i.this.g = pVar;
                        return;
                    }
                    i.this.g = new p(i.this.b);
                    i.this.g.a(i.this.b, i.this.m, i.this.h);
                }
            });
        }
        return this.n.o();
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Configuration configuration) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Bundle bundle) {
    }

    public void a(final com.sigmob.sdk.base.common.a aVar) {
        final Integer numE = this.n.e();
        this.n.a(new ValueCallback<String>() { // from class: com.sigmob.sdk.mraid.i.1
            @Override // android.webkit.ValueCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(String str) {
                try {
                    if (a.a(str) == null) {
                        if (aVar.equals(com.sigmob.sdk.base.common.a.AD_SHOW_SKIP)) {
                            i.this.g.a(false, 0);
                        }
                        i.this.g.a(aVar, 0);
                        return;
                    }
                    Float fValueOf = Float.valueOf(str);
                    float fIntValue = -1.0f;
                    if (fValueOf.floatValue() > 1.0E-7d && numE != null && numE.intValue() > 0) {
                        fIntValue = numE.intValue() * fValueOf.floatValue();
                    }
                    if (aVar.equals(com.sigmob.sdk.base.common.a.AD_SHOW_SKIP)) {
                        i.this.g.a(false, (int) fIntValue);
                    }
                    i.this.g.a(aVar, (int) fIntValue);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.sigmob.sdk.videoAd.b, com.sigmob.sdk.base.common.g
    public void b() {
        super.b();
        if (this.g != null) {
            this.g.a(com.sigmob.sdk.base.common.a.AD_SHOW, 0);
        }
        j().addView(a(), new FrameLayout.LayoutParams(-1, -1));
        if (this.n != null) {
            this.n.a(k());
        }
        a(IntentActions.ACTION_REWARDED_VIDEO_PLAY);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void c() {
        if (this.r) {
            a(IntentActions.ACTION_INTERSTITIAL_DISMISS);
        } else {
            this.n.b();
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void d() {
        this.n.c();
    }

    @Override // com.sigmob.sdk.base.common.g
    public void e() {
        if (this.g != null) {
            this.g.a(com.sigmob.sdk.base.common.a.AD_VCLOSE, 0);
            this.g.a();
            this.g = null;
        }
        if (this.n != null) {
            this.n.k();
        }
        this.j.c();
        if (this.r) {
            return;
        }
        a(IntentActions.ACTION_INTERSTITIAL_DISMISS);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void f() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public boolean g() {
        return false;
    }
}
