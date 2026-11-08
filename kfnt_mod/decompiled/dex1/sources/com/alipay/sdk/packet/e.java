package com.alipay.sdk.packet;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.net.a;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.k;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.b.d.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2621a = "msp-gzip";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2622b = "Msp-Param";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2623c = "Operation-Type";
    public static final String d = "content-type";
    public static final String e = "Version";
    public static final String f = "AppId";
    public static final String g = "des-mode";
    public static final String h = "namespace";
    public static final String i = "api_name";
    public static final String j = "api_version";
    public static final String k = "data";
    public static final String l = "params";
    public static final String m = "public_key";
    public static final String n = "device";
    public static final String o = "action";
    public static final String p = "type";
    public static final String q = "method";
    protected boolean r = true;
    protected boolean s = true;

    protected abstract JSONObject a() throws JSONException;

    protected String b() {
        return "4.9.0";
    }

    protected Map<String, String> a(boolean z, String str) {
        HashMap map = new HashMap();
        map.put(f2621a, String.valueOf(z));
        map.put(f2623c, "alipay.msp.cashier.dispatch.bytes");
        map.put(d, a.f.f5151a);
        map.put(e, "2.0");
        map.put(f, "TAOBAO");
        map.put(f2622b, a.a(str));
        map.put(g, "CBC");
        return map;
    }

    protected String c() throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        map.put(n, Build.MODEL);
        map.put("namespace", "com.alipay.mobilecashier");
        map.put(i, "com.alipay.mcpay");
        map.put(j, b());
        return a(map, new HashMap<>());
    }

    protected static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put(q, str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    protected String a(com.alipay.sdk.sys.a aVar, String str, JSONObject jSONObject) throws JSONException {
        com.alipay.sdk.sys.b bVarA = com.alipay.sdk.sys.b.a();
        com.alipay.sdk.tid.a aVarA = com.alipay.sdk.tid.a.a(bVarA.b());
        JSONObject jSONObjectA = com.alipay.sdk.util.b.a(new JSONObject(), jSONObject);
        try {
            jSONObjectA.put(com.alipay.sdk.cons.b.d, str);
            jSONObjectA.put("tid", aVarA.a());
            jSONObjectA.put(com.alipay.sdk.cons.b.f2578b, bVarA.c().a(aVar, aVarA));
            jSONObjectA.put(com.alipay.sdk.cons.b.e, k.b(aVar, bVarA.b(), com.alipay.sdk.app.a.f2557a));
            jSONObjectA.put(com.alipay.sdk.cons.b.f, k.a(bVarA.b()));
            jSONObjectA.put("app_key", com.alipay.sdk.cons.a.f);
            jSONObjectA.put(com.alipay.sdk.cons.b.g, bVarA.e());
            jSONObjectA.put(com.alipay.sdk.cons.b.j, aVarA.b());
            jSONObjectA.put(com.alipay.sdk.cons.b.k, com.alipay.sdk.data.b.a(bVarA.b()));
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "BodyErr", th);
            com.alipay.sdk.util.c.a(th);
        }
        return jSONObjectA.toString();
    }

    protected static boolean a(a.b bVar) {
        return Boolean.valueOf(a(bVar, f2621a)).booleanValue();
    }

    protected static String a(a.b bVar, String str) {
        List<String> list;
        if (bVar == null || str == null || bVar.f2612a == null || (list = bVar.f2612a.get(str)) == null) {
            return null;
        }
        return TextUtils.join(IWeiboService.Scope.EMPTY_SCOPE, list);
    }

    protected String a(HashMap<String, String> map, HashMap<String, String> map2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    private static boolean a(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has("params")) {
                return false;
            }
            String strOptString = jSONObject.getJSONObject("params").optString(m, null);
            if (TextUtils.isEmpty(strOptString)) {
                return false;
            }
            com.alipay.sdk.data.b.a(strOptString);
            return true;
        } catch (JSONException e2) {
            com.alipay.sdk.util.c.a(e2);
            return false;
        }
    }

    public b a(com.alipay.sdk.sys.a aVar, Context context) throws Throwable {
        return a(aVar, context, "");
    }

    public b a(com.alipay.sdk.sys.a aVar, Context context, String str) throws Throwable {
        return a(aVar, context, str, j.a(context));
    }

    public b a(com.alipay.sdk.sys.a aVar, Context context, String str, String str2) throws Throwable {
        return a(aVar, context, str, str2, true);
    }

    protected b a(com.alipay.sdk.sys.a aVar, Context context, String str, String str2, boolean z) throws Throwable {
        com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "Packet: " + str2);
        c cVar = new c(this.s);
        b bVar = new b(c(), a(aVar, str, a()));
        Map<String, String> mapA = a(false, str);
        d dVarA = cVar.a(bVar, this.r, mapA.get("iSr"));
        a.b bVarA = com.alipay.sdk.net.a.a(context, new a.C0079a(str2, a(dVarA.a(), str), dVarA.b()));
        if (bVarA == null) {
            throw new RuntimeException("Response is null.");
        }
        b bVarA2 = cVar.a(new d(a(bVarA), bVarA.f2614c), mapA.get("iSr"));
        return (bVarA2 != null && a(bVarA2.a()) && z) ? a(aVar, context, str, str2, false) : bVarA2;
    }
}
