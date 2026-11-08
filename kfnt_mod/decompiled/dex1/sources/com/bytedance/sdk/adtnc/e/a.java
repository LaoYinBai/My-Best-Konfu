package com.bytedance.sdk.adtnc.e;

import android.content.Context;
import com.bytedance.sdk.adtnc.e.b;
import com.bytedance.sdk.adtnc.sdk.b.c;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements c {
    @Override // com.bytedance.sdk.adtnc.sdk.b.c
    public int a(Context context, String str, int i) {
        int iA = b.b(context).a(str, i);
        com.bytedance.sdk.adtnc.g.b.a("AdTNCDefaultProvider", "getProviderInt", "result :", Integer.valueOf(iA));
        return iA;
    }

    @Override // com.bytedance.sdk.adtnc.sdk.b.c
    public String a(Context context, String str, String str2) {
        String strA = b.b(context).a(str, str2);
        com.bytedance.sdk.adtnc.g.b.a("AdTNCDefaultProvider", "getProviderString", "result :", strA);
        return strA;
    }

    @Override // com.bytedance.sdk.adtnc.sdk.b.c
    public void a(Context context, Map<String, ?> map) {
        try {
            b.a aVarA = b.a(context);
            if (map != null) {
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Integer) {
                        aVarA.a(entry.getKey(), ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        aVarA.a(entry.getKey(), ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        aVarA.a(entry.getKey(), ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        aVarA.a(entry.getKey(), ((Boolean) value).booleanValue());
                    } else if (value instanceof String) {
                        aVarA.a(entry.getKey(), (String) value);
                    }
                    com.bytedance.sdk.adtnc.g.b.a("AdTNCDefaultProvider", "saveMapToProvider", "key :", entry.getKey(), "value :" + value);
                }
                aVarA.b();
            }
        } catch (Throwable unused) {
        }
    }
}
