package com.mbridge.msdk.mbbanner.common.d;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.same.report.c;
import com.mbridge.msdk.foundation.same.report.d.b;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import java.net.URLEncoder;

/* compiled from: BannerReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static void a(CampaignEx campaignEx, String str, String str2) {
        if (campaignEx != null) {
            try {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                new c(com.mbridge.msdk.foundation.controller.a.f().j()).b(campaignEx.getRequestId(), campaignEx.getRequestIdNotice(), campaignEx.getId(), str, str2, campaignEx.isBidCampaign());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(String str, CampaignEx campaignEx, String str2) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j());
            StringBuilder sb = new StringBuilder();
            if (campaignEx.isBidCampaign()) {
                sb.append("hb=");
                sb.append(1);
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append(DomainCampaignEx.LOOPBACK_KEY);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode("2000070", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(campaignEx.getRequestIdNotice(), "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(campaignEx.getRequestId(), "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(BidResponsedEx.KEY_CID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(campaignEx.getId(), "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(MBridgeConstans.PROPERTIES_UNIT_ID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(CampaignEx.JSON_KEY_CLICK_URL);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str2, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("network_type");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(String.valueOf(o.C(com.mbridge.msdk.foundation.controller.a.f().j())), "utf-8"));
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(sb.toString());
            } else {
                aVar.c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(sb.toString(), com.mbridge.msdk.foundation.controller.a.f().j(), str), new b() { // from class: com.mbridge.msdk.mbbanner.common.d.a.1
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str3) {
                        s.a("", "Mraid Expand REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str3) {
                        s.a("", "Mraid Expand REPORT FAILED");
                    }
                });
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static void a(com.mbridge.msdk.mbbanner.common.a.a aVar, String str) {
        if (aVar != null) {
            aVar.a("2000068");
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(aVar.b());
            } else {
                com.mbridge.msdk.foundation.same.report.b.a(aVar.b(), com.mbridge.msdk.foundation.controller.a.f().j(), str);
            }
        }
    }

    public static void a(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().j() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().j(), false, false);
    }
}
