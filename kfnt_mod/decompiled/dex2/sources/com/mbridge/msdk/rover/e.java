package com.mbridge.msdk.rover;

import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RoverReportData.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private String a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;

    public final void a(String str) {
        this.a = str;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final void c(int i) {
        this.d = i;
    }

    public final void b(String str) {
        this.e = str;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.a);
            jSONObject.put(Message.TYPE, this.b);
            jSONObject.put("time", this.c);
            jSONObject.put(CommandMessage.CODE, this.d);
            jSONObject.put("header", this.e);
            jSONObject.put("exception", this.f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String toString() {
        return "url=" + this.a + ", type=" + this.b + ", time=" + this.c + ", code=" + this.d + ", header=" + this.e + ", exception=" + this.f;
    }
}
