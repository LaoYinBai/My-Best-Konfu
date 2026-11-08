package com.mbridge.msdk.mbjscommon.bridge;

import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.a.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.h;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CommonJSBridgeImpUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static final String a = "b";
    public static int b = 0;
    public static int c = 1;

    public static void a(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                a(obj, "data is empty");
                return;
            }
            final CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject);
            if (campaignWithBackData == null) {
                a(obj, "data camapign is empty");
                return;
            }
            new Thread(new Runnable() { // from class: com.mbridge.msdk.mbjscommon.bridge.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m mVarA = m.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                        if (mVarA != null) {
                            if (!mVarA.a(campaignWithBackData.getId())) {
                                com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                                iVar.a(campaignWithBackData.getId());
                                iVar.a(campaignWithBackData.getFca());
                                iVar.b(campaignWithBackData.getFcb());
                                iVar.d(0);
                                iVar.c(1);
                                iVar.a(System.currentTimeMillis());
                                mVarA.a(iVar);
                            } else {
                                mVarA.b(campaignWithBackData.getId());
                            }
                        }
                        b.a(campaignWithBackData.getCampaignUnitId(), campaignWithBackData);
                    } catch (Throwable th) {
                        s.b(b.a, th.getMessage(), th);
                    }
                }
            }).start();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(CommandMessage.CODE, b);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("id", "");
                jSONObject2.put("data", jSONObject3);
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            } catch (Exception e) {
                a(obj, e.getMessage());
                s.a(a, e.getMessage());
            }
        } catch (Throwable th) {
            a(obj, th.getMessage());
        }
    }

    public static String a(float f, float f2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(com.mbridge.msdk.foundation.same.a.m, w.a(com.mbridge.msdk.foundation.controller.a.f().j(), f));
            jSONObject2.put(com.mbridge.msdk.foundation.same.a.n, w.a(com.mbridge.msdk.foundation.controller.a.f().j(), f2));
            jSONObject2.put(com.mbridge.msdk.foundation.same.a.p, 0);
            jSONObject2.put(com.mbridge.msdk.foundation.same.a.q, com.mbridge.msdk.foundation.controller.a.f().j().getResources().getConfiguration().orientation);
            jSONObject2.put(com.mbridge.msdk.foundation.same.a.r, w.d(com.mbridge.msdk.foundation.controller.a.f().j()));
            jSONObject.put(com.mbridge.msdk.foundation.same.a.o, jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            String string = jSONObject.toString();
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Throwable unused) {
            s.d(a, "code to string is error");
            return "";
        }
    }

    public static void a(Object obj, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, c);
            jSONObject.put("message", str);
            jSONObject.put("data", new JSONObject());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            s.a(a, e.getMessage());
        }
    }

    static /* synthetic */ void a(String str, CampaignEx campaignEx) {
        if (d.h == null || TextUtils.isEmpty(campaignEx.getId())) {
            return;
        }
        d.b(str, campaignEx, "banner");
    }
}
