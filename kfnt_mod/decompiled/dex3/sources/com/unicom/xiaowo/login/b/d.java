package com.unicom.xiaowo.login.b;

import android.content.Context;
import com.unicom.xiaowo.login.ResultListener;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static volatile d b;
    private ResultListener a;

    private d() {
    }

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    public final void a(Context context, String str) throws JSONException {
        b(str, "");
    }

    public final void a(ResultListener resultListener) {
        this.a = resultListener;
    }

    public final void a(String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.taobao.agoo.a.a.b.JSON_ERRORCODE, 0);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", new JSONObject(str2));
            this.a.onResult(jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    public final void b(String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.taobao.agoo.a.a.b.JSON_ERRORCODE, 1);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("traceId", str2);
            this.a.onResult(jSONObject.toString());
        } catch (Exception unused) {
        }
    }
}
