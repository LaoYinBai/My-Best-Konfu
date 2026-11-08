package com.mbridge.msdk.foundation.same.net.g;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.List;

/* compiled from: HttpUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final String a = "b";

    public static String a(List<com.mbridge.msdk.foundation.same.net.c.b> list) {
        com.mbridge.msdk.foundation.same.net.c.b bVarB = b(list, "Content-Type");
        if (bVarB == null) {
            return C.UTF8_NAME;
        }
        String strB = bVarB.b();
        if (TextUtils.isEmpty(strB)) {
            return C.UTF8_NAME;
        }
        String[] strArrSplit = strB.split(";");
        for (int i = 1; i < strArrSplit.length; i++) {
            String[] strArrSplit2 = strArrSplit[i].trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
            if (strArrSplit2.length == 2 && strArrSplit2[0].equals("charset")) {
                return strArrSplit2[1];
            }
        }
        return C.UTF8_NAME;
    }

    private static com.mbridge.msdk.foundation.same.net.c.b b(List<com.mbridge.msdk.foundation.same.net.c.b> list, String str) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                com.mbridge.msdk.foundation.same.net.c.b bVar = list.get(i);
                if (bVar != null && str.equals(bVar.a())) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public static String a(List<com.mbridge.msdk.foundation.same.net.c.b> list, String str) {
        com.mbridge.msdk.foundation.same.net.c.b bVarB = b(list, str);
        return bVarB != null ? bVarB.b() : "";
    }

    public static boolean b(List<com.mbridge.msdk.foundation.same.net.c.b> list) {
        return TextUtils.equals(a(list, "Content-Encoding"), "gzip");
    }

    public static void a(com.mbridge.msdk.foundation.same.net.h.d dVar, String str, String str2) {
        if (dVar != null) {
            try {
                if (!z.a(str) && !z.a(str2)) {
                    dVar.a(str, str2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
