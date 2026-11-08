package com.mbridge.msdk.video.js.a;

import android.content.res.Configuration;
import android.util.Base64;
import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSActivityProxy.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h extends a {
    private WebView a;
    private int b = 0;

    public h(WebView webView) {
        this.a = webView;
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void a() {
        super.a();
        this.b = 1;
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onSystemPause", "");
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void b() {
        super.b();
        this.b = 0;
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onSystemResume", "");
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void c() {
        super.c();
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onSystemDestory", "");
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void a(Configuration configuration) throws JSONException {
        super.a(configuration);
        try {
            JSONObject jSONObject = new JSONObject();
            if (configuration.orientation == 2) {
                jSONObject.put("orientation", "landscape");
            } else {
                jSONObject.put("orientation", "portrait");
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "orientation", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void g() {
        super.g();
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onSystemBackPressed", "");
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final void a(int i) {
        super.a(i);
        this.b = i;
    }

    @Override // com.mbridge.msdk.video.js.a.a, com.mbridge.msdk.video.js.a
    public final int h() {
        return this.b;
    }
}
