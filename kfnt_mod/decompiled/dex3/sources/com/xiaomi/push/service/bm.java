package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.mipush.sdk.Constants;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class bm implements ai {
    private static volatile bm a;

    /* renamed from: a, reason: collision with other field name */
    private long f932a;

    /* renamed from: a, reason: collision with other field name */
    Context f933a;

    /* renamed from: a, reason: collision with other field name */
    private SharedPreferences f934a;

    /* renamed from: a, reason: collision with other field name */
    private volatile boolean f936a = false;

    /* renamed from: a, reason: collision with other field name */
    private ConcurrentHashMap<String, a> f935a = new ConcurrentHashMap<>();

    public static abstract class a implements Runnable {
        long a;

        /* renamed from: a, reason: collision with other field name */
        String f937a;

        a(String str, long j) {
            this.f937a = str;
            this.a = j;
        }

        abstract void a(bm bmVar);

        @Override // java.lang.Runnable
        public void run() {
            if (bm.a != null) {
                Context context = bm.a.f933a;
                if (com.xiaomi.push.bg.d(context)) {
                    if (System.currentTimeMillis() - bm.a.f934a.getLong(":ts-" + this.f937a, 0L) > this.a || com.xiaomi.push.af.a(context)) {
                        com.xiaomi.push.r.a(bm.a.f934a.edit().putLong(":ts-" + this.f937a, System.currentTimeMillis()));
                        a(bm.a);
                    }
                }
            }
        }
    }

    private bm(Context context) {
        this.f933a = context.getApplicationContext();
        this.f934a = context.getSharedPreferences("sync", 0);
    }

    public static bm a(Context context) {
        if (a == null) {
            synchronized (bm.class) {
                if (a == null) {
                    a = new bm(context);
                }
            }
        }
        return a;
    }

    public String a(String str, String str2) {
        return this.f934a.getString(str + Constants.COLON_SEPARATOR + str2, "");
    }

    @Override // com.xiaomi.push.service.ai
    /* renamed from: a, reason: collision with other method in class */
    public void mo626a() {
        if (this.f936a) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f932a < 3600000) {
            return;
        }
        this.f932a = jCurrentTimeMillis;
        this.f936a = true;
        com.xiaomi.push.ai.a(this.f933a).a(new bn(this), (int) (Math.random() * 10.0d));
    }

    public void a(a aVar) {
        if (this.f935a.putIfAbsent(aVar.f937a, aVar) == null) {
            com.xiaomi.push.ai.a(this.f933a).a(aVar, ((int) (Math.random() * 30.0d)) + 10);
        }
    }

    public void a(String str, String str2, String str3) {
        com.xiaomi.push.r.a(a.f934a.edit().putString(str + Constants.COLON_SEPARATOR + str2, str3));
    }
}
