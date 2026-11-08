package com.yxcorp.kuaishou.addfp.a.b.c;

import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d implements com.yxcorp.kuaishou.addfp.a.b.a, com.yxcorp.kuaishou.addfp.a.b.b {
    private e a;
    private com.yxcorp.kuaishou.addfp.a.a.a b;
    private Context c;
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private boolean h = false;

    public d(Context context, com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.b = aVar;
        this.c = context;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.a = new e(this.c, this);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void a(String str) {
        com.yxcorp.kuaishou.addfp.a.a.a aVar;
        try {
            if (TextUtils.isEmpty(str)) {
                aVar = this.b;
            } else {
                this.d = str;
                if (!TextUtils.isEmpty(this.d)) {
                    this.b.a(true, this);
                }
                aVar = this.b;
            }
            aVar.a(false, null);
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            d();
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean a() {
        return false;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return null;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final String b() {
        return this.d;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        if (this.a != null) {
            return this.a.a();
        }
        return false;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
        if (this.a != null) {
            this.a.b();
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void e() {
        if (this.b != null) {
            this.b.a(false, this);
        }
    }
}
