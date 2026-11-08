package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bc {
    private static bc a;

    /* renamed from: a, reason: collision with other field name */
    private int f915a = 0;

    /* renamed from: a, reason: collision with other field name */
    private Context f916a;

    private bc(Context context) {
        this.f916a = context.getApplicationContext();
    }

    public static bc a(Context context) {
        if (a == null) {
            a = new bc(context);
        }
        return a;
    }

    @SuppressLint({"NewApi"})
    public int a() {
        if (this.f915a != 0) {
            return this.f915a;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                this.f915a = Settings.Global.getInt(this.f916a.getContentResolver(), "device_provisioned", 0);
            } catch (Exception unused) {
            }
            return this.f915a;
        }
        this.f915a = Settings.Secure.getInt(this.f916a.getContentResolver(), "device_provisioned", 0);
        return this.f915a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a, reason: collision with other method in class */
    public Uri m612a() {
        return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getUriFor("device_provisioned") : Settings.Secure.getUriFor("device_provisioned");
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m613a() {
        return com.xiaomi.push.ab.f103a.contains("xmsf") || com.xiaomi.push.ab.f103a.contains("xiaomi") || com.xiaomi.push.ab.f103a.contains("miui");
    }
}
