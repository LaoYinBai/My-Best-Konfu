package com.mbridge.msdk.video.module.a.a;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;

/* compiled from: ContainerViewStatisticsListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends j {
    public c(CampaignEx campaignEx, com.mbridge.msdk.videocommon.download.a aVar, com.mbridge.msdk.videocommon.b.d dVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, int i, boolean z) {
        super(campaignEx, aVar, dVar, str, str2, aVar2, i, z);
    }

    @Override // com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public void a(int i, Object obj) {
        super.a(i, obj);
        if (this.a) {
            switch (i) {
                case 105:
                case 106:
                case 113:
                    com.mbridge.msdk.video.module.b.a.d(com.mbridge.msdk.foundation.controller.a.f().j(), this.b);
                    if (i != 105) {
                        String noticeUrl = this.b.getNoticeUrl();
                        if (!TextUtils.isEmpty(noticeUrl)) {
                            if (!noticeUrl.contains(com.mbridge.msdk.foundation.same.a.p)) {
                                noticeUrl = noticeUrl + ContainerUtils.FIELD_DELIMITER + com.mbridge.msdk.foundation.same.a.p + "=2";
                            } else {
                                noticeUrl = noticeUrl.replace(com.mbridge.msdk.foundation.same.a.p + ContainerUtils.KEY_VALUE_DELIMITER + Uri.parse(noticeUrl).getQueryParameter(com.mbridge.msdk.foundation.same.a.p), com.mbridge.msdk.foundation.same.a.p + "=2");
                            }
                        }
                        com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, this.f, noticeUrl, true, false);
                        break;
                    }
                    break;
                case 109:
                    b(2);
                    a(2);
                    break;
                case 110:
                    b(1);
                    a(1);
                    break;
                case 111:
                    a(1);
                    break;
                case 122:
                    a();
                    break;
            }
        }
    }
}
