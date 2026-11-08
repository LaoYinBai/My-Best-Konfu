package com.cmic.sso.sdk.b.b;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends g {

    /* renamed from: a, reason: collision with root package name */
    private String f5409a;

    /* renamed from: b, reason: collision with root package name */
    private String f5410b;

    /* renamed from: c, reason: collision with root package name */
    private String f5411c;

    public d(String str, String str2, String str3) {
        this.f5409a = str;
        this.f5410b = str2;
        this.f5411c = str3;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return this.f5409a;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", this.f5410b);
            jSONObject.put("data", this.f5411c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
