package com.bytedance.push.e;

import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public long f4793a;

    /* renamed from: b, reason: collision with root package name */
    public String f4794b;

    /* renamed from: c, reason: collision with root package name */
    public int f4795c;
    private String d;
    private String e;

    public c(int i, String str, String str2, String str3, long j) {
        this.f4795c = i;
        this.f4794b = str;
        this.d = str2;
        this.e = str3;
        this.f4793a = j;
    }

    public boolean a(c cVar) {
        return (cVar != this && cVar.f4795c == this.f4795c && TextUtils.equals(this.e, cVar.e) && TextUtils.equals(this.f4794b, cVar.f4794b) && TextUtils.equals(this.d, cVar.d)) ? true : true;
    }

    public JSONObject a() throws JSONException {
        if (this.f4795c <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f4795c);
            jSONObject.put("token", this.f4794b);
            jSONObject.put("did", this.d);
            jSONObject.put("vc", this.e);
            jSONObject.put(DispatchConstants.TIMESTAMP, this.f4793a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static c a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        int iOptInt = jSONObject.optInt("type");
        String strOptString = jSONObject.optString("token");
        String strOptString2 = jSONObject.optString("did");
        String strOptString3 = jSONObject.optString("vc");
        long jOptLong = jSONObject.optLong(DispatchConstants.TIMESTAMP);
        if (iOptInt <= 0) {
            return null;
        }
        return new c(iOptInt, strOptString, strOptString2, strOptString3, jOptLong);
    }

    public String toString() {
        return "{updateTime=" + this.f4793a + ", deviceId='" + this.d + "', versionCode='" + this.e + "', token='" + this.f4794b + "', type=" + this.f4795c + '}';
    }
}
