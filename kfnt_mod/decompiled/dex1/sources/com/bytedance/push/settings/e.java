package com.bytedance.push.settings;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {
    public List<String> a() {
        return new ArrayList();
    }

    public List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
