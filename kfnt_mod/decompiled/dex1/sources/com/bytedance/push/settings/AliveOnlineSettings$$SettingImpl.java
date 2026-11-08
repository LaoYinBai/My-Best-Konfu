package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AliveOnlineSettings$$SettingImpl implements AliveOnlineSettings {

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f4918c;

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f4916a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f4917b = new ConcurrentHashMap<>();
    private final c d = new c() { // from class: com.bytedance.push.settings.AliveOnlineSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.c
        public <T> T a(Class<T> cls) {
            if (cls == e.class) {
                return (T) new e();
            }
            return null;
        }
    };

    public AliveOnlineSettings$$SettingImpl(com.bytedance.push.settings.storage.i iVar) {
        this.f4918c = iVar;
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean a() {
        if (this.f4918c == null || !this.f4918c.f("allow_off_alive")) {
            return true;
        }
        return this.f4918c.e("allow_off_alive");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public String b() {
        return (this.f4918c == null || !this.f4918c.f("uninstall_question_url")) ? "" : this.f4918c.a("uninstall_question_url");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean c() {
        if (this.f4918c == null || !this.f4918c.f("allow_push_job_service")) {
            return true;
        }
        return this.f4918c.e("allow_push_job_service");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean d() {
        if (this.f4918c == null || !this.f4918c.f("allow_push_daemon_monitor")) {
            return true;
        }
        return this.f4918c.e("allow_push_daemon_monitor");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean e() {
        if (this.f4918c == null || !this.f4918c.f("allow_close_boot_receiver")) {
            return true;
        }
        return this.f4918c.e("allow_close_boot_receiver");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean f() {
        if (this.f4918c == null || !this.f4918c.f("is_close_alarm_wakeup")) {
            return false;
        }
        return this.f4918c.e("is_close_alarm_wakeup");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean g() {
        if (this.f4918c == null || !this.f4918c.f("is_use_start_foreground_notification")) {
            return true;
        }
        return this.f4918c.e("is_use_start_foreground_notification");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public int h() {
        if (this.f4918c == null || !this.f4918c.f("job_schedule_wake_up_interval_second")) {
            return 3600;
        }
        return this.f4918c.b("job_schedule_wake_up_interval_second");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean i() {
        if (this.f4918c == null || !this.f4918c.f("is_use_c_native_process_keep_alive")) {
            return true;
        }
        return this.f4918c.e("is_use_c_native_process_keep_alive");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean j() {
        if (this.f4918c == null || !this.f4918c.f("is_notify_service_stick")) {
            return true;
        }
        return this.f4918c.e("is_notify_service_stick");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean k() {
        if (this.f4918c == null || !this.f4918c.f("key_is_miui_close_daemon")) {
            return true;
        }
        return this.f4918c.e("key_is_miui_close_daemon");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public List<String> l() {
        if (this.f4918c != null && this.f4918c.f("ttpush_alv_white_list")) {
            return ((e) b.a(e.class, this.d)).a(this.f4918c.a("ttpush_alv_white_list"));
        }
        return ((e) b.a(e.class, this.d)).a();
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void a(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("allow_off_alive", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void a(String str) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putString("uninstall_question_url", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void b(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("allow_push_job_service", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void c(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("allow_push_daemon_monitor", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void d(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("allow_close_boot_receiver", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void e(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("is_close_alarm_wakeup", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void f(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("is_use_start_foreground_notification", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void a(int i) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putInt("job_schedule_wake_up_interval_second", i);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void g(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("is_use_c_native_process_keep_alive", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void h(boolean z) {
        if (this.f4918c != null) {
            SharedPreferences.Editor editorB = this.f4918c.b();
            editorB.putBoolean("is_notify_service_stick", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void updateSettings(Context context, JSONObject jSONObject) {
        if (jSONObject == null || this.f4918c == null) {
            if (jSONObject == null) {
                return;
            }
            com.bytedance.push.settings.storage.i iVar = this.f4918c;
            return;
        }
        SharedPreferences.Editor editorB = this.f4918c.b();
        if (jSONObject != null) {
            if (jSONObject.has("ttpush_allow_off_alive")) {
                editorB.putBoolean("allow_off_alive", d.a(jSONObject, "ttpush_allow_off_alive"));
            }
            if (jSONObject.has("ttpush_uninstall_question_url")) {
                editorB.putString("uninstall_question_url", jSONObject.optString("ttpush_uninstall_question_url"));
            }
            if (jSONObject.has("ttpush_allow_push_job_service")) {
                editorB.putBoolean("allow_push_job_service", d.a(jSONObject, "ttpush_allow_push_job_service"));
            }
            if (jSONObject.has("ttpush_allow_push_daemon_monitor")) {
                editorB.putBoolean("allow_push_daemon_monitor", d.a(jSONObject, "ttpush_allow_push_daemon_monitor"));
            }
            if (jSONObject.has("ttpush_allow_close_boot_receiver")) {
                editorB.putBoolean("allow_close_boot_receiver", d.a(jSONObject, "ttpush_allow_close_boot_receiver"));
            }
            if (jSONObject.has("ttpush_is_close_alarm_wakeup")) {
                editorB.putBoolean("is_close_alarm_wakeup", d.a(jSONObject, "ttpush_is_close_alarm_wakeup"));
            }
            if (jSONObject.has("ttpush_use_start_foreground_notification")) {
                editorB.putBoolean("is_use_start_foreground_notification", d.a(jSONObject, "ttpush_use_start_foreground_notification"));
            }
            if (jSONObject.has("ttpush_job_schedule_wake_up_interval_second")) {
                editorB.putInt("job_schedule_wake_up_interval_second", jSONObject.optInt("ttpush_job_schedule_wake_up_interval_second"));
            }
            if (jSONObject.has("ttpush_is_use_c_native_process_keep_alive")) {
                editorB.putBoolean("is_use_c_native_process_keep_alive", d.a(jSONObject, "ttpush_is_use_c_native_process_keep_alive"));
            }
            if (jSONObject.has("ttpush_is_notify_service_stick")) {
                editorB.putBoolean("is_notify_service_stick", d.a(jSONObject, "ttpush_is_notify_service_stick"));
            }
            if (jSONObject.has("ttpush_key_is_miui_close_daemon")) {
                editorB.putBoolean("key_is_miui_close_daemon", d.a(jSONObject, "ttpush_key_is_miui_close_daemon"));
            }
            if (jSONObject.has("ttpush_alv_white_list")) {
                editorB.putString("ttpush_alv_white_list", jSONObject.optString("ttpush_alv_white_list"));
            }
        }
        editorB.apply();
    }

    @Override // com.bytedance.push.settings.ISettings
    public void registerValChanged(Context context, String str, String str2, a aVar) {
        if (this.f4918c != null) {
            this.f4918c.a(context, str, str2, aVar);
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void unregisterValChanged(a aVar) {
        if (this.f4918c != null) {
            this.f4918c.a(aVar);
        }
    }
}
