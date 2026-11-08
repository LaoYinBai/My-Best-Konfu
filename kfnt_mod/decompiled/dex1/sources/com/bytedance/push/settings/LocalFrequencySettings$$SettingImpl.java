package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LocalFrequencySettings$$SettingImpl implements LocalFrequencySettings {

    /* renamed from: a, reason: collision with root package name */
    private Context f4920a;

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f4921b;

    /* renamed from: c, reason: collision with root package name */
    private final c f4922c = new c() { // from class: com.bytedance.push.settings.LocalFrequencySettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.c
        public <T> T a(Class<T> cls) {
            if (cls == j.class) {
                return (T) new j();
            }
            return null;
        }
    };

    public LocalFrequencySettings$$SettingImpl(Context context, com.bytedance.push.settings.storage.i iVar) {
        this.f4920a = context;
        this.f4921b = iVar;
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public long a() {
        if (this.f4921b == null || !this.f4921b.f("last_update_sender_time_mil")) {
            return 0L;
        }
        return this.f4921b.c("last_update_sender_time_mil");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(long j) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putLong("last_update_sender_time_mil", j);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String b() {
        return (this.f4921b == null || !this.f4921b.f("last_update_sender_did")) ? "" : this.f4921b.a("last_update_sender_did");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("last_update_sender_did", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String c() {
        return (this.f4921b == null || !this.f4921b.f("last_update_sender_vc")) ? "" : this.f4921b.a("last_update_sender_vc");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void b(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("last_update_sender_vc", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String d() {
        return (this.f4921b == null || !this.f4921b.f("last_update_sender_gray_vc")) ? "" : this.f4921b.a("last_update_sender_gray_vc");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void c(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("last_update_sender_gray_vc", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String e() {
        return (this.f4921b == null || !this.f4921b.f("last_update_sender_channel")) ? "" : this.f4921b.a("last_update_sender_channel");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void d(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("last_update_sender_channel", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String f() {
        return (this.f4921b == null || !this.f4921b.f("last_update_sender_supported")) ? "" : this.f4921b.a("last_update_sender_supported");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void e(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("last_update_sender_supported", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public boolean g() {
        if (this.f4921b == null || !this.f4921b.f("last_send_switcher_stat")) {
            return false;
        }
        return this.f4921b.e("last_send_switcher_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(boolean z) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putBoolean("last_send_switcher_stat", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public int h() {
        if (this.f4921b == null || !this.f4921b.f("sys_switcher_stat")) {
            return -2;
        }
        return this.f4921b.b("sys_switcher_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(int i) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putInt("sys_switcher_stat", i);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String i() {
        return (this.f4921b == null || !this.f4921b.f("notify_channel_stat")) ? "" : this.f4921b.a("notify_channel_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void f(String str) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("notify_channel_stat", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public List<com.bytedance.push.e.c> j() {
        if (this.f4921b != null && this.f4921b.f("token_cache")) {
            return ((j) b.a(j.class, this.f4922c)).a(this.f4921b.a("token_cache"));
        }
        return ((j) b.a(j.class, this.f4922c)).a();
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(List<com.bytedance.push.e.c> list) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putString("token_cache", ((j) b.a(j.class, this.f4922c)).a(list));
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public long k() {
        if (this.f4921b == null || !this.f4921b.f("last_upload_switch_ts")) {
            return 0L;
        }
        return this.f4921b.c("last_upload_switch_ts");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void b(long j) {
        if (this.f4921b != null) {
            SharedPreferences.Editor editorB = this.f4921b.b();
            editorB.putLong("last_upload_switch_ts", j);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, a aVar) {
        if (this.f4921b != null) {
            this.f4921b.a(context, str, str2, aVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(a aVar) {
        if (this.f4921b != null) {
            this.f4921b.a(aVar);
        }
    }
}
