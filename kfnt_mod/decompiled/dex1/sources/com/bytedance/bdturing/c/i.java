package com.bytedance.bdturing.c;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    static final String f3786a = "JSSDK";

    /* renamed from: b, reason: collision with root package name */
    static final String f3787b = "func";

    /* renamed from: c, reason: collision with root package name */
    static final String f3788c = "params";
    static final String d = "__msg_type";
    static final String e = "__callback_id";
    static final String f = "code";
    static final String g = "__params";
    static final String h = "data";
    public static final String i = "callback";
    public static final String j = "call";
    public static final String k = "bytedcert.goToClose";
    public static final String l = "bytedcert.verifyReport";
    public static final int m = 1;
    public static final int n = 0;
    public static final int o = -3;
    public static final int p = -2;
    public static final int q = -1;
    String r = null;
    String s = null;
    String t = null;
    String u = null;
    String v = null;

    i(String str) {
        try {
            b(str);
        } catch (JSONException e2) {
            com.bytedance.bdturing.g.a(e2);
        }
    }

    private void b(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.r = jSONObject.getString(f3786a);
        this.s = jSONObject.getString(f3787b);
        this.t = jSONObject.getString("params");
        this.u = jSONObject.getString(d);
        this.v = jSONObject.getString(e);
        com.bytedance.bdturing.e.a(true, this.s);
    }

    public static JSONObject a(int i2, String str, JSONObject jSONObject, i iVar) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code", i2);
        jSONObject2.put(f3787b, iVar.s);
        jSONObject2.put(d, str);
        jSONObject2.put("data", jSONObject);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(g, jSONObject2);
        jSONObject3.put(e, iVar.v);
        return jSONObject3;
    }

    public static String a(int i2, String str, String str2, JSONObject jSONObject, String str3) throws JSONException {
        String string;
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", i2);
            jSONObject2.put(f3787b, str);
            jSONObject2.put(d, str2);
            jSONObject2.put("data", jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(g, jSONObject2);
            jSONObject3.put(e, str3);
            string = jSONObject3.toString();
        } catch (JSONException e2) {
            com.bytedance.bdturing.g.a(e2);
            string = null;
        }
        com.bytedance.bdturing.e.a(false, str);
        return string;
    }

    public static JSONArray a(String str) {
        try {
            return new JSONObject(str).getJSONObject(g).getJSONObject("data").getJSONArray("data");
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
