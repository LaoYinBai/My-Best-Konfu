package com.mbridge.msdk.mbbanner.common.b;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.util.List;
import java.util.Random;

/* compiled from: DeductionBannerShowListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements c {
    private static final String a = "e";
    private c b;
    private com.mbridge.msdk.b.d c;
    private boolean d = false;

    public e(c cVar, com.mbridge.msdk.b.d dVar) {
        this.c = dVar;
        this.b = cVar;
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void a(List<CampaignEx> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    com.mbridge.msdk.b.d dVar = this.c;
                    boolean z = false;
                    if (!list.get(0).isBidCampaign() && dVar != null && dVar.e() != 1.0d) {
                        if (new Random().nextDouble() > dVar.e()) {
                            z = true;
                        }
                    }
                    this.d = z;
                    s.d(a, this.d + "");
                }
            } catch (Exception e) {
                s.b(a, "Exception", e);
            }
        }
        if (this.b != null) {
            this.b.a(list);
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void a() {
        if (this.b == null || this.d) {
            return;
        }
        this.b.a();
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void b() {
        if (this.b != null) {
            this.b.b();
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void c() {
        if (this.b != null) {
            this.b.c();
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void d() {
        if (this.b != null) {
            this.b.d();
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void a(CampaignEx campaignEx, boolean z) {
        if (this.b != null) {
            this.b.a(campaignEx, this.d);
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void e() {
        if (this.b != null) {
            this.b.e();
        }
    }

    @Override // com.mbridge.msdk.mbbanner.common.b.c
    public final void a(boolean z) {
        if (this.b != null) {
            this.b.a(z);
        }
    }
}
