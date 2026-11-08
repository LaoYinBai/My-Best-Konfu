package com.mbridge.msdk.advanced.common.a;

import android.content.Context;
import com.mbridge.msdk.foundation.db.c;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.tools.w;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* compiled from: NativeAdvancedUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";

    public static String a(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            i iVarA = i.a(context);
            if (iVarA == null) {
                return "";
            }
            c cVarA = c.a(iVarA);
            cVarA.a();
            return cVarA.a(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a() {
        try {
            JSONArray jSONArray = new JSONArray();
            List<Long> listI = com.mbridge.msdk.foundation.controller.a.f().i();
            if (listI != null && listI.size() > 0) {
                Iterator<Long> it = listI.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().longValue());
                }
            }
            return jSONArray.length() > 0 ? w.a(jSONArray) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
