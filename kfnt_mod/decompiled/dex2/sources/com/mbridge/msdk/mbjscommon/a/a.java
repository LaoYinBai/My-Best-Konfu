package com.mbridge.msdk.mbjscommon.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.same.report.d.b;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: H5ReportManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String c = "a";
    private String d = "handlerNativeResult";
    int a = 0;
    int b = 1;

    /* compiled from: H5ReportManager.java */
    /* renamed from: com.mbridge.msdk.mbjscommon.a.a$a, reason: collision with other inner class name */
    private static final class C0118a {
        private static a a = new a();
    }

    public static a a() {
        return C0118a.a;
    }

    public final void a(Object obj, String str, JSONArray jSONArray, int i) {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        JSONObject jSONObjectOptJSONObject3;
        JSONObject jSONObjectOptJSONObject4;
        JSONObject jSONObjectOptJSONObject5;
        JSONObject jSONObjectOptJSONObject6;
        JSONObject jSONObjectOptJSONObject7;
        JSONObject jSONObjectOptJSONObject8;
        JSONObject jSONObjectOptJSONObject9;
        JSONObject jSONObjectOptJSONObject10;
        JSONObject jSONObjectOptJSONObject11;
        int i2;
        try {
            if (jSONArray == null) {
                a(this.b, "called reporter failed, params empty", obj);
                return;
            }
            int length = jSONArray.length();
            if (length == 0) {
                a(this.b, "called reporter failed, params empty", obj);
                return;
            }
            if ("reportMessageR".equalsIgnoreCase(str)) {
                StringBuffer stringBuffer = new StringBuffer();
                int i3 = 0;
                while (true) {
                    i2 = length - 1;
                    if (i3 >= i2) {
                        break;
                    }
                    JSONObject jSONObjectOptJSONObject12 = jSONArray.optJSONObject(i3);
                    if (jSONObjectOptJSONObject12 != null) {
                        stringBuffer.append(jSONObjectOptJSONObject12.optString(DomainCampaignEx.LOOPBACK_KEY));
                        stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                        stringBuffer.append(jSONObjectOptJSONObject12.opt("value"));
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    i3++;
                }
                JSONObject jSONObjectOptJSONObject13 = jSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject13 != null) {
                    stringBuffer.append(jSONObjectOptJSONObject13.optString(DomainCampaignEx.LOOPBACK_KEY));
                    stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    stringBuffer.append(jSONObjectOptJSONObject13.opt("value"));
                }
                String string = stringBuffer.toString();
                Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                if (contextJ != null && !TextUtils.isEmpty(string)) {
                    try {
                        new com.mbridge.msdk.foundation.same.report.d.a(contextJ).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(string, contextJ), new b() { // from class: com.mbridge.msdk.mbjscommon.a.a.1
                            @Override // com.mbridge.msdk.foundation.same.report.d.b
                            public final void a(String str2) {
                                s.d(a.c, str2);
                            }

                            @Override // com.mbridge.msdk.foundation.same.report.d.b
                            public final void b(String str2) {
                                s.d(a.c, str2);
                            }
                        });
                    } catch (Exception e) {
                        s.d(c, e.getMessage());
                    }
                }
            } else if ("reportMessageD".equalsIgnoreCase(str)) {
                String string2 = "";
                String string3 = "";
                String string4 = "";
                String string5 = "";
                String string6 = "";
                if (length > 0 && (jSONObjectOptJSONObject11 = jSONArray.optJSONObject(0)) != null) {
                    string2 = jSONObjectOptJSONObject11.getString("value");
                }
                String str2 = string2;
                int i4 = (length <= 1 || (jSONObjectOptJSONObject10 = jSONArray.optJSONObject(1)) == null) ? -1 : jSONObjectOptJSONObject10.getInt("value");
                int i5 = (length <= 2 || (jSONObjectOptJSONObject9 = jSONArray.optJSONObject(2)) == null) ? -1 : jSONObjectOptJSONObject9.getInt("value");
                int i6 = (length <= 3 || (jSONObjectOptJSONObject8 = jSONArray.optJSONObject(3)) == null) ? -1 : jSONObjectOptJSONObject8.getInt("value");
                int i7 = (length <= 4 || (jSONObjectOptJSONObject7 = jSONArray.optJSONObject(4)) == null) ? -1 : jSONObjectOptJSONObject7.getInt("value");
                if (length > 5 && (jSONObjectOptJSONObject6 = jSONArray.optJSONObject(5)) != null) {
                    string3 = jSONObjectOptJSONObject6.getString("value");
                }
                String str3 = string3;
                if (length > 6 && (jSONObjectOptJSONObject5 = jSONArray.optJSONObject(6)) != null) {
                    string4 = jSONObjectOptJSONObject5.getString("value");
                }
                String str4 = string4;
                int i8 = (length <= 7 || (jSONObjectOptJSONObject4 = jSONArray.optJSONObject(7)) == null) ? -1 : jSONObjectOptJSONObject4.getInt("value");
                if (length > 8 && (jSONObjectOptJSONObject3 = jSONArray.optJSONObject(8)) != null) {
                    string5 = jSONObjectOptJSONObject3.getString("value");
                }
                String str5 = string5;
                int i9 = (length <= 9 || (jSONObjectOptJSONObject2 = jSONArray.optJSONObject(9)) == null) ? -1 : jSONObjectOptJSONObject2.getInt("value");
                if (length > 10 && (jSONObjectOptJSONObject = jSONArray.optJSONObject(10)) != null) {
                    string6 = jSONObjectOptJSONObject.getString("value");
                }
                String str6 = string6;
                try {
                    Context contextJ2 = com.mbridge.msdk.foundation.controller.a.f().j();
                    if (contextJ2 != null) {
                        u.a(i.a(contextJ2)).a(new q(str2, i4, i5, i6, i7, str3, str4, i8, str5, i9, str6));
                    }
                } catch (Throwable th) {
                    s.a(c, th.getMessage());
                }
            }
            a(this.a, "called reporter success", obj);
        } catch (Throwable th2) {
            s.a(c, th2.getMessage());
            a(this.b, "exception: " + th2.getMessage(), obj);
        }
    }

    private void a(int i, String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            jSONObject.put("message", str);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (JSONException e) {
            s.a(c, e.getMessage());
        } catch (Throwable th) {
            s.a(c, th.getMessage());
        }
    }
}
