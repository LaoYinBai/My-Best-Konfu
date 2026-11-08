package com.kwad.sdk.e;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    public static <T extends c> List<T> a(String str, @NonNull d<T> dVar) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                c cVarCreateInstance = dVar.createInstance();
                cVarCreateInstance.parseJson(jSONObject);
                arrayList.add(cVarCreateInstance);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static <T> JSONArray a(@NonNull Set<T> set) {
        JSONArray jSONArray = new JSONArray();
        for (Object json : set) {
            if (!(json instanceof JSONObject)) {
                if (json instanceof c) {
                    json = ((c) json).toJson();
                }
            }
            jSONArray.put(json);
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

    public static void a(JSONObject jSONObject, String str, int i) throws JSONException {
        try {
            jSONObject.put(str, i);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
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

    public static void a(JSONObject jSONObject, String str, boolean z) throws JSONException {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, z);
        } catch (JSONException unused) {
        }
    }
}
