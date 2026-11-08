package com.mbridge.msdk.mbjscommon.mraid;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.mraid.a;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MraidJSBridge extends AbsMbridgeDownload {
    private b a;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.mbridge.msdk.mbjscommon.windvane.j
    public void initialize(Context context, WindVaneWebView windVaneWebView) {
        super.initialize(context, windVaneWebView);
        try {
            if (context instanceof b) {
                this.a = (b) context;
                return;
            }
            if (windVaneWebView.getObject() != null && (windVaneWebView.getObject() instanceof b)) {
                this.a = (b) windVaneWebView.getObject();
            }
            if (windVaneWebView.getMraidObject() == null || !(windVaneWebView.getMraidObject() instanceof b)) {
                return;
            }
            this.a = (b) windVaneWebView.getMraidObject();
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void open(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, "open");
        }
        try {
            String strOptString = new JSONObject(str).optString("url");
            s.d("MraidJSBridge", "MRAID Open " + strOptString);
            if (this.a == null || TextUtils.isEmpty(strOptString)) {
                return;
            }
            this.a.open(strOptString);
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID Open", th);
        }
    }

    public void close(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        }
        try {
            s.d("MraidJSBridge", "MRAID close");
            if (this.a != null) {
                this.a.close();
            }
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID close", th);
        }
    }

    public void unload(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, "unload");
        }
        try {
            s.d("MraidJSBridge", "MRAID unload");
            if (this.a != null) {
                this.a.unload();
            }
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID unload", th);
        }
    }

    public void useCustomClose(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, "useCustomClose");
        }
        try {
            String strOptString = new JSONObject(str).optString("shouldUseCustomClose");
            s.d("MraidJSBridge", "MRAID useCustomClose " + strOptString);
            if (TextUtils.isEmpty(strOptString) || this.a == null) {
                return;
            }
            this.a.useCustomClose(strOptString.toLowerCase().equals("true"));
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID useCustomClose", th);
        }
    }

    public void expand(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, "expand");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            String strOptString2 = jSONObject.optString("shouldUseCustomClose");
            s.d("MraidJSBridge", "MRAID expand " + strOptString + " " + strOptString2);
            if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2) || this.a == null) {
                return;
            }
            this.a.expand(strOptString, strOptString2.toLowerCase().equals("true"));
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID expand", th);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0069. Please report as an issue. */
    public void setOrientationProperties(Object obj, String str) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            a.C0121a.a.a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a, "setOrientationProperties");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("allowOrientationChange");
            String strOptString2 = jSONObject.optString("forceOrientation");
            s.d("MraidJSBridge", "MRAID setOrientationProperties");
            if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2) || this.a == null) {
                return;
            }
            strOptString.toLowerCase().equals("true");
            String lowerCase = strOptString2.toLowerCase();
            char c = 65535;
            int iHashCode = lowerCase.hashCode();
            if (iHashCode != 729267099) {
                if (iHashCode == 1430647483 && lowerCase.equals("landscape")) {
                    c = 1;
                }
            } else if (lowerCase.equals("portrait")) {
                c = 0;
            }
            switch (c) {
            }
        } catch (Throwable th) {
            s.b("MraidJSBridge", "MRAID setOrientationProperties", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        open(obj, str);
    }
}
