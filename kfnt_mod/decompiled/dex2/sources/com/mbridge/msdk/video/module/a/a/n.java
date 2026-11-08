package com.mbridge.msdk.video.module.a.a;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.video.module.MBridgeVideoView;
import java.util.Map;

/* compiled from: VideoViewStatisticsListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n extends j {
    protected double j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private Map<Integer, String> p;
    private int q;

    public n(CampaignEx campaignEx, com.mbridge.msdk.videocommon.b.d dVar, com.mbridge.msdk.videocommon.download.a aVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, double d, int i, boolean z) {
        super(campaignEx, aVar, dVar, str, str2, aVar2, i, z);
        this.o = false;
        this.j = 1.0d;
        this.q = -1;
        if (this.a) {
            this.p = campaignEx.getAdvImpList();
        }
        this.j = d;
    }

    @Override // com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public void a(int i, Object obj) {
        int i2;
        int videoLength = 0;
        try {
            switch (i) {
                case 2:
                case 6:
                case 16:
                    if (this.a && !this.m) {
                        this.m = true;
                        d();
                        com.mbridge.msdk.video.module.b.a.e(com.mbridge.msdk.foundation.controller.a.f().j(), this.b);
                        break;
                    }
                    break;
                case 7:
                    if (this.a && obj != null && (obj instanceof Integer)) {
                        int iIntValue = ((Integer) obj).intValue();
                        if (iIntValue == 2) {
                            if (!this.l) {
                                this.l = true;
                                com.mbridge.msdk.video.module.b.a.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.b);
                                break;
                            }
                        } else if (iIntValue == 1 && !this.k) {
                            this.k = true;
                            com.mbridge.msdk.video.module.b.a.c(com.mbridge.msdk.foundation.controller.a.f().j(), this.b);
                            break;
                        }
                    }
                    break;
                case 11:
                    com.mbridge.msdk.videocommon.download.b.getInstance().b(false);
                    d();
                    break;
                case 12:
                    if (obj != null && (obj instanceof String)) {
                        a((String) obj);
                    }
                    d();
                    b();
                    c();
                    i();
                    com.mbridge.msdk.videocommon.download.b.getInstance().b(false);
                    break;
                case 13:
                    b();
                    c();
                    break;
                case 15:
                    g();
                    f();
                    e();
                    if (obj == null || !(obj instanceof MBridgeVideoView.a)) {
                        i2 = 0;
                    } else {
                        i2 = ((MBridgeVideoView.a) obj).a;
                        videoLength = ((MBridgeVideoView.a) obj).b;
                    }
                    if (videoLength == 0 && this.b != null) {
                        videoLength = this.b.getVideoLength();
                    }
                    com.mbridge.msdk.video.module.b.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, i2, videoLength, this.i);
                    com.mbridge.msdk.video.module.b.a.a(this.b, this.p, this.f, i2);
                    if (!this.n) {
                        this.n = true;
                        com.mbridge.msdk.video.module.b.a.a(this.b, this.f);
                    }
                    if (!this.o && i2 >= videoLength * this.j) {
                        this.o = true;
                        i = 17;
                    }
                    this.q = i2;
                    break;
            }
            this.h.a(i, obj);
        } catch (Throwable th) {
            s.b("NotifyListener", th.getMessage(), th);
        }
    }
}
