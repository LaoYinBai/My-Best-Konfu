package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.token.TokenConstants;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ed {
    public static void a(Context context, String str, int i, String str2) {
        ai.a(context).a(new ee(context, str, i, str2));
    }

    private static void a(Context context, HashMap<String, String> map) {
        el elVarM289a = eh.a(context).m289a();
        if (elVarM289a != null) {
            elVarM289a.a(context, map);
        }
    }

    private static void b(Context context, HashMap<String, String> map) {
        el elVarM289a = eh.a(context).m289a();
        if (elVarM289a != null) {
            elVarM289a.c(context, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, int i, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("awake_info", str);
            map.put("event_type", String.valueOf(i));
            map.put(TokenConstants.ERROR_MSG, str2);
            switch (eh.a(context).a()) {
                case 1:
                    a(context, map);
                    break;
                case 3:
                    a(context, map);
                case 2:
                    c(context, map);
                    break;
            }
            b(context, map);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    private static void c(Context context, HashMap<String, String> map) {
        el elVarM289a = eh.a(context).m289a();
        if (elVarM289a != null) {
            elVarM289a.b(context, map);
        }
    }
}
