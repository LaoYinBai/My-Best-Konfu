package com.mbridge.msdk.mbbid.common.c;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.same.report.d.b;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: BidReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "com.mbridge.msdk.mbbid.common.c.a";

    public static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("key=");
            sb.append("2000064");
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("result=");
            sb.append("1");
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("network_type=");
            sb.append(o.C(context));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("bidid=");
            sb.append(str2);
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(sb.toString());
                return;
            }
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(sb.toString(), context, str), new b() { // from class: com.mbridge.msdk.mbbid.common.c.a.1
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

    public static void b(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a("key=2000064" + ContainerUtils.FIELD_DELIMITER + "result=2" + ContainerUtils.FIELD_DELIMITER + "network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER + "reason=" + str2, context, str), new b() { // from class: com.mbridge.msdk.mbbid.common.c.a.2
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
}
