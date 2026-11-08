package com.bytedance.push.third;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends a {

    /* renamed from: c, reason: collision with root package name */
    private static e f4976c;

    @Override // com.bytedance.push.third.a
    public int e() {
        return 6;
    }

    public boolean e(int i) {
        return i == 10 || i == 16 || i == 7;
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ int a(String str) {
        return super.a(str);
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ IPushAdapter a(int i) {
        return super.a(i);
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ Set b() {
        return super.b();
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ boolean b(int i) {
        return super.b(i);
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ boolean b(String str) {
        return super.b(str);
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ JSONArray c() {
        return super.c();
    }

    @Override // com.bytedance.push.third.a
    public /* bridge */ /* synthetic */ String d(int i) {
        return super.d(i);
    }

    public static e a(Context context) {
        if (f4976c == null) {
            synchronized (e.class) {
                if (f4976c == null) {
                    f4976c = new e(context);
                }
            }
        }
        return f4976c;
    }

    private e(Context context) {
        com.ss.android.message.a.a((Application) context.getApplicationContext());
    }

    @Override // com.bytedance.push.third.a
    protected void a() {
        if (this.f4969a.size() == 0) {
            Application applicationA = com.ss.android.message.a.a();
            this.f4969a.put(1, new d(1, "com.xm.MiPushAdapter", "xm/MiPush", new com.bytedance.push.third.a.c(applicationA)));
            this.f4969a.put(2, new d(2, "com.bytedance.push.self.SelfPushAdapter", "SelfPush", new com.bytedance.push.third.a.a(applicationA)));
            this.f4969a.put(6, new d(6, "com.um.UmPushAdapter", "um", new com.bytedance.push.third.a.b()));
            this.f4969a.put(7, new d(7, "com.hw.HWPushAdapter", "hw", new com.bytedance.push.third.a.b()));
            this.f4969a.put(8, new d(8, "com.mz.MzPushAdapter", "mz", new com.bytedance.push.third.a.c(applicationA)));
            this.f4969a.put(10, new d(10, "com.coloros.OpPushAdapter", "op", new com.bytedance.push.third.a.c(applicationA)));
            this.f4969a.put(11, new d(11, "com.vv.VvPushAdapter", "vv", new com.bytedance.push.third.a.a(applicationA)));
            this.f4969a.put(16, new d(16, "com.smt.SmtPushAdapter", "SmtPush", new com.bytedance.push.third.a.a(applicationA)));
            this.f4969a.put(5, new d(5, "com.fcm.FcmPushAdapter", "fcm", new com.bytedance.push.third.a.d(com.ss.android.message.a.a())));
            this.f4969a.put(14, new d(14, "com.adm.push.AdmPushAdapter", "amazon", new com.bytedance.push.third.a.a(com.ss.android.message.a.a())));
        }
    }

    @Override // com.bytedance.push.third.a
    public Pair<String, String> a(int i, com.bytedance.push.d dVar) {
        if (i == 1) {
            return dVar.q.a();
        }
        if (i == 8) {
            return dVar.q.b();
        }
        if (i == 10) {
            return dVar.q.c();
        }
        return null;
    }
}
