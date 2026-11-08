package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.ai;
import com.xiaomi.push.ci;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bw {
    private static volatile bw a;

    /* renamed from: a, reason: collision with other field name */
    private Context f166a;

    /* renamed from: a, reason: collision with other field name */
    private cl f168a;

    /* renamed from: a, reason: collision with other field name */
    private cm f169a;
    private String e;
    private String f;

    /* renamed from: a, reason: collision with other field name */
    private final String f170a = "push_stat_sp";

    /* renamed from: b, reason: collision with other field name */
    private final String f171b = "upload_time";

    /* renamed from: c, reason: collision with other field name */
    private final String f172c = "delete_time";
    private final String d = "check_time";

    /* renamed from: a, reason: collision with other field name */
    private ai.a f167a = new bx(this);
    private ai.a b = new by(this);
    private ai.a c = new bz(this);

    private bw(Context context) {
        this.f166a = context;
    }

    public static bw a(Context context) {
        if (a == null) {
            synchronized (bw.class) {
                if (a == null) {
                    a = new bw(context);
                }
            }
        }
        return a;
    }

    private boolean a() {
        return com.xiaomi.push.service.aq.a(this.f166a).a(ho.StatDataSwitch.a(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        SharedPreferences.Editor editorEdit = this.f166a.getSharedPreferences("push_stat_sp", 0).edit();
        editorEdit.putLong(str, System.currentTimeMillis());
        r.a(editorEdit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        return this.f166a.getDatabasePath(ca.f176a).getAbsolutePath();
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m146a() {
        return this.e;
    }

    public void a(ci.a aVar) {
        ci.a(this.f166a).a(aVar);
    }

    public void a(hn hnVar) {
        if (a() && com.xiaomi.push.service.bo.a(hnVar.e())) {
            a(cf.a(this.f166a, c(), hnVar));
        }
    }

    public void a(String str) {
        if (a() && !TextUtils.isEmpty(str)) {
            a(cn.a(this.f166a, str));
        }
    }

    public void a(String str, String str2, Boolean bool) {
        if (this.f168a != null) {
            if (bool.booleanValue()) {
                this.f168a.a(this.f166a, str2, str);
            } else {
                this.f168a.b(this.f166a, str2, str);
            }
        }
    }

    public String b() {
        return this.f;
    }
}
