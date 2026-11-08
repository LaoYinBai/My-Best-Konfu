package com.yxcorp.kuaishou.addfp.android.a;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e {
    private SharedPreferences a;
    private SharedPreferences.Editor b;

    public e(Context context) {
        try {
            this.a = context.getSharedPreferences("kscfg_outdfp", 0);
            this.b = this.a.edit();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(String str) {
        this.b.putString("kwtk", str);
        this.b.commit();
    }

    public final boolean a() {
        return this.a.getBoolean("xytk", true);
    }

    public final String b() {
        return this.a.getString("kwtk", "");
    }

    public final void b(String str) {
        this.b.putString("oai_ll_sn_d", str);
        this.b.commit();
    }

    public final String c() {
        return this.a.getString("oai_ll_sn_d", "");
    }
}
