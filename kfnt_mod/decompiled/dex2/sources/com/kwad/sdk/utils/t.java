package com.kwad.sdk.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class t {
    public static <T> List<T> a(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj = jSONArray.get(i);
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        return arrayList;
    }

    public static <T extends com.kwad.sdk.core.b> List<T> a(String str, @NonNull com.kwad.sdk.core.c<T> cVar) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                com.kwad.sdk.core.b bVarA = cVar.a();
                bVarA.parseJson(jSONObject);
                arrayList.add(bVarA);
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        return arrayList;
    }

    public static JSONArray a(@NonNull List<String> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray;
    }

    public static JSONObject a(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (map != null && !map.isEmpty()) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public static void a(JSONArray jSONArray, JSONObject jSONObject) {
        jSONArray.put(jSONObject);
    }

    public static void a(JSONObject jSONObject, String str, double d) throws JSONException {
        try {
            jSONObject.put(str, d);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, float f) throws JSONException {
        try {
            jSONObject.put(str, f);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, com.kwad.sdk.core.b bVar) throws JSONException {
        if (bVar == null) {
            return;
        }
        try {
            jSONObject.put(str, bVar.toJson());
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, List<?> list) throws JSONException {
        if (list == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        boolean z = false;
        for (Object obj : list) {
            if (obj instanceof com.kwad.sdk.core.b) {
                a(jSONArray, ((com.kwad.sdk.core.b) obj).toJson());
            } else if ((obj instanceof String) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof JSONObject) || (obj instanceof JSONArray) || (obj instanceof Double) || (obj instanceof Boolean)) {
                jSONArray.put(obj);
            } else if (!(obj instanceof Float)) {
                break;
            } else {
                try {
                    jSONArray.put(((Float) obj).floatValue());
                } catch (JSONException unused) {
                }
            }
            z = true;
        }
        if (z) {
            a(jSONObject, str, jSONArray);
        }
    }

    public static void a(JSONObject jSONObject, String str, JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0 || jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, jSONArray);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, JSONObject jSONObject2) throws JSONException {
        if (jSONObject2 == null || jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, z);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String string = itKeys.next().toString();
            Object objOpt = jSONObject2.opt(string);
            if (objOpt != null) {
                try {
                    jSONObject.put(string, objOpt);
                } catch (JSONException unused) {
                }
            }
        }
    }

    public static Map<String, String> b(String str) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String strOptString = jSONObject.optString(next, "");
                if (TextUtils.isEmpty(strOptString) || TextUtils.equals("null", strOptString)) {
                    strOptString = "";
                }
                map.put(next, strOptString);
            }
        } catch (JSONException unused) {
        }
        return map;
    }

    public static <T> JSONArray b(@NonNull List<T> list) {
        JSONArray jSONArray = new JSONArray();
        for (Object json : list) {
            if (!(json instanceof JSONObject)) {
                if (!(json instanceof com.kwad.sdk.core.b)) {
                    throw new IllegalArgumentException("<T> now suppprt type: " + json.getClass().getName());
                }
                json = ((com.kwad.sdk.core.b) json).toJson();
            }
            jSONArray.put(json);
        }
        return jSONArray;
    }
}
