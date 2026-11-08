package com.bytedance.push;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Pair;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l implements com.ss.android.pushmanager.b {

    /* renamed from: a, reason: collision with root package name */
    private final Application f4868a;

    /* renamed from: b, reason: collision with root package name */
    private final PushContext f4869b;

    /* renamed from: c, reason: collision with root package name */
    private ApplicationInfo f4870c;

    public l(Application application, PushContext pushContext) {
        this.f4868a = application;
        this.f4869b = pushContext;
    }

    public Pair<String, String> a() {
        return new Pair<>(a("XIAOMI_PUSH_APP_ID"), a("XIAOMI_PUSH_APP_KEY"));
    }

    public Pair<String, String> b() {
        return new Pair<>(a("MEIZU_PUSH_APP_ID"), a("MEIZU_PUSH_APP_KEY"));
    }

    public Pair<String, String> c() {
        return new Pair<>(a("OPPO_PUSH_APP_KEY"), a("OPPO_PUSH_APP_SECRET"));
    }

    public com.ss.android.push.b<String, String, String> d() {
        return new com.ss.android.push.b<String, String, String>() { // from class: com.bytedance.push.l.1
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String f() {
                return l.this.a("UMENG_APPKEY");
            }

            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public String e() {
                return l.this.a("UMENG_MESSAGE_SECRET");
            }

            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public String d() {
                return l.this.f4869b.getTweakedChannel();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        try {
            if (this.f4870c == null) {
                this.f4870c = this.f4868a.getPackageManager().getApplicationInfo(this.f4868a.getPackageName(), 128);
            }
            return String.valueOf(this.f4870c.metaData.get(str));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
