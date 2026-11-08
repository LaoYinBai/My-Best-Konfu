package com.mbridge.msdk.nativex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.nativex.listener.b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class WindVaneWebViewForNV extends WindVaneWebView {
    private static String e = "WindVaneWebViewForNV";
    private b f;
    private boolean g;

    public void setInterceptTouch(boolean z) {
        this.g = z;
    }

    public void setBackListener(b bVar) {
        this.f = bVar;
    }

    public WindVaneWebViewForNV(Context context) {
        super(context);
        this.g = false;
    }

    public WindVaneWebViewForNV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
    }

    public WindVaneWebViewForNV(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView, android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        s.a(e, "====webview 返回keyCode:" + i);
        if (i == 4) {
            s.a(e, "====webview 返回");
            if (this.f != null) {
                this.f.a();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void webViewShow(CampaignEx campaignEx, String str) {
        try {
            s.a(e, "webviewshow");
            h.a().a((WebView) this, "webviewshow", "");
            q qVar = new q();
            qVar.k(campaignEx.getRequestId());
            qVar.l(campaignEx.getRequestIdNotice());
            if (getContext() != null && getContext().getApplicationContext() != null) {
                qVar.n(campaignEx.getId());
                qVar.a(campaignEx.isMraid() ? q.a : q.b);
                com.mbridge.msdk.foundation.same.report.b.a(qVar, getContext().getApplicationContext(), str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void orientation(boolean z) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            if (z) {
                jSONObject.put("orientation", "landscape");
            } else {
                jSONObject.put("orientation", "portrait");
            }
            h.a().a((WebView) this, "orientation", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
