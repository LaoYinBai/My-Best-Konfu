package com.onevcat.uniwebview;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UniWebViewResultPayload {
    String data;
    String identifier;
    String resultCode;

    public UniWebViewResultPayload(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.identifier = jSONObject.getString("identifier");
            this.resultCode = jSONObject.getString("resultCode");
            this.data = jSONObject.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public UniWebViewResultPayload(String str, String str2, String str3) {
        this.identifier = str;
        this.resultCode = str2;
        this.data = str3;
    }

    public String toString() {
        HashMap map = new HashMap();
        map.put("identifier", this.identifier);
        map.put("resultCode", this.resultCode);
        map.put("data", this.data);
        return new JSONObject(map).toString();
    }
}
