package com.mbridge.msdk.interstitial.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.mbridge.msdk.activity.MBBaseActivity;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.same.report.c;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.foundation.webview.BrowserView;
import com.mbridge.msdk.interstitial.c.a;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.mbjscommon.mraid.b;
import com.mbridge.msdk.mbjscommon.mraid.d;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBInterstitialActivity extends MBBaseActivity implements b {
    public static final String INTENT_CAMAPIGN = "campaign";
    public static final String INTENT_UNIT_ID = "unitId";
    public static final long WATI_JS_INVOKE = 2000;
    public static final long WEB_LOAD_TIME = 15000;
    private CampaignEx e;
    private WindVaneWebView f;
    private ImageView g;
    private a.c h;
    private boolean i;
    private d j;
    private long k;
    private boolean l;
    private boolean m;
    public ProgressBar mProgressBar;
    public String mUnitid;
    private com.mbridge.msdk.click.b o;
    private boolean c = false;
    private boolean d = false;
    public boolean mIsMBPage = false;
    private Handler n = new Handler() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
        }
    };
    Runnable a = new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.8
        @Override // java.lang.Runnable
        public final void run() {
            s.d("MBInterstitialActivity", "load page timeOut");
            if (!MBInterstitialActivity.this.d) {
                MBInterstitialActivity.this.c = true;
                if (MBInterstitialActivity.this.h != null) {
                    MBInterstitialActivity.this.h.a("load page timeout");
                    if (MBInterstitialActivity.this.f != null) {
                        MBInterstitialActivity.this.f.setVisibility(8);
                        MBInterstitialActivity.this.f.setWebViewListener(null);
                        MBInterstitialActivity.this.f.release();
                    }
                    MBInterstitialActivity.this.hideLoading();
                    return;
                }
                return;
            }
            s.d("MBInterstitialActivity", "mLoadTimeTask 已经打开非mb的页面了 return ");
        }
    };
    Runnable b = new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.9
        @Override // java.lang.Runnable
        public final void run() {
            s.d("MBInterstitialActivity", "mWaitJsInvokeTask 开始执行run方法");
            if (!MBInterstitialActivity.this.mIsMBPage) {
                if (MBInterstitialActivity.this.e != null && MBInterstitialActivity.this.e.isMraid()) {
                    MBInterstitialActivity.h(MBInterstitialActivity.this);
                }
                com.mbridge.msdk.interstitial.b.a.a().a(MBInterstitialActivity.this.e, MBInterstitialActivity.this.mUnitid);
                if (!MBInterstitialActivity.this.c) {
                    MBInterstitialActivity.this.d = true;
                    MBInterstitialActivity.this.hideLoading();
                    MBInterstitialActivity.this.showWebView();
                    s.d("MBInterstitialActivity", "mWaitJsInvokeTask 最终显示非mb的页面 ");
                    return;
                }
                s.b("MBInterstitialActivity", "mWaitJsInvokeTask webview 已加载超时 return");
                return;
            }
            s.b("MBInterstitialActivity", "mWaitJsInvokeTask getinfo 已调用 return");
        }
    };

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void expand(String str, boolean z) {
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            int iA = m.a(getApplicationContext(), "mbridge_interstitial_activity", TtmlNode.TAG_LAYOUT);
            if (iA != -1) {
                setContentView(iA);
                initView();
                a();
                this.g.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MBInterstitialActivity.this.finish();
                    }
                });
                if (this.f != null && this.e != null) {
                    BrowserView.MBDownloadListener mBDownloadListener = new BrowserView.MBDownloadListener(this.e);
                    mBDownloadListener.setTitle(this.e.getAppName());
                    this.f.setCampaignId(this.e.getId());
                    this.f.setDownloadListener(mBDownloadListener);
                }
                b();
                try {
                    if (this.e != null && (!TextUtils.isEmpty(this.e.getHtmlUrl()) || this.e.isMraid())) {
                        s.b("MBInterstitialActivity", "url:" + this.e.getHtmlUrl());
                        goneWebView();
                        this.f.setWebViewListener(new com.mbridge.msdk.mbjscommon.windvane.d() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.7
                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void a(WebView webView, int i) {
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void b(WebView webView, int i) {
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void c(WebView webView, int i) {
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final boolean b(WebView webView, String str) {
                                s.d("MBInterstitialActivity", "shouldOverrideUrlLoading");
                                return true;
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                                s.d("MBInterstitialActivity", "onReceivedSslError");
                                MBInterstitialActivity.this.i = true;
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void a(WebView webView, int i, String str, String str2) {
                                try {
                                    MBInterstitialActivity.this.i = true;
                                    s.d("MBInterstitialActivity", "onReceivedError");
                                    if (MBInterstitialActivity.this.h != null) {
                                        MBInterstitialActivity.this.h.a(str);
                                    }
                                    MBInterstitialActivity.a(MBInterstitialActivity.this, 3, str);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void a(WebView webView, String str, Bitmap bitmap) {
                                s.b("MBInterstitialActivity", "onPageStarted");
                            }

                            @Override // com.mbridge.msdk.mbjscommon.windvane.d
                            public final void a(WebView webView, String str) {
                                try {
                                    if (MBInterstitialActivity.this.i) {
                                        return;
                                    }
                                    MBInterstitialActivity.a(MBInterstitialActivity.this, 1, "");
                                    s.d("MBInterstitialActivity", "onPageFinished");
                                    if (MBInterstitialActivity.this.a != null && MBInterstitialActivity.this.n != null) {
                                        MBInterstitialActivity.this.n.removeCallbacks(MBInterstitialActivity.this.a);
                                    }
                                    if (MBInterstitialActivity.this.h != null) {
                                        MBInterstitialActivity.this.h.a();
                                    }
                                    if (!MBInterstitialActivity.this.mIsMBPage) {
                                        MBInterstitialActivity.this.n.postDelayed(MBInterstitialActivity.this.b, 2000L);
                                        s.d("MBInterstitialActivity", "不是mb页面 getinfo还没调用 2秒后执行task");
                                    } else {
                                        s.d("MBInterstitialActivity", "是mb页面 getinfo已调用 不做处理");
                                    }
                                    MBInterstitialActivity.d(MBInterstitialActivity.this);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    if (MBInterstitialActivity.this.h != null) {
                                        MBInterstitialActivity.this.h.a("load page failed");
                                    }
                                }
                            }
                        });
                        String htmlUrl = this.e.getHtmlUrl();
                        if (this.e.isMraid()) {
                            File file = new File(this.e.getMraid());
                            if (file.exists() && file.isFile() && file.canRead()) {
                                htmlUrl = "file:////" + this.e.getMraid();
                            }
                        }
                        this.k = System.currentTimeMillis();
                        this.f.loadUrl(htmlUrl);
                        this.n.postDelayed(this.a, WEB_LOAD_TIME);
                        return;
                    }
                    if (this.h != null) {
                        this.h.a("offerwall htmlurl is null");
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            a();
            b();
            if (this.h != null) {
                this.h.a("not found resource");
            }
            finish();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.e == null || !this.e.isMraid()) {
            return;
        }
        com.mbridge.msdk.mbjscommon.mraid.a.a().b(this.f, "true");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.e == null || !this.e.isMraid()) {
            return;
        }
        com.mbridge.msdk.mbjscommon.mraid.a.a().b(this.f, "false");
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mUnitid = intent.getStringExtra("unitId");
            this.e = (CampaignEx) intent.getSerializableExtra("campaign");
        }
        if (this.e == null || !this.e.isMraid()) {
            return;
        }
        this.j = new d(this);
        this.j.c();
        this.j.a(new d.b() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.10
            @Override // com.mbridge.msdk.mbjscommon.mraid.d.b
            public final void a(double d) {
                s.d("MBInterstitialActivity", "volume is : " + d);
                com.mbridge.msdk.mbjscommon.mraid.a.a().a(MBInterstitialActivity.this.f, d);
            }
        });
    }

    public void initView() {
        this.f = (WindVaneWebView) findViewById(m.a(getApplicationContext(), "mbridge_interstitial_wv", "id"));
        this.mProgressBar = (ProgressBar) findViewById(m.a(getApplicationContext(), "mbridge_interstitial_pb", "id"));
        this.g = (ImageView) findViewById(m.a(getApplicationContext(), "mbridge_interstitial_iv_close", "id"));
    }

    private void b() {
        try {
            if (a.e == null || TextUtils.isEmpty(this.mUnitid) || !a.e.containsKey(this.mUnitid)) {
                return;
            }
            this.h = a.e.get(this.mUnitid);
            s.b("MBInterstitialActivity", "mShowIntersInnerListener 初始化成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWebView() {
        try {
            runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.11
                @Override // java.lang.Runnable
                public final void run() {
                    if (MBInterstitialActivity.this.f != null) {
                        MBInterstitialActivity.this.f.setVisibility(0);
                        if (MBInterstitialActivity.this.e.isMraid()) {
                            MBInterstitialActivity.j(MBInterstitialActivity.this);
                        }
                        MBInterstitialActivity.k(MBInterstitialActivity.this);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goneWebView() {
        try {
            runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.12
                @Override // java.lang.Runnable
                public final void run() {
                    if (MBInterstitialActivity.this.f != null) {
                        MBInterstitialActivity.this.f.setVisibility(8);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        try {
            runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.13
                @Override // java.lang.Runnable
                public final void run() {
                    if (MBInterstitialActivity.this.mProgressBar != null) {
                        MBInterstitialActivity.this.mProgressBar.setVisibility(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideLoading() {
        try {
            runOnUiThread(new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (MBInterstitialActivity.this.mProgressBar != null) {
                        MBInterstitialActivity.this.mProgressBar.setVisibility(8);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (this.h != null) {
                this.h.b();
            }
            if (this.o != null) {
                this.o.a(false);
                this.o.a((NativeListener.NativeTrackingListener) null);
                this.o.a();
            }
            if (this.j != null) {
                this.j.d();
            }
            if (!this.l) {
                c();
            }
            if (a.e != null && !TextUtils.isEmpty(this.mUnitid)) {
                a.e.remove(this.mUnitid);
            }
            this.h = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (!this.l) {
            c();
        }
        if (this.m) {
            return;
        }
        reportPlayableClosed();
    }

    public void reportPlayableClosed() {
        if (this.e != null) {
            q qVar = new q("2000061", this.e.getId(), this.e.getRequestId(), this.e.getRequestIdNotice(), this.mUnitid, o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
            qVar.a(this.e.isMraid() ? q.a : q.b);
            com.mbridge.msdk.foundation.same.report.b.b(qVar, com.mbridge.msdk.foundation.controller.a.f().j(), this.mUnitid);
            this.m = true;
        }
    }

    private void c() {
        c cVar = new c(getApplicationContext());
        if (this.e != null) {
            cVar.a(this.e.getRequestId(), this.e.getRequestIdNotice(), this.e.getId(), this.mUnitid, com.mbridge.msdk.mbjscommon.mraid.c.a(this.e.getId()), this.e.isBidCampaign());
            com.mbridge.msdk.mbjscommon.mraid.c.b(this.e.getId());
            this.l = true;
        }
    }

    public void onIntersClick() {
        try {
            if (this.h != null) {
                this.h.c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public com.mbridge.msdk.b.d getIntersUnitSetting() {
        try {
            if (TextUtils.isEmpty(this.mUnitid)) {
                return null;
            }
            com.mbridge.msdk.b.d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.mUnitid);
            if (dVarF != null) {
                return dVarF;
            }
            s.b("MBInterstitialActivity", "获取默认的unitsetting");
            return com.mbridge.msdk.b.d.f(this.mUnitid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clickTracking(final boolean z, final String str) {
        this.o = new com.mbridge.msdk.click.b(getApplicationContext(), this.mUnitid);
        try {
            if (this.e != null && this.e.needShowIDialog()) {
                com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.3
                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void b() {
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void a() {
                        MBInterstitialActivity.this.a(z, str);
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void c() {
                        a();
                    }
                };
                if (com.mbridge.msdk.click.c.a(this.e) && this.e.needShowIDialog()) {
                    if (!this.o.b(this.e)) {
                        e.a().a("", this.e, this, this.mUnitid, aVar);
                        return;
                    } else {
                        onIntersClick();
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            s.a("MBInterstitialActivity", th.getMessage());
        }
        a(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str) {
        try {
            if (this.e != null && !z.a(this.mUnitid)) {
                onIntersClick();
                this.o.a(new NativeListener.NativeTrackingListener() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.4
                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDismissLoading(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadFinish(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadProgress(int i) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadStart(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final boolean onInterceptDefaultLoadingDialog() {
                        return false;
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onShowLoading(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onStartRedirection(Campaign campaign, String str2) {
                        try {
                            s.d("MBInterstitialActivity", "=====showloading");
                            MBInterstitialActivity.this.showLoading();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onRedirectionFailed(Campaign campaign, String str2) {
                        MBInterstitialActivity.this.hideLoading();
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onFinishRedirection(Campaign campaign, String str2) {
                        try {
                            MBInterstitialActivity.this.hideLoading();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.o.c(this.e);
                if (!z || TextUtils.isEmpty(str)) {
                    return;
                }
                a(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void open(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e.setClickURL(str);
        }
        clickTracking(true, str);
    }

    private void a(String str) {
        CampaignEx mraidCampaign = getMraidCampaign();
        if (mraidCampaign != null) {
            new c(getApplicationContext()).b(mraidCampaign.getRequestId(), mraidCampaign.getRequestIdNotice(), mraidCampaign.getId(), this.mUnitid, str, mraidCampaign.isBidCampaign());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void close() {
        finish();
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void unload() {
        close();
    }

    public CampaignEx getMraidCampaign() {
        return this.e;
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void useCustomClose(boolean z) {
        if (z) {
            this.g.setImageDrawable(new ColorDrawable(0));
        } else {
            this.g.setImageResource(m.a(getApplicationContext(), "mbridge_interstitial_close", "drawable"));
        }
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity
    public void setTopControllerPadding(int i, int i2, int i3, int i4, int i5) {
        int iB = w.b(this, 10.0f);
        if (i2 <= 0) {
            i2 = iB;
        }
        if (i3 <= 0) {
            i3 = iB;
        }
        if (i4 <= 0) {
            i4 = iB;
        }
        if (i5 > 0) {
            iB = i5;
        }
        if (this.g != null) {
            int iB2 = w.b(this, 25.0f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iB2, iB2);
            layoutParams.addRule(11);
            layoutParams.setMargins(i2, i4, i3, iB);
            this.g.setLayoutParams(layoutParams);
        }
    }

    static /* synthetic */ void a(MBInterstitialActivity mBInterstitialActivity, int i, String str) {
        if (mBInterstitialActivity.e == null || !mBInterstitialActivity.e.isMraid()) {
            return;
        }
        q qVar = new q();
        qVar.k(mBInterstitialActivity.e.getRequestId());
        qVar.l(mBInterstitialActivity.e.getRequestIdNotice());
        qVar.n(mBInterstitialActivity.e.getId());
        qVar.c(i);
        qVar.q(String.valueOf(System.currentTimeMillis() - mBInterstitialActivity.k));
        qVar.f("");
        qVar.p(str);
        qVar.h(CampaignEx.CLICKMODE_ON);
        qVar.a(mBInterstitialActivity.e.isMraid() ? q.a : q.b);
        com.mbridge.msdk.foundation.same.report.b.b(qVar, mBInterstitialActivity.mUnitid);
    }

    static /* synthetic */ void d(MBInterstitialActivity mBInterstitialActivity) throws JSONException {
        if (mBInterstitialActivity.e == null || !mBInterstitialActivity.e.isMraid()) {
            return;
        }
        String str = "UNDEFINED";
        switch (mBInterstitialActivity.getResources().getConfiguration().orientation) {
            case 0:
                str = "undefined";
                break;
            case 1:
                str = "portrait";
                break;
            case 2:
                str = "landscape";
                break;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("orientation", str);
            jSONObject.put("locked", "true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        float fW = o.w(mBInterstitialActivity);
        float fX = o.x(mBInterstitialActivity);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mBInterstitialActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.widthPixels;
        float f2 = displayMetrics.heightPixels;
        HashMap map = new HashMap();
        map.put("placementType", "Interstitial");
        map.put("state", "default");
        map.put("viewable", "true");
        map.put("currentAppOrientation", jSONObject);
        com.mbridge.msdk.mbjscommon.mraid.a.a().b(mBInterstitialActivity.f, fW, fX);
        com.mbridge.msdk.mbjscommon.mraid.a.a().c(mBInterstitialActivity.f, f, f2);
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBInterstitialActivity.f, map);
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBInterstitialActivity.f, mBInterstitialActivity.j.a());
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBInterstitialActivity.f);
    }

    static /* synthetic */ void h(MBInterstitialActivity mBInterstitialActivity) {
        List<String> pv_urls;
        try {
            com.mbridge.msdk.foundation.controller.a.f().a(mBInterstitialActivity);
            if (!TextUtils.isEmpty(mBInterstitialActivity.e.getImpressionURL())) {
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), mBInterstitialActivity.e, mBInterstitialActivity.mUnitid, mBInterstitialActivity.e.getImpressionURL(), false, true);
            }
            if (!TextUtils.isEmpty(mBInterstitialActivity.e.getOnlyImpressionURL())) {
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), mBInterstitialActivity.e, mBInterstitialActivity.mUnitid, mBInterstitialActivity.e.getOnlyImpressionURL(), false, true);
            }
            com.mbridge.msdk.foundation.same.a.d.b(mBInterstitialActivity.mUnitid, mBInterstitialActivity.e, "interstitial");
            com.mbridge.msdk.foundation.db.m.a(i.a(mBInterstitialActivity)).b(mBInterstitialActivity.e.getId());
            if (mBInterstitialActivity.e == null || (pv_urls = mBInterstitialActivity.e.getPv_urls()) == null || pv_urls.size() <= 0) {
                return;
            }
            Iterator<String> it = pv_urls.iterator();
            while (it.hasNext()) {
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), mBInterstitialActivity.e, mBInterstitialActivity.mUnitid, it.next(), false, true);
            }
        } catch (Throwable th) {
            s.a("MBInterstitialActivity", th.getMessage());
        }
    }

    static /* synthetic */ void j(MBInterstitialActivity mBInterstitialActivity) {
        try {
            q qVar = new q();
            qVar.k(mBInterstitialActivity.e.getRequestId());
            qVar.l(mBInterstitialActivity.e.getRequestIdNotice());
            qVar.n(mBInterstitialActivity.e.getId());
            qVar.a(mBInterstitialActivity.e.isMraid() ? q.a : q.b);
            com.mbridge.msdk.foundation.same.report.b.a(qVar, mBInterstitialActivity.getApplicationContext(), mBInterstitialActivity.mUnitid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void k(MBInterstitialActivity mBInterstitialActivity) {
        if (mBInterstitialActivity.e == null || !mBInterstitialActivity.e.isMraid()) {
            return;
        }
        mBInterstitialActivity.f.post(new Runnable() { // from class: com.mbridge.msdk.interstitial.view.MBInterstitialActivity.5
            @Override // java.lang.Runnable
            public final void run() {
                com.mbridge.msdk.mbjscommon.mraid.a.a().a(MBInterstitialActivity.this.f, MBInterstitialActivity.this.f.getLeft(), MBInterstitialActivity.this.f.getTop(), MBInterstitialActivity.this.f.getWidth(), MBInterstitialActivity.this.f.getHeight());
                com.mbridge.msdk.mbjscommon.mraid.a.a().b(MBInterstitialActivity.this.f, MBInterstitialActivity.this.f.getLeft(), MBInterstitialActivity.this.f.getTop(), MBInterstitialActivity.this.f.getWidth(), MBInterstitialActivity.this.f.getHeight());
            }
        });
    }
}
