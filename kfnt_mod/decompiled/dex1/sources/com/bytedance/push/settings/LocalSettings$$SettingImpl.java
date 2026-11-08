package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LocalSettings$$SettingImpl implements LocalSettings {

    /* renamed from: a, reason: collision with root package name */
    private Context f4924a;

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f4925b;

    /* renamed from: c, reason: collision with root package name */
    private final c f4926c = new c() { // from class: com.bytedance.push.settings.LocalSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.c
        public <T> T a(Class<T> cls) {
            return null;
        }
    };

    public LocalSettings$$SettingImpl(Context context, com.bytedance.push.settings.storage.i iVar) {
        this.f4924a = context;
        this.f4925b = iVar;
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean a() {
        if (this.f4925b == null || !this.f4925b.f("allow_network")) {
            return true;
        }
        return this.f4925b.e("allow_network");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(boolean z) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putBoolean("allow_network", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String b() {
        return (this.f4925b == null || !this.f4925b.f("push_daemon_monitor")) ? "" : this.f4925b.a("push_daemon_monitor");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(String str) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putString("push_daemon_monitor", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String c() {
        return (this.f4925b == null || !this.f4925b.f("push_daemon_monitor_result")) ? "" : this.f4925b.a("push_daemon_monitor_result");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(String str) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putString("push_daemon_monitor_result", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String d() {
        return (this.f4925b == null || !this.f4925b.f("push_channels_json_array")) ? "" : this.f4925b.a("push_channels_json_array");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void c(String str) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putString("push_channels_json_array", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public int e() {
        if (this.f4925b == null || !this.f4925b.f("ali_push_type")) {
            return -1;
        }
        return this.f4925b.b("ali_push_type");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(int i) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putInt("ali_push_type", i);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean f() {
        if (this.f4925b == null || !this.f4925b.f("push_notify_enable")) {
            return true;
        }
        return this.f4925b.e("push_notify_enable");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(boolean z) {
        if (this.f4925b != null) {
            SharedPreferences.Editor editorB = this.f4925b.b();
            editorB.putBoolean("push_notify_enable", z);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, a aVar) {
        if (this.f4925b != null) {
            this.f4925b.a(context, str, str2, aVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(a aVar) {
        if (this.f4925b != null) {
            this.f4925b.a(aVar);
        }
    }
}
