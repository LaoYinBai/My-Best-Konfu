package com.cmic.sso.sdk.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint({"ApplySharedPref"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static Context f5461a;

    public static void a(Context context) {
        f5461a = context.getApplicationContext();
    }

    public static int a(String str, int i) {
        return f5461a.getSharedPreferences("ssoconfigs", 0).getInt(d.a(str), i);
    }

    public static long a(String str, long j) {
        return f5461a.getSharedPreferences("ssoconfigs", 0).getLong(d.a(str), j);
    }

    static void a(String str, String str2) {
        SharedPreferences sharedPreferences = f5461a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putString(d.a(str), str2).commit();
    }

    public static String b(String str, String str2) {
        return f5461a.getSharedPreferences("ssoconfigs", 0).getString(d.a(str), str2);
    }

    public static a a() {
        return new a(f5461a.getSharedPreferences("ssoconfigs", 0).edit());
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private SharedPreferences.Editor f5462a;

        a(SharedPreferences.Editor editor) {
            this.f5462a = editor;
        }

        public void a(String str, String str2) {
            this.f5462a.putString(d.a(str), str2);
        }

        public void a(String str, long j) {
            this.f5462a.putLong(d.a(str), j);
        }

        public void a(String str, int i) {
            this.f5462a.putInt(d.a(str), i);
        }

        public void a() {
            this.f5462a.apply();
        }

        public void b() {
            this.f5462a.commit();
        }

        void a(String str) {
            this.f5462a.remove(d.a(str));
        }
    }
}
