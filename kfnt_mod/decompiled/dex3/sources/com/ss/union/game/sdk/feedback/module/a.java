package com.ss.union.game.sdk.feedback.module;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final int a = 1;
    public static final int b = 2;
    private static final String h = "id";
    private static final String i = "author";
    private static final String j = "content";
    private static final String k = "created_at";
    private static final String l = "has_not_read";
    private static final String m = "images";
    private static final String n = "author_type";
    public long c;
    public String d;
    public String e;
    public boolean f;
    public List<b> g;
    private long o;
    private int p;

    /* renamed from: com.ss.union.game.sdk.feedback.module.a$a, reason: collision with other inner class name */
    public @interface InterfaceC0097a {
    }

    public int a() {
        return this.p;
    }

    public long b() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.o);
        sb.append("");
        return sb.toString().length() == 10 ? this.o * 1000 : this.o;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        this.c = jSONObject.optLong("id");
        this.d = jSONObject.optString(i, "");
        this.e = jSONObject.optString("content", "");
        this.o = jSONObject.optLong(k);
        this.f = jSONObject.optBoolean(l, false);
        this.p = jSONObject.optInt(n, 1);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(m);
        if (jSONArrayOptJSONArray != null) {
            this.g = new ArrayList();
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                b bVar = new b();
                bVar.a(jSONObject2);
                this.g.add(bVar);
            }
        }
    }
}
