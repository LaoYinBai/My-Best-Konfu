package com.onevcat.uniwebview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class VideoEnabledWebView extends WebView {
    private VideoEnabledWebChromeClient videoEnabledWebChromeClient;

    public void notifyVideoEnd() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.onevcat.uniwebview.VideoEnabledWebView.1
            @Override // java.lang.Runnable
            public void run() {
                if (VideoEnabledWebView.this.videoEnabledWebChromeClient != null) {
                    VideoEnabledWebView.this.videoEnabledWebChromeClient.onHideCustomView();
                }
            }
        });
    }

    public VideoEnabledWebView(Context context) {
        super(context);
    }

    public VideoEnabledWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoEnabledWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean isVideoFullscreen() {
        return this.videoEnabledWebChromeClient != null && this.videoEnabledWebChromeClient.isVideoFullscreen();
    }

    @Override // android.webkit.WebView
    @SuppressLint({"SetJavaScriptEnabled"})
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        getSettings().setJavaScriptEnabled(true);
        if (webChromeClient instanceof VideoEnabledWebChromeClient) {
            this.videoEnabledWebChromeClient = (VideoEnabledWebChromeClient) webChromeClient;
        }
        super.setWebChromeClient(webChromeClient);
    }
}
