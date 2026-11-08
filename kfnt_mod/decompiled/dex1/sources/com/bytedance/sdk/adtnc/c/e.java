package com.bytedance.sdk.adtnc.c;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f5094a = new HashMap();

    private boolean c(String str, com.bytedance.sdk.adtnc.a.a aVar) {
        String str2 = aVar.d.get(str);
        return this.f5094a.get(str2) != null && this.f5094a.get(str2).intValue() >= 3;
    }

    public e a() {
        this.f5094a.clear();
        return this;
    }

    public String a(String str, com.bytedance.sdk.adtnc.a.a aVar) {
        String protocol;
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host: ", str);
        if (aVar == null || TextUtils.isEmpty(str) || str.contains("/get_domains/v4")) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host ignore");
            return str;
        }
        String host = null;
        try {
            URL url = new URL(str);
            protocol = url.getProtocol();
            try {
                host = url.getHost();
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                if (TextUtils.isEmpty(protocol)) {
                }
                com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host is bad");
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
            protocol = null;
        }
        if (!TextUtils.isEmpty(protocol) || (!(HttpConstant.HTTP.equals(protocol) || "https".equals(protocol)) || TextUtils.isEmpty(host))) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host is bad");
            return str;
        }
        Map<String, String> map = aVar.d;
        if (map == null || !map.containsKey(host)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host not match 1");
            return str;
        }
        String str2 = map.get(host);
        if (TextUtils.isEmpty(str2)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host not match 2");
            return str;
        }
        if (c(host, aVar)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "TNC host number of failures over limit");
            return str;
        }
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "match, origin:", str);
        String str3 = protocol + HttpConstant.SCHEME_SPLIT + host;
        String str4 = protocol + HttpConstant.SCHEME_SPLIT + str2;
        if (str.startsWith(str3)) {
            str = str.replaceFirst(str3, str4);
        }
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "filterUrl", "match, target:", str);
        return str;
    }

    public void a(String str) {
        String str2;
        String str3;
        String str4;
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "onNetSuccess", "host:", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.f5094a.containsKey(str)) {
            this.f5094a.put(str, 0);
            str2 = "TNCReplaceManager";
            str3 = "onNetSuccess";
            str4 = "host error count reset";
        } else {
            str2 = "TNCReplaceManager";
            str3 = "onNetSuccess";
            str4 = "host not in hostReplaceMap";
        }
        com.bytedance.sdk.adtnc.g.b.a(str2, str3, str4);
    }

    public void b(String str, com.bytedance.sdk.adtnc.a.a aVar) {
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "onNetError", "host:", str);
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        Map<String, String> map = aVar.d;
        if (map == null || !map.containsValue(str)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "onNetError", "host not in hostReplaceMap");
            return;
        }
        if (this.f5094a.get(str) == null) {
            this.f5094a.put(str, 1);
        } else {
            this.f5094a.put(str, Integer.valueOf(this.f5094a.get(str).intValue() + 1));
        }
        com.bytedance.sdk.adtnc.g.b.a("TNCReplaceManager", "onNetError", "host error count", this.f5094a.get(str));
    }
}
