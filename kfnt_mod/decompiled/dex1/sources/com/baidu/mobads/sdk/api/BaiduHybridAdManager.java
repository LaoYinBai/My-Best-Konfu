package com.baidu.mobads.sdk.api;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import com.baidu.mobads.sdk.internal.cq;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduHybridAdManager {
    private cq mAdProd;
    private String mAppSid;
    private WebView mWebView;
    private BaiduHybridAdViewListener mBaiduHybridAdViewListener = new BaiduHybridAdViewListener() { // from class: com.baidu.mobads.sdk.api.BaiduHybridAdManager.1
        @Override // com.baidu.mobads.sdk.api.BaiduHybridAdViewListener
        public void onAdClick(int i, String str) {
        }

        @Override // com.baidu.mobads.sdk.api.BaiduHybridAdViewListener
        public void onAdFailed(int i, String str, String str2) {
        }

        @Override // com.baidu.mobads.sdk.api.BaiduHybridAdViewListener
        public void onAdShow(int i, String str) {
        }
    };
    private boolean mAppPageReceivedError = false;

    public void setBaiduHybridAdViewListener(BaiduHybridAdViewListener baiduHybridAdViewListener) {
        this.mBaiduHybridAdViewListener = baiduHybridAdViewListener;
    }

    public void injectJavaScriptBridge(WebView webView) throws JSONException {
        if (this.mAppPageReceivedError) {
            return;
        }
        this.mWebView = webView;
        this.mAdProd = new cq(this.mWebView);
        this.mAdProd.f(this.mAppSid);
        this.mAdProd.a(this.mBaiduHybridAdViewListener);
        this.mAdProd.b_();
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.mAppPageReceivedError = false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!this.mAppPageReceivedError && this.mAdProd != null) {
            this.mAdProd.b(webView, str);
        }
        try {
            Uri uri = Uri.parse(str);
            if ("mobadssdk".equals(uri.getScheme())) {
                return true;
            }
            return "mobads".equals(uri.getScheme());
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.mAppPageReceivedError = true;
    }

    public void setAppSid(String str) {
        this.mAppSid = str;
    }
}
