package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ax implements ar {
    private static volatile ax a;

    /* renamed from: a, reason: collision with other field name */
    private int f137a = aw.a;

    /* renamed from: a, reason: collision with other field name */
    private ar f138a;

    private ax(Context context) {
        this.f138a = aw.a(context);
        com.xiaomi.channel.commonutils.logger.b.m22a("create id manager is: " + this.f137a);
    }

    public static ax a(Context context) {
        if (a == null) {
            synchronized (ax.class) {
                if (a == null) {
                    a = new ax(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    private String a(String str) {
        return str == null ? "" : str;
    }

    private String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.length() > 5 ? str.substring(str.length() - 5) : str;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public String mo100a() {
        return a(this.f138a.mo100a());
    }

    public void a(Map<String, String> map) {
        if (map == null) {
            return;
        }
        String strMo100a = mo100a();
        if (!TextUtils.isEmpty(strMo100a)) {
            map.put("udid", strMo100a);
        }
        String strMo102b = mo102b();
        if (!TextUtils.isEmpty(strMo102b)) {
            map.put("oaid", strMo102b);
        }
        String strC = c();
        if (!TextUtils.isEmpty(strC)) {
            map.put("vaid", strC);
        }
        String strD = d();
        if (!TextUtils.isEmpty(strD)) {
            map.put("aaid", strD);
        }
        map.put("oaid_type", String.valueOf(this.f137a));
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public boolean mo101a() {
        return this.f138a.mo101a();
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b */
    public String mo102b() {
        return a(this.f138a.mo102b());
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        return a(this.f138a.c());
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        return a(this.f138a.d());
    }

    public String e() {
        return "t:" + this.f137a + " s:" + mo101a() + " d:" + b(mo100a()) + " | " + b(mo102b()) + " | " + b(c()) + " | " + b(d());
    }
}
