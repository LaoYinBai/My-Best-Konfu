package com.mbridge.msdk.mbjscommon.confirmation;

import android.content.Context;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: MBAlertDialogProxyListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c implements com.mbridge.msdk.widget.dialog.a {
    private static String a = "MBAlertDialogProxyListener";
    private com.mbridge.msdk.widget.dialog.a b;
    private Context c;
    private CampaignEx d;
    private String e;
    private String f;
    private int g = 2;

    public c(com.mbridge.msdk.widget.dialog.a aVar, Context context, CampaignEx campaignEx, String str, String str2) {
        this.b = aVar;
        this.c = context;
        this.d = campaignEx;
        this.e = str;
        this.f = str2;
    }

    public final void a(int i) {
        this.g = i;
    }

    @Override // com.mbridge.msdk.widget.dialog.a
    public final void a() {
        e.a().b();
        s.a(a, "onDialogCancel");
        com.mbridge.msdk.foundation.same.report.d.a(this.c, this.d, this.e, this.f, this.g, 1, 2);
        if (this.b != null) {
            this.b.a();
        }
    }

    @Override // com.mbridge.msdk.widget.dialog.a
    public final void b() {
        e.a().b();
        s.a(a, "onDialogConfirm");
        com.mbridge.msdk.foundation.same.report.d.a(this.c, this.d, this.e, this.f, this.g, 0, 2);
        if (this.b != null) {
            this.b.b();
        }
    }

    @Override // com.mbridge.msdk.widget.dialog.a
    public final void c() {
        s.a(a, "onDialogCancel");
        com.mbridge.msdk.foundation.same.report.d.a(this.c, this.d, this.e, this.f, this.g, 2, 2);
        if (this.b != null) {
            this.b.c();
        }
    }
}
