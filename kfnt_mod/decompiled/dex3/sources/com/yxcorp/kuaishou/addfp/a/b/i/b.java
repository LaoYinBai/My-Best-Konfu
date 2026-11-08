package com.yxcorp.kuaishou.addfp.a.b.i;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b implements com.yxcorp.kuaishou.addfp.a.b.a {
    private Context a;

    public b(Context context) {
        this.a = context;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean a() {
        return true;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final String b() {
        String strA = a.a(this.a);
        return TextUtils.isEmpty(strA) ? "" : strA;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        return a.a();
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
    }
}
