package com.mbridge.msdk.video.module.a.a;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.t;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.z;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: StatisticsOnNotifyListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends e {
    protected boolean a;
    protected CampaignEx b;
    protected boolean c;
    protected com.mbridge.msdk.videocommon.download.a d;
    protected com.mbridge.msdk.videocommon.b.d e;
    protected String f;
    protected String g;
    protected com.mbridge.msdk.video.module.a.a h;
    protected int i;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;

    public j(CampaignEx campaignEx, com.mbridge.msdk.videocommon.download.a aVar, com.mbridge.msdk.videocommon.b.d dVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, int i, boolean z) {
        this.c = false;
        this.h = new e();
        this.i = 1;
        if (!z && campaignEx != null && z.b(str2) && aVar != null && aVar2 != null) {
            this.b = campaignEx;
            this.g = str;
            this.f = str2;
            this.d = aVar;
            this.e = dVar;
            this.h = aVar2;
            this.a = true;
            this.i = i;
            this.c = false;
            return;
        }
        if (!z || campaignEx == null || !z.b(str2) || aVar2 == null) {
            return;
        }
        this.b = campaignEx;
        this.g = str;
        this.f = str2;
        this.d = aVar;
        this.e = dVar;
        this.h = aVar2;
        this.a = true;
        this.i = i;
        this.c = true;
    }

    @Override // com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public void a(int i, Object obj) {
        super.a(i, obj);
        this.h.a(i, obj);
    }

    public final void a(int i) {
        if (this.b != null) {
            switch (i) {
                case 1:
                case 2:
                    com.mbridge.msdk.video.module.b.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, i, this.i);
                    break;
            }
        }
    }

    public final void a() {
        if (!this.a || this.b == null) {
            return;
        }
        q qVar = new q("2000061", this.b.getId(), this.b.getRequestId(), this.b.getRequestIdNotice(), this.f, o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
        qVar.a(this.b.isMraid() ? q.a : q.b);
        com.mbridge.msdk.foundation.same.report.b.b(qVar, com.mbridge.msdk.foundation.controller.a.f().j(), this.f);
    }

    public final void a(int i, String str) {
        if (this.b != null) {
            com.mbridge.msdk.foundation.same.report.b.c(new q("2000062", this.b.getId(), this.b.getRequestId(), this.b.getRequestIdNotice(), this.f, o.C(com.mbridge.msdk.foundation.controller.a.f().j()), i, str), com.mbridge.msdk.foundation.controller.a.f().j(), this.f);
        }
    }

    public final void b(int i) {
        if (this.b != null) {
            String noticeUrl = this.b.getNoticeUrl();
            if (TextUtils.isEmpty(noticeUrl)) {
                return;
            }
            if (i == 1 || i == 2) {
                if (!noticeUrl.contains("endscreen_type")) {
                    StringBuilder sb = new StringBuilder(noticeUrl);
                    if (noticeUrl.contains("?")) {
                        sb.append("&endscreen_type=");
                        sb.append(i);
                    } else {
                        sb.append("?endscreen_type=");
                        sb.append(i);
                    }
                    noticeUrl = sb.toString();
                } else if (i == 2) {
                    if (noticeUrl.contains("endscreen_type=1")) {
                        noticeUrl = noticeUrl.replace("endscreen_type=1", "endscreen_type=2");
                    }
                } else if (noticeUrl.contains("endscreen_type=2")) {
                    noticeUrl = noticeUrl.replace("endscreen_type=2", "endscreen_type=1");
                }
                this.b.setNoticeUrl(noticeUrl);
            }
        }
    }

    public final void b() {
        try {
            if (!this.a || this.b == null || !z.b(this.f) || com.mbridge.msdk.foundation.controller.a.f().j() == null) {
                return;
            }
            com.mbridge.msdk.foundation.db.l lVarA = com.mbridge.msdk.foundation.db.l.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            com.mbridge.msdk.foundation.entity.h hVar = new com.mbridge.msdk.foundation.entity.h();
            hVar.a(System.currentTimeMillis());
            hVar.b(this.f);
            hVar.a(this.b.getId());
            lVarA.a(hVar);
        } catch (Throwable th) {
            s.b("NotifyListener", th.getMessage(), th);
        }
    }

    public final void c() {
        try {
            if (this.a && this.b != null && z.b(this.f)) {
                com.mbridge.msdk.videocommon.a.a.a().a(this.b, this.f);
            }
            com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.video.module.a.a.j.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        s.a("NotifyListener", "Delete cached campaigns by alrbs");
                        com.mbridge.msdk.videocommon.a.a.a().c(j.this.g, j.this.b.getAdType());
                    } catch (Exception e) {
                        if (MBridgeConstans.DEBUG) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th) {
                        s.a("NotifyListener", th.getMessage());
                    }
                }
            });
        } catch (Throwable th) {
            s.b("NotifyListener", th.getMessage(), th);
        }
    }

    protected final void d() {
        if (this.d != null) {
            this.d.b(true);
        }
    }

    protected final void e() {
        String str;
        try {
            if (!this.a || TextUtils.isEmpty(this.b.getOnlyImpressionURL()) || com.mbridge.msdk.foundation.same.a.d.a == null || com.mbridge.msdk.foundation.same.a.d.a.containsKey(this.b.getOnlyImpressionURL()) || this.k) {
                return;
            }
            com.mbridge.msdk.foundation.same.a.d.a.put(this.b.getOnlyImpressionURL(), Long.valueOf(System.currentTimeMillis()));
            String onlyImpressionURL = this.b.getOnlyImpressionURL();
            if (this.b.getSpareOfferFlag() == 1) {
                str = onlyImpressionURL + "&to=1&cbt=" + this.b.getCbt() + "&tmorl=" + this.i;
            } else {
                str = onlyImpressionURL + "&to=0&cbt=" + this.b.getCbt() + "&tmorl=" + this.i;
            }
            String str2 = str;
            if (!this.c) {
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, this.f, str2, false, true);
                c();
            }
            this.k = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final void f() {
        try {
            if (!this.a || this.j || TextUtils.isEmpty(this.b.getImpressionURL())) {
                return;
            }
            this.j = true;
            if (this.b.isBidCampaign() && this.b != null) {
                try {
                    HashMap map = new HashMap();
                    List<com.mbridge.msdk.foundation.entity.d> listB = com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).b(this.b.getCampaignUnitId(), this.b.getRequestId());
                    if (listB != null && listB.size() > 0 && listB.get(0) != null) {
                        int iD = listB.get(0).d();
                        String strB = listB.get(0).b();
                        if (iD == 1) {
                            map.put("encrypt_p=", "encrypt_p=" + strB);
                            map.put("irlfa=", "irlfa=1");
                            for (Map.Entry entry : map.entrySet()) {
                                String str = (String) entry.getKey();
                                String str2 = (String) entry.getValue();
                                this.b.setImpressionURL(this.b.getImpressionURL().replaceAll(str, str2));
                                this.b.setOnlyImpressionURL(this.b.getOnlyImpressionURL().replaceAll(str, str2));
                            }
                        }
                        s.a("BidReplaceCampignDao", "removeReplace count " + com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(this.b.getRequestId()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String impressionURL = this.b.getImpressionURL();
            com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, this.f, this.b.getSpareOfferFlag() == 1 ? impressionURL + "&to=1&cbt=" + this.b.getCbt() + "&tmorl=" + this.i : impressionURL + "&to=0&cbt=" + this.b.getCbt() + "&tmorl=" + this.i, false, true);
            com.mbridge.msdk.video.module.b.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b);
            new Thread(new Runnable() { // from class: com.mbridge.msdk.video.module.a.a.j.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.mbridge.msdk.foundation.db.m.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).b(j.this.b.getId());
                    } catch (Throwable th) {
                        s.b("NotifyListener", th.getMessage(), th);
                    }
                }
            }).start();
            if (!this.a || com.mbridge.msdk.foundation.same.a.d.d == null || TextUtils.isEmpty(this.b.getId())) {
                return;
            }
            com.mbridge.msdk.foundation.same.a.d.b(this.f, this.b, "reward");
        } catch (Throwable th) {
            s.b("NotifyListener", th.getMessage(), th);
        }
    }

    protected final void g() {
        List<String> pv_urls;
        try {
            if (!this.a || this.l || this.b == null) {
                return;
            }
            this.l = true;
            if (this.c || (pv_urls = this.b.getPv_urls()) == null || pv_urls.size() <= 0) {
                return;
            }
            Iterator<String> it = pv_urls.iterator();
            while (it.hasNext()) {
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, this.f, it.next(), false, true);
            }
        } catch (Throwable th) {
            s.d("NotifyListener", th.getMessage());
        }
    }

    protected final void h() {
        if (this.b == null || TextUtils.isEmpty(this.b.getCampaignUnitId()) || this.b.getNativeVideoTracking() == null || this.b.getNativeVideoTracking().m() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, this.b.getCampaignUnitId(), this.b.getNativeVideoTracking().m(), false, false);
    }

    protected final void i() {
        try {
            if (!this.a || this.d == null) {
                return;
            }
            if (this.d.g() != null && !TextUtils.isEmpty(this.d.g().getVideoUrlEncode())) {
                t.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).b(this.d.g().getVideoUrlEncode());
            }
            if (TextUtils.isEmpty(this.d.d())) {
                return;
            }
            File file = new File(this.d.d());
            if (file.exists() && file.isFile() && file.delete()) {
                s.a("NotifyListener", "DEL File :" + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final void a(String str) {
        try {
            if (this.a) {
                u uVarA = u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                q qVar = null;
                if (!TextUtils.isEmpty(this.b.getNoticeUrl())) {
                    int iC = o.C(com.mbridge.msdk.foundation.controller.a.f().j());
                    qVar = new q("2000021", iC, this.b.getNoticeUrl(), str, o.a(com.mbridge.msdk.foundation.controller.a.f().j(), iC));
                } else if (!TextUtils.isEmpty(this.b.getClickURL())) {
                    int iC2 = o.C(com.mbridge.msdk.foundation.controller.a.f().j());
                    qVar = new q("2000021", iC2, this.b.getClickURL(), str, o.a(com.mbridge.msdk.foundation.controller.a.f().j(), iC2));
                }
                if (qVar != null) {
                    qVar.n(this.b.getId());
                    qVar.e(this.b.getVideoUrlEncode());
                    qVar.p(str);
                    qVar.k(this.b.getRequestId());
                    qVar.l(this.b.getRequestIdNotice());
                    qVar.m(this.f);
                    uVarA.a(qVar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
