package com.mbridge.msdk.mbjscommon.webEnvCheck;

import android.content.Context;
import com.mbridge.msdk.b.b.c;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class WebEnvCheckEntry {
    public void check(Context context) {
        new WindVaneWebView(context).loadDataWithBaseURL(null, "<html><script>" + c.a().b() + "</script></html>", "text/html", "utf-8", null);
    }
}
