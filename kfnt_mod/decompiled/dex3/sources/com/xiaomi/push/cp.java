package com.xiaomi.push;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cp {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private long f195a;

    /* renamed from: a, reason: collision with other field name */
    private String f196a;
    private long b;
    private long c;

    public cp() {
        this(0, 0L, 0L, null);
    }

    public cp(int i, long j, long j2, Exception exc) {
        this.a = i;
        this.f195a = j;
        this.c = j2;
        this.b = System.currentTimeMillis();
        if (exc != null) {
            this.f196a = exc.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.a;
    }

    public cp a(JSONObject jSONObject) {
        this.f195a = jSONObject.getLong("cost");
        this.c = jSONObject.getLong("size");
        this.b = jSONObject.getLong("ts");
        this.a = jSONObject.getInt("wt");
        this.f196a = jSONObject.optString("expt");
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public JSONObject m172a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.f195a);
        jSONObject.put("size", this.c);
        jSONObject.put("ts", this.b);
        jSONObject.put("wt", this.a);
        jSONObject.put("expt", this.f196a);
        return jSONObject;
    }
}
