package com.mbridge.msdk.video.bt.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.i;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.mraid.d;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.video.bt.module.b.f;
import com.mbridge.msdk.video.bt.module.b.h;
import com.mbridge.msdk.video.js.a.c;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.container.AbstractJSContainer;
import com.mbridge.msdk.video.module.MBridgeContainerView;
import com.mbridge.msdk.video.module.MBridgeVideoView;
import com.mbridge.msdk.video.module.a.a.g;
import com.mbridge.msdk.video.module.a.a.l;
import com.mbridge.msdk.videocommon.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBTempContainer extends AbstractJSContainer {
    private static final String y = "MBTempContainer";
    private CampaignEx A;
    private com.mbridge.msdk.videocommon.download.a B;
    private h C;
    private com.mbridge.msdk.video.bt.module.a.c D;
    private String E;
    private com.mbridge.msdk.video.js.factory.b F;
    private int G;
    private int H;
    private com.mbridge.msdk.video.bt.module.a.b I;
    private boolean J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private String P;
    private String Q;
    private LayoutInflater R;
    private int S;
    private int T;
    private int U;
    private View V;
    private boolean W;
    protected boolean a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private com.mbridge.msdk.mbjscommon.mraid.d aj;
    private Runnable ak;
    protected boolean b;
    protected a c;
    protected WindVaneWebView d;
    protected MBridgeVideoView e;
    protected MBridgeContainerView f;
    protected Handler g;
    protected Runnable h;
    protected Runnable i;
    private View z;

    public void preload() {
    }

    public void setMediaPlayerUrl(String str) {
    }

    public MBTempContainer(Context context) {
        super(context);
        this.E = "";
        this.G = com.mbridge.msdk.foundation.same.a.z;
        this.J = false;
        this.P = "";
        this.a = false;
        this.b = false;
        this.c = new a.C0144a();
        this.g = new Handler();
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.h = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.1
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                if (MBTempContainer.this.getActivityProxy().h() == 0) {
                    Log.d(MBTempContainer.y, "run: WebView load timeout");
                    MBTempContainer.this.defaultLoad(-1, "WebView load timeout");
                } else {
                    MBTempContainer.this.S = -3;
                }
            }
        };
        this.i = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.2
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                if (MBTempContainer.this.getActivityProxy().h() != 0) {
                    MBTempContainer.this.S = -4;
                } else {
                    MBTempContainer.this.defaultLoad(-3, "JS bridge connect timeout");
                }
            }
        };
        this.W = false;
        this.aa = false;
        this.ab = false;
        this.ad = false;
        this.ae = false;
        this.af = false;
        this.ag = false;
        this.ah = false;
        this.ai = false;
        this.ak = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBTempContainer.this.V != null) {
                    MBTempContainer.this.V.setVisibility(8);
                }
            }
        };
        init(context);
    }

    public MBTempContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = "";
        this.G = com.mbridge.msdk.foundation.same.a.z;
        this.J = false;
        this.P = "";
        this.a = false;
        this.b = false;
        this.c = new a.C0144a();
        this.g = new Handler();
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.h = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.1
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                if (MBTempContainer.this.getActivityProxy().h() == 0) {
                    Log.d(MBTempContainer.y, "run: WebView load timeout");
                    MBTempContainer.this.defaultLoad(-1, "WebView load timeout");
                } else {
                    MBTempContainer.this.S = -3;
                }
            }
        };
        this.i = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.2
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                if (MBTempContainer.this.getActivityProxy().h() != 0) {
                    MBTempContainer.this.S = -4;
                } else {
                    MBTempContainer.this.defaultLoad(-3, "JS bridge connect timeout");
                }
            }
        };
        this.W = false;
        this.aa = false;
        this.ab = false;
        this.ad = false;
        this.ae = false;
        this.af = false;
        this.ag = false;
        this.ah = false;
        this.ai = false;
        this.ak = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBTempContainer.this.V != null) {
                    MBTempContainer.this.V.setVisibility(8);
                }
            }
        };
        init(context);
    }

    public void init(Context context) {
        this.R = LayoutInflater.from(context);
    }

    public void setMatchParent() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        } else {
            layoutParams.height = -1;
            layoutParams.width = -1;
        }
    }

    public void registerErrorListener(a aVar) {
        this.c = aVar;
    }

    public int findID(String str) {
        return m.a(getContext(), str, "id");
    }

    public int findLayout(String str) {
        return m.a(getContext(), str, TtmlNode.TAG_LAYOUT);
    }

    public interface a {
        void a(String str);

        void a(boolean z);

        /* renamed from: com.mbridge.msdk.video.bt.module.MBTempContainer$a$a, reason: collision with other inner class name */
        public static class C0144a implements a {
            private boolean a = false;

            @Override // com.mbridge.msdk.video.bt.module.MBTempContainer.a
            public void a(String str) {
                s.d("ActivityErrorListener", str);
                this.a = true;
            }

            @Override // com.mbridge.msdk.video.bt.module.MBTempContainer.a
            public final void a(boolean z) {
                this.a = z;
            }
        }
    }

    public boolean isLoadSuccess() {
        return this.b;
    }

    private int c() {
        j jVarA = a(this.A);
        if (jVarA != null) {
            return jVarA.k();
        }
        return 0;
    }

    private boolean d() {
        j jVarA = a(this.A);
        if (jVarA != null) {
            return jVarA.n();
        }
        return false;
    }

    private boolean e() {
        if (this.e != null) {
            return this.e.isShowingAlertView() || this.e.isInstallDialogShowing();
        }
        return false;
    }

    public void superDefaultLoad(int i, String str) {
        s.d(y, "receiveError:" + i + ",descroption:" + str);
        this.g.removeCallbacks(this.h);
        this.g.removeCallbacks(this.i);
        this.c.a(true);
        if (this.d != null) {
            this.d.setVisibility(8);
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate() {
        ViewGroup viewGroup;
        int iH;
        int iG;
        s.a(y, "onCreate isBigOffer: " + this.w);
        if (this.m == null) {
            this.m = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.k, this.r);
        }
        this.ai = false;
        try {
            if (this.w) {
                this.C = new com.mbridge.msdk.video.bt.module.b.d(this.D, this.E);
            } else {
                this.C = new com.mbridge.msdk.video.bt.module.b.b(getContext(), this.r, this.m, this.A, this.C, b(), this.k);
            }
            registerErrorListener(new com.mbridge.msdk.video.bt.module.b.e(this.C));
            a(this.m, this.A);
            setShowingTransparent();
            int layoutID = getLayoutID();
            if (layoutID <= 0) {
                a("layoutID not found");
                return;
            }
            this.z = this.R.inflate(layoutID, (ViewGroup) null);
            addView(this.z, new RelativeLayout.LayoutParams(-1, -1));
            if (this.a) {
                setMatchParent();
            }
            this.d = findWindVaneWebView();
            this.e = findMBridgeVideoView();
            this.e.setIsIV(this.r);
            this.e.setUnitId(this.k);
            if (this.w) {
                this.e.setNotchPadding(this.L, this.M, this.N, this.O);
            }
            this.f = findMBridgeContainerView();
            if (this.w) {
                this.f.setNotchPadding(this.K, this.L, this.M, this.N, this.O);
            }
            if (!((this.e == null || this.f == null || !initViews()) ? false : true)) {
                this.c.a("not found View IDS");
                if (this.j != null) {
                    this.j.finish();
                    return;
                }
                return;
            }
            this.b = true;
            WindVaneWebView windVaneWebView = this.d;
            this.F = new com.mbridge.msdk.video.js.factory.b(this.j, windVaneWebView, this.e, this.f, this.A, new d());
            registerJsFactory(this.F);
            if (windVaneWebView != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.z.findViewById(m.a(getContext(), "mbridge_video_templete_webview_parent", "id"));
                windVaneWebView.setApiManagerJSFactory(this.F);
                if (windVaneWebView.getParent() != null) {
                    defaultLoad(0, "preload template webview is null or load error");
                    return;
                }
                if (windVaneWebView.getObject() instanceof j) {
                    this.F.a((j) windVaneWebView.getObject());
                    getJSCommon().a(this.q);
                    getJSCommon().a(this.k);
                    getJSCommon().a(this.m);
                    getJSCommon().a(new d());
                    if (this.A != null && (this.A.isMraid() || this.A.isActiveOm())) {
                        this.aj = new com.mbridge.msdk.mbjscommon.mraid.d(getContext());
                        this.aj.c();
                        this.aj.a();
                        this.aj.a(new d.b() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.6
                            @Override // com.mbridge.msdk.mbjscommon.mraid.d.b
                            public final void a(double d2) {
                                s.d(MBTempContainer.y, "volume is : " + d2);
                                try {
                                    if (!MBTempContainer.this.A.isMraid() || MBTempContainer.this.f == null || MBTempContainer.this.f.getH5EndCardView() == null) {
                                        return;
                                    }
                                    MBTempContainer.this.f.getH5EndCardView().volumeChange(d2);
                                } catch (Exception e2) {
                                    s.d(MBTempContainer.y, e2.getMessage());
                                }
                            }
                        });
                    }
                    getJSContainerModule().readyStatus(((j) windVaneWebView.getObject()).p());
                    try {
                        if (this.d != null) {
                            int i = getResources().getConfiguration().orientation;
                            if (d()) {
                                iH = w.i(getContext());
                                iG = w.j(getContext());
                                if (w.a(getContext())) {
                                    int iK = w.k(getContext());
                                    if (i == 2) {
                                        iH += iK;
                                    } else {
                                        iG += iK;
                                    }
                                }
                            } else {
                                iH = w.h(getContext());
                                iG = w.g(getContext());
                            }
                            int iB = this.A.getRewardTemplateMode().b();
                            if (b(this.A) == 1) {
                                iB = i;
                            }
                            getJSNotifyProxy().a(i, iB, iH, iG);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(com.mbridge.msdk.foundation.same.a.r, w.d(getContext()));
                            try {
                                if (this.o != null) {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("name", this.o.a());
                                    jSONObject2.put(RewardPlus.AMOUNT, this.o.b());
                                    jSONObject2.put("id", this.p);
                                    jSONObject.put("userId", this.n);
                                    jSONObject.put("reward", jSONObject2);
                                    jSONObject.put("playVideoMute", this.q);
                                    jSONObject.put(PushConstants.EXTRA, this.Q);
                                }
                            } catch (JSONException e2) {
                                s.a(y, e2.getMessage());
                            } catch (Exception e3) {
                                s.a(y, e3.getMessage());
                            }
                            getJSNotifyProxy().a(jSONObject.toString());
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.d, "oncutoutfetched", Base64.encodeToString(this.P.getBytes(), 0));
                            getJSCommon().b(true);
                            if (this.I == null) {
                                this.I = new com.mbridge.msdk.video.bt.module.a.b() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.3
                                    @Override // com.mbridge.msdk.video.bt.module.a.b
                                    public final void a() {
                                        MBTempContainer.this.getJSVideoModule().setInstallDialogState(true);
                                        MBTempContainer.this.getJSVideoModule().setCover(true);
                                        MBTempContainer.this.getJSVideoModule().videoOperate(2);
                                        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBTempContainer.this.d, "onInstallAlertShow", "");
                                    }

                                    @Override // com.mbridge.msdk.video.bt.module.a.b
                                    public final void b() {
                                        MBTempContainer.this.getJSVideoModule().setInstallDialogState(false);
                                        MBTempContainer.this.getJSVideoModule().setCover(false);
                                        MBTempContainer.this.getJSVideoModule().videoOperate(1);
                                        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBTempContainer.this.d, "onInstallAlertHide", "");
                                    }
                                };
                            }
                            getJSCommon().a(this.I);
                            loadModuleDatas();
                            this.g.postDelayed(this.h, 2000L);
                        }
                    } catch (Exception e4) {
                        if (MBridgeConstans.DEBUG) {
                            e4.printStackTrace();
                        }
                    }
                    ((j) windVaneWebView.getObject()).m.a();
                    if (this.w) {
                        getJSCommon().f(this.T);
                        getJSCommon().e(this.U);
                    }
                }
                if (getJSCommon().b() == 1 && (viewGroup = (ViewGroup) this.z.findViewById(m.a(getContext(), "mbridge_video_templete_webview_parent", "id"))) != null) {
                    ((ViewGroup) this.z).removeView(viewGroup);
                    ((ViewGroup) this.z).addView(viewGroup, 1);
                }
                viewGroup2.addView(windVaneWebView, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            s.a(y, "template webview is null");
            defaultLoad(0, "preload template webview is null or load error");
        } catch (Throwable th) {
            a("onCreate error" + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    protected final void a(String str) {
        if (this.C != null) {
            this.C.a(str);
        }
        super.a(str);
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onPause() {
        super.onPause();
        this.ab = true;
        try {
            getJSVideoModule().videoOperate(2);
        } catch (Throwable th) {
            s.b(y, th.getMessage(), th);
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onResume() {
        Runnable runnable;
        super.onResume();
        if (this.S == -3) {
            runnable = this.h;
        } else {
            runnable = this.S == -4 ? this.i : null;
        }
        if (runnable != null) {
            runnable.run();
            this.S = 0;
        }
        try {
            if (this.e != null && !e() && !this.e.isMiniCardShowing()) {
                this.e.setCover(false);
            }
            if (this.ab && !e()) {
                getJSVideoModule().videoOperate(1);
            }
            if (this.j != null) {
                w.a(this.j.getWindow().getDecorView());
            }
            if (this.ad && this.ae && this.j != null) {
                this.j.finish();
            }
        } catch (Throwable th) {
            s.b(y, th.getMessage(), th);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onStop() {
        super.onStop();
        if (this.e != null) {
            this.e.setCover(true);
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onDestroy() {
        if (this.J) {
            return;
        }
        boolean z = true;
        this.J = true;
        super.onDestroy();
        try {
            if (this.I != null) {
                this.I = null;
            }
            if (this.e != null) {
                this.e.releasePlayer();
            }
            if (this.d != null) {
                ViewGroup viewGroup = (ViewGroup) this.d.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.d.clearWebView();
                this.d.release();
            }
            if (this.D != null) {
                this.D = null;
            }
            this.g.removeCallbacks(this.h);
            this.g.removeCallbacks(this.i);
            getJSCommon().f();
            if (this.r) {
                com.mbridge.msdk.b.b.a().e(this.k);
            }
            if (!this.W) {
                try {
                    this.W = true;
                    if (this.A != null && this.A.getPlayable_ads_without_video() == 2) {
                        this.aa = true;
                    }
                    if (this.C != null) {
                        if (this.r && (this.t == com.mbridge.msdk.foundation.same.a.v || this.t == com.mbridge.msdk.foundation.same.a.w)) {
                            h hVar = this.C;
                            if (this.H != 1) {
                                z = false;
                            }
                            hVar.a(z, this.G);
                        }
                        if (!this.aa) {
                            this.o.a(0);
                        }
                        this.C.a(this.aa, this.o);
                    }
                    this.g.removeCallbacks(this.ak);
                    if (!this.r && !this.w && this.aa && (this.C == null || !this.C.b())) {
                        s.a(y, "sendToServerRewardInfo");
                        com.mbridge.msdk.video.module.b.a.a(this.A, this.o, this.k, this.n, this.Q);
                    }
                    if (!this.w) {
                        if (this.r) {
                            com.mbridge.msdk.videocommon.a.b(287, this.A);
                        } else {
                            com.mbridge.msdk.videocommon.a.b(94, this.A);
                        }
                    }
                    if (this.f != null) {
                        this.f.release();
                    }
                } catch (Throwable th) {
                    s.b(y, th.getMessage(), th);
                }
            }
            if (!this.ac) {
                f();
            }
            if (this.aj != null) {
                this.aj.d();
            }
            if (!this.w) {
                if (isLoadSuccess()) {
                    this.g.postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.5
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (MBTempContainer.this.j != null) {
                                MBTempContainer.this.j.finish();
                            }
                        }
                    }, 100L);
                } else if (this.j != null) {
                    this.j.finish();
                }
            }
            if (!this.ac) {
                f();
            }
            com.mbridge.msdk.video.bt.a.c.a().g(this.E);
        } catch (Throwable th2) {
            s.a(y, th2.getMessage());
        }
    }

    private void f() {
        com.mbridge.msdk.foundation.same.report.c cVar = new com.mbridge.msdk.foundation.same.report.c(getContext());
        if (this.A != null) {
            cVar.a(this.A.getRequestId(), this.A.getRequestIdNotice(), this.A.getId(), this.k, com.mbridge.msdk.mbjscommon.mraid.c.a(this.A.getId()), this.A.isBidCampaign());
            com.mbridge.msdk.mbjscommon.mraid.c.b(this.A.getId());
            this.ac = true;
        }
    }

    public void setShowingTransparent() {
        int iA;
        this.ad = d();
        if (this.ad || (iA = m.a(getContext(), "mbridge_reward_theme", TtmlNode.TAG_STYLE)) <= 1 || this.j == null) {
            return;
        }
        this.j.setTheme(iA);
    }

    public void receiveSuccess() {
        s.a(y, "receiveSuccess ,start hybrid");
        this.g.removeCallbacks(this.i);
        this.g.postDelayed(this.ak, 250L);
    }

    public void defaultLoad(int i, String str) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        superDefaultLoad(i, str);
        s.a(y, "hybrid load error ,start defaultLoad,desc:" + str);
        if (isLoadSuccess()) {
            if (this.A.getPlayable_ads_without_video() == 2) {
                this.f.setCampaign(this.A);
                this.f.setUnitID(this.k);
                this.f.setCloseDelayTime(this.m.o());
                this.f.setPlayCloseBtnTm(this.m.j());
                this.f.setNotifyListener(new g(this.A, this.B, this.o, b(), this.k, new c(), this.m.C(), this.w));
                this.f.preLoadData(this.F);
                this.f.showPlayableView();
                return;
            }
            a(i, str);
            this.V.setVisibility(8);
            loadModuleDatas();
            int iH = this.m.h();
            int iC = c();
            this.e.setNotifyListener(new l(this.e, this.f, this.A, this.o, this.B, b(), this.k, iC != 0 ? iC : iH, this.m.g(), new e(), this.m.z(), this.m.C(), this.w, this.m.I()));
            this.e.defaultShow();
            this.f.setNotifyListener(new com.mbridge.msdk.video.module.a.a.a(this.e, this.f, this.A, this.o, this.B, b(), this.k, new b(this.j, this.A), this.m.C(), this.w));
            this.f.defaultShow();
            return;
        }
        a(i, str);
        if (this.j != null) {
            this.j.finish();
        }
    }

    public int getLayoutID() {
        return findLayout(this.ad ? "mbridge_reward_activity_video_templete_transparent" : "mbridge_reward_activity_video_templete");
    }

    public WindVaneWebView findWindVaneWebView() {
        a.C0146a c0146aA;
        try {
            if (this.w) {
                s.a(y, "当前大模板");
                if (this.A == null || this.A.getRewardTemplateMode() == null) {
                    return null;
                }
                s.a(y, "当前大模板，存在播放模板");
                a.C0146a c0146aA2 = com.mbridge.msdk.videocommon.a.a(this.k + "_" + this.A.getId() + "_" + this.A.getRequestId() + "_" + this.A.getRewardTemplateMode().d());
                if (c0146aA2 != null) {
                    return c0146aA2.a();
                }
                return null;
            }
            s.a(y, "当前非大模板");
            if (this.r) {
                c0146aA = com.mbridge.msdk.videocommon.a.a(287, this.A);
            } else {
                c0146aA = com.mbridge.msdk.videocommon.a.a(94, this.A);
            }
            if (c0146aA == null || !c0146aA.c()) {
                return null;
            }
            if (this.r) {
                com.mbridge.msdk.videocommon.a.b(287, this.A);
            } else {
                com.mbridge.msdk.videocommon.a.b(94, this.A);
            }
            WindVaneWebView windVaneWebViewA = c0146aA.a();
            if (this.ad) {
                windVaneWebViewA.setWebViewTransparent();
            }
            return windVaneWebViewA;
        } catch (Exception e2) {
            if (!MBridgeConstans.DEBUG) {
                return null;
            }
            e2.printStackTrace();
            return null;
        }
    }

    public MBridgeVideoView findMBridgeVideoView() {
        return (MBridgeVideoView) findViewById(findID("mbridge_video_templete_videoview"));
    }

    public MBridgeContainerView findMBridgeContainerView() {
        return (MBridgeContainerView) findViewById(findID("mbridge_video_templete_container"));
    }

    public boolean initViews() {
        this.V = findViewById(findID("mbridge_video_templete_progressbar"));
        return this.V != null;
    }

    public boolean canBackPress() {
        return this.f == null || this.f.canBackPress();
    }

    public void onBackPressed() {
        if (this.ad && this.e != null) {
            this.e.notifyVideoClose();
            return;
        }
        if (this.af && this.e != null) {
            if (this.e.isMiniCardShowing()) {
                if (this.f != null) {
                    this.f.onMiniEndcardBackPress();
                    return;
                }
                return;
            }
            this.e.onBackPress();
            return;
        }
        if (this.ah && this.f != null) {
            this.f.onPlayableBackPress();
            return;
        }
        if (this.ag && this.f != null) {
            this.f.onEndcardBackPress();
        }
        if (getJSCommon().c()) {
            if (getJSContainerModule() == null || !getJSContainerModule().miniCardShowing()) {
                getActivityProxy().g();
                return;
            }
            return;
        }
        if (canBackPress()) {
            if (this.j == null || this.w || this.ai) {
                return;
            }
            this.ai = true;
            this.j.onBackPressed();
            return;
        }
        s.a(y, "onBackPressed can't excute");
    }

    private void a(int i, String str) {
        try {
            q qVar = new q();
            qVar.o("2000037");
            qVar.j("code=" + i + ",desc=" + str);
            String strD = "";
            if (this.A != null && this.A.getRewardTemplateMode() != null) {
                strD = this.A.getRewardTemplateMode().d();
            }
            qVar.i(strD);
            qVar.m(this.k);
            qVar.n(this.A != null ? this.A.getId() : "");
            if (this.A != null && !TextUtils.isEmpty(this.A.getRequestId())) {
                qVar.k(this.A.getRequestId());
            }
            if (this.A != null && !TextUtils.isEmpty(this.A.getRequestIdNotice())) {
                qVar.l(this.A.getRequestIdNotice());
            }
            int iC = o.C(getContext());
            qVar.b(iC);
            qVar.r(o.a(getContext(), iC));
            com.mbridge.msdk.video.module.b.a.a(q.e(qVar), this.k);
        } catch (Throwable th) {
            s.b(y, th.getMessage(), th);
        }
    }

    private int g() {
        try {
            com.mbridge.msdk.videocommon.d.a aVarB = com.mbridge.msdk.videocommon.d.b.a().b();
            if (aVarB == null) {
                com.mbridge.msdk.videocommon.d.b.a().c();
            }
            iF = aVarB != null ? (int) aVarB.f() : 5;
            s.b(y, "MBridgeBaseView buffetTimeout:" + iF);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return iF;
    }

    private final class e extends com.mbridge.msdk.video.module.a.a.e {
        private e() {
        }

        @Override // com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
        public final void a(int i, Object obj) {
            super.a(i, obj);
            if (MBTempContainer.this.r) {
                try {
                    if (obj instanceof String) {
                        String str = (String) obj;
                        if (!TextUtils.isEmpty(str)) {
                            JSONObject jSONObject = new JSONObject(str);
                            MBTempContainer.this.G = jSONObject.getInt("Alert_window_status");
                            MBTempContainer.this.H = jSONObject.getInt("complete_info");
                        }
                    }
                } catch (Exception e) {
                    s.d("NotifyListener", e.getMessage());
                }
            }
            if (i != 2) {
                if (i != 121) {
                    switch (i) {
                        case 10:
                            MBTempContainer.this.af = true;
                            MBTempContainer.this.C.a();
                            f.a(MBTempContainer.this.getContext(), MBTempContainer.this.A, MBTempContainer.this.k);
                            break;
                        case 11:
                        case 12:
                            break;
                        default:
                            switch (i) {
                                case 16:
                                    MBTempContainer.k(MBTempContainer.this);
                                    break;
                                case 17:
                                    MBTempContainer.this.aa = true;
                                    break;
                            }
                    }
                }
                MBTempContainer.this.aa = true;
                MBTempContainer.this.C.a(MBTempContainer.this.l, MBTempContainer.this.k);
                MBTempContainer.this.af = false;
                return;
            }
            if (i == 12) {
                String string = "Play error but has no message.";
                try {
                    if (obj instanceof String) {
                        string = obj.toString();
                    }
                } catch (Throwable th) {
                    s.d("NotifyListener", th.getMessage());
                }
                f.a(MBTempContainer.this.getContext(), MBTempContainer.this.A, MBTempContainer.this.k, string);
                if (!MBTempContainer.this.af) {
                    if (MBTempContainer.this.m.I() == 0) {
                        MBTempContainer.this.C.a("play error");
                        MBTempContainer.this.af = false;
                        MBTempContainer.k(MBTempContainer.this);
                        return;
                    }
                    MBTempContainer.this.C.a();
                }
            }
            MBTempContainer.this.getJSVideoModule().videoOperate(3);
            MBTempContainer.this.af = false;
        }
    }

    private final class b extends com.mbridge.msdk.video.module.a.a.e {
        private Activity b;
        private CampaignEx c;

        public b(Activity activity, CampaignEx campaignEx) {
            this.b = activity;
            this.c = campaignEx;
        }

        @Override // com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
        public final void a(int i, Object obj) {
            Context context = MBTempContainer.this.getContext();
            if (context != null && context != context.getApplicationContext()) {
                MBTempContainer.this.getJSCommon().a(context);
            }
            if (this.b != null) {
                MBTempContainer.this.getJSCommon().a(this.b);
            }
            MBTempContainer.this.ag = true;
            if (i == 108) {
                if (MBTempContainer.this.I == null) {
                    MBTempContainer.this.I = new com.mbridge.msdk.video.bt.module.a.b() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.b.2
                        @Override // com.mbridge.msdk.video.bt.module.a.b
                        public final void a() {
                            MBTempContainer.this.getJSVideoModule().setInstallDialogState(true);
                            MBTempContainer.this.getJSVideoModule().setCover(true);
                            MBTempContainer.this.getJSVideoModule().videoOperate(2);
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBTempContainer.this.d, "onInstallAlertShow", "");
                        }

                        @Override // com.mbridge.msdk.video.bt.module.a.b
                        public final void b() {
                            MBTempContainer.this.getJSVideoModule().setInstallDialogState(false);
                            MBTempContainer.this.getJSVideoModule().setCover(false);
                            MBTempContainer.this.getJSVideoModule().videoOperate(1);
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBTempContainer.this.d, "onInstallAlertHide", "");
                        }
                    };
                }
                MBTempContainer.this.getJSCommon().a(MBTempContainer.this.I);
                MBTempContainer.this.getJSCommon().a(new c.b(MBTempContainer.this.getJSCommon(), new d()));
                MBTempContainer.this.getJSCommon().click(1, obj != null ? obj.toString() : "");
            } else if (i == 113) {
                MBTempContainer.this.C.a(true, MBTempContainer.this.l, MBTempContainer.this.k);
            } else if (i == 117) {
                MBTempContainer.this.C.b(MBTempContainer.this.l, MBTempContainer.this.k);
            } else if (i == 126) {
                MBTempContainer.this.C.a(false, MBTempContainer.this.l, MBTempContainer.this.k);
            } else {
                switch (i) {
                    case 103:
                    case 104:
                        MBTempContainer.k(MBTempContainer.this);
                        break;
                    case 105:
                        if (MBTempContainer.this.I == null) {
                            MBTempContainer.this.I = new com.mbridge.msdk.video.bt.module.a.b() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.b.1
                                @Override // com.mbridge.msdk.video.bt.module.a.b
                                public final void a() {
                                    MBTempContainer.this.getJSVideoModule().setInstallDialogState(true);
                                    MBTempContainer.this.getJSVideoModule().setCover(true);
                                    MBTempContainer.this.getJSVideoModule().videoOperate(2);
                                }

                                @Override // com.mbridge.msdk.video.bt.module.a.b
                                public final void b() {
                                    MBTempContainer.this.getJSVideoModule().setInstallDialogState(false);
                                    MBTempContainer.this.getJSVideoModule().setCover(false);
                                    MBTempContainer.this.getJSVideoModule().videoOperate(1);
                                }
                            };
                        }
                        MBTempContainer.this.getJSCommon().a(MBTempContainer.this.I);
                        MBTempContainer.this.getJSCommon().click(1, obj != null ? obj.toString() : "");
                        break;
                    case 106:
                        if (MBTempContainer.this.D != null) {
                            MBTempContainer.this.D.a(MBTempContainer.this.E, MBTempContainer.this.l, MBTempContainer.this.k);
                        } else {
                            MBTempContainer.this.C.a(true, MBTempContainer.this.l, MBTempContainer.this.k);
                        }
                        if (this.b != null && this.c != null) {
                            try {
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                String strA = com.mbridge.msdk.click.c.a(this.c.getClickURL(), "-999", "-999");
                                if (!TextUtils.isEmpty(strA)) {
                                    intent.setData(Uri.parse(strA));
                                    this.b.startActivity(intent);
                                }
                            } catch (Throwable th) {
                                s.b("NotifyListener", th.getMessage(), th);
                            }
                            MBTempContainer.k(MBTempContainer.this);
                            break;
                        }
                        break;
                }
            }
            super.a(i, obj);
        }
    }

    private final class c extends com.mbridge.msdk.video.module.a.a.e {
        private c() {
        }

        @Override // com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
        public final void a(int i, Object obj) {
            super.a(i, obj);
            try {
                String str = (String) obj;
                if (MBTempContainer.this.r && !TextUtils.isEmpty(str)) {
                    JSONObject jSONObject = new JSONObject(str);
                    int iOptInt = jSONObject.optInt(Message.TYPE);
                    int iOptInt2 = jSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_COMPLETE);
                    switch (iOptInt) {
                        case 2:
                            MBTempContainer.this.G = com.mbridge.msdk.foundation.same.a.B;
                            break;
                        case 3:
                            MBTempContainer.this.G = com.mbridge.msdk.foundation.same.a.A;
                            break;
                        default:
                            MBTempContainer.this.G = com.mbridge.msdk.foundation.same.a.z;
                            break;
                    }
                    MBTempContainer.this.H = iOptInt2;
                }
            } catch (Exception unused) {
                s.d("NotifyListener", "PlayableResultListener ERROR");
            }
            if (i != 120) {
                switch (i) {
                    case 100:
                        MBTempContainer.this.ah = true;
                        MBTempContainer.this.g.postDelayed(MBTempContainer.this.ak, 250L);
                        MBTempContainer.this.C.a();
                        return;
                    case 101:
                    case 102:
                        MBTempContainer.this.getJSCommon().e();
                        return;
                    case 103:
                        MBTempContainer.this.aa = true;
                        if (!MBTempContainer.this.A.isMraid()) {
                            MBTempContainer.this.getJSCommon().e();
                            return;
                        }
                        break;
                    case 104:
                        break;
                    default:
                        switch (i) {
                            case 126:
                                MBTempContainer.this.C.a(false, MBTempContainer.this.l, MBTempContainer.this.k);
                                break;
                            case 127:
                                MBTempContainer.this.aa = true;
                                MBTempContainer.this.getJSContainerModule().showEndcard(100);
                                break;
                        }
                }
                MBTempContainer.k(MBTempContainer.this);
                return;
            }
            MBTempContainer.this.C.b(MBTempContainer.this.l, MBTempContainer.this.k);
        }
    }

    private class d extends c.a {
        private d() {
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.video.js.c.a
        public final void a() {
            super.a();
            MBTempContainer.this.receiveSuccess();
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onStartRedirection(Campaign campaign, String str) {
            super.onStartRedirection(campaign, str);
            MBTempContainer.G(MBTempContainer.this);
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onFinishRedirection(Campaign campaign, String str) {
            super.onFinishRedirection(campaign, str);
            MBTempContainer.this.ae = true;
            MBTempContainer.H(MBTempContainer.this);
            if (campaign == null || !(campaign instanceof CampaignEx)) {
                return;
            }
            try {
                CampaignEx campaignEx = (CampaignEx) campaign;
                String strOptString = new JSONObject(MBTempContainer.this.getJSVideoModule().getCurrentProgress()).optString("progress", "");
                if (campaignEx.getLinkType() == 3 && campaignEx.getEndcard_click_result() == 2 && strOptString.equals("1.0") && MBTempContainer.this.j != null) {
                    if (!MBTempContainer.this.w) {
                        MBTempContainer.this.j.finish();
                    } else {
                        MBTempContainer.k(MBTempContainer.this);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onRedirectionFailed(Campaign campaign, String str) {
            super.onRedirectionFailed(campaign, str);
            MBTempContainer.H(MBTempContainer.this);
            MBTempContainer.this.ae = true;
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.video.js.c.a
        public final void a(boolean z) {
            super.a(z);
            MBTempContainer.this.C.a(z, MBTempContainer.this.l, MBTempContainer.this.k);
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.video.js.c.a
        public final void a(int i, String str) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
            super.a(i, str);
            MBTempContainer.this.defaultLoad(i, str);
        }

        @Override // com.mbridge.msdk.video.js.a.c.a, com.mbridge.msdk.video.js.c.a
        public final void b() {
            super.b();
            if (MBTempContainer.this.g != null) {
                MBTempContainer.this.g.removeCallbacks(MBTempContainer.this.h);
            }
        }
    }

    public void setCampaign(CampaignEx campaignEx) {
        this.A = campaignEx;
    }

    public CampaignEx getCampaign() {
        return this.A;
    }

    public void setCampaignExpired(boolean z) {
        try {
            if (this.A != null) {
                if (z) {
                    this.A.setSpareOfferFlag(1);
                    if (this.s) {
                        this.A.setCbt(0);
                    } else if (this.m != null) {
                        if (this.m.C() == 1) {
                            this.A.setCbt(1);
                        } else {
                            this.A.setCbt(0);
                        }
                    }
                } else {
                    this.A.setSpareOfferFlag(0);
                    if (this.A.isBidCampaign()) {
                        this.A.setCbt(0);
                    } else if (this.m != null) {
                        this.A.setCbt(this.m.d());
                    }
                }
            }
        } catch (Exception e2) {
            s.d(y, e2.getMessage());
        }
    }

    public void setCampaignDownLoadTask(com.mbridge.msdk.videocommon.download.a aVar) {
        this.B = aVar;
    }

    public void setShowRewardListener(h hVar) {
        this.C = hVar;
    }

    public void setMBridgeTempCallback(com.mbridge.msdk.video.bt.module.a.c cVar) {
        this.D = cVar;
    }

    public void setJSFactory(com.mbridge.msdk.video.js.factory.b bVar) {
        this.F = bVar;
    }

    public void setInstanceId(String str) {
        this.E = str;
    }

    public String getInstanceId() {
        return this.E;
    }

    public void setH5Cbp(int i) {
        this.U = i;
    }

    public void setWebViewFront(int i) {
        this.T = i;
    }

    public void notifyEvent(String str) {
        if (this.d != null) {
            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.d, str, Base64.encodeToString(this.E.getBytes(), 2));
        }
    }

    public void setNotchPadding(int i, int i2, int i3, int i4, int i5) {
        this.K = i;
        this.L = i2;
        this.M = i3;
        this.N = i4;
        this.O = i5;
        this.P = i.a(i, i2, i3, i4, i5);
        s.d(y, this.P);
        if (getJSCommon() != null && !TextUtils.isEmpty(this.P)) {
            getJSCommon().b(this.P);
            if (this.d != null && !TextUtils.isEmpty(this.P)) {
                com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.d, "oncutoutfetched", Base64.encodeToString(this.P.getBytes(), 0));
            }
        }
        if (this.e != null) {
            this.e.setNotchPadding(i2, i3, i4, i5);
        }
        if (this.f != null) {
            this.f.setNotchPadding(i, i2, i3, i4, i5);
        }
    }

    public void setDeveloperExtraData(String str) {
        this.Q = str;
    }

    public void loadModuleDatas() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        j jVarA = a(this.A);
        int iL = jVarA != null ? jVarA.l() : 0;
        if (iL != 0) {
            this.q = iL;
        }
        int iH = this.m.h();
        int iC = c();
        int i = iC != 0 ? iC : iH;
        this.e.setSoundState(this.q);
        this.e.setCampaign(this.A);
        this.e.setPlayURL(this.B.n());
        this.e.setVideoSkipTime(this.m.g());
        this.e.setCloseAlert(this.m.k());
        this.e.setBufferTimeout(g());
        this.e.setNotifyListener(new com.mbridge.msdk.video.module.a.a.m(this.F, this.A, this.o, this.B, b(), this.k, i, this.m.g(), new e(), this.m.z(), this.m.C(), this.w, this.m.I()));
        this.e.setShowingTransparent(this.ad);
        if (this.r && (this.t == com.mbridge.msdk.foundation.same.a.v || this.t == com.mbridge.msdk.foundation.same.a.w)) {
            this.e.setIVRewardEnable(this.t, this.u, this.v);
            this.e.setDialogRole(getJSCommon() != null ? getJSCommon().i() : 1);
        }
        this.f.setCampaign(this.A);
        this.f.setUnitID(this.k);
        this.f.setCloseDelayTime(this.m.o());
        this.f.setPlayCloseBtnTm(this.m.j());
        this.f.setVideoInteractiveType(this.m.i());
        this.f.setEndscreenType(this.m.p());
        this.f.setVideoSkipTime(this.m.g());
        this.f.setShowingTransparent(this.ad);
        this.f.setJSFactory(this.F);
        if (this.A.getPlayable_ads_without_video() == 2) {
            this.f.setNotifyListener(new g(this.A, this.B, this.o, b(), this.k, new c(), this.m.C(), this.w));
            this.f.preLoadData(this.F);
            this.f.showPlayableView();
        } else {
            this.f.setNotifyListener(new com.mbridge.msdk.video.module.a.a.b(this.F, this.A, this.o, this.B, b(), this.k, new b(this.j, this.A), this.m.C(), this.w));
            this.f.preLoadData(this.F);
            this.e.preLoadData(this.F);
        }
        if (this.ad) {
            this.f.setMBridgeClickMiniCardViewTransparent();
        }
    }

    static /* synthetic */ void k(MBTempContainer mBTempContainer) {
        try {
            if (mBTempContainer.D != null) {
                if (mBTempContainer.r && (mBTempContainer.t == com.mbridge.msdk.foundation.same.a.v || mBTempContainer.t == com.mbridge.msdk.foundation.same.a.w)) {
                    com.mbridge.msdk.video.bt.module.a.c cVar = mBTempContainer.D;
                    boolean z = true;
                    if (mBTempContainer.H != 1) {
                        z = false;
                    }
                    cVar.a(z, mBTempContainer.G);
                }
                mBTempContainer.D.a(mBTempContainer.E, mBTempContainer.aa, mBTempContainer.o);
                return;
            }
            if (mBTempContainer.j != null) {
                mBTempContainer.j.finish();
            }
        } catch (Exception unused) {
            if (mBTempContainer.j != null) {
                mBTempContainer.j.finish();
            }
        }
    }

    static /* synthetic */ void G(MBTempContainer mBTempContainer) {
        if (mBTempContainer.isLoadSuccess()) {
            mBTempContainer.j.runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.7
                @Override // java.lang.Runnable
                public final void run() {
                    MBTempContainer.this.V.setBackgroundColor(0);
                    MBTempContainer.this.V.setVisibility(0);
                    MBTempContainer.this.V.bringToFront();
                }
            });
        }
    }

    static /* synthetic */ void H(MBTempContainer mBTempContainer) {
        if (mBTempContainer.isLoadSuccess()) {
            mBTempContainer.j.runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBTempContainer.8
                @Override // java.lang.Runnable
                public final void run() {
                    MBTempContainer.this.V.setVisibility(8);
                }
            });
        }
    }
}
