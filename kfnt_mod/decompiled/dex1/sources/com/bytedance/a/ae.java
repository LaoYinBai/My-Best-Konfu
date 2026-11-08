package com.bytedance.a;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ae extends bd {
    public boolean m;
    public int n;
    public long o;

    /* renamed from: a, reason: collision with root package name */
    @org.b.a.d
    public String f3336a = "";

    /* renamed from: b, reason: collision with root package name */
    @org.b.a.d
    public String f3337b = "";

    /* renamed from: c, reason: collision with root package name */
    @org.b.a.d
    public String f3338c = "";

    @org.b.a.d
    public String d = "";

    @org.b.a.d
    public String e = "";

    @org.b.a.d
    public String f = "";

    @org.b.a.d
    public String g = "";

    @org.b.a.d
    public String h = "";

    @org.b.a.d
    public String i = "";

    @org.b.a.d
    public String j = "";

    @org.b.a.d
    public String k = "";

    @org.b.a.d
    public String l = "";

    @org.b.a.d
    public String p = "";

    @org.b.a.d
    public String q = "";

    @org.b.a.d
    public String r = "";

    @Override // com.bytedance.a.bd
    @org.b.a.d
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.f3336a);
        jSONObject.put("utm_campaign", this.f3337b);
        jSONObject.put("utm_source", this.f3338c);
        jSONObject.put("utm_medium", this.d);
        jSONObject.put("utm_content", this.e);
        jSONObject.put("utm_term", this.f);
        jSONObject.put("tr_shareuser", this.g);
        jSONObject.put("tr_admaster", this.h);
        jSONObject.put("tr_param1", this.i);
        jSONObject.put("tr_param2", this.j);
        jSONObject.put("tr_param3", this.k);
        jSONObject.put("tr_param4", this.l);
        jSONObject.put("tr_dp", this.p);
        jSONObject.put("is_retargeting", this.m);
        jSONObject.put("reengagement_window", this.n);
        jSONObject.put("reengagement_time", this.o);
        jSONObject.put("deeplink_value", this.q);
        jSONObject.put("token", this.r);
        return jSONObject;
    }

    public final void a(@org.b.a.d String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.r = str;
    }

    @Override // com.bytedance.a.bd
    public void a(@org.b.a.d JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        String strOptString = jSONObject.optString("name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString, "json.optString(\"name\")");
        this.f3336a = strOptString;
        String strOptString2 = jSONObject.optString("utm_campaign");
        Intrinsics.checkExpressionValueIsNotNull(strOptString2, "json.optString(\"utm_campaign\")");
        this.f3337b = strOptString2;
        String strOptString3 = jSONObject.optString("utm_source");
        Intrinsics.checkExpressionValueIsNotNull(strOptString3, "json.optString(\"utm_source\")");
        this.f3338c = strOptString3;
        String strOptString4 = jSONObject.optString("utm_medium");
        Intrinsics.checkExpressionValueIsNotNull(strOptString4, "json.optString(\"utm_medium\")");
        this.d = strOptString4;
        String strOptString5 = jSONObject.optString("utm_content");
        Intrinsics.checkExpressionValueIsNotNull(strOptString5, "json.optString(\"utm_content\")");
        this.e = strOptString5;
        String strOptString6 = jSONObject.optString("utm_term");
        Intrinsics.checkExpressionValueIsNotNull(strOptString6, "json.optString(\"utm_term\")");
        this.f = strOptString6;
        String strOptString7 = jSONObject.optString("tr_shareuser");
        Intrinsics.checkExpressionValueIsNotNull(strOptString7, "json.optString(\"tr_shareuser\")");
        this.g = strOptString7;
        String strOptString8 = jSONObject.optString("tr_admaster");
        Intrinsics.checkExpressionValueIsNotNull(strOptString8, "json.optString(\"tr_admaster\")");
        this.h = strOptString8;
        String strOptString9 = jSONObject.optString("tr_param1");
        Intrinsics.checkExpressionValueIsNotNull(strOptString9, "json.optString(\"tr_param1\")");
        this.i = strOptString9;
        String strOptString10 = jSONObject.optString("tr_param2");
        Intrinsics.checkExpressionValueIsNotNull(strOptString10, "json.optString(\"tr_param2\")");
        this.j = strOptString10;
        String strOptString11 = jSONObject.optString("tr_param3");
        Intrinsics.checkExpressionValueIsNotNull(strOptString11, "json.optString(\"tr_param3\")");
        this.k = strOptString11;
        String strOptString12 = jSONObject.optString("tr_param4");
        Intrinsics.checkExpressionValueIsNotNull(strOptString12, "json.optString(\"tr_param4\")");
        this.l = strOptString12;
        this.m = jSONObject.optBoolean("is_retargeting");
        this.n = jSONObject.optInt("reengagement_window");
        this.o = jSONObject.optLong("reengagement_time");
        String strOptString13 = jSONObject.optString("tr_dp");
        Intrinsics.checkExpressionValueIsNotNull(strOptString13, "json.optString(\"tr_dp\")");
        this.p = strOptString13;
        String strOptString14 = jSONObject.optString("deeplink_value");
        Intrinsics.checkExpressionValueIsNotNull(strOptString14, "json.optString(\"deeplink_value\")");
        this.q = strOptString14;
        String strOptString15 = jSONObject.optString("token");
        Intrinsics.checkExpressionValueIsNotNull(strOptString15, "json.optString(\"token\")");
        this.r = strOptString15;
    }

    @org.b.a.d
    public String toString() {
        String string = a().toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "toJson().toString()");
        return string;
    }
}
