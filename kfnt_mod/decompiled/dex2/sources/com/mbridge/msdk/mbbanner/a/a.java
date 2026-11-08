package com.mbridge.msdk.mbbanner.a;

import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.tools.ac;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbanner.common.a.b;
import com.mbridge.msdk.mbbanner.common.c.c;
import com.mbridge.msdk.out.BannerAdListener;
import com.mbridge.msdk.out.BannerSize;
import com.mbridge.msdk.out.MBBannerView;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.List;

/* compiled from: BannerController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static String a = "BannerController";
    private String b;
    private String c;
    private MBridgeIds d;
    private boolean e;
    private int f;
    private MBBannerView g;
    private int h;
    private int i;
    private int j;
    private BannerAdListener l;
    private CampaignUnit m;
    private c n;
    private d o;
    private com.mbridge.msdk.b.c p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private int k = -1;
    private com.mbridge.msdk.mbbanner.common.b.c v = new com.mbridge.msdk.mbbanner.common.b.c() { // from class: com.mbridge.msdk.mbbanner.a.a.1
        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void a(List<CampaignEx> list) {
            if (a.this.l != null) {
                a.this.l.onLoadSuccessed(a.this.d);
            }
            s.d(a.a, "onShowSuccessed:");
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void a(CampaignEx campaignEx, boolean z) {
            a.this.c();
            if (a.this.l == null || z) {
                return;
            }
            a.this.l.onLogImpression(a.this.d);
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void a(String str) {
            a.this.b(str);
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void a() {
            if (a.this.l != null) {
                a.this.l.onClick(a.this.d);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void b() {
            if (a.this.l != null) {
                a.this.l.onLeaveApp(a.this.d);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void c() {
            if (a.this.l != null) {
                a.this.l.showFullScreen(a.this.d);
                a.this.u = true;
                com.mbridge.msdk.mbbanner.common.c.a.a().a(2, a.this.c, a.this.b, null, null);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void d() {
            if (a.this.l != null) {
                a.this.l.closeFullScreen(a.this.d);
                a.this.u = false;
                com.mbridge.msdk.mbbanner.common.c.a.a().a(3, a.this.c, a.this.b, new b(a.this.i + "x" + a.this.h, a.this.j * 1000), a.this.w);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void e() {
            if (a.this.l != null) {
                a.this.l.onCloseBanner(a.this.d);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.c
        public final void a(boolean z) {
            if (z) {
                com.mbridge.msdk.mbbanner.common.c.a.a().a(2, a.this.c, a.this.b, null, null);
                return;
            }
            com.mbridge.msdk.mbbanner.common.c.a.a().a(3, a.this.c, a.this.b, new b(a.this.i + "x" + a.this.h, a.this.j * 1000), a.this.w);
        }
    };
    private com.mbridge.msdk.mbbanner.common.b.b w = new com.mbridge.msdk.mbbanner.common.b.b() { // from class: com.mbridge.msdk.mbbanner.a.a.2
        @Override // com.mbridge.msdk.mbbanner.common.b.b
        public final void a(String str, CampaignUnit campaignUnit, boolean z) {
            a.this.m = campaignUnit;
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.b
        public final void a(String str, String str2, boolean z) {
            if (a.this.l != null) {
                a.this.l.onLoadFailed(a.this.d, str2);
            }
            s.d(a.a, "onCampaignFail:" + str2);
            a.this.c();
            com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, a.this.b, z);
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.b
        public final void a(String str, boolean z) throws Throwable {
            if (a.this.m != null) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), a.this.m.getAds(), a.this.b, z);
            }
            if (a.this.g != null) {
                a.this.t = true;
                a.this.h();
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.b
        public final void b(String str, boolean z) {
            if (a.this.l != null) {
                a.this.l.onLoadFailed(a.this.d, "banner res load failed");
            }
            s.d(a.a, "onResourceFail:");
            a.this.c();
            com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), "banner res load failed", a.this.b, z);
        }
    };

    private int b(int i) {
        if (i <= 0) {
            return i;
        }
        if (i < 10) {
            return 10;
        }
        if (i > 180) {
            return 180;
        }
        return i;
    }

    public final String a() {
        return (this.m == null || this.m.getRequestId() == null) ? "" : this.m.getRequestId();
    }

    public a(MBBannerView mBBannerView, BannerSize bannerSize, String str, String str2) {
        this.g = mBBannerView;
        if (bannerSize != null) {
            this.h = bannerSize.getHeight();
            this.i = bannerSize.getWidth();
        }
        this.b = str2;
        this.c = str;
        this.d = new MBridgeIds(this.c, this.b);
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String strL = com.mbridge.msdk.foundation.controller.a.f().l();
        if (this.p == null) {
            this.p = new com.mbridge.msdk.b.c();
        }
        this.p.a(com.mbridge.msdk.foundation.controller.a.f().j(), strK, strL, this.b);
        g();
    }

    public final void a(BannerSize bannerSize) {
        if (bannerSize != null) {
            this.h = bannerSize.getHeight();
            this.i = bannerSize.getWidth();
        }
    }

    public final void a(int i) {
        this.k = b(i);
        this.j = this.k;
    }

    public final void a(boolean z) {
        this.e = z;
        this.f = z ? 1 : 2;
    }

    private void g() {
        this.o = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.b);
        if (this.o == null) {
            this.o = d.d(this.b);
        }
        if (this.k == -1) {
            this.j = b(this.o.b());
        }
        if (this.f == 0) {
            this.e = this.o.c() == 1;
            if (this.n != null) {
                this.n.a(this.e);
            }
        }
    }

    public final void a(String str) {
        if (this.h < 1 || this.i < 1) {
            if (this.l != null) {
                this.l.onLoadFailed(this.d, "banner load failed because params are exception");
                return;
            }
            return;
        }
        b bVar = new b(this.i + "x" + this.h, this.j * 1000);
        bVar.a(str);
        bVar.b(this.c);
        com.mbridge.msdk.mbbanner.common.c.a.a().a(this.c, this.b, bVar, this.w);
        com.mbridge.msdk.mbbanner.common.c.a.a().a(1, this.c, this.b, bVar, this.w);
    }

    public final void a(BannerAdListener bannerAdListener) {
        this.l = bannerAdListener;
    }

    public final void b() {
        this.s = true;
        if (this.l != null) {
            this.l = null;
        }
        if (this.w != null) {
            this.w = null;
        }
        if (this.v != null) {
            this.v = null;
        }
        if (this.g != null) {
            this.g = null;
        }
        com.mbridge.msdk.mbbanner.common.c.a.a().a(4, this.c, this.b, null, null);
        com.mbridge.msdk.mbbanner.common.c.a.a().a(this.b);
        com.mbridge.msdk.mbbanner.common.c.a.a().b();
        if (this.n != null) {
            this.n.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws Throwable {
        if (this.s || !this.t) {
            return;
        }
        MBBannerView mBBannerView = this.g;
        if (this.m != null) {
            if (this.n == null) {
                this.n = new c(this.g, this.v, this.c, this.b, this.e, this.o);
            }
            this.n.b(this.q);
            this.n.c(this.r);
            this.n.a(this.e, this.f);
            this.n.a(this.m);
        } else {
            b("banner show failed because campain is exception");
        }
        this.t = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.l != null) {
            this.l.onLoadFailed(this.d, str);
        }
        s.d(a, "showFailed:" + str);
        c();
    }

    public final void c() {
        if (this.s) {
            return;
        }
        i();
        g();
        b bVar = new b(this.i + "x" + this.h, this.j * 1000);
        bVar.b(this.c);
        com.mbridge.msdk.mbbanner.common.c.a.a().b(this.c, this.b, bVar, this.w);
    }

    public final void b(boolean z) {
        this.q = z;
        j();
        h();
    }

    public final void c(boolean z) {
        this.r = z;
        j();
    }

    private void i() {
        if (this.g != null) {
            if (this.q && this.r && !this.u && !ac.a(this.g)) {
                com.mbridge.msdk.mbbanner.common.c.a.a().a(3, this.c, this.b, new b(this.i + "x" + this.h, this.j * 1000), this.w);
            } else {
                com.mbridge.msdk.mbbanner.common.c.a.a().a(2, this.c, this.b, null, null);
            }
            if (this.q) {
                return;
            }
            com.mbridge.msdk.mbbanner.common.c.a.a().a(4, this.c, this.b, null, null);
            com.mbridge.msdk.mbbanner.common.c.a.a().a(this.b);
        }
    }

    private void j() {
        i();
        if (this.n != null) {
            this.n.b(this.q);
            this.n.c(this.r);
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        if (this.n != null) {
            this.n.a(i, i2, i3, i4);
        }
    }

    public final void d() {
        com.mbridge.msdk.mbbanner.common.c.a.a().a(4, this.c, this.b, new b(this.i + "x" + this.h, this.j * 1000), this.w);
    }

    public final void e() {
        com.mbridge.msdk.mbbanner.common.c.a.a().a(3, this.c, this.b, new b(this.i + "x" + this.h, this.j * 1000), this.w);
    }
}
