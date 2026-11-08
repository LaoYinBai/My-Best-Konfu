package com.mbridge.msdk.mbbid.common.a;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.same.net.h.d;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.mbbid.out.BidListennning;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.reflect.Method;

/* compiled from: RequesManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private String a;
    private String b;
    private String c;
    private BidListennning e;
    private BidResponsedEx f;
    private int g;
    private long i;
    private long j;
    private boolean k;
    private int l;
    private boolean h = false;
    private Context d = com.mbridge.msdk.foundation.controller.a.f().j();

    public final void a(boolean z) {
        this.k = z;
    }

    public final void a(int i) {
        this.l = i;
    }

    public final void b(int i) {
        this.g = i;
    }

    public final void a(long j) {
        this.i = j;
    }

    public final void b(long j) {
        this.j = j;
    }

    public b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void b(boolean z) {
        try {
            if (!this.h) {
                this.h = true;
                if (this.d == null) {
                    a("context is null");
                }
                com.mbridge.msdk.mbbid.common.b.a aVar = new com.mbridge.msdk.mbbid.common.b.a(this.d);
                d dVar = new d();
                String strK = com.mbridge.msdk.foundation.controller.a.f().k();
                dVar.a("app_id", strK);
                dVar.a("sign", SameMD5.getMD5(strK + com.mbridge.msdk.foundation.controller.a.f().l()));
                dVar.a(MBridgeConstans.PROPERTIES_UNIT_ID, this.b);
                if (!TextUtils.isEmpty(this.a)) {
                    dVar.a("placement_id", this.a);
                }
                dVar.a("bid_floor", this.c);
                dVar.a(d.c, w.b(this.d, this.b));
                dVar.a("install_ids", w.a(0));
                dVar.a(d.b, com.mbridge.msdk.foundation.same.a.d.a(this.b, ""));
                dVar.a("req_type", this.k ? "1" : "2");
                dVar.a("orientation", o.s(this.d) + "");
                if (this.g == 296) {
                    if (this.i > 0 && this.j > 0) {
                        dVar.a("unit_size", this.j + "x" + this.i);
                        try {
                            Method method = Class.forName("com.mbridge.msdk.mbbanner.common.util.BannerUtils").getMethod("getCloseIds", String.class);
                            if (method.invoke(null, this.b) instanceof String) {
                                dVar.a("close_id", method.invoke(null, this.b).toString());
                            }
                        } catch (Exception unused) {
                            a("banner module is miss");
                            return;
                        }
                    } else {
                        a("bid required param is missing or error");
                        return;
                    }
                } else if (this.g == 297) {
                    if (this.i > 0 && this.j > 0) {
                        dVar.a("unit_size", this.j + "x" + this.i);
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.l);
                        sb.append("");
                        dVar.a("orientation", sb.toString());
                    } else {
                        a("ad display area is too small");
                        return;
                    }
                } else if (this.g != 298) {
                    dVar.a("rw_plus", z ? "1" : PushConstants.PUSH_TYPE_NOTIFY);
                } else if (this.i > 0 && this.j > 0) {
                    dVar.a("unit_size", this.j + "x" + this.i);
                } else {
                    a("bid required param is missing or error");
                    return;
                }
                a aVar2 = new a(this.a, this.b) { // from class: com.mbridge.msdk.mbbid.common.a.b.1
                    @Override // com.mbridge.msdk.mbbid.common.b.b
                    public final void a(BidResponsedEx bidResponsedEx) {
                        b.this.h = false;
                        b.this.f = bidResponsedEx;
                        com.mbridge.msdk.mbbid.common.c.a.a(b.this.d, b.this.b, bidResponsedEx.getBidId());
                        b.a(b.this, (BidResponsed) bidResponsedEx);
                    }

                    @Override // com.mbridge.msdk.mbbid.common.b.b
                    public final void a(int i, String str) {
                        b.this.h = false;
                        com.mbridge.msdk.mbbid.common.c.a.b(b.this.d, b.this.b, str);
                        b.this.a(str);
                    }
                };
                aVar2.d = this.b;
                aVar2.e = this.a;
                aVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(false, ""), dVar, aVar2);
                return;
            }
            a("current unit is biding");
        } catch (Throwable th) {
            a(th.getMessage());
        }
    }

    public final void a(BidListennning bidListennning) {
        this.e = bidListennning;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.e != null) {
            this.e.onFailed(str);
        }
    }

    static /* synthetic */ void a(b bVar, BidResponsed bidResponsed) {
        if (bVar.e != null) {
            bVar.e.onSuccessed(bidResponsed);
        }
    }
}
