package com.mbridge.msdk.rover;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import java.util.ArrayList;

/* compiled from: RoverController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b d;
    Context a;
    long b = 259200000;
    private Handler c = new Handler() { // from class: com.mbridge.msdk.rover.b.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            b.a(b.this);
        }
    };

    private b() {
    }

    public final void a(Context context) {
        this.a = context;
    }

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public final void b() {
        if (this.a == null) {
            s.d("RoverController", "Context is null");
            return;
        }
        if (c()) {
            Object objB = y.b(this.a, a.d, 0L);
            long jLongValue = objB instanceof Long ? ((Long) objB).longValue() : 1L;
            long jCurrentTimeMillis = System.currentTimeMillis();
            int iV = a.c;
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB != null && aVarB.v() > 0) {
                iV = (int) aVarB.v();
            }
            if (jCurrentTimeMillis - jLongValue > ((long) iV) && jLongValue != 1) {
                this.c.sendEmptyMessageDelayed(0, 30000L);
            }
        }
    }

    private boolean c() {
        if (this.a != null) {
            try {
                long j = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).lastUpdateTime;
                long jCurrentTimeMillis = System.currentTimeMillis();
                z = jCurrentTimeMillis - j > this.b;
                s.a("", "currentTime=" + jCurrentTimeMillis + ",lastUpdateTime:" + j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    static /* synthetic */ void a(b bVar) {
        y.a(bVar.a, a.d, Long.valueOf(System.currentTimeMillis()));
        new g(bVar.a).a(1, com.mbridge.msdk.foundation.same.net.g.d.c().o, new com.mbridge.msdk.foundation.same.net.h.d(), new h() { // from class: com.mbridge.msdk.rover.b.2
            @Override // com.mbridge.msdk.rover.h
            public final void a(RoverCampaignUnit roverCampaignUnit) {
                ArrayList<CampaignEx> arrayList = roverCampaignUnit.ads;
                if (arrayList == null || arrayList.size() <= 0) {
                    return;
                }
                for (CampaignEx campaignEx : arrayList) {
                    if (campaignEx != null) {
                        new c(roverCampaignUnit, campaignEx, b.this.a);
                    }
                }
            }
        });
    }
}
