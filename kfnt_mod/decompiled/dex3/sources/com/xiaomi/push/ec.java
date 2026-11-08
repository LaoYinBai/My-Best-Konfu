package com.xiaomi.push;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.ss.android.token.TokenConstants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ec {
    public static Uri a(String str, String str2) {
        return Uri.parse("content://" + str).buildUpon().appendPath(str2).build();
    }

    public static String a(String str) {
        return Base64.encodeToString(bm.m134a(str), 2);
    }

    public static String a(HashMap<String, String> map) throws JSONException {
        if (map == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
        } catch (JSONException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        return jSONObject.toString();
    }

    public static String b(String str) {
        return bm.a(Base64.decode(str, 2));
    }

    public static String b(HashMap<String, String> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.put("event_type", map.get("event_type") + "");
            map2.put(TokenConstants.ERROR_MSG, map.get(TokenConstants.ERROR_MSG) + "");
            String str = map.get("awake_info");
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    map2.put("__planId__", String.valueOf(jSONObject.opt("__planId__")));
                    map2.put("flow_id", String.valueOf(jSONObject.opt("flow_id")));
                    map2.put("jobkey", String.valueOf(jSONObject.opt("jobkey")));
                    map2.put("msg_id", String.valueOf(jSONObject.opt("msg_id")));
                    map2.put("A", String.valueOf(jSONObject.opt("awake_app")));
                    map2.put("B", String.valueOf(jSONObject.opt("awakened_app")));
                    map2.put(com.umeng.commonsdk.proguard.o.d, String.valueOf(jSONObject.opt("awake_type")));
                } catch (JSONException e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
        }
        return a((HashMap<String, String>) map2);
    }
}
