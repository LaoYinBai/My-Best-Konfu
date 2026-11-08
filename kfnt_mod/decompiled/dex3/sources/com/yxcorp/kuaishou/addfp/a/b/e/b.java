package com.yxcorp.kuaishou.addfp.a.b.e;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b implements com.yxcorp.kuaishou.addfp.a.b.a {
    private String a = "";
    private Context b;

    public b(Context context) {
        this.b = context;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        if (aVar != null) {
            try {
                aVar.a(c(), this);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean a() {
        return false;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final String b() {
        String strB = a.a(this.b).b();
        return TextUtils.isEmpty(strB) ? "" : strB;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        return a.a(this.b).a();
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
    }
}
