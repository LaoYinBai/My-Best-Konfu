package com.mbridge.msdk.reward.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.same.report.d.b;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import java.util.List;

/* compiled from: RewardReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "com.mbridge.msdk.reward.b.a";

    public static void a(Context context, String str) {
        if (context != null) {
            try {
                u uVarA = u.a(i.a(context));
                if (TextUtils.isEmpty(str) || uVarA == null || uVarA.a() <= 0) {
                    return;
                }
                List<q> listA = uVarA.a("2000022");
                List<q> listA2 = uVarA.a("2000021");
                List<q> listA3 = uVarA.a("2000039");
                List<q> listA4 = uVarA.a("2000043");
                List<q> listA5 = uVarA.a("2000045");
                List<q> listA6 = uVarA.a("2000044");
                String strB = q.b(listA2);
                String strC = q.c(listA);
                String strG = q.g(listA3);
                String strD = q.d(listA4);
                String strE = q.e(listA5);
                String strF = q.f(listA6);
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(strB)) {
                    sb.append(strB);
                }
                if (!TextUtils.isEmpty(strC)) {
                    sb.append(strC);
                }
                if (!TextUtils.isEmpty(strG)) {
                    sb.append(strG);
                }
                if (!TextUtils.isEmpty(strD)) {
                    sb.append(strD);
                }
                if (!TextUtils.isEmpty(strE)) {
                    sb.append(strE);
                }
                if (!TextUtils.isEmpty(strF)) {
                    sb.append(strF);
                }
                s.b(a, "reward 批量上报：" + ((Object) sb));
                if (TextUtils.isEmpty(sb.toString())) {
                    return;
                }
                String string = sb.toString();
                if (context == null || TextUtils.isEmpty(string) || TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(string, context, str), new b() { // from class: com.mbridge.msdk.reward.b.a.1
                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void a(String str2) {
                            s.d(a.a, str2);
                        }

                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void b(String str2) {
                            s.d(a.a, str2);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    s.d(a, e.getMessage());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(str, context, str2), new b() { // from class: com.mbridge.msdk.reward.b.a.2
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str3) {
                    s.d(a.a, str3);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str3) {
                    s.d(a.a, str3);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    private static void b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(str, context), new b() { // from class: com.mbridge.msdk.reward.b.a.3
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str2) {
                    s.d(a.a, str2);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str2) {
                    s.d(a.a, str2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    public static void a(Context context, List<CampaignEx> list, String str) {
        if (context == null || list == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=");
            stringBuffer.append("2000048");
            stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("network_type=");
            stringBuffer.append(o.C(context));
            stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=");
            stringBuffer.append(str);
            stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            if (list == null || list.size() <= 0) {
                return;
            }
            stringBuffer.append("cid=");
            StringBuilder sb = new StringBuilder("rtins_type=");
            for (int i = 0; i < list.size(); i++) {
                CampaignEx campaignEx = list.get(i);
                if (i < list.size() - 1) {
                    stringBuffer.append(campaignEx.getId());
                    stringBuffer.append(",");
                    sb.append(campaignEx.getRtinsType());
                    sb.append(",");
                } else {
                    stringBuffer.append(campaignEx.getId());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    if (campaignEx.isBidCampaign()) {
                        stringBuffer.append("hb=1&");
                    } else {
                        stringBuffer.append("hb=0&");
                    }
                    sb.append(campaignEx.getRtinsType());
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            stringBuffer.append((CharSequence) sb);
            CampaignEx campaignEx2 = list.get(0);
            if (campaignEx2 != null) {
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx2.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            }
            String str2 = "";
            for (int i2 = 0; i2 < list.size(); i2++) {
                str2 = str2 + list.get(i2).getRequestIdNotice();
                if (i2 < list.size() - 1) {
                    str2 = str2 + ",";
                }
            }
            stringBuffer.append("rid_n=");
            stringBuffer.append(str2);
            stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
            } else {
                a(context, stringBuffer.toString(), str);
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2, boolean z, String str3, String str4) {
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
                } else {
                    stringBuffer.append("hb=0&");
                }
                if (!TextUtils.isEmpty(str3)) {
                    stringBuffer.append("rid=");
                    stringBuffer.append(str3);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                if (!TextUtils.isEmpty(str4)) {
                    stringBuffer.append("rid_n=");
                    stringBuffer.append(str4);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("reason=" + str);
                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                    com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                } else {
                    b(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
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
            stringBuffer.append("key=2000048&");
            if (campaignEx != null) {
                stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
            if (campaignEx.isBidCampaign()) {
                stringBuffer.append("hb=1&");
            } else {
                stringBuffer.append("hb=0&");
            }
            if (w.b(campaignEx)) {
                stringBuffer.append("rtins_type=");
                stringBuffer.append(campaignEx.getRtinsType());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            }
            if (campaignEx != null) {
                stringBuffer.append("rid=" + campaignEx.getRequestId());
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

    public static void b(Context context, String str, String str2, boolean z, String str3, String str4) {
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
                } else {
                    stringBuffer.append("hb=0&");
                }
                if (!TextUtils.isEmpty(str3)) {
                    stringBuffer.append("rid=");
                    stringBuffer.append(str3);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                if (!TextUtils.isEmpty(str4)) {
                    stringBuffer.append("rid_n=");
                    stringBuffer.append(str4);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("reason=" + str);
                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                    com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                } else {
                    b(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(CampaignEx campaignEx, Context context, String str, String str2, boolean z, String str3, String str4, long j) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000078&");
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
                } else {
                    stringBuffer.append("hb=0&");
                }
                if (!TextUtils.isEmpty(str3)) {
                    stringBuffer.append("rid=");
                    stringBuffer.append(str3);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                } else if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getRequestId())) {
                    stringBuffer.append("rid=");
                    stringBuffer.append(campaignEx.getRequestId());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                if (!TextUtils.isEmpty(str4)) {
                    stringBuffer.append("rid_n=");
                    stringBuffer.append(str4);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                } else if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getRequestIdNotice())) {
                    stringBuffer.append("rid_n=");
                    stringBuffer.append(campaignEx.getRequestIdNotice());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("during=");
                stringBuffer.append(j);
                stringBuffer.append("&reason=" + str);
                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                    com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                } else {
                    b(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
