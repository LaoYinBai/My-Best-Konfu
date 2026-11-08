package com.bytedance.push.legacy;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.d;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.utils.RomVersionParamHelper;
import com.ss.android.b.a.b;
import com.ss.android.pushmanager.IMessageContext;
import com.ss.android.pushmanager.a.a;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static void a(final d dVar, final com.bytedance.push.g.a aVar, final IMessageContext iMessageContext) {
        b.a(a.a.class, new a.a() { // from class: com.bytedance.push.legacy.a.1
            public void a(Context context) {
                com.ss.android.message.a.a(dVar.f4770a);
            }
        });
        b.a(a.b.class, new a.b() { // from class: com.bytedance.push.legacy.a.2
            public String b() {
                return "message_data";
            }

            public String c() {
                return "com.ss.android.message";
            }

            public IMessageContext a() {
                return iMessageContext;
            }

            public String d() {
                return com.ss.android.message.a.b.f();
            }

            public void a(Context context, String str, String str2, String str3, long j, long j2, JSONObject jSONObject) {
                if (dVar.l != null) {
                    dVar.l.a(context, str, str2, str3, j, j2, jSONObject);
                }
            }

            public void a(String str, JSONObject jSONObject) {
                if (dVar.l != null) {
                    dVar.l.a(str, jSONObject);
                }
            }

            public void a(String str, Bundle bundle) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                if (bundle != null) {
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject.put(str2, bundle.get(str2));
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                a(str, jSONObject);
            }

            public boolean e() {
                return com.bytedance.push.alive.b.a(dVar.f4770a).c();
            }

            public Map<String, String> f() {
                return aVar.a();
            }

            public boolean g() {
                Application application = dVar.f4770a;
                if (com.ss.android.message.a.b.c(application)) {
                    if (((PushOnlineSettings) SettingsManager.obtain(dVar.f4770a, PushOnlineSettings.class)).j()) {
                        return true;
                    }
                    return ((PushOnlineSettings) SettingsManager.obtain(dVar.f4770a, PushOnlineSettings.class)).needControlFlares() && RomVersionParamHelper.isMiui12();
                }
                SharedPreferences sharedPreferences = application.getSharedPreferences("push_multi_process_config", 0);
                if (sharedPreferences.getBoolean("remove_auto_boot_v2", false)) {
                    return true;
                }
                return sharedPreferences.getBoolean("need_control_miui_flares_v2", false) && RomVersionParamHelper.isMiui12();
            }
        });
    }
}
