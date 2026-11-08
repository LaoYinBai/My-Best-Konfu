package com.vivo.push.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class u implements c {
    private ContentResolver a;

    u() {
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        if (!i.b()) {
            return false;
        }
        this.a = context.getContentResolver();
        return true;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        try {
            return Settings.System.getString(this.a, str);
        } catch (Exception e) {
            e.printStackTrace();
            n.b("SettingsCache", "getString error by " + str);
            return str2;
        }
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        try {
            Settings.System.putString(this.a, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            n.b("SettingsCache", "putString error by " + str);
        }
    }
}
