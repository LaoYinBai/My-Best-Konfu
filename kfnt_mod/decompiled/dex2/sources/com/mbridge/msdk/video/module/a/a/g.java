package com.mbridge.msdk.video.module.a.a;

import com.mbridge.msdk.foundation.entity.CampaignEx;

/* compiled from: PlayableStatisticsOnNotifyListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class g extends j {
    public g(CampaignEx campaignEx, com.mbridge.msdk.videocommon.download.a aVar, com.mbridge.msdk.videocommon.b.d dVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, int i, boolean z) {
        super(campaignEx, aVar, dVar, str, str2, aVar2, i, z);
    }

    @Override // com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public final void a(int i, Object obj) {
        if (i == 100) {
            g();
            f();
            e();
            a(2);
        } else if (i == 109) {
            b(2);
        } else if (i == 122) {
            a();
        } else {
            switch (i) {
                case 118:
                    String str = "";
                    if (obj != null && (obj instanceof String)) {
                        str = (String) obj;
                    }
                    a(3, str);
                    break;
                case 119:
                    String str2 = "";
                    if (obj != null && (obj instanceof String)) {
                        str2 = (String) obj;
                    }
                    a(4, str2);
                    break;
            }
        }
        super.a(i, obj);
    }
}
