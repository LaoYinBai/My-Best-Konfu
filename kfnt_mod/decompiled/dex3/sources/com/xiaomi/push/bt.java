package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bt {
    private static volatile bt a;

    /* renamed from: a, reason: collision with other field name */
    private Context f159a;

    private bt(Context context) {
        this.f159a = context;
    }

    public static bt a(Context context) {
        if (a == null) {
            synchronized (bt.class) {
                if (a == null) {
                    a = new bt(context);
                }
            }
        }
        return a;
    }

    public synchronized long a(String str, String str2, long j) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return this.f159a.getSharedPreferences(str, 4).getLong(str2, j);
    }

    public synchronized String a(String str, String str2, String str3) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return this.f159a.getSharedPreferences(str, 4).getString(str2, str3);
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m140a(String str, String str2, long j) {
        SharedPreferences.Editor editorEdit = this.f159a.getSharedPreferences(str, 4).edit();
        editorEdit.putLong(str2, j);
        editorEdit.commit();
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m141a(String str, String str2, String str3) {
        SharedPreferences.Editor editorEdit = this.f159a.getSharedPreferences(str, 4).edit();
        editorEdit.putString(str2, str3);
        editorEdit.commit();
    }
}
