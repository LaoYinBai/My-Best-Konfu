package com.ss.union.game.sdk.feedback.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final String j = "id";
    private static final String k = "feedback_type_name";
    private static final String l = "content";
    private static final String m = "is_helpful";
    private static final String n = "images";
    private static final String o = "posts";
    private static final String p = "created_at";
    public long d;
    public String e;
    public String f;
    public int g;
    public List<b> h;
    public List<com.ss.union.game.sdk.feedback.module.a> i;
    private long q;

    @Retention(RetentionPolicy.SOURCE)
    @interface a {
    }

    public long a() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.q);
        sb.append("");
        return sb.toString().length() == 10 ? this.q * 1000 : this.q;
    }

    public void a(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.d = jSONObject.optLong("id", -1L);
            this.e = jSONObject.optString(k, "");
            this.f = jSONObject.optString("content", "");
            this.g = jSONObject.optInt(m, 0);
            this.q = jSONObject.optLong(p, 0L);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(n);
            if (jSONArrayOptJSONArray != null) {
                this.h = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    b bVar = new b();
                    bVar.a(jSONObject2);
                    this.h.add(bVar);
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(o);
            if (jSONArrayOptJSONArray2 != null) {
                this.i = new ArrayList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i2);
                    com.ss.union.game.sdk.feedback.module.a aVar = new com.ss.union.game.sdk.feedback.module.a();
                    aVar.a(jSONObjectOptJSONObject);
                    this.i.add(aVar);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
