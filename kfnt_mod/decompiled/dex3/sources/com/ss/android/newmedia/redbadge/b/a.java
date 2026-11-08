package com.ss.android.newmedia.redbadge.b;

import android.content.Context;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;
    private PushMultiProcessSharedProvider.MultiProcessShared b;
    private Context c;

    public static synchronized a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    private a(Context context) {
        this.c = context.getApplicationContext();
        this.b = PushMultiProcessSharedProvider.getMultiprocessShared(this.c);
    }

    public void a(boolean z) {
        this.b.edit().putBoolean("is_desktop_red_badge_show", z).apply();
    }

    public boolean a() {
        return this.b.getBoolean("is_desktop_red_badge_show", false);
    }

    public void a(String str) {
        this.b.edit().putString("desktop_red_badge_args", str).apply();
    }

    public String b() {
        return this.b.getString("desktop_red_badge_args", "");
    }

    public void a(long j) {
        this.b.edit().putLong("red_badge_last_request_time", j).apply();
    }

    public long c() {
        return this.b.getLong("red_badge_last_request_time", 0L);
    }

    public void a(int i) {
        this.b.edit().putInt("red_badge_next_query_interval", i).apply();
    }

    public int d() {
        return this.b.getInt("red_badge_next_query_interval", 600);
    }

    public void b(String str) {
        this.b.edit().putString("red_badge_last_time_paras", str).apply();
    }

    public String e() {
        return this.b.getString("red_badge_last_time_paras", "");
    }

    public void c(String str) {
        this.b.edit().putString("red_badge_last_last_time_paras", str).apply();
    }

    public String f() {
        return this.b.getString("red_badge_last_last_time_paras", "");
    }

    public void b(int i) {
        this.b.edit().putInt("red_badge_launch_times", i).apply();
    }

    public int g() {
        return this.b.getInt("red_badge_launch_times", 0);
    }

    public void c(int i) {
        this.b.edit().putInt("red_badge_show_times", i).apply();
    }

    public int h() {
        return this.b.getInt("red_badge_show_times", 0);
    }

    public void d(String str) {
        this.b.edit().putString("session_key", str).apply();
    }

    public String i() {
        return this.b.getString("session_key", "");
    }

    public void e(String str) {
        this.b.edit().putString("rom", str).apply();
    }

    public String j() {
        return this.b.getString("rom", "");
    }

    public void f(String str) {
        this.b.edit().putString("red_badge_last_valid_response", str).apply();
    }

    public String k() {
        return this.b.getString("red_badge_last_valid_response", "");
    }

    public boolean l() {
        return this.b.getBoolean("red_badge_is_use_last_valid_response", true);
    }
}
