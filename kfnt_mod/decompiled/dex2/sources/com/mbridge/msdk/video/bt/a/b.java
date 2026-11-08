package com.mbridge.msdk.video.bt.a;

import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HandlerH5MessageManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    int a;
    int b;
    private String c;

    private b() {
        this.c = "handlerNativeResult";
        this.a = 0;
        this.b = 1;
    }

    /* compiled from: HandlerH5MessageManager.java */
    private static final class a {
        private static b a = new b();
    }

    public static b a() {
        return a.a;
    }

    public final void a(Object obj, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (!TextUtils.isEmpty(jSONObject.toString())) {
                    String strOptString = jSONObject.optString("uniqueIdentifier");
                    String strOptString2 = jSONObject.optString("name");
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(PushConstants.PARAMS);
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                        int iOptInt = 0;
                        if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.toString())) {
                            iOptInt = jSONObjectOptJSONObject.optInt(Message.TYPE, 0);
                        }
                        a(this.a, "receivedMessage", obj);
                        if (strOptString.equalsIgnoreCase("reporter")) {
                            com.mbridge.msdk.mbjscommon.a.a.a().a(obj, strOptString2, jSONArrayOptJSONArray, iOptInt);
                            return;
                        } else {
                            if (strOptString.equalsIgnoreCase("MediaPlayer")) {
                                com.mbridge.msdk.video.bt.a.a.a().a(obj, strOptString2, jSONArrayOptJSONArray, iOptInt);
                                return;
                            }
                            return;
                        }
                    }
                    a(this.b, "module or method is null", obj);
                    return;
                }
            } catch (Exception e) {
                s.a("HandlerH5MessageManager", e.getMessage());
                a(this.b, e.getMessage(), obj);
                return;
            } catch (Throwable th) {
                s.a("HandlerH5MessageManager", th.getMessage());
                a(this.b, th.getMessage(), obj);
                return;
            }
        }
        a(this.b, "params is null", obj);
    }

    private void a(int i, String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            jSONObject.put("message", str);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (JSONException e) {
            s.a("HandlerH5MessageManager", e.getMessage());
        } catch (Throwable th) {
            s.a("HandlerH5MessageManager", th.getMessage());
        }
    }
}
