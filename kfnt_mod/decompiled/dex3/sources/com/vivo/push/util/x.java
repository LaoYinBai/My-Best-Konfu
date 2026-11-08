package com.vivo.push.util;

import android.content.Context;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class x implements c {
    private static final HashMap<String, Integer> b = new HashMap<>();
    private static final HashMap<String, Long> c = new HashMap<>();
    private static final HashMap<String, String> d = new HashMap<>();
    private static x e;
    public Context a;
    private c f;
    private boolean g;

    private x(Context context) {
        this.g = false;
        this.a = context;
        this.g = a(context);
        n.d("SystemCache", "init status is " + this.g + ";  curCache is " + this.f);
    }

    public static synchronized x b(Context context) {
        if (e == null) {
            e = new x(context.getApplicationContext());
        }
        return e;
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        this.f = new u();
        boolean zA = this.f.a(context);
        if (!zA) {
            this.f = new t();
            zA = this.f.a(context);
        }
        if (!zA) {
            this.f = new w();
            zA = this.f.a(context);
        }
        if (!zA) {
            this.f = null;
        }
        return zA;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        String str3 = d.get(str);
        return (str3 != null || this.f == null) ? str3 : this.f.a(str, str2);
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        d.put(str, str2);
        if (!this.g || this.f == null) {
            return;
        }
        this.f.b(str, str2);
    }
}
