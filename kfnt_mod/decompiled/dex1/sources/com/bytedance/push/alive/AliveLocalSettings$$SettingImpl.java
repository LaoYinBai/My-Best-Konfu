package com.bytedance.push.alive;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AliveLocalSettings$$SettingImpl implements AliveLocalSettings {

    /* renamed from: a, reason: collision with root package name */
    private Context f4723a;

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f4724b;

    /* renamed from: c, reason: collision with root package name */
    private final com.bytedance.push.settings.c f4725c = new com.bytedance.push.settings.c() { // from class: com.bytedance.push.alive.AliveLocalSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.c
        public <T> T a(Class<T> cls) {
            return null;
        }
    };

    public AliveLocalSettings$$SettingImpl(Context context, com.bytedance.push.settings.storage.i iVar) {
        this.f4723a = context;
        this.f4724b = iVar;
    }

    @Override // com.bytedance.push.alive.AliveLocalSettings
    public String a() {
        return (this.f4724b == null || !this.f4724b.f("native_socket")) ? "" : this.f4724b.a("native_socket");
    }

    @Override // com.bytedance.push.alive.AliveLocalSettings
    public void a(String str) {
        if (this.f4724b != null) {
            SharedPreferences.Editor editorB = this.f4724b.b();
            editorB.putString("native_socket", str);
            editorB.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, com.bytedance.push.settings.a aVar) {
        if (this.f4724b != null) {
            this.f4724b.a(context, str, str2, aVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(com.bytedance.push.settings.a aVar) {
        if (this.f4724b != null) {
            this.f4724b.a(aVar);
        }
    }
}
