package com.huawei.updatesdk.service.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private SharedPreferences a;

    private b(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    public static b a(String str, Context context) {
        SharedPreferences sharedPreferences;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                context = context.createDeviceProtectedStorageContext();
            }
            sharedPreferences = context.getSharedPreferences(str, 0);
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("SharedPreferencesWrapper", "getSharedPreference error");
            sharedPreferences = null;
        }
        return new b(sharedPreferences);
    }

    public void a(String str) {
        try {
            SharedPreferences.Editor editorEdit = this.a.edit();
            editorEdit.remove(str);
            editorEdit.commit();
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("SharedPreferencesWrapper", "remove error!!key:" + str);
        }
    }

    public void a(String str, long j) {
        try {
            SharedPreferences.Editor editorEdit = this.a.edit();
            editorEdit.putLong(str, j);
            editorEdit.commit();
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("SharedPreferencesWrapper", "putLong error!!key:" + str, e);
        }
    }

    public void a(String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = this.a.edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("SharedPreferencesWrapper", "putString error!!key:" + str, e);
        }
    }

    public long b(String str, long j) {
        try {
            return this.a.getLong(str, j);
        } catch (Exception unused) {
            return j;
        }
    }

    public String b(String str, String str2) {
        try {
            return this.a.getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }
}
