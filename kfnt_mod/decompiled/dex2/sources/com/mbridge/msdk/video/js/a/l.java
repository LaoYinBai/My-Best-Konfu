package com.mbridge.msdk.video.js.a;

import android.util.Base64;
import android.webkit.WebView;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.video.module.MBridgeVideoView;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSNotifyProxy.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class l extends e {
    private WebView a;

    public l(WebView webView) {
        this.a = webView;
    }

    @Override // com.mbridge.msdk.video.js.a.e, com.mbridge.msdk.video.js.f
    public final void a(int i) throws JSONException {
        super.a(i);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", i);
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onVideoStatusNotify", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.e, com.mbridge.msdk.video.js.f
    public final void a(int i, String str) throws JSONException {
        super.a(i, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Message.TYPE, i);
            jSONObject.put("pt", str);
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onJSClick", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.e, com.mbridge.msdk.video.js.f
    public final void a(MBridgeVideoView.a aVar) throws JSONException {
        super.a(aVar);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("progress", a(aVar.a, aVar.b));
            jSONObject.put("time", String.valueOf(aVar.a));
            jSONObject.put("duration", String.valueOf(aVar.b));
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "onVideoProgressNotify", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String a(int i, int i2) {
        if (i2 != 0) {
            try {
                return w.a(Double.valueOf(i / i2)) + "";
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return i2 + "";
    }

    @Override // com.mbridge.msdk.video.js.a.e, com.mbridge.msdk.video.js.f
    public final void a(Object obj) {
        super.a(obj);
        String strEncodeToString = "";
        if (obj != null && (obj instanceof String)) {
            strEncodeToString = Base64.encodeToString(obj.toString().getBytes(), 2);
        }
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "webviewshow", strEncodeToString);
    }

    @Override // com.mbridge.msdk.video.js.a.e, com.mbridge.msdk.video.js.f
    public final void a(int i, int i2, int i3, int i4) throws JSONException {
        String str;
        super.a(i, i2, i3, i4);
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            if (i == 2) {
                str = i2 == 1 ? "portrait" : "landscape";
            } else if (i2 == 2) {
                str = "landscape";
            }
            jSONObject2.put("orientation", str);
            jSONObject2.put("screen_width", i3);
            jSONObject2.put("screen_height", i4);
            jSONObject.put("data", jSONObject2);
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(this.a, "showDataInfo", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }
}
