package com.mbridge.msdk.mbnative.c;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.s;
import java.util.List;

/* compiled from: NativeVideoTrackingReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final String a = "com.mbridge.msdk.mbnative.c.d";

    public static void a(Context context, String str) {
        if (context != null) {
            try {
                u uVarA = u.a(i.a(context));
                if (TextUtils.isEmpty(str) || uVarA == null || uVarA.a() <= 0) {
                    return;
                }
                List<q> listA = uVarA.a("2000022");
                List<q> listA2 = uVarA.a("2000021");
                List<q> listA3 = uVarA.a("2000043");
                String strB = q.b(listA2);
                String strC = q.c(listA);
                String strD = q.d(listA3);
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(strB)) {
                    sb.append(strB);
                }
                if (!TextUtils.isEmpty(strC)) {
                    sb.append(strC);
                }
                if (!TextUtils.isEmpty(strD)) {
                    sb.append(strD);
                }
                if (TextUtils.isEmpty(sb.toString())) {
                    return;
                }
                String string = sb.toString();
                if (context == null || TextUtils.isEmpty(string) || TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, com.mbridge.msdk.foundation.same.report.d.a(string, context, str), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.mbnative.c.d.1
                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void a(String str2) {
                            s.d(d.a, str2);
                        }

                        @Override // com.mbridge.msdk.foundation.same.report.d.b
                        public final void b(String str2) {
                            s.d(d.a, str2);
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
}
