package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.baidu.mobads.sdk.api.BaiduHybridAdViewListener;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IXHybridAdRenderer;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cq extends ar {
    private WebView o;
    private BaiduHybridAdViewListener p;
    private IXHybridAdRenderer q;

    public cq(WebView webView) {
        super(webView.getContext());
        this.o = webView;
        this.o.removeJavascriptInterface("searchBoxJavaBridge_");
        this.o.removeJavascriptInterface("accessibility");
        this.o.removeJavascriptInterface("accessibilityTraversal");
        this.o.getSettings().setAllowContentAccess(false);
        this.o.getSettings().setSavePassword(false);
        this.o.getSettings().setAllowFileAccess(false);
    }

    public void a(BaiduHybridAdViewListener baiduHybridAdViewListener) {
        this.p = baiduHybridAdViewListener;
    }

    public void a(WebView webView, String str, Bitmap bitmap) {
        if (this.q != null) {
            this.q.onPageStarted(webView, str, bitmap);
        }
    }

    public void a(WebView webView, String str) {
        if (this.q != null) {
            this.q.onPageFinished(webView, str);
        }
    }

    public void a(WebView webView, int i, String str, String str2) {
        if (this.q != null) {
            this.q.onReceivedError(webView, i, str, str2);
        }
    }

    public boolean b(WebView webView, String str) {
        if (this.q == null) {
            return false;
        }
        return this.q.shouldOverrideUrlLoading(webView, str);
    }

    public void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.q != null) {
            this.q.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void i() throws JSONException {
        this.q = (IXHybridAdRenderer) aj.a(r.j, ba.a(this.g), (Class<?>[]) new Class[]{Context.class}, this.g);
        this.j = this.q;
        if (this.k) {
            return;
        }
        b_();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() throws JSONException {
        if (this.q == null) {
            this.k = false;
            return;
        }
        this.k = true;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_JSSDK);
            this.q.createProdHandler(jSONObject3);
            n();
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            jSONObject.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_JSSDK);
            jSONObject2.put("timeout", NetCallback.ERROR_CODE_REQUEST_SUCCESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.q.loadAd(jSONObject, jSONObject2);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        this.q.setCustomerWebView(this.o);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        super.q();
        if (this.p != null) {
            this.p.onAdShow(0, null);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        super.h(iOAdEvent);
        if (this.p != null) {
            this.p.onAdClick(0, null);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        super.a(str, i);
        if (this.p != null) {
            this.p.onAdFailed(0, "", str);
        }
    }
}
