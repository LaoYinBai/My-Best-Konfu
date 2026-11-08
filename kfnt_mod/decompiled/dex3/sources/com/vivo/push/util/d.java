package com.vivo.push.util;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    public static boolean a(long j, HashMap<String, String> map) {
        com.vivo.push.b.x xVar = new com.vivo.push.b.x(j);
        xVar.c = map;
        if (xVar.c == null) {
            n.d("ReporterCommand", "reportParams is empty");
        } else {
            StringBuilder sb = new StringBuilder("report message reportType:");
            sb.append(xVar.d);
            sb.append(",msgId:");
            String str = xVar.c.get("messageID");
            if (TextUtils.isEmpty(str)) {
                str = xVar.c.get("message_id");
            }
            sb.append(str);
            n.d("ReporterCommand", sb.toString());
        }
        com.vivo.push.d.a().a(xVar);
        return true;
    }
}
