package com.mbridge.msdk.video.module;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.same.report.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.webview.BrowserView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.videocommon.d.b;
import com.mbridge.msdk.videocommon.d.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeAlertWebview extends MBridgeH5EndCardView {
    private String t;

    public MBridgeAlertWebview(Context context) {
        super(context);
    }

    public MBridgeAlertWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final String a() {
        if (TextUtils.isEmpty(this.q)) {
            return "";
        }
        b.a().a(a.f().k(), this.q, false);
        this.t = c.a;
        return !TextUtils.isEmpty(this.t) ? H5DownLoadManager.getInstance().getH5ResAddress(this.t) : "";
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void preLoadData(com.mbridge.msdk.video.js.factory.b bVar) {
        String strA = a();
        if (this.f && this.b != null && !TextUtils.isEmpty(strA)) {
            BrowserView.MBDownloadListener mBDownloadListener = new BrowserView.MBDownloadListener(this.b);
            mBDownloadListener.setTitle(this.b.getAppName());
            this.l.setDownloadListener(mBDownloadListener);
            this.l.setCampaignId(this.b.getId());
            setCloseVisible(8);
            this.l.setApiManagerJSFactory(bVar);
            this.l.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.video.module.MBridgeAlertWebview.1
                @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
                public final void a(WebView webView, String str) {
                    super.a(webView, str);
                    s.d("MBridgeAlertWebview", "===========finish+" + str);
                    h.a().a(webView, "onJSBridgeConnected", "");
                }

                @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
                public final void a(WebView webView, int i, String str, String str2) {
                    super.a(webView, i, str, str2);
                    s.d("MBridgeAlertWebview", "===========onReceivedError");
                    if (MBridgeAlertWebview.this.p) {
                        return;
                    }
                    s.a(MBridgeBaseView.TAG, "onReceivedError,url:" + str2);
                    d.a(MBridgeAlertWebview.this.a, MBridgeAlertWebview.this.b, MBridgeAlertWebview.this.t, MBridgeAlertWebview.this.q, 2, str, 1);
                    MBridgeAlertWebview.this.p = true;
                }

                @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
                public final void a(WebView webView, int i) {
                    super.a(webView, i);
                    s.d("MBridgeAlertWebview", "===========readyState  :  " + i);
                    if (MBridgeAlertWebview.this.p) {
                        return;
                    }
                    MBridgeAlertWebview.this.o = i == 1;
                    String str = "";
                    if (MBridgeAlertWebview.this.o) {
                        str = "readyState state is " + i;
                    }
                    d.a(MBridgeAlertWebview.this.a, MBridgeAlertWebview.this.b, MBridgeAlertWebview.this.t, MBridgeAlertWebview.this.q, i, str, 1);
                }
            });
            setHtmlSource(HTMLResourceManager.getInstance().getHtmlContentFromUrl(strA));
            this.o = false;
            if (TextUtils.isEmpty(this.n)) {
                s.a(MBridgeBaseView.TAG, "load url:" + strA);
                this.l.loadUrl(strA);
            } else {
                s.a(MBridgeBaseView.TAG, "load html...");
                this.l.loadDataWithBaseURL(strA, this.n, "text/html", C.UTF8_NAME, null);
            }
            this.l.setBackgroundColor(0);
            setBackgroundColor(0);
            return;
        }
        this.e.a(101, "");
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void webviewshow() {
        if (this.j != null) {
            this.j.setBackgroundColor(0);
        }
        super.webviewshow();
        d.a(this.a, this.b, this.t, this.q, 2, 1);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final RelativeLayout.LayoutParams b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        return layoutParams;
    }
}
