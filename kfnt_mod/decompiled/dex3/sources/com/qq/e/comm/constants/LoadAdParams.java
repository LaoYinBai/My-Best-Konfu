package com.qq.e.comm.constants;

import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadAdParams {
    private LoginType a;
    private String b;
    private String c;
    private String d;
    private Map<String, String> e;
    private JSONObject f;

    public Map getDevExtra() {
        return this.e;
    }

    public String getDevExtraJsonString() {
        try {
            return (this.e == null || this.e.size() <= 0) ? "" : new JSONObject(this.e).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public JSONObject getExtraInfo() {
        return this.f;
    }

    public String getLoginAppId() {
        return this.b;
    }

    public String getLoginOpenid() {
        return this.c;
    }

    public LoginType getLoginType() {
        return this.a;
    }

    public String getUin() {
        return this.d;
    }

    public void setDevExtra(Map<String, String> map) {
        this.e = map;
    }

    public void setExtraInfo(JSONObject jSONObject) {
        this.f = jSONObject;
    }

    public void setLoginAppId(String str) {
        this.b = str;
    }

    public void setLoginOpenid(String str) {
        this.c = str;
    }

    public void setLoginType(LoginType loginType) {
        this.a = loginType;
    }

    public void setUin(String str) {
        this.d = str;
    }

    public String toString() {
        return "LoadAdParams{, loginType=" + this.a + ", loginAppId=" + this.b + ", loginOpenid=" + this.c + ", uin=" + this.d + ", passThroughInfo=" + this.e + ", extraInfo=" + this.f + '}';
    }
}
