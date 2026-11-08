package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g implements i {

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f4961a;

    /* renamed from: b, reason: collision with root package name */
    private Map<com.bytedance.push.settings.a, SharedPreferences.OnSharedPreferenceChangeListener> f4962b = new ConcurrentHashMap();

    private void a(Exception exc) {
    }

    g(Context context, String str) {
        this.f4961a = context.getSharedPreferences(str, 0);
    }

    @Override // com.bytedance.push.settings.storage.i
    public String a(String str) {
        return a(str, (String) null);
    }

    @Override // com.bytedance.push.settings.storage.i
    public String a(String str, String str2) {
        try {
            return this.f4961a.getString(str, str2);
        } catch (Exception e) {
            a(e);
            return str2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public int b(String str) {
        return a(str, 0);
    }

    @Override // com.bytedance.push.settings.storage.i
    public int a(String str, int i) {
        try {
            return this.f4961a.getInt(str, i);
        } catch (Exception e) {
            a(e);
            return i;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public long c(String str) {
        return a(str, 0L);
    }

    @Override // com.bytedance.push.settings.storage.i
    public long a(String str, long j) {
        try {
            return this.f4961a.getLong(str, j);
        } catch (Exception e) {
            a(e);
            return j;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public float d(String str) {
        return a(str, 0.0f);
    }

    @Override // com.bytedance.push.settings.storage.i
    public float a(String str, float f) {
        try {
            return this.f4961a.getFloat(str, f);
        } catch (Exception e) {
            a(e);
            return f;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean e(String str) {
        return a(str, false);
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean a(String str, boolean z) {
        try {
            return this.f4961a.getBoolean(str, z);
        } catch (Exception e) {
            a(e);
            return z;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean f(String str) {
        return this.f4961a.contains(str);
    }

    @Override // com.bytedance.push.settings.storage.i
    public SharedPreferences.Editor b() {
        return this.f4961a.edit();
    }

    @Override // com.bytedance.push.settings.storage.i
    public void a(Context context, final String str, String str2, final com.bytedance.push.settings.a aVar) {
        if (aVar == null) {
            return;
        }
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.bytedance.push.settings.storage.g.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str3) {
                if (TextUtils.equals(str, str3)) {
                    aVar.a();
                }
            }
        };
        this.f4962b.put(aVar, onSharedPreferenceChangeListener);
        this.f4961a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // com.bytedance.push.settings.storage.i
    public void a(com.bytedance.push.settings.a aVar) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListenerRemove;
        if (aVar == null || (onSharedPreferenceChangeListenerRemove = this.f4962b.remove(aVar)) == null) {
            return;
        }
        this.f4961a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListenerRemove);
    }
}
