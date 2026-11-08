package com.taobao.accs.utl;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class p {
    public static String a(JSONObject jSONObject, String str, String str2) throws JSONException {
        return (jSONObject != null && jSONObject.has(str)) ? jSONObject.getString(str) : str2;
    }

    public static class a {
        JSONObject a = new JSONObject();

        public a a(String str, String str2) throws JSONException {
            if (str2 != null && str != null) {
                try {
                    this.a.put(str, str2);
                } catch (JSONException unused) {
                }
            }
            return this;
        }

        public a a(String str, Integer num) throws JSONException {
            if (num == null) {
                return this;
            }
            try {
                this.a.put(str, num);
            } catch (JSONException unused) {
            }
            return this;
        }

        public JSONObject a() {
            return this.a;
        }
    }
}
