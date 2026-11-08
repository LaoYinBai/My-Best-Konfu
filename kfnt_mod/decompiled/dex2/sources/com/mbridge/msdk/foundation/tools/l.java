package com.mbridge.msdk.foundation.tools;

import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: PermMappingUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class l {
    public static JSONArray a(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                jSONArray2.put(k.a(jSONArray.getString(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray2;
    }
}
