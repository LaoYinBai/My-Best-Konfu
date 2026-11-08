package com.mbridge.msdk.mbjscommon.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CommonBannerJSBridgeImp.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements c {
    public static final String a = "a";

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void f(Object obj, String str) {
        try {
            if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
                h.a().a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a);
            }
        } catch (Throwable th) {
            s.b(a, "onJSBridgeConnect", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void c(Object obj, String str) {
        s.a(a, "readyStatus: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void a(Object obj, String str) {
        s.a(a, "init: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void b(Object obj, String str) {
        s.a(a, "click: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void d(Object obj, String str) {
        s.a(a, "toggleCloseBtn: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void e(Object obj, String str) {
        s.a(a, "triggerCloseBtn: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void i(Object obj, String str) {
        s.a(a, "sendImpressions: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void k(Object obj, String str) throws JSONException {
        s.a(a, "reportUrls:" + str);
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), (CampaignEx) null, "", jSONObject.optString("url"), false, jSONObject.optInt(Message.TYPE) != 0);
            }
            h.a().a(obj, b.a(0));
        } catch (Throwable th) {
            s.b(a, "reportUrls", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void l(Object obj, String str) throws JSONException {
        s.a(a, "increaseOfferFrequence:" + str);
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is null");
        } else {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                b.a(obj, new JSONObject(str));
            } catch (Throwable th) {
                s.b(a, "increaseOfferFrequence", th);
            }
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void h(Object obj, String str) {
        s.a(a, "resetCountdown: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void m(Object obj, String str) {
        s.a(a, "handlerH5Exception: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void g(Object obj, String str) {
        s.a(a, "install: " + str);
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void n(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView;
        s.d(a, "openURL:" + str);
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is null");
            return;
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (contextJ == null) {
            try {
                if ((obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) && (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) != null) {
                    contextJ = windVaneWebView.getContext();
                }
            } catch (Exception e) {
                s.d(a, e.getMessage());
            }
        }
        if (contextJ == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            int iOptInt = jSONObject.optInt(Message.TYPE);
            if (iOptInt == 1) {
                com.mbridge.msdk.click.c.a(contextJ, strOptString);
            } else if (iOptInt == 2) {
                com.mbridge.msdk.click.c.b(contextJ, strOptString);
            }
        } catch (JSONException e2) {
            s.d(a, e2.getMessage());
        } catch (Throwable th) {
            s.d(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void o(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView;
        s.d(a, "getNetstat:" + str);
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is null");
            return;
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (contextJ == null) {
            try {
                if ((obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) && (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) != null) {
                    contextJ = windVaneWebView.getContext();
                }
            } catch (Exception e) {
                s.d(a, e.getMessage());
            }
        }
        if (contextJ == null) {
            h.a().a(obj, b.a(1));
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("netstat", o.C(contextJ));
            String string = jSONObject.toString();
            if (!TextUtils.isEmpty(string)) {
                string = Base64.encodeToString(string.getBytes(), 2);
            }
            h.a().a(obj, string);
        } catch (Throwable th) {
            s.d(a, th.getMessage());
            h.a().a(obj, b.a(1));
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void p(Object obj, String str) throws JSONException {
        s.a(a, "cai:" + str);
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("packageName");
            if (TextUtils.isEmpty(strOptString)) {
                b.a(obj, "packageName is empty");
            }
            int i = w.c(com.mbridge.msdk.foundation.controller.a.f().j(), strOptString) ? 1 : 2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, b.b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("result", i);
                jSONObject.put("data", jSONObject2);
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception e) {
                b.a(obj, e.getMessage());
                s.a(a, e.getMessage());
            }
        } catch (JSONException e2) {
            b.a(obj, "exception: " + e2.getLocalizedMessage());
            s.b(a, "cai", e2);
        } catch (Throwable th) {
            b.a(obj, "exception: " + th.getLocalizedMessage());
            s.b(a, "cai", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public final void q(Object obj, String str) throws JSONException {
        s.a(a, "gial:" + str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, b.b);
            JSONObject jSONObject2 = new JSONObject();
            List<String> list = com.mbridge.msdk.foundation.controller.a.b;
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject2.put("packageNameList", jSONArray);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            b.a(obj, e.getMessage());
            s.a(a, e.getMessage());
        } catch (Throwable th) {
            b.a(obj, th.getMessage());
            s.a(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.c
    public void j(Object obj, String str) {
        s.a(a, "getFileInfo:" + str);
    }
}
