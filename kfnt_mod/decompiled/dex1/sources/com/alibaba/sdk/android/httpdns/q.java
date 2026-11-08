package com.alibaba.sdk.android.httpdns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class q {
    private boolean enabled;
    private String[] f;

    q(String str) throws JSONException {
        this.enabled = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            i.d("Schedule center response:" + jSONObject.toString());
            if (jSONObject.has("service_status")) {
                this.enabled = !jSONObject.getString("service_status").equals("disable");
            }
            if (jSONObject.has("service_ip")) {
                JSONArray jSONArray = jSONObject.getJSONArray("service_ip");
                this.f = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.f[i] = (String) jSONArray.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] c() {
        return this.f;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
