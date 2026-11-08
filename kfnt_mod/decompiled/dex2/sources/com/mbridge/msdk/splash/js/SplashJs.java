package com.mbridge.msdk.splash.js;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SplashJs extends AbsMbridgeDownload {
    private static String a = "SplashJs";
    private b b;

    @Override // com.mbridge.msdk.mbjscommon.windvane.j
    public void initialize(Context context, WindVaneWebView windVaneWebView) {
        super.initialize(context, windVaneWebView);
        try {
            if (windVaneWebView.getObject() == null || !(windVaneWebView.getObject() instanceof b)) {
                return;
            }
            this.b = (b) windVaneWebView.getObject();
        } catch (Throwable th) {
            s.b(a, "initialize", th);
        }
    }

    public void init(Object obj, String str) {
        s.d(a, "initialize" + str);
        if (this.b != null) {
            this.b.a(obj, str);
        }
    }

    public void toggleCloseBtn(Object obj, String str) {
        s.d(a, "toggleCloseBtn" + str);
        if (this.b != null) {
            this.b.b(obj, str);
        }
    }

    public void triggerCloseBtn(Object obj, String str) {
        s.d(a, "triggerCloseBtn" + str);
        if (this.b != null) {
            this.b.c(obj, str);
        }
    }

    public void readyStatus(Object obj, String str) {
        if (obj != null) {
            try {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                int iOptInt = new JSONObject(str).optInt("isReady", 1);
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        windVaneWebView.getWebViewListener().a(windVaneWebView, iOptInt);
                    }
                }
            } catch (Throwable th) {
                s.b(a, "readyStatus", th);
            }
        }
    }

    public void install(Object obj, String str) {
        String strAddDownloaderListener = addDownloaderListener(obj, str);
        if (this.b != null) {
            this.b.d(obj, strAddDownloaderListener);
        }
    }

    public void resetCountdown(Object obj, String str) throws JSONException {
        s.d(a, "resetCountdown" + str);
        if (this.b != null) {
            this.b.h(obj, str);
        }
    }

    public void openURL(Object obj, String str) throws JSONException {
        s.d(a, "openURL" + str);
        if (this.b != null) {
            this.b.e(obj, str);
        }
    }

    public void cai(Object obj, String str) throws JSONException {
        s.d(a, "cai" + str);
        if (this.b != null) {
            this.b.f(obj, str);
        }
    }

    public void gial(Object obj, String str) throws JSONException {
        s.d(a, "gial" + str);
        if (this.b != null) {
            this.b.g(obj, str);
        }
    }

    public void handlerH5Exception(Object obj, String str) {
        if (obj != null) {
            try {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        windVaneWebView.getWebViewListener().a(windVaneWebView, 0, str.toString(), windVaneWebView.getUrl());
                    }
                }
            } catch (Throwable th) {
                s.b(a, "handlerH5Exception", th);
            }
        }
    }

    public void getFileInfo(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            c.a(obj, new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendImpressions(Object obj, String str) {
        try {
            s.d(a, "sendImpressions");
            if (this.b != null) {
                this.b.i(obj, str);
            }
        } catch (Throwable th) {
            s.b(a, "sendImpressions", th);
        }
    }

    public void reportUrls(Object obj, String str) {
        try {
            s.d(a, "reportUrls");
            if (this.b != null) {
                this.b.j(obj, str);
            }
        } catch (Throwable th) {
            s.b(a, "reportUrls", th);
        }
    }

    public void increaseOfferFrequence(Object obj, String str) {
        try {
            c.b(obj, new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onJSBridgeConnect(Object obj, String str) {
        try {
            s.d(a, "onJSBridgeConnect");
            if (this.b != null) {
                this.b.k(obj, str);
            }
        } catch (Throwable th) {
            s.b(a, "onJSBridgeConnect", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        install(obj, str);
    }

    public void pauseCountDown(Object obj, String str) {
        try {
            s.d(a, "pauseCountDown");
            if (this.b != null) {
                this.b.l(obj, str);
            }
        } catch (Throwable th) {
            s.b(a, "pauseCountDown", th);
        }
    }

    public void resumeCountDown(Object obj, String str) {
        try {
            s.d(a, "resumeCountDown");
            if (this.b != null) {
                this.b.m(obj, str);
            }
        } catch (Throwable th) {
            s.b(a, "resumeCountDown", th);
        }
    }
}
