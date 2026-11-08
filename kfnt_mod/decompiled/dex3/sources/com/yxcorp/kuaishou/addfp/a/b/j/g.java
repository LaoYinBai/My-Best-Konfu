package com.yxcorp.kuaishou.addfp.a.b.j;

import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class g implements com.yxcorp.kuaishou.addfp.a.b.a, com.yxcorp.kuaishou.addfp.a.b.b {
    private Context a;
    private com.yxcorp.kuaishou.addfp.a.a.a b;
    private String c;

    public g(Context context, com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.a = context;
        this.b = aVar;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        f.a.a(this.a, this);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void a(String str) {
        if (this.b != null) {
            if (TextUtils.isEmpty(str)) {
                this.b.a(false, null);
            } else {
                this.c = str;
                this.b.a(true, this);
            }
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
        return this.c;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        return f.a.a();
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
        f.a.a(this.a);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void e() {
        if (this.b != null) {
            this.b.a(false, this);
        }
    }
}
