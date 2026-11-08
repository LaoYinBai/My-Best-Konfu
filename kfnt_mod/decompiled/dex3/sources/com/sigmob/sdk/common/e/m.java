package com.sigmob.sdk.common.e;

import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.m;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m {
    public static String a() {
        return "appId=" + com.sigmob.sdk.common.a.n().ab() + "&" + ("sdkVersion=" + com.sigmob.sdk.common.a.a);
    }

    public static String a(Map<String, String> map, String str) {
        m.a.a(str);
        if (map == null || map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : map.keySet()) {
            try {
                jSONObject.put(str2, map.get(str2));
            } catch (Throwable unused) {
                SigmobLog.d("Unable to add " + str2 + " to JSON body.");
            }
        }
        return jSONObject.toString();
    }

    static boolean a(String str) {
        try {
            HashSet hashSetI = f.i();
            if (hashSetI != null) {
                return hashSetI.contains(str);
            }
            return false;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return false;
        }
    }

    public static boolean b(String str) {
        return true;
    }
}
