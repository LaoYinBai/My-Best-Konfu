package com.mbridge.msdk.mbbanner.common.util;

import android.os.Handler;
import android.os.Looper;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbanner.common.b.b;

/* compiled from: BannerDelivery.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private final Handler b = new Handler(Looper.getMainLooper());
    private boolean c;

    public final void a(boolean z) {
        this.c = z;
    }

    public final void a(final b bVar, final CampaignUnit campaignUnit, final String str) {
        s.d(a, "postCampaignSuccess unitId=" + str);
        this.b.post(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.util.a.1
            @Override // java.lang.Runnable
            public final void run() {
                if (bVar != null) {
                    bVar.a(str, campaignUnit, a.this.c);
                }
            }
        });
    }

    public final void a(final b bVar, final String str, final String str2) {
        s.b(a, "postCampaignFail errorMsg=" + str + " unitId=" + str2);
        this.b.post(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.util.a.2
            @Override // java.lang.Runnable
            public final void run() {
                if (bVar != null) {
                    bVar.a(str2, str, a.this.c);
                }
            }
        });
    }

    public final void a(final b bVar, final String str) {
        s.d(a, "postResourceSuccess unitId=" + str);
        this.b.post(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.util.a.3
            @Override // java.lang.Runnable
            public final void run() {
                if (bVar != null) {
                    bVar.a(str, a.this.c);
                }
            }
        });
    }

    public final void b(final b bVar, final String str) {
        s.d(a, "postResourceFail unitId=" + str);
        this.b.post(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.util.a.4
            @Override // java.lang.Runnable
            public final void run() {
                if (bVar != null) {
                    bVar.b(str, a.this.c);
                }
            }
        });
    }
}
