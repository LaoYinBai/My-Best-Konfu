package com.huawei.hms.framework.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PLSharedPreferences {
    private static final String MOVE_TO_DE_RECORDS = "grs_move2DE_records";
    private static final String TAG = "PLSharedPreferences";
    private SharedPreferences sp;

    public PLSharedPreferences(Context context, String str) {
        this.sp = getSharedPreferences(context, str);
    }

    private SharedPreferences getSharedPreferences(Context context, String str) {
        if (context == null) {
            Logger.e(TAG, "context is null, must call init method to set context");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Context contextCreateDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
            SharedPreferences sharedPreferences = contextCreateDeviceProtectedStorageContext.getSharedPreferences(MOVE_TO_DE_RECORDS, 0);
            if (sharedPreferences.getBoolean(str, false)) {
                context = contextCreateDeviceProtectedStorageContext;
            } else if (contextCreateDeviceProtectedStorageContext.moveSharedPreferencesFrom(context, str)) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putBoolean(str, true);
                editorEdit.apply();
                context = contextCreateDeviceProtectedStorageContext;
            }
        }
        return context.getSharedPreferences(str, 0);
    }

    public void clear() {
        if (this.sp == null) {
            return;
        }
        this.sp.edit().clear().apply();
    }

    public SharedPreferences.Editor edit() {
        if (this.sp == null) {
            return null;
        }
        return this.sp.edit();
    }

    public Map<String, ?> getAll() {
        if (this.sp == null) {
            return null;
        }
        return this.sp.getAll();
    }

    public long getLong(String str, long j) {
        return this.sp == null ? j : this.sp.getLong(str, j);
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        return this.sp == null ? str2 : this.sp.getString(str, str2);
    }

    public void putLong(String str, long j) {
        if (this.sp == null) {
            return;
        }
        this.sp.edit().putLong(str, j).apply();
    }

    public void putString(String str, String str2) {
        if (this.sp == null) {
            return;
        }
        this.sp.edit().putString(str, str2).apply();
    }

    public void remove(String str) {
        if (this.sp == null) {
            return;
        }
        this.sp.edit().remove(str).apply();
    }

    public void removeKeyValue(String str) {
        if (this.sp == null) {
            return;
        }
        this.sp.edit().remove(str).apply();
    }
}
