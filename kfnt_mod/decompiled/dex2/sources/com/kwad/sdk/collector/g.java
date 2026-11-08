package com.kwad.sdk.collector;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static int a = 0;
    public static int b = 1;
    public static int c = 2;
    private String d;
    private int e;

    public g(String str, int i) {
        this.e = a;
        this.d = str;
        this.e = i;
    }

    public static JSONArray a(List<g> list) {
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return jSONArray;
        }
        Iterator<g> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        return jSONArray;
    }

    private String b() {
        int iLastIndexOf;
        String str = this.d;
        return ((this.d.startsWith("com.android.") || this.d.startsWith("android.permission")) && (iLastIndexOf = this.d.lastIndexOf(".")) < this.d.length() + (-1)) ? this.d.substring(iLastIndexOf + 1) : str;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", b());
            jSONObject.put("state", this.e);
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        return jSONObject;
    }
}
