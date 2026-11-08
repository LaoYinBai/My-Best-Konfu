package com.mbridge.msdk.videocommon.c;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.same.net.h.d;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.s;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardSettingController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "com.mbridge.msdk.videocommon.c.a";

    public final void a(Context context, final String str, String str2) {
        b bVar = new b(context);
        d dVar = new d();
        dVar.a("app_id", str);
        dVar.a("sign", SameMD5.getMD5(str + str2));
        bVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().l, dVar, new com.mbridge.msdk.foundation.same.net.h.c() { // from class: com.mbridge.msdk.videocommon.c.a.1
            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    try {
                        jSONObject.put("current_time", System.currentTimeMillis());
                        com.mbridge.msdk.videocommon.d.b.a();
                        String str3 = str;
                        String string = jSONObject.toString();
                        com.mbridge.msdk.foundation.a.a.a.a().a("reward_" + str3, string);
                        com.mbridge.msdk.videocommon.d.b.a = com.mbridge.msdk.videocommon.d.a.a(string);
                    } catch (JSONException e) {
                        com.mbridge.msdk.videocommon.d.b.a().a(false);
                        s.d(a.a, e.getMessage());
                    }
                }
            }

            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(String str3) {
                com.mbridge.msdk.videocommon.d.b.a().a(false);
                s.d(a.a, str3);
            }
        });
    }

    public final void a(Context context, final String str, String str2, String str3, final c cVar) {
        b bVar = new b(context);
        d dVar = new d();
        dVar.a("app_id", str);
        dVar.a("sign", SameMD5.getMD5(str + str2));
        dVar.a("unit_ids", "[" + str3 + "]");
        com.mbridge.msdk.foundation.same.net.h.c cVar2 = new com.mbridge.msdk.foundation.same.net.h.c() { // from class: com.mbridge.msdk.videocommon.c.a.2
            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    try {
                        if (com.mbridge.msdk.videocommon.d.b.b(jSONObject.toString())) {
                            jSONObject.put("current_time", System.currentTimeMillis());
                            com.mbridge.msdk.videocommon.d.b.a().a(str, this.d, jSONObject.toString());
                            if (cVar != null) {
                                cVar.a("request success");
                            }
                        } else if (cVar != null) {
                            cVar.b("data error");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(String str4) {
                if (!TextUtils.isEmpty(str4)) {
                    if (cVar != null) {
                        cVar.b(str4);
                    }
                } else if (cVar != null) {
                    cVar.b("request error");
                }
            }
        };
        cVar2.d = str3;
        bVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().l, dVar, cVar2);
    }
}
