package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.b.h;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private GrsBaseInfo b;
    private com.huawei.hms.framework.network.grs.a.a c;
    private h d;

    public a(GrsBaseInfo grsBaseInfo, com.huawei.hms.framework.network.grs.a.a aVar, h hVar) {
        this.b = grsBaseInfo;
        this.c = aVar;
        this.d = hVar;
    }

    public static CountryCodeBean a(Context context, boolean z) {
        return new CountryCodeBean(context, z);
    }

    private String a(String str, String str2, com.huawei.hms.framework.network.grs.a.b bVar, Context context) {
        String strA = this.c.a(this.b, str, str2, bVar, context);
        if (TextUtils.isEmpty(strA)) {
            return com.huawei.hms.framework.network.grs.local.b.a(context.getPackageName()).a(this.b, str, str2);
        }
        Logger.i(a, "get url from sp is not empty.");
        return strA;
    }

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new JSONObject(str).getJSONObject(str2).getString(str3);
        } catch (JSONException e) {
            Logger.w(a, "Method{getServiceNameUrl} query url from SP occur an JSONException", e);
            return "";
        }
    }

    public static Map<String, Map<String, String>> a(String str) {
        HashMap map = new HashMap(16);
        if (TextUtils.isEmpty(str)) {
            Logger.v(a, "isSpExpire jsonValue is null.");
            return map;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String string = itKeys.next().toString();
                map.put(string, a(jSONObject.getJSONObject(string)));
            }
            return map;
        } catch (JSONException e) {
            Logger.w(a, "getServicesUrlsMap occur a JSONException", e);
            return map;
        }
    }

    private Map<String, String> a(String str, com.huawei.hms.framework.network.grs.a.b bVar, Context context) {
        Map<String, String> mapA = this.c.a(this.b, str, bVar, context);
        return (mapA == null || mapA.isEmpty()) ? com.huawei.hms.framework.network.grs.local.b.a(context.getPackageName()).a(this.b, str) : mapA;
    }

    public static Map<String, String> a(String str, String str2) throws JSONException {
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(str)) {
            Logger.v(a, "isSpExpire jsonValue is null.");
            return map;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(str2);
            if (jSONObject == null) {
                Logger.v(a, "getServiceNameUrls jsObject null.");
                return map;
            }
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String string = itKeys.next().toString();
                map.put(string, jSONObject.get(string).toString());
            }
            return map;
        } catch (JSONException e) {
            Logger.w(a, "Method{getServiceNameUrls} query url from SP occur an JSONException", e);
            return map;
        }
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap map = new HashMap(16);
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String string = itKeys.next().toString();
                map.put(string, jSONObject.get(string).toString());
            }
            return map;
        } catch (JSONException e) {
            Logger.w(a, "getServiceUrls occur a JSONException", e);
            return map;
        }
    }

    private void a(final String str, final Map<String, String> map, final IQueryUrlsCallBack iQueryUrlsCallBack, Context context) {
        this.d.a(this.b, context, new b() { // from class: com.huawei.hms.framework.network.grs.a.2
            @Override // com.huawei.hms.framework.network.grs.b
            public void a() {
                if (map == null || map.isEmpty()) {
                    iQueryUrlsCallBack.onCallBackFail(-3);
                } else {
                    iQueryUrlsCallBack.onCallBackSuccess(map);
                }
            }

            @Override // com.huawei.hms.framework.network.grs.b
            public void a(com.huawei.hms.framework.network.grs.b.d dVar) throws JSONException {
                Map<String, String> mapA = a.a(dVar.c(), str);
                if (mapA != null && !mapA.isEmpty()) {
                    iQueryUrlsCallBack.onCallBackSuccess(mapA);
                } else if (map == null || map.isEmpty()) {
                    iQueryUrlsCallBack.onCallBackFail(-5);
                } else {
                    iQueryUrlsCallBack.onCallBackSuccess(map);
                }
            }
        }, str);
    }

    public String a(Context context, String str) {
        com.huawei.hms.framework.network.grs.b.d dVarA = this.d.a(this.b, context, str);
        return dVarA == null ? "" : dVarA.c();
    }

    public String a(String str, String str2, Context context) {
        com.huawei.hms.framework.network.grs.a.b bVar = new com.huawei.hms.framework.network.grs.a.b();
        String strA = a(str, str2, bVar, context);
        if (bVar.a()) {
            Logger.v(a, "get unexpired cache localUrl{%s}", strA);
            return strA;
        }
        String strA2 = a(a(context, str), str, str2);
        if (TextUtils.isEmpty(strA2)) {
            return strA;
        }
        Logger.i(a, "get url is from remote server");
        return strA2;
    }

    public Map<String, String> a(String str, Context context) {
        Map<String, String> mapA;
        com.huawei.hms.framework.network.grs.a.b bVar = new com.huawei.hms.framework.network.grs.a.b();
        Map<String, String> mapA2 = a(str, bVar, context);
        return (bVar.a() || (mapA = a(a(context, str), str)) == null || mapA.isEmpty()) ? mapA2 : mapA;
    }

    public void a(String str, IQueryUrlsCallBack iQueryUrlsCallBack, Context context) {
        com.huawei.hms.framework.network.grs.a.b bVar = new com.huawei.hms.framework.network.grs.a.b();
        Map<String, String> mapA = a(str, bVar, context);
        if (!bVar.a()) {
            a(str, mapA, iQueryUrlsCallBack, context);
        } else if (mapA == null || mapA.isEmpty()) {
            iQueryUrlsCallBack.onCallBackFail(-5);
        } else {
            iQueryUrlsCallBack.onCallBackSuccess(mapA);
        }
    }

    public void a(final String str, final String str2, final IQueryUrlCallBack iQueryUrlCallBack, Context context) {
        com.huawei.hms.framework.network.grs.a.b bVar = new com.huawei.hms.framework.network.grs.a.b();
        final String strA = a(str, str2, bVar, context);
        if (!bVar.a()) {
            this.d.a(this.b, context, new b() { // from class: com.huawei.hms.framework.network.grs.a.1
                @Override // com.huawei.hms.framework.network.grs.b
                public void a() {
                    if (TextUtils.isEmpty(strA)) {
                        iQueryUrlCallBack.onCallBackFail(-3);
                    } else {
                        iQueryUrlCallBack.onCallBackSuccess(strA);
                    }
                }

                @Override // com.huawei.hms.framework.network.grs.b
                public void a(com.huawei.hms.framework.network.grs.b.d dVar) {
                    String strA2 = a.a(dVar.c(), str, str2);
                    if (!TextUtils.isEmpty(strA2)) {
                        iQueryUrlCallBack.onCallBackSuccess(strA2);
                    } else if (TextUtils.isEmpty(strA)) {
                        iQueryUrlCallBack.onCallBackFail(-5);
                    } else {
                        iQueryUrlCallBack.onCallBackSuccess(strA);
                    }
                }
            }, str);
        } else if (TextUtils.isEmpty(strA)) {
            iQueryUrlCallBack.onCallBackFail(-5);
        } else {
            iQueryUrlCallBack.onCallBackSuccess(strA);
        }
    }
}
