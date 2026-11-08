package com.mbridge.msdk.mbbid.common;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.u;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.out.MBConfiguration;

/* compiled from: AreaBidCommon.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static String a(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (context != null) {
            try {
                if (com.mbridge.msdk.foundation.controller.a.f().j() == null) {
                    com.mbridge.msdk.foundation.controller.a.f().b(context);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        stringBuffer.append(o.A());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        String strG = "";
        if (aVarB.F() == 1 && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_android_id")) {
            strG = o.g(context);
        }
        stringBuffer.append(strG);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        String strB = "";
        if (aVarB.E() == 1 && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
            strB = o.b(context);
        }
        stringBuffer.append(strB);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.v());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.u());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.C(context));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(MBConfiguration.SDK_VERSION);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.w(context));
        stringBuffer.append("x");
        stringBuffer.append(o.x(context));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.w());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(w.a(20));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(w.b(20));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(com.mbridge.msdk.foundation.same.a.f);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.H());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.G());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(o.s());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(com.mbridge.msdk.foundation.tools.b.a());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(com.mbridge.msdk.foundation.tools.b.b());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(u.b());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(u.a());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append("");
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(com.mbridge.msdk.foundation.a.a.a.a().b(com.huawei.updatesdk.service.d.a.b.a));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(com.mbridge.msdk.foundation.a.a.a.a().b("c"));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append("");
        String strB2 = aVarB != null ? aVarB.b() : "";
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(strB2);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(w.c());
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        String strD = w.d();
        if (TextUtils.isEmpty(strD)) {
            strD = "";
        }
        stringBuffer.append(strD);
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        stringBuffer.append(w.f(str));
        stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        if (aVarB.j() == 1 && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
            stringBuffer.append(o.a(context));
        }
        return n.a(stringBuffer.toString());
    }
}
