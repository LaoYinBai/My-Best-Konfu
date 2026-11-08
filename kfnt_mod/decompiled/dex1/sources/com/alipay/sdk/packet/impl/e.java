package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alipay.sdk.net.a;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends com.alipay.sdk.packet.e {
    @Override // com.alipay.sdk.packet.e
    protected String a(com.alipay.sdk.sys.a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.packet.e
    protected JSONObject a() {
        return null;
    }

    @Override // com.alipay.sdk.packet.e
    protected Map<String, String> a(boolean z, String str) {
        return new HashMap();
    }

    @Override // com.alipay.sdk.packet.e
    public com.alipay.sdk.packet.b a(com.alipay.sdk.sys.a aVar, Context context, String str) throws Throwable {
        com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "mdap post");
        byte[] bArrA = com.alipay.sdk.encrypt.b.a(str.getBytes(Charset.forName("UTF-8")));
        HashMap map = new HashMap();
        map.put("utdId", com.alipay.sdk.sys.b.a().e());
        map.put("logHeader", "RAW");
        map.put("bizCode", "alipaysdk");
        map.put("productId", "alipaysdk_android");
        map.put("Content-Encoding", "Gzip");
        map.put("productVersion", "15.7.9");
        a.b bVarA = com.alipay.sdk.net.a.a(context, new a.C0079a(com.alipay.sdk.cons.a.d, map, bArrA));
        com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "mdap got " + bVarA);
        if (bVarA == null) {
            throw new RuntimeException("Response is null");
        }
        boolean zA = a(bVarA);
        try {
            byte[] bArrB = bVarA.f2614c;
            if (zA) {
                bArrB = com.alipay.sdk.encrypt.b.b(bArrB);
            }
            return new com.alipay.sdk.packet.b("", new String(bArrB, Charset.forName("UTF-8")));
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            return null;
        }
    }
}
