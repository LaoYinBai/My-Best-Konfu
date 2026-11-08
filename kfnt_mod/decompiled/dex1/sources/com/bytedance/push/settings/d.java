package com.bytedance.push.settings;

import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {
    public static boolean a(JSONObject jSONObject, String str) {
        return a(jSONObject, str, false);
    }

    public static boolean a(JSONObject jSONObject, String str, boolean z) {
        Boolean boolA = a(jSONObject.opt(str));
        return boolA != null ? boolA.booleanValue() : z;
    }

    private static Boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() == 1) {
                return true;
            }
            return num.intValue() == 0 ? false : null;
        }
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
            return true;
        }
        return ("false".equalsIgnoreCase(str) || OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(str)) ? false : null;
    }
}
