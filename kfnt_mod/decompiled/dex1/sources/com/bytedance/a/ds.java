package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ds extends bz {

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f3514a;

    /* renamed from: b, reason: collision with root package name */
    public final SharedPreferences f3515b;

    public ds(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.f3514a = context.getSharedPreferences("snssdk_openudid", 0);
        this.f3515b = context.getSharedPreferences(str, 0);
    }

    @Override // com.bytedance.a.bz
    public String a(String str) {
        return d(str).getString(str, null);
    }

    @Override // com.bytedance.a.bz
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = d(str).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    @Override // com.bytedance.a.bz
    public String[] b(String str) {
        String string = d(str).getString(str, null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string.split("\n");
    }

    @Override // com.bytedance.a.bz
    public void c(String str) {
        SharedPreferences sharedPreferencesD = d(str);
        if (sharedPreferencesD != null && sharedPreferencesD.contains(str)) {
            d(str).edit().remove(str).apply();
        }
        super.c(str);
    }

    public final SharedPreferences d(String str) {
        return com.bytedance.b.a.c.h.x.equals(str) ? this.f3515b : this.f3514a;
    }

    @Override // com.bytedance.a.bz
    public void a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        String strJoin = TextUtils.join("\n", strArr);
        if (TextUtils.isEmpty(strJoin)) {
            return;
        }
        SharedPreferences.Editor editorEdit = d(str).edit();
        editorEdit.putString(str, strJoin);
        editorEdit.apply();
    }
}
