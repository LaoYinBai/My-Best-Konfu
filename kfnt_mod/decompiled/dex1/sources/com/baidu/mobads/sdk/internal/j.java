package com.baidu.mobads.sdk.internal;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {
    public static JSONArray a(double[] dArr) throws JSONException {
        JSONArray jSONArray;
        if (dArr == null) {
            return null;
        }
        try {
            jSONArray = new JSONArray();
            for (double d : dArr) {
                try {
                    jSONArray.put(d);
                } catch (Exception e) {
                    e = e;
                    bb.a().a(e);
                    return jSONArray;
                }
            }
            return jSONArray;
        } catch (Exception e2) {
            e = e2;
            jSONArray = null;
        }
    }

    public static JSONArray a(List<String[]> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < list.get(i).length; i2++) {
                    jSONArray2.put(list.get(i)[i2]);
                }
                jSONArray.put(jSONArray2);
            } catch (Exception e) {
                bb.a().a(e);
            }
        }
        return jSONArray;
    }

    public static HashMap<String, String> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                map.put(next, jSONObject.optString(next));
            } catch (Exception unused) {
                bb.a().a("json2HashMap error");
            }
        }
        return map;
    }

    public static JSONObject a(HashMap<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            return new JSONObject(map);
        } catch (Exception unused) {
            bb.a().a("hashMap2Json error");
            return null;
        }
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject2 != null && jSONObject2.length() > 0) {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.opt(next));
            }
        }
        return jSONObject;
    }

    public static Object a(JSONObject jSONObject, String str) {
        return (jSONObject != null && jSONObject.has(str)) ? jSONObject.opt(str) : "";
    }

    @TargetApi(8)
    public static Bitmap a(String str) {
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
