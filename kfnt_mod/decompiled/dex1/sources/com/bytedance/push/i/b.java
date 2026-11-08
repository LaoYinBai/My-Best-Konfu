package com.bytedance.push.i;

import com.bytedance.common.push.utility.f;
import com.bytedance.push.c.h;
import com.ss.android.message.d;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final h f4848a;

    public b(h hVar) {
        this.f4848a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.i.b.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectOptJSONObject;
                JSONObject jSONObjectOptJSONObject2;
                try {
                    JSONObject jSONObject = new JSONObject(b.this.a());
                    if (!"success".equals(jSONObject.optString("message")) || (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) == null || (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("settings")) == null) {
                        return;
                    }
                    b.this.f4848a.a(com.ss.android.message.a.a(), jSONObjectOptJSONObject2);
                    HashMap map = new HashMap();
                    map.put("interval_query_settings", Long.valueOf(jSONObjectOptJSONObject2.optLong("interval_query_settings", TimeUnit.HOURS.toMillis(2L))));
                    PushSetting.getInstance().saveMapToProvider(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        d.a().a(this, PushSetting.getInstance().getProviderLong("interval_query_settings", TimeUnit.HOURS.toMillis(2L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        try {
            return f.a().a(com.ss.android.message.a.b.a("https://open.toutiao.com/service/settings/v3/?caller_name=push", com.ss.android.pushmanager.a.a.b().f()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
