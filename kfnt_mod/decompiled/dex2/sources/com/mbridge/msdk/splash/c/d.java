package com.mbridge.msdk.splash.c;

import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.splash.view.MBSplashView;
import com.mbridge.msdk.splash.view.MBSplashWebview;
import java.util.ArrayList;

/* compiled from: WebViewRenderManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private boolean a;
    private boolean b;

    /* compiled from: WebViewRenderManager.java */
    public interface b {
        void a();

        void a(int i);

        void a(String str);
    }

    private d() {
        this.a = false;
        this.b = false;
    }

    /* compiled from: WebViewRenderManager.java */
    static class a {
        private static final d a = new d();
    }

    public final void a() {
        this.a = false;
        this.b = false;
    }

    public final void a(final MBSplashView mBSplashView, c cVar, final b bVar) {
        String requestId;
        if (mBSplashView == null || cVar == null) {
            return;
        }
        final String strC = cVar.c();
        String strB = cVar.b();
        final CampaignEx campaignExD = cVar.d();
        String strA = cVar.a();
        boolean zE = cVar.e();
        int iF = cVar.f();
        MBSplashWebview splashWebview = mBSplashView.getSplashWebview();
        if (splashWebview == null) {
            return;
        }
        com.mbridge.msdk.splash.js.b bVar2 = new com.mbridge.msdk.splash.js.b(mBSplashView.getContext(), strB, strC);
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignExD);
        bVar2.a(arrayList);
        bVar2.a(zE ? 1 : 0);
        bVar2.b(iF);
        mBSplashView.setSplashJSBridgeImpl(bVar2);
        if (TextUtils.isEmpty(campaignExD.getRequestId())) {
            requestId = campaignExD.getRequestIdNotice();
        } else {
            requestId = campaignExD.getRequestId();
        }
        String requestId2 = splashWebview.getRequestId();
        s.d("WebViewRenderManager", "CampaignEx RequestId = " + requestId + " WebView RequestId = " + requestId2);
        if (!TextUtils.isEmpty(requestId2) && requestId2.equals(requestId) && (this.a || this.b)) {
            mBSplashView.setH5Ready(true);
            if (bVar != null) {
                bVar.a(1);
                return;
            }
            return;
        }
        a();
        splashWebview.setRequestId(requestId);
        com.mbridge.msdk.splash.a.b bVarA = com.mbridge.msdk.splash.a.b.a().c(campaignExD.getId()).d(campaignExD.getRequestId()).g(campaignExD.getRequestIdNotice()).b(strC).a(campaignExD.isBidCampaign());
        if (bVarA != null) {
            bVarA.a("2000067");
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(bVarA.b());
            } else {
                com.mbridge.msdk.foundation.same.report.b.a(bVarA.b(), com.mbridge.msdk.foundation.controller.a.f().j(), strC);
            }
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        splashWebview.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.splash.c.d.1
            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i) {
                super.a(webView, i);
                if (bVar != null) {
                    bVar.a(i);
                }
                if (i == 1) {
                    d.this.a = true;
                    mBSplashView.setH5Ready(true);
                    s.a("WindVaneWebView", "======渲染成功：ready");
                    com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "", jCurrentTimeMillis, 1);
                    com.mbridge.msdk.splash.e.a.a(campaignExD, strC);
                    return;
                }
                d.this.a = false;
                mBSplashView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.splash.e.a.b(campaignExD, "readyState 2", strC);
                com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "readyState 2", jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i, String str, String str2) {
                super.a(webView, i, str, str2);
                if (bVar != null) {
                    bVar.a(str);
                }
                d.this.a();
                mBSplashView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.splash.e.a.b(campaignExD, "error code:" + i + str, strC);
                com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "error code:" + i + str, jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.a(webView, sslErrorHandler, sslError);
                if (bVar != null) {
                    bVar.a(sslError.toString());
                }
                d.this.a();
                mBSplashView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.splash.e.a.b(campaignExD, "error url:" + sslError.getUrl(), strC);
                com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "error url:" + sslError.getUrl(), jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, String str) {
                super.a(webView, str);
                if (bVar != null) {
                    bVar.a();
                }
                d.this.b = true;
                if (!campaignExD.isHasMBTplMark()) {
                    mBSplashView.setH5Ready(true);
                    s.a("WindVaneWebView", "======渲染成功：finish");
                    com.mbridge.msdk.splash.e.a.a(campaignExD, strC);
                    com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "", jCurrentTimeMillis, 1);
                }
                com.mbridge.msdk.splash.js.c.a(webView);
            }
        });
        if (!splashWebview.isDestoryed()) {
            s.a("WebViewRenderManager", "======开始渲染：" + strA);
            splashWebview.loadUrl(strA);
            return;
        }
        mBSplashView.setH5Ready(false);
        com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignExD, strC, "webview had destory", jCurrentTimeMillis, 3);
    }

    /* compiled from: WebViewRenderManager.java */
    public static class c {
        private String a;
        private String b;
        private CampaignEx c;
        private String d;
        private boolean e;
        private int f;

        public final String a() {
            return this.d;
        }

        public final void a(String str) {
            this.d = str;
        }

        public final String b() {
            return this.a;
        }

        public final void b(String str) {
            this.a = str;
        }

        public final String c() {
            return this.b;
        }

        public final void c(String str) {
            this.b = str;
        }

        public final CampaignEx d() {
            return this.c;
        }

        public final void a(CampaignEx campaignEx) {
            this.c = campaignEx;
        }

        public final boolean e() {
            return this.e;
        }

        public final void a(boolean z) {
            this.e = z;
        }

        public final int f() {
            return this.f;
        }

        public final void a(int i) {
            this.f = i;
        }
    }
}
