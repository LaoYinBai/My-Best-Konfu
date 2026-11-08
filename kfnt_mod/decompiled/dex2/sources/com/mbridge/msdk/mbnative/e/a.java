package com.mbridge.msdk.mbnative.e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.out.Campaign;
import java.util.List;

/* compiled from: NativeReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static void a(Context context, List<Campaign> list, String str) {
        if (context == null || list == null) {
            return;
        }
        try {
            if (list.size() <= 0 || TextUtils.isEmpty(str)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000048&");
            if (list != null && list.size() > 0) {
                stringBuffer.append("cid=" + list.get(0).getId() + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
            if (list != null && list.size() > 1) {
                CampaignEx campaignEx = (CampaignEx) list.get(0);
                if (campaignEx.isBidCampaign()) {
                    stringBuffer.append("hb=");
                    stringBuffer.append(1);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                if (w.b(campaignEx)) {
                    stringBuffer.append("rtins_type=");
                    stringBuffer.append(campaignEx.getRtinsType());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("rid=" + campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice());
            } else if (list.size() == 1) {
                CampaignEx campaignEx2 = (CampaignEx) list.get(0);
                if (campaignEx2.isBidCampaign()) {
                    stringBuffer.append("hb=");
                    stringBuffer.append(1);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                if (w.b(campaignEx2)) {
                    stringBuffer.append("rtins_type=");
                    stringBuffer.append(campaignEx2.getRtinsType());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("rid=" + campaignEx2.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx2.getRequestIdNotice());
            }
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                return;
            }
            String string = stringBuffer.toString();
            if (context == null || TextUtils.isEmpty(string) || TextUtils.isEmpty(str)) {
                return;
            }
            try {
                new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(string, context, str), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.mbnative.e.a.1
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str2) {
                        s.d("NativeReport", str2);
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str2) {
                        s.d("NativeReport", str2);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                s.d("NativeReport", e.getMessage());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000047&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                if (!TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.s)) {
                    stringBuffer.append("b=" + com.mbridge.msdk.foundation.same.a.s + ContainerUtils.FIELD_DELIMITER);
                }
                if (!TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.t)) {
                    stringBuffer.append("c=" + com.mbridge.msdk.foundation.same.a.t + ContainerUtils.FIELD_DELIMITER);
                }
                if (z) {
                    stringBuffer.append("hb=1&");
                }
                stringBuffer.append("reason=" + str);
                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                    com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                    return;
                }
                String string = stringBuffer.toString();
                if (context == null || TextUtils.isEmpty(string)) {
                    return;
                }
                try {
                    new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(string, context), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.mbnative.e.a.2
                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void a(String str3) {
                            s.d("NativeReport", str3);
                        }

                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void b(String str3) {
                            s.d("NativeReport", str3);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    s.d("NativeReport", e.getMessage());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
