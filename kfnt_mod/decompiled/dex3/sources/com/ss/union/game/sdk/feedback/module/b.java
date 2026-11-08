package com.ss.union.game.sdk.feedback.module;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String f = "image_no";
    private static final String g = "width";
    private static final String h = "height";
    private static final String i = "size";
    private static final String j = "url";
    public String a;
    public int b;
    public int c;
    public int d;
    public String e;

    public void a(JSONObject jSONObject) {
        this.a = jSONObject.optString(f, "");
        this.b = jSONObject.optInt("width", 0);
        this.c = jSONObject.optInt("height", 0);
        this.d = jSONObject.optInt(i, 0);
        this.e = jSONObject.optString("url", "");
    }
}
