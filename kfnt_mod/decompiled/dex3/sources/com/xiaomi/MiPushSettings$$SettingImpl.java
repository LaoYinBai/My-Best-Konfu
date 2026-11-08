package com.xiaomi;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.push.settings.a;
import com.bytedance.push.settings.c;
import com.bytedance.push.settings.storage.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MiPushSettings$$SettingImpl implements MiPushSettings {
    private Context a;
    private i b;
    private final c c = new c() { // from class: com.xiaomi.MiPushSettings$$SettingImpl.1
        public <T> T a(Class<T> cls) {
            return null;
        }
    };

    public MiPushSettings$$SettingImpl(Context context, i iVar) {
        this.a = context;
        this.b = iVar;
    }

    @Override // com.xiaomi.MiPushSettings
    public boolean a() {
        if (this.b == null || !this.b.f("mipush_upgrade_3616")) {
            return false;
        }
        return this.b.e("mipush_upgrade_3616");
    }

    @Override // com.xiaomi.MiPushSettings
    public void a(boolean z) {
        if (this.b != null) {
            SharedPreferences.Editor editorB = this.b.b();
            editorB.putBoolean("mipush_upgrade_3616", z);
            editorB.apply();
        }
    }

    public void registerValChanged(Context context, String str, String str2, a aVar) {
        if (this.b != null) {
            this.b.a(context, str, str2, aVar);
        }
    }

    public void unregisterValChanged(a aVar) {
        if (this.b != null) {
            this.b.a(aVar);
        }
    }
}
