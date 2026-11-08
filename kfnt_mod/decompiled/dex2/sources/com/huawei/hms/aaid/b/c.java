package com.huawei.hms.aaid.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    protected SharedPreferences a;

    public c(Context context, String str) {
        if (context == null) {
            throw new NullPointerException("context is null!");
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Context contextCreateDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
            SharedPreferences sharedPreferences = contextCreateDeviceProtectedStorageContext.getSharedPreferences("move_to_de_records", 0);
            if (sharedPreferences.getBoolean(str, false)) {
                context = contextCreateDeviceProtectedStorageContext;
            } else if (contextCreateDeviceProtectedStorageContext.moveSharedPreferencesFrom(context, str)) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putBoolean(str, true);
                editorEdit.apply();
                context = contextCreateDeviceProtectedStorageContext;
            }
        }
        this.a = context.getSharedPreferences(str, 0);
    }

    public boolean a(String str) {
        return this.a != null && this.a.getBoolean(str, false);
    }

    public String b(String str) {
        return this.a != null ? this.a.getString(str, "") : "";
    }

    public long c(String str) {
        if (this.a != null) {
            return this.a.getLong(str, 0L);
        }
        return 0L;
    }

    public boolean a(String str, String str2) {
        SharedPreferences.Editor editorEdit;
        if (this.a == null || (editorEdit = this.a.edit()) == null) {
            return false;
        }
        return editorEdit.putString(str, str2).commit();
    }

    public void a(String str, Long l) {
        SharedPreferences.Editor editorEdit;
        if (this.a == null || (editorEdit = this.a.edit()) == null) {
            return;
        }
        editorEdit.putLong(str, l.longValue()).commit();
    }

    public void a(String str, boolean z) {
        SharedPreferences.Editor editorEdit;
        if (this.a == null || (editorEdit = this.a.edit()) == null) {
            return;
        }
        editorEdit.putBoolean(str, z).commit();
    }

    public boolean d(String str) {
        return this.a != null && this.a.contains(str);
    }

    public boolean e(String str) {
        SharedPreferences.Editor editorEdit;
        if (this.a == null || !this.a.contains(str) || (editorEdit = this.a.edit()) == null) {
            return false;
        }
        return editorEdit.remove(str).commit();
    }
}
