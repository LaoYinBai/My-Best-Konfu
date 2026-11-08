package com.mbridge.msdk.video.bt.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.c.a;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.mbjscommon.mraid.b;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.videocommon.d.c;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeBTWebView extends BTBaseView implements b {
    private String p;
    private String q;
    private String r;
    private boolean s;
    private ImageView t;
    private boolean u;
    private c v;
    private List<CampaignEx> w;
    private WindVaneWebView x;
    private j y;
    private WebView z;

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void expand(String str, boolean z) {
    }

    public c getRewardUnitSetting() {
        return this.v;
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void setRewardUnitSetting(c cVar) {
        this.v = cVar;
    }

    public List<CampaignEx> getCampaigns() {
        return this.w;
    }

    public void setCampaigns(List<CampaignEx> list) {
        this.w = list;
    }

    public void setCreateWebView(WebView webView) {
        this.z = webView;
    }

    public String getFileURL() {
        return this.p;
    }

    public void setFileURL(String str) {
        this.p = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean zContains = str.contains("play.google.com");
        setWebviewClickable(!zContains);
        if (zContains) {
            com.mbridge.msdk.mbjscommon.base.c cVar = new com.mbridge.msdk.mbjscommon.base.c();
            if (this.x != null) {
                this.x.setFilter(cVar);
            }
        }
    }

    public String getFilePath() {
        return this.q;
    }

    public void setFilePath(String str) {
        this.q = str;
    }

    public String getHtml() {
        return this.r;
    }

    public void setHtml(String str) {
        this.r = str;
    }

    public WindVaneWebView getWebView() {
        return this.x;
    }

    public MBridgeBTWebView(Context context) {
        super(context);
        this.s = false;
        this.u = false;
    }

    public MBridgeBTWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = false;
        this.u = false;
    }

    public void setWebviewClickable(boolean z) {
        if (this.x != null) {
            this.x.setClickable(z);
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void init(Context context) {
        this.x = new WindVaneWebView(context);
        this.x.setBackgroundColor(0);
        this.x.setVisibility(0);
        this.y = new j(null, this.b, this.w);
        this.y.a(this.c);
        this.y.c(false);
        this.x.setObject(this.y);
        this.x.setMraidObject(this);
        this.x.setWebViewListener(new a() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTWebView.1
            @Override // com.mbridge.msdk.mbjscommon.c.a
            public final void a(Object obj) {
                String strEncodeToString;
                super.a(obj);
                try {
                    String strD = MBridgeBTWebView.this.y != null ? MBridgeBTWebView.this.y.d() : "";
                    if (!TextUtils.isEmpty(strD)) {
                        strEncodeToString = Base64.encodeToString(strD.getBytes(), 2);
                        s.a("RVWindVaneWebView", "getEndScreenInfo success");
                    } else {
                        strEncodeToString = "";
                        s.a("RVWindVaneWebView", "getEndScreenInfo failed");
                    }
                    h.a().a(obj, strEncodeToString);
                } catch (Throwable th) {
                    s.a("RVWindVaneWebView", th.getMessage());
                }
            }

            @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, String str) throws JSONException {
                super.a(webView, str);
                if (MBridgeBTWebView.this.z != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", MBridgeBTWebView.this.d);
                        jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("id", MBridgeBTWebView.this.d);
                        jSONObject2.put("result", 1);
                        jSONObject.put("data", jSONObject2);
                        h.a().a(MBridgeBTWebView.this.z, "onWebviewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception e) {
                        com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.z, e.getMessage());
                        s.a("RVWindVaneWebView", e.getMessage());
                    }
                }
                h.a().a(MBridgeBTWebView.this.x);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i, String str, String str2) throws JSONException {
                super.a(webView, i, str, str2);
                if (MBridgeBTWebView.this.z != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", MBridgeBTWebView.this.d);
                        jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("id", MBridgeBTWebView.this.d);
                        jSONObject2.put("result", 2);
                        jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str);
                        jSONObject.put("data", jSONObject2);
                        h.a().a(MBridgeBTWebView.this.z, "onWebviewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception e) {
                        com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.z, e.getMessage());
                        s.a("RVWindVaneWebView", e.getMessage());
                    }
                }
            }

            @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) throws JSONException {
                super.a(webView, sslErrorHandler, sslError);
                if (MBridgeBTWebView.this.z != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", MBridgeBTWebView.this.d);
                        jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("id", MBridgeBTWebView.this.d);
                        jSONObject2.put("result", 2);
                        jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, sslError.toString());
                        jSONObject.put("data", jSONObject2);
                        h.a().a(MBridgeBTWebView.this.z, "onWebviewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception e) {
                        com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.z, e.getMessage());
                        s.a("RVWindVaneWebView", e.getMessage());
                    }
                }
            }

            @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i) {
                super.a(webView, i);
            }
        });
        addView(this.x, new FrameLayout.LayoutParams(-1, -1));
        setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTWebView.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws JSONException {
                if (MBridgeBTWebView.this.z != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                        jSONObject.put("id", MBridgeBTWebView.this.d);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("x", String.valueOf(view.getX()));
                        jSONObject2.put("y", String.valueOf(view.getY()));
                        jSONObject.put("data", jSONObject2);
                        h.a().a(MBridgeBTWebView.this.z, "onClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception unused) {
                        com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.z, "onClicked", MBridgeBTWebView.this.d);
                    }
                }
            }
        });
        try {
            this.t = new ImageView(getContext());
            this.t.setImageResource(findDrawable("mbridge_reward_close"));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(96, 96);
            layoutParams.gravity = 8388661;
            layoutParams.setMargins(30, 30, 30, 30);
            this.t.setLayoutParams(layoutParams);
            this.t.setVisibility(this.s ? 4 : 8);
            if (this.b != null && this.b.isMraid()) {
                this.t.setVisibility(4);
            }
            this.t.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTWebView.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MBridgeBTWebView.this.close();
                }
            });
            addView(this.t);
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    public void setWebViewRid(String str) {
        if (this.x != null) {
            this.x.setRid(str);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            Activity activityD = com.mbridge.msdk.video.bt.a.c.a().d(this.c + "_" + this.b.getRequestId());
            if (activityD != null) {
                this.y.a(activityD);
            }
        }
    }

    public void preload() {
        if (!TextUtils.isEmpty(this.p)) {
            this.x.loadUrl(this.p);
        } else if (!TextUtils.isEmpty(this.q)) {
            this.x.loadUrl(this.q);
        } else {
            if (TextUtils.isEmpty(this.r)) {
                return;
            }
            this.x.loadDataWithBaseURL("", this.r, "text/html", C.UTF8_NAME, null);
        }
    }

    public void webviewLoad(int i) {
        if (this.y == null) {
            this.y = new j(null, this.b, this.w);
        }
        if (this.b != null) {
            this.y.a(this.b);
        } else if (this.w != null && this.w.size() > 0) {
            this.y.a(this.w);
            if (this.w.size() == 1) {
                this.y.a(this.w.get(0));
            }
        }
        if (this.v != null) {
            this.y.a(this.v);
        }
        this.y.a(this.c);
        this.y.c(this.d);
        this.y.c(false);
        if (i == 1) {
            this.y.o();
        }
        if (this.x != null) {
            this.x.setObject(this.y);
        }
        if (this.b != null && this.b.isMraid()) {
            this.t.setVisibility(4);
        }
        preload();
    }

    public boolean webviewReload() {
        if (this.x == null) {
            return false;
        }
        this.x.reload();
        return true;
    }

    public boolean webviewGoBack() {
        if (this.x == null || !this.x.canGoBack()) {
            return false;
        }
        this.x.goBack();
        return true;
    }

    public boolean webviewGoForward() {
        if (this.x == null || !this.x.canGoForward()) {
            return false;
        }
        this.x.goForward();
        return true;
    }

    public void broadcast(String str, JSONObject jSONObject) throws JSONException {
        if (this.x != null) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(CommandMessage.CODE, n);
                jSONObject2.put("id", this.d);
                jSONObject2.put("eventName", str);
                jSONObject2.put("data", jSONObject);
                h.a().a((WebView) this.x, "broadcast", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            } catch (Exception unused) {
                com.mbridge.msdk.video.bt.a.c.a().a((WebView) this.x, "broadcast", this.d);
            }
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView, android.view.View
    public void onConfigurationChanged(Configuration configuration) throws JSONException {
        if (this.x != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (configuration.orientation == 2) {
                    jSONObject.put("orientation", "landscape");
                } else {
                    jSONObject.put("orientation", "portrait");
                }
                jSONObject.put("instanceId", this.d);
                h.a().a((WebView) this.x, "orientation", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackPressed() throws JSONException {
        if (this.x != null) {
            com.mbridge.msdk.video.bt.a.c.a().a((WebView) this.x, "onSystemBackPressed", this.d);
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void onDestory() {
        if (this.u) {
            return;
        }
        this.u = true;
        try {
            if (this.x != null) {
                com.mbridge.msdk.video.bt.a.c.a().a((WebView) this.x, "onSystemDestory", this.d);
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTWebView.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (MBridgeBTWebView.this.x != null) {
                            MBridgeBTWebView.this.x.clearWebView();
                            MBridgeBTWebView.this.x.release();
                        }
                        MBridgeBTWebView.this.p = null;
                        MBridgeBTWebView.this.q = null;
                        MBridgeBTWebView.this.r = null;
                        if (MBridgeBTWebView.this.z != null) {
                            MBridgeBTWebView.this.z = null;
                        }
                    } catch (Throwable th) {
                        s.a(BTBaseView.TAG, th.getMessage());
                    }
                }
            }, 500L);
            setOnClickListener(null);
            removeAllViews();
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void open(final String str) {
        try {
            if (this.b != null && this.b.needShowIDialog() && com.mbridge.msdk.click.c.a(this.b)) {
                if (new com.mbridge.msdk.click.b(getContext(), this.c).b(this.b)) {
                    return;
                }
                this.b.getAppName();
                try {
                    com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTWebView.5
                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void a() {
                            MBridgeBTWebView.this.a(str);
                            if (MBridgeBTWebView.this.b != null) {
                                com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.c, MBridgeBTWebView.this.b.getRequestId(), "onInstallAlertHide");
                            }
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void b() {
                            if (MBridgeBTWebView.this.b != null) {
                                com.mbridge.msdk.video.bt.a.c.a().a(MBridgeBTWebView.this.c, MBridgeBTWebView.this.b.getRequestId(), "onInstallAlertHide");
                            }
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void c() {
                            MBridgeBTWebView.this.a(str);
                        }
                    };
                    Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                    if (contextJ == null || contextJ == contextJ.getApplicationContext()) {
                        contextJ = com.mbridge.msdk.video.bt.a.c.a().d(this.c + "_" + this.b.getRequestId());
                    }
                    Context context = contextJ;
                    if (context == null || this.b == null) {
                        return;
                    }
                    com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.c, false);
                    e.a().a(c.a, this.b, context, this.c, aVar);
                    com.mbridge.msdk.video.bt.a.c.a().a(this.c, this.b.getRequestId(), "onInstallAlertShow");
                    return;
                } catch (Throwable th) {
                    s.a(BTBaseView.TAG, th.getMessage());
                    a(str);
                    return;
                }
            }
            a(str);
        } catch (Throwable th2) {
            s.d(BTBaseView.TAG, th2.getMessage());
            a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            String clickURL = this.b.getClickURL();
            if (!TextUtils.isEmpty(str)) {
                this.b.setClickURL(str);
                b(str);
            }
            new com.mbridge.msdk.click.b(getContext(), this.c).c(this.b);
            this.b.setClickURL(clickURL);
        } catch (Exception e) {
            s.d(BTBaseView.TAG, e.getMessage());
        }
    }

    private void b(String str) {
        try {
            CampaignEx mraidCampaign = getMraidCampaign();
            if (mraidCampaign != null) {
                new com.mbridge.msdk.foundation.same.report.c(getContext()).b(mraidCampaign.getRequestId(), mraidCampaign.getRequestIdNotice(), mraidCampaign.getId(), this.c, str, this.b.isBidCampaign());
            }
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void close() {
        if (this.z != null) {
            a(this.z, "onPlayerCloseBtnClicked", this.d);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void unload() {
        close();
    }

    public CampaignEx getMraidCampaign() {
        return this.b;
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void useCustomClose(boolean z) {
        try {
            this.t.setVisibility(z ? 4 : 0);
        } catch (Throwable th) {
            s.d(BTBaseView.TAG, th.getMessage());
        }
    }

    public void notifyEvent(String str) {
        if (this.x != null) {
            a(this.x, str, this.d);
        }
    }
}
