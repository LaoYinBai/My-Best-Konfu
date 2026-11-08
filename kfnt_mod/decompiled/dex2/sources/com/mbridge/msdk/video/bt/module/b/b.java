package com.mbridge.msdk.video.bt.module.b;

import android.content.Context;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.util.List;
import java.util.Random;

/* compiled from: DeductionShowRewardListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b extends c {
    private h a;
    private com.mbridge.msdk.videocommon.d.c b;
    private String c;
    private String d;
    private boolean e;
    private Context f;
    private boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;

    public b(Context context, boolean z, com.mbridge.msdk.videocommon.d.c cVar, CampaignEx campaignEx, h hVar, String str, String str2) {
        this.g = false;
        this.a = hVar;
        this.g = a(cVar, campaignEx);
        this.b = cVar;
        this.c = str2;
        this.d = str;
        this.e = z;
        this.f = context;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a() {
        super.a();
        if (this.a == null || this.h) {
            return;
        }
        if (!this.g || (this.g && !a(1))) {
            this.a.a();
        }
        this.a.a(2, this.d, this.c);
        this.h = true;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a(boolean z, com.mbridge.msdk.videocommon.b.d dVar) {
        super.a(z, dVar);
        if (this.a == null || this.j) {
            return;
        }
        this.a.a(7, this.d, this.c);
        if (!this.g) {
            this.a.a(z, dVar);
            this.j = true;
            return;
        }
        if (!a(5)) {
            if (c() == 0 && a(1)) {
                this.a.a("");
            }
            this.a.a(z, dVar);
        } else {
            this.k = true;
        }
        this.j = true;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a(boolean z, int i) {
        super.a(z, i);
        if (this.a == null || this.j) {
            return;
        }
        if (!this.g) {
            this.a.a(z, i);
        } else {
            if (a(5)) {
                return;
            }
            if (c() == 0 && a(1)) {
                this.a.a("");
            }
            this.a.a(z, i);
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a(String str) {
        super.a(str);
        if (this.a == null || this.i) {
            return;
        }
        if (!this.g || (this.g && !a(1))) {
            this.a.a(str);
        }
        this.a.a(4, this.d, this.c);
        this.i = true;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a(boolean z, String str, String str2) {
        super.a(z, str, str2);
        if (this.a != null) {
            if ((!this.g || (this.g && !a(4))) && !a(z)) {
                this.a.a(z, str, str2);
            }
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void b(String str, String str2) {
        super.b(str, str2);
        if (this.a != null && (!this.g || (this.g && !a(3)))) {
            this.a.b(str, str2);
        }
        if (this.a != null) {
            this.a.a(6, str, str2);
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final void a(String str, String str2) {
        super.a(str, str2);
        if (this.a != null && (!this.g || (this.g && !a(2)))) {
            this.a.a(str, str2);
        }
        if (this.a != null) {
            this.a.a(5, str, str2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039 A[Catch: Exception -> 0x007a, TryCatch #0 {Exception -> 0x007a, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x001b, B:9:0x0025, B:11:0x002f, B:13:0x0039, B:16:0x0040, B:18:0x0046, B:20:0x004f, B:22:0x0053, B:24:0x0057, B:26:0x005c, B:29:0x0064, B:34:0x0071, B:36:0x0077), top: B:39:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040 A[Catch: Exception -> 0x007a, TryCatch #0 {Exception -> 0x007a, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x001b, B:9:0x0025, B:11:0x002f, B:13:0x0039, B:16:0x0040, B:18:0x0046, B:20:0x004f, B:22:0x0053, B:24:0x0057, B:26:0x005c, B:29:0x0064, B:34:0x0071, B:36:0x0077), top: B:39:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.mbridge.msdk.videocommon.d.c r8, com.mbridge.msdk.foundation.entity.CampaignEx r9) {
        /*
            r7 = this;
            r0 = 0
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Exception -> L7a
            java.lang.String r1 = r1.k()     // Catch: java.lang.Exception -> L7a
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L7a
            r3 = 0
            if (r2 != 0) goto L2e
            com.mbridge.msdk.b.b r2 = com.mbridge.msdk.b.b.a()     // Catch: java.lang.Exception -> L7a
            com.mbridge.msdk.b.a r1 = r2.b(r1)     // Catch: java.lang.Exception -> L7a
            if (r1 != 0) goto L23
            com.mbridge.msdk.b.b r1 = com.mbridge.msdk.b.b.a()     // Catch: java.lang.Exception -> L7a
            com.mbridge.msdk.b.a r1 = r1.b()     // Catch: java.lang.Exception -> L7a
        L23:
            if (r1 == 0) goto L2e
            long r1 = r1.B()     // Catch: java.lang.Exception -> L7a
            r5 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r5
            goto L2f
        L2e:
            r1 = r3
        L2f:
            com.mbridge.msdk.videocommon.d.b r5 = com.mbridge.msdk.videocommon.d.b.a()     // Catch: java.lang.Exception -> L7a
            com.mbridge.msdk.videocommon.d.a r5 = r5.b()     // Catch: java.lang.Exception -> L7a
            if (r5 == 0) goto L3d
            long r3 = r5.d()     // Catch: java.lang.Exception -> L7a
        L3d:
            r5 = 1
            if (r9 == 0) goto L5a
            boolean r1 = r9.isSpareOffer(r3, r1)     // Catch: java.lang.Exception -> L7a
            if (r1 == 0) goto L57
            r9.setSpareOfferFlag(r5)     // Catch: java.lang.Exception -> L7a
            int r8 = r8.C()     // Catch: java.lang.Exception -> L7a
            if (r8 != r5) goto L53
            r9.setCbt(r5)     // Catch: java.lang.Exception -> L7a
            return r5
        L53:
            r9.setCbt(r0)     // Catch: java.lang.Exception -> L7a
            return r0
        L57:
            r9.setSpareOfferFlag(r0)     // Catch: java.lang.Exception -> L7a
        L5a:
            if (r9 == 0) goto L6f
            boolean r1 = r9.isBidCampaign()     // Catch: java.lang.Exception -> L7a
            if (r1 != 0) goto L6f
            if (r8 == 0) goto L6f
            int r8 = r8.d()     // Catch: java.lang.Exception -> L7a
            r9.setCbt(r8)     // Catch: java.lang.Exception -> L7a
            if (r8 != r5) goto L6e
            r0 = 1
        L6e:
            return r0
        L6f:
            if (r9 == 0) goto L7a
            boolean r8 = r9.isBidCampaign()     // Catch: java.lang.Exception -> L7a
            if (r8 == 0) goto L7a
            r9.setCbt(r0)     // Catch: java.lang.Exception -> L7a
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.video.bt.module.b.b.a(com.mbridge.msdk.videocommon.d.c, com.mbridge.msdk.foundation.entity.CampaignEx):boolean");
    }

    private int c() {
        com.mbridge.msdk.videocommon.b.c cVarY;
        if (this.b == null || (cVarY = this.b.y()) == null) {
            return 1;
        }
        return cVarY.a();
    }

    private boolean a(int i) {
        com.mbridge.msdk.videocommon.b.c cVarY;
        if (this.b != null && (cVarY = this.b.y()) != null) {
            if (cVarY.a() == 0) {
                return i <= 4;
            }
            List<Integer> listB = cVarY.b();
            if (listB != null) {
                return listB.contains(Integer.valueOf(i));
            }
        }
        return i <= 4;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.c, com.mbridge.msdk.video.bt.module.b.h
    public final boolean b() {
        return this.k;
    }

    private boolean a(boolean z) {
        try {
        } catch (Throwable th) {
            s.b("DeductionShowRewardListener", "", th);
        }
        if (this.b == null) {
            return false;
        }
        switch (this.b.A()) {
            case 1:
                return z;
            case 2:
                return z && d();
            case 3:
                return d();
            default:
                return false;
        }
    }

    private boolean d() {
        try {
            if (this.b == null) {
                return false;
            }
            double dB = this.b.B();
            if (dB == 1.0d) {
                return false;
            }
            return new Random().nextDouble() > dB;
        } catch (Throwable th) {
            s.b("DeductionShowRewardListener", "", th);
            return false;
        }
    }
}
