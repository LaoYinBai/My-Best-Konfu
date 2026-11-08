package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.bytedance.sdk.b.d.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends com.alipay.sdk.packet.e {
    public static final String t = "log_v";

    @Override // com.alipay.sdk.packet.e
    protected String a(com.alipay.sdk.sys.a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.packet.e
    protected JSONObject a() throws JSONException {
        return null;
    }

    @Override // com.alipay.sdk.packet.e
    protected Map<String, String> a(boolean z, String str) {
        HashMap map = new HashMap();
        map.put(com.alipay.sdk.packet.e.f2621a, String.valueOf(z));
        map.put(com.alipay.sdk.packet.e.d, a.f.f5151a);
        map.put(com.alipay.sdk.packet.e.g, "CBC");
        return map;
    }

    @Override // com.alipay.sdk.packet.e
    protected String c() throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        map.put(com.alipay.sdk.packet.e.i, "/sdk/log");
        map.put(com.alipay.sdk.packet.e.j, "1.0.0");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put(t, "1.0");
        return a(map, map2);
    }

    @Override // com.alipay.sdk.packet.e
    public com.alipay.sdk.packet.b a(com.alipay.sdk.sys.a aVar, Context context, String str) throws Throwable {
        return a(aVar, context, str, com.alipay.sdk.cons.a.f2576c, true);
    }
}
