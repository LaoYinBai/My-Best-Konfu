package com.mbridge.msdk.mbjscommon.confirmation.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.c;
import com.mbridge.msdk.foundation.tools.l;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.bridge.b;
import com.mbridge.msdk.mbjscommon.confirmation.a;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ConfirmationJsBridgePlugin extends AbsMbridgeDownload {
    private static String a = "ConfirmationJsBridgePlugin";

    public void readyStatus(Object obj, String str) throws JSONException {
        if (obj != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                int iOptInt = new JSONObject(str).optInt("isReady", 1);
                h.a().a(obj, b.a(0));
                a aVarA = a(obj);
                if ((aVarA != null ? aVarA.b() : null) != null) {
                    e.a().a(obj, iOptInt);
                } else {
                    e.a().a(obj, 2);
                }
            } catch (Throwable th) {
                s.b(a, "readyStatus", th);
                b.a(obj, "exception: " + th.getLocalizedMessage());
            }
        }
    }

    public void init(Object obj, String str) throws JSONException {
        s.d(a, " INIT INVOKE");
        a aVarA = a(obj);
        if (aVarA != null) {
            CampaignEx campaignExB = aVarA.b();
            ArrayList arrayList = new ArrayList();
            arrayList.add(campaignExB);
            try {
                JSONObject jSONObject = new JSONObject();
                c cVar = new c(com.mbridge.msdk.foundation.controller.a.f().j());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("dev_close_state", 0);
                jSONObject.put("sdkSetting", jSONObject2);
                jSONObject.put("device", cVar.a());
                jSONObject.put("campaignList", CampaignEx.parseCamplistToJson(arrayList));
                d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), aVarA.d());
                if (dVarF == null) {
                    dVarF = d.d(aVarA.d());
                }
                jSONObject.put("unitSetting", dVarF.v());
                String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
                if (!TextUtils.isEmpty(strC)) {
                    jSONObject.put("appSetting", new JSONObject(strC));
                }
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Throwable th) {
                s.b(a, "init", th);
                b.a(obj, "exception: " + th.getLocalizedMessage());
            }
        }
    }

    private a a(Object obj) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            Object object = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a.getObject();
            if (object instanceof a) {
                return (a) object;
            }
        }
        return null;
    }

    public void click(Object obj, String str) throws JSONException {
        try {
            a(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    public void install(Object obj, String str) throws JSONException {
        try {
            a(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    public void confirmClick(Object obj, String str) throws JSONException {
        try {
            a(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    private void a(Object obj, String str) throws JSONException {
        try {
            addDownloaderListener(obj, str);
            e.a().b(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    public void confirmClose(Object obj, String str) throws JSONException {
        try {
            e.a().a(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    public void confirmCancel(Object obj, String str) throws JSONException {
        try {
            e.a().a(obj, str);
            h.a().a(obj, b.a(0));
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        e.a().b(obj, str);
    }

    public void translatePermission(Object obj, String str) throws JSONException {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject("permissionMap");
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject2.put(next, l.a(jSONObject2.getJSONArray(next)));
                }
                jSONObject.put("permissionMap", jSONObject2);
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                return;
            }
            b.a(obj, "exception: params is " + str);
        } catch (Exception e) {
            b.a(obj, "exception: " + e.getLocalizedMessage());
        }
    }

    public void openURL(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView;
        try {
            s.d(a, "openURL:" + str);
            if (TextUtils.isEmpty(str)) {
                b.a(obj, "params is null");
                return;
            }
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (!TextUtils.isEmpty(str)) {
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
                    b.a(obj, "context is null");
                    return;
                }
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String strOptString = jSONObject.optString("url");
                        int iOptInt = jSONObject.optInt(Message.TYPE);
                        if (iOptInt == 1) {
                            com.mbridge.msdk.click.c.a(contextJ, strOptString);
                        } else if (iOptInt == 2) {
                            com.mbridge.msdk.click.c.b(contextJ, strOptString);
                        }
                    } catch (Throwable th) {
                        s.d(a, th.getMessage());
                    }
                } catch (JSONException e2) {
                    s.d(a, e2.getMessage());
                }
            }
            h.a().a(obj, b.a(0));
        } catch (Exception e3) {
            b.a(obj, "exception: " + e3.getLocalizedMessage());
        }
    }
}
