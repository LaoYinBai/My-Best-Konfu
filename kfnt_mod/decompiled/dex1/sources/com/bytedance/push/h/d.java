package com.bytedance.push.h;

import android.content.Context;
import android.os.Looper;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final JSONObject f4834a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f4835b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f4836c;

    public d(Context context, JSONObject jSONObject, boolean z) {
        this.f4835b = context;
        this.f4834a = jSONObject;
        this.f4836c = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4834a == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = this.f4834a;
        if (this.f4834a.has("sdk_key_PushSDK")) {
            jSONObjectOptJSONObject = this.f4834a.optJSONObject("sdk_key_PushSDK");
        }
        if (jSONObjectOptJSONObject == null) {
            com.bytedance.push.utils.e.b("Settings", "can't find settings");
            if (com.bytedance.push.utils.e.a()) {
                throw new IllegalArgumentException("settings missing sdk_key_PushSDK");
            }
        } else if (this.f4836c) {
            a(this.f4835b, jSONObjectOptJSONObject);
        } else {
            b(this.f4835b, jSONObjectOptJSONObject);
        }
    }

    private void a(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
            jSONObject2.remove("ttpush_is_notify_service_stick");
            jSONObject2.remove("ttpush_allow_push_daemon_monitor");
            jSONObject2.remove("ttpush_is_close_alarm_wakeup");
            jSONObject2.remove("ttpush_allow_push_job_service");
            b(context, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void b(final Context context, final JSONObject jSONObject) {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.h.d.1
            @Override // java.lang.Runnable
            public void run() {
                ((AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class)).updateSettings(context, jSONObject);
                ((PushOnlineSettings) SettingsManager.obtain(context, PushOnlineSettings.class)).updateSettings(context, jSONObject);
            }
        };
        if (Looper.myLooper() == Looper.getMainLooper()) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        } else {
            runnable.run();
        }
    }
}
