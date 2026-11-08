package com.mbridge.msdk.splash.view;

import android.content.Context;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBSplashWebview extends WindVaneWebView {
    private static final String e = "MBSplashWebview";
    private String f;

    public MBSplashWebview(Context context) {
        super(context);
        setBackgroundColor(0);
    }

    public String getRequestId() {
        return this.f;
    }

    public void setRequestId(String str) {
        this.f = str;
    }
}
