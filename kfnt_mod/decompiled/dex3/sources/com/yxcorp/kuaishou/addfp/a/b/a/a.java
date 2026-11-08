package com.yxcorp.kuaishou.addfp.a.b.a;

import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a implements com.yxcorp.kuaishou.addfp.a.b.a, com.yxcorp.kuaishou.addfp.a.b.b {
    private com.yxcorp.kuaishou.addfp.a.a.a a;
    private e e;
    private String b = "";
    private String c = "";
    private String d = "";
    private boolean f = false;

    public a(Context context, com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.a = aVar;
        this.e = new e(context);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.e.a(this);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void a(String str) {
        this.b = str;
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        this.a.a(true, this);
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
        return this.b;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        return false;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
        if (this.e != null) {
            this.e.a();
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void e() {
        if (this.a != null) {
            this.a.a(false, this);
        }
    }
}
