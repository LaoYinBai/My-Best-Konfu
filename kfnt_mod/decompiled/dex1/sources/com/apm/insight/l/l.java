package com.apm.insight.l;

import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class l {
    public static int a(@Nullable JSONObject jSONObject, int i, String... strArr) {
        JSONObject jSONObjectC = c(jSONObject, strArr);
        if (jSONObjectC == null) {
            return i;
        }
        int iOptInt = jSONObjectC.optInt(strArr[strArr.length - 1], i);
        q.a("JSONUtil", "normal get jsonInt: " + strArr[strArr.length - 1] + " : " + iOptInt);
        return iOptInt;
    }

    public static JSONArray a(int i, int i2, JSONArray jSONArray) {
        int i3 = i2 + i;
        if (jSONArray.length() <= i3) {
            return jSONArray;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i4 = 0; i4 < i; i4++) {
            jSONArray2.put(jSONArray.opt(i4));
        }
        while (i < i3) {
            jSONArray2.put(jSONArray.opt(jSONArray.length() - (i3 - i)));
            i++;
        }
        return jSONArray2;
    }

    @Nullable
    public static JSONArray a(@Nullable JSONObject jSONObject, String... strArr) {
        JSONObject jSONObjectC = c(jSONObject, strArr);
        if (jSONObjectC == null) {
            return null;
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectC.optJSONArray(strArr[strArr.length - 1]);
        q.a("ApmConfig", "normal get configArray: " + strArr[strArr.length - 1] + " : " + jSONArrayOptJSONArray);
        return jSONArrayOptJSONArray;
    }

    @Nullable
    public static JSONArray a(@Nullable String[] strArr) {
        if (strArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : strArr) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                if (!jSONObject.has(next)) {
                    jSONObject.put(next, jSONObject2.opt(next));
                }
            } catch (Throwable unused) {
                return;
            }
        }
    }

    public static boolean a(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static boolean a(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return a(jSONObject) || a(jSONObject.optJSONArray(str));
    }

    @Nullable
    public static String b(@Nullable JSONObject jSONObject, String... strArr) {
        JSONObject jSONObjectC = c(jSONObject, strArr);
        if (jSONObjectC == null) {
            return null;
        }
        String strOptString = jSONObjectC.optString(strArr[strArr.length - 1]);
        q.a("ApmConfig", "normal get configArray: " + strArr[strArr.length - 1] + " : " + strOptString);
        return strOptString;
    }

    public static HashMap<String, Object> b(JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        if (jSONObject == null) {
            return map;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject.opt(next));
        }
        return map;
    }

    @Nullable
    public static JSONObject c(@Nullable JSONObject jSONObject, String... strArr) {
        if (jSONObject == null) {
            q.a("JSONUtil", "err get JsonFromParent: null json", new RuntimeException());
            return null;
        }
        for (int i = 0; i < strArr.length - 1; i++) {
            jSONObject = jSONObject.optJSONObject(strArr[i]);
            if (jSONObject == null) {
                q.a("JSONUtil", "err get json: not found node:" + strArr[i]);
                return null;
            }
        }
        return jSONObject;
    }
}
