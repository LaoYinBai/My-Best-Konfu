package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.date.DateDef;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushOnlineSettings$$SettingImpl implements PushOnlineSettings {

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f4930c;

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f4928a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f4929b = new ConcurrentHashMap<>();
    private final c d = new c() { // from class: com.bytedance.push.settings.PushOnlineSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.c
        public <T> T a(Class<T> cls) {
            return null;
        }
    };

    public PushOnlineSettings$$SettingImpl(com.bytedance.push.settings.storage.i iVar) {
        this.f4930c = iVar;
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean a() {
        if (this.f4930c == null || !this.f4930c.f("allow_settings_notify_enable")) {
            return true;
        }
        return this.f4930c.e("allow_settings_notify_enable");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public long b() {
        if (this.f4930c == null || !this.f4930c.f("ttpush_update_sender_interval")) {
            return 10800000L;
        }
        return this.f4930c.c("ttpush_update_sender_interval");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public long c() {
        return (this.f4930c == null || !this.f4930c.f("ttpush_update_token_interval")) ? DateDef.DAY : this.f4930c.c("ttpush_update_token_interval");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean d() {
        if (this.f4930c == null || !this.f4930c.f("ttpush_enable_restrict_update_token")) {
            return false;
        }
        return this.f4930c.e("ttpush_enable_restrict_update_token");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean e() {
        if (this.f4930c == null || !this.f4930c.f("ttpush_shut_push_on_stop_service")) {
            return false;
        }
        return this.f4930c.e("ttpush_shut_push_on_stop_service");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean f() {
        if (this.f4930c == null || !this.f4930c.f("is_receiver_message_wakeup_screen")) {
            return false;
        }
        return this.f4930c.e("is_receiver_message_wakeup_screen");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public int g() {
        if (this.f4930c == null || !this.f4930c.f("receiver_message_wakeup_screen_time")) {
            return 5000;
        }
        return this.f4930c.b("receiver_message_wakeup_screen_time");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public long h() {
        return (this.f4930c == null || !this.f4930c.f("ttpush_upload_switch_interval")) ? DateDef.DAY : this.f4930c.c("ttpush_upload_switch_interval");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public int i() {
        if (this.f4930c == null || !this.f4930c.f("ttpush_forbid_alias")) {
            return 0;
        }
        return this.f4930c.b("ttpush_forbid_alias");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean needControlFlares() {
        if (this.f4930c == null || !this.f4930c.f("need_control_miui_flares_v2")) {
            return false;
        }
        return this.f4930c.e("need_control_miui_flares_v2");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public boolean j() {
        if (this.f4930c == null || !this.f4930c.f("remove_auto_boot_v2")) {
            return false;
        }
        return this.f4930c.e("remove_auto_boot_v2");
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void a(boolean z) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putBoolean("allow_settings_notify_enable", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void a(long j) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putLong("ttpush_update_sender_interval", j);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void b(long j) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putLong("ttpush_update_token_interval", j);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void b(boolean z) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putBoolean("ttpush_shut_push_on_stop_service", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void c(boolean z) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putBoolean("is_receiver_message_wakeup_screen", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.PushOnlineSettings
    public void a(int i) {
        if (this.f4930c != null) {
            SharedPreferences.Editor editorB = this.f4930c.b();
            editorB.putInt("receiver_message_wakeup_screen_time", i);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void updateSettings(Context context, JSONObject jSONObject) {
        if (jSONObject == null || this.f4930c == null) {
            if (jSONObject == null) {
                return;
            }
            com.bytedance.push.settings.storage.i iVar = this.f4930c;
            return;
        }
        SharedPreferences.Editor editorB = this.f4930c.b();
        if (jSONObject != null) {
            if (jSONObject.has("ttpush_allow_settings_notify_enable")) {
                editorB.putBoolean("allow_settings_notify_enable", d.a(jSONObject, "ttpush_allow_settings_notify_enable"));
            }
            if (jSONObject.has("ttpush_update_sender_interval")) {
                editorB.putLong("ttpush_update_sender_interval", jSONObject.optLong("ttpush_update_sender_interval"));
            }
            if (jSONObject.has("ttpush_update_token_interval")) {
                editorB.putLong("ttpush_update_token_interval", jSONObject.optLong("ttpush_update_token_interval"));
            }
            if (jSONObject.has("ttpush_enable_restrict_update_token")) {
                editorB.putBoolean("ttpush_enable_restrict_update_token", d.a(jSONObject, "ttpush_enable_restrict_update_token"));
            }
            if (jSONObject.has("ttpush_shut_push_on_stop_service")) {
                editorB.putBoolean("ttpush_shut_push_on_stop_service", d.a(jSONObject, "ttpush_shut_push_on_stop_service"));
            }
            if (jSONObject.has("ttpush_is_receiver_message_wakeup_screen")) {
                editorB.putBoolean("is_receiver_message_wakeup_screen", d.a(jSONObject, "ttpush_is_receiver_message_wakeup_screen"));
            }
            if (jSONObject.has("ttpush_receiver_message_wakeup_screen_time")) {
                editorB.putInt("receiver_message_wakeup_screen_time", jSONObject.optInt("ttpush_receiver_message_wakeup_screen_time"));
            }
            if (jSONObject.has("ttpush_upload_switch_interval")) {
                editorB.putLong("ttpush_upload_switch_interval", jSONObject.optLong("ttpush_upload_switch_interval"));
            }
            if (jSONObject.has("ttpush_forbid_alias")) {
                editorB.putInt("ttpush_forbid_alias", jSONObject.optInt("ttpush_forbid_alias"));
            }
            if (jSONObject.has("need_control_miui_flares_v2")) {
                editorB.putBoolean("need_control_miui_flares_v2", d.a(jSONObject, "need_control_miui_flares_v2"));
            }
            if (jSONObject.has("remove_auto_boot_v2")) {
                editorB.putBoolean("remove_auto_boot_v2", d.a(jSONObject, "remove_auto_boot_v2"));
            }
        }
        editorB.apply();
    }

    @Override // com.bytedance.push.settings.ISettings
    public void registerValChanged(Context context, String str, String str2, a aVar) {
        if (this.f4930c != null) {
            this.f4930c.a(context, str, str2, aVar);
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void unregisterValChanged(a aVar) {
        if (this.f4930c != null) {
            this.f4930c.a(aVar);
        }
    }
}
