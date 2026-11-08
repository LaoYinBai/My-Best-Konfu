package com.yxcorp.kuaishou.addfp.a.b.h;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.yxcorp.kuaishou.addfp.android.b.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b implements com.yxcorp.kuaishou.addfp.a.b.a {
    private String a = "";
    private Context b;

    public b(Context context) {
        this.b = context;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                aVar.a(false, new c());
            }
            if (aVar != null) {
                aVar.a(c(), this);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(String str) {
        this.a = str;
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
