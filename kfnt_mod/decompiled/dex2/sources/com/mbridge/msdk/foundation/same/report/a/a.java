package com.mbridge.msdk.foundation.same.report.a;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.n;
import com.mbridge.msdk.foundation.entity.k;
import com.mbridge.msdk.foundation.tools.o;

/* compiled from: CampaignRequestTimeUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private k a;
    private i b;
    private Context c = com.mbridge.msdk.foundation.controller.a.f().j();

    public a(k kVar) {
        this.b = null;
        this.a = kVar;
        this.b = i.a(this.c);
        if (this.a == null || this.c == null) {
            return;
        }
        int iC = o.C(this.c);
        this.a.e(iC);
        this.a.c(o.a(this.c, iC));
        this.a.d(2);
    }

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.a.a(str);
    }

    public final void a(int i) {
        if (this.a != null) {
            this.a.b(i);
        }
    }

    public final void b(int i) {
        if (this.a != null) {
            this.a.c(i);
        }
    }

    public final void a() {
        if (this.a != null) {
            n.a(this.b).a(this.a);
        }
    }

    public final void b(String str) {
        if (this.a != null) {
            this.a.b(str);
        }
    }

    public final void c(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }
}
