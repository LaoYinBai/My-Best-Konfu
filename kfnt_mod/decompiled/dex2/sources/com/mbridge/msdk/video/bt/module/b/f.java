package com.mbridge.msdk.video.bt.module.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import java.net.URLEncoder;

/* compiled from: RewardReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static final String a = "com.mbridge.msdk.video.bt.module.b.f";

    private static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, com.mbridge.msdk.foundation.same.report.d.a(str, context, str2), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.video.bt.module.b.f.1
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str3) {
                    s.d(f.a, str3);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str3) {
                    s.d(f.a, str3);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str) {
        if (context == null || campaignEx == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000054&");
            stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("reason=&");
            stringBuffer.append("result=2&");
            if (campaignEx != null && campaignEx.getAdType() == 287) {
                stringBuffer.append("ad_type=3&");
            } else if (campaignEx == null || campaignEx.getAdType() == 94) {
                stringBuffer.append("ad_type=1&");
            } else {
                stringBuffer.append("ad_type=1&");
            }
            String str2 = campaignEx.getendcard_url();
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("creative=" + URLEncoder.encode(str2) + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("devid=" + o.A() + ContainerUtils.FIELD_DELIMITER);
            if (campaignEx != null) {
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice());
            }
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
            } else {
                a(context, stringBuffer.toString(), str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2) {
        if (context == null || campaignEx == null) {
            return;
        }
        try {
            if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty(str2))) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000054&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=1&");
                if (!TextUtils.isEmpty(campaignEx.getendcard_url())) {
                    stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("devid=" + o.A() + ContainerUtils.FIELD_DELIMITER);
                if (campaignEx != null && campaignEx.getAdType() == 287) {
                    stringBuffer.append("ad_type=3&");
                } else if (campaignEx == null || campaignEx.getAdType() == 94) {
                    stringBuffer.append("ad_type=1&");
                } else {
                    stringBuffer.append("ad_type=1&");
                }
                if (campaignEx != null) {
                    stringBuffer.append("rid=");
                    stringBuffer.append(campaignEx.getRequestId());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice());
                }
                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                    com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                } else {
                    a(context, stringBuffer.toString(), str);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
