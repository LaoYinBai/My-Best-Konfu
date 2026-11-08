package com.mbridge.msdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.kwad.sdk.collector.AppStatusRules;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.s;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SettingRequestController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final String a = "c";

    public final void a(final Context context, final String str, final String str2) {
        if (context == null) {
            return;
        }
        com.mbridge.msdk.b.a.c cVar = new com.mbridge.msdk.b.a.c(context);
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        dVar.a("app_id", str);
        dVar.a("sign", SameMD5.getMD5(str + str2));
        dVar.a("jm_a", com.mbridge.msdk.c.b.a(context).c());
        dVar.a("jm_n", com.mbridge.msdk.c.b.a(context).a() + "");
        dVar.a("launcher", com.mbridge.msdk.c.b.a(context).b());
        cVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().j, dVar, new com.mbridge.msdk.foundation.same.net.h.c() { // from class: com.mbridge.msdk.b.c.1
            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(JSONObject jSONObject) throws JSONException {
                try {
                    if (jSONObject == null) {
                        s.d(c.a, "app setting is null");
                    } else {
                        MBridgeConstans.ALLOW_APK_DOWNLOAD = jSONObject.optBoolean("aa");
                        SharedPreferences.Editor editorEdit = com.mbridge.msdk.foundation.controller.a.f().j().getApplicationContext().getSharedPreferences("cv", 0).edit();
                        editorEdit.clear();
                        editorEdit.commit();
                        jSONObject.put("current_time", System.currentTimeMillis());
                        jSONObject.put("host_setting", com.mbridge.msdk.foundation.same.net.g.d.c().c);
                        b.a().c(str, jSONObject.toString());
                        if (!com.mbridge.msdk.b.b.a.a().c()) {
                            new Thread(new Runnable() { // from class: com.mbridge.msdk.b.c.1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    Looper.prepare();
                                    com.mbridge.msdk.b.b.a.a().e();
                                    Looper.loop();
                                }
                            }).start();
                        } else {
                            com.mbridge.msdk.b.b.a.a().d();
                        }
                        new Thread(new Runnable() { // from class: com.mbridge.msdk.b.c.1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                com.mbridge.msdk.foundation.controller.a.g();
                            }
                        }).start();
                        com.mbridge.msdk.foundation.same.net.g.d.c().d();
                        if (!TextUtils.isEmpty(jSONObject.optString("mraid_js"))) {
                            com.mbridge.msdk.b.b.b.a().a(context, jSONObject.optString("mraid_js"));
                        }
                        if (!TextUtils.isEmpty(jSONObject.optString("web_env_url"))) {
                            com.mbridge.msdk.b.b.c.a().a(context, jSONObject.optString("web_env_url"));
                        }
                    }
                    new com.mbridge.msdk.foundation.same.report.c(context).a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.mbridge.msdk.foundation.same.net.h.c
            public final void a(String str3) {
                com.mbridge.msdk.foundation.same.report.c cVar2 = new com.mbridge.msdk.foundation.same.report.c(context);
                cVar2.a();
                cVar2.a(str3, com.mbridge.msdk.foundation.same.net.g.d.c().c);
                com.mbridge.msdk.foundation.same.net.g.d.c().r++;
                c.this.b(context, str, str2);
                s.d(c.a, "get app setting error" + str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Context context, final String str, final String str2) {
        if (!(!com.mbridge.msdk.foundation.same.net.g.d.c().a())) {
            a(context, str, str2);
            return;
        }
        try {
            if (com.mbridge.msdk.foundation.same.net.g.d.c().s) {
                return;
            }
            com.mbridge.msdk.foundation.same.net.g.d.c().s = true;
            if (System.currentTimeMillis() >= com.mbridge.msdk.foundation.a.a.a.a().a("mkey_spare_host_ts").longValue() + AppStatusRules.DEFAULT_START_TIME) {
                String strB = com.mbridge.msdk.foundation.a.a.a.a().b("mkey_spare_host");
                if (!TextUtils.isEmpty(strB)) {
                    for (String str3 : strB.split("\n")) {
                        if (!TextUtils.isEmpty(str3.trim()) && !com.mbridge.msdk.foundation.same.net.g.d.c().t.contains(str3.trim())) {
                            com.mbridge.msdk.foundation.same.net.g.d.c().t.add(str3.trim());
                        }
                    }
                    b(context, str, str2);
                    return;
                }
            }
            new com.mbridge.msdk.foundation.same.net.h.b(context.getApplicationContext()).a(0, com.mbridge.msdk.foundation.same.net.g.d.c().q, null, new com.mbridge.msdk.foundation.same.net.b.a() { // from class: com.mbridge.msdk.b.c.2
                @Override // com.mbridge.msdk.foundation.same.net.b.a
                public final void a(String str4) {
                    s.a(c.a, "fetch CNDSettingHost success, content = " + str4);
                    if (TextUtils.isEmpty(str4)) {
                        return;
                    }
                    com.mbridge.msdk.foundation.a.a.a.a().a("mkey_spare_host_ts", System.currentTimeMillis());
                    com.mbridge.msdk.foundation.a.a.a.a().a("mkey_spare_host", str4);
                    for (String str5 : str4.split("\n")) {
                        if (!TextUtils.isEmpty(str5.trim()) && !com.mbridge.msdk.foundation.same.net.g.d.c().t.contains(str5.trim())) {
                            com.mbridge.msdk.foundation.same.net.g.d.c().t.add(str5.trim());
                        }
                    }
                    c.this.b(context, str, str2);
                }

                @Override // com.mbridge.msdk.foundation.same.net.b.a
                public final void b(String str4) {
                    s.a(c.a, "fetch CNDSettingHost failed, errorCode = " + str4);
                }
            });
        } catch (Exception e) {
            s.d(a, e.getMessage());
        } catch (Throwable th) {
            s.d(a, th.getMessage());
        }
    }

    public final void a(Context context, final String str, String str2, final String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            str = com.mbridge.msdk.foundation.controller.a.f().k();
            str2 = com.mbridge.msdk.foundation.controller.a.f().l();
        }
        if (b.a().b(str3, str) && b.a().a(str, 2, str3)) {
            com.mbridge.msdk.b.a.c cVar = new com.mbridge.msdk.b.a.c(context);
            com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
            dVar.a("unit_ids", "[" + str3 + "]");
            dVar.a("app_id", str);
            dVar.a("sign", SameMD5.getMD5(str + str2));
            cVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().j, dVar, new com.mbridge.msdk.foundation.same.net.h.c() { // from class: com.mbridge.msdk.b.c.3
                @Override // com.mbridge.msdk.foundation.same.net.h.c
                public final void a(JSONObject jSONObject) throws JSONException {
                    try {
                        if (jSONObject == null) {
                            s.d(c.a, "UNIT SETTING IS NULL");
                        } else {
                            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("unitSetting");
                            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0);
                                jSONObjectOptJSONObject.put("current_time", System.currentTimeMillis());
                                b.a().a(str, str3, jSONObjectOptJSONObject.toString());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.mbridge.msdk.foundation.same.net.h.c
                public final void a(String str4) {
                    s.d(c.a, "GET UNITID SETTING ERROR" + str4);
                }
            });
        }
    }
}
