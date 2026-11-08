package com.bytedance.push.settings;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {
    public List<com.bytedance.push.e.c> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                com.bytedance.push.e.c cVarA = com.bytedance.push.e.c.a(jSONArray.optJSONObject(i));
                if (cVarA != null) {
                    arrayList.add(cVarA);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String a(List<com.bytedance.push.e.c> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (com.bytedance.push.e.c cVar : list) {
                if (cVar != null) {
                    jSONArray.put(cVar.a());
                }
            }
        }
        return jSONArray.toString();
    }

    public List<com.bytedance.push.e.c> a() {
        return new ArrayList();
    }
}
