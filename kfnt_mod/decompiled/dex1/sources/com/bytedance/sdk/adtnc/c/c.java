package com.bytedance.sdk.adtnc.c;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.a.a f5087a = new com.bytedance.sdk.adtnc.a.a();

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.sdk.b.a f5088b;

    public c(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        this.f5088b = aVar;
    }

    private com.bytedance.sdk.adtnc.a.a b(JSONObject jSONObject) {
        try {
            com.bytedance.sdk.adtnc.a.a aVar = new com.bytedance.sdk.adtnc.a.a();
            if (jSONObject.has("local_enable")) {
                aVar.f5075a = jSONObject.getInt("local_enable") != 0;
            }
            if (jSONObject.has("probe_enable")) {
                aVar.f5076b = jSONObject.getInt("probe_enable") != 0;
            }
            if (jSONObject.has("local_host_filter")) {
                JSONArray jSONArray = jSONObject.getJSONArray("local_host_filter");
                HashMap map = new HashMap();
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String string = jSONArray.getString(i);
                        if (!TextUtils.isEmpty(string)) {
                            map.put(string, 0);
                        }
                    }
                }
                aVar.f5077c = map;
            } else {
                aVar.f5077c = null;
            }
            if (jSONObject.has("host_replace_map")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("host_replace_map");
                HashMap map2 = new HashMap();
                if (jSONObject2.length() > 0) {
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        String string2 = jSONObject2.getString(next);
                        if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(string2)) {
                            map2.put(next, string2);
                        }
                    }
                }
                aVar.d = map2;
            } else {
                aVar.d = null;
            }
            aVar.e = jSONObject.optInt("req_to_cnt", aVar.e);
            aVar.f = jSONObject.optInt("req_to_api_cnt", aVar.f);
            aVar.g = jSONObject.optInt("req_to_ip_cnt", aVar.g);
            aVar.h = jSONObject.optInt("req_err_cnt", aVar.h);
            aVar.i = jSONObject.optInt("req_err_api_cnt", aVar.i);
            aVar.j = jSONObject.optInt("req_err_ip_cnt", aVar.j);
            aVar.k = jSONObject.optInt("update_interval", aVar.k);
            aVar.l = jSONObject.optInt("update_random_range", aVar.l);
            aVar.m = jSONObject.optString("http_code_black", aVar.m);
            return aVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a() {
        String string = this.f5088b.a().getSharedPreferences(com.bytedance.sdk.adtnc.g.a.a(this.f5088b.c()), 0).getString("tnc_config_str", null);
        if (TextUtils.isEmpty(string)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigMainProcess", "jsonStr is empty");
            return;
        }
        try {
            com.bytedance.sdk.adtnc.a.a aVarB = b(new JSONObject(string));
            if (aVarB != null) {
                this.f5087a = aVarB;
            }
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigMainProcess", "parse success config:", aVarB);
        } catch (Throwable th) {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigMainProcess", (Object) "parse json exception", th);
        }
    }

    public synchronized void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        String str;
        com.bytedance.sdk.adtnc.a.a aVarB;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            boolean z = jSONObject2.optInt("ttnet_url_dispatcher_enabled", 0) > 0;
            JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("ttnet_dispatch_actions");
            JSONObject jSONObject3 = null;
            if (z && jSONArrayOptJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject4 = ((JSONObject) jSONArrayOptJSONArray.get(i)).getJSONObject("param");
                    if (jSONObject4.optString("service_name", "").equals("idc_selection")) {
                        arrayList.add(jSONObject4.getJSONObject("strategy_info"));
                    }
                }
                if (!arrayList.isEmpty()) {
                    jSONObject3 = new JSONObject();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        JSONObject jSONObject5 = (JSONObject) it.next();
                        Iterator<String> itKeys = jSONObject5.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            jSONObject3.put(next, jSONObject5.getString(next));
                        }
                    }
                }
            }
            jSONObjectOptJSONObject = jSONObject2.optJSONObject("tnc_config");
            if (jSONObjectOptJSONObject == null && jSONObject3 == null) {
                com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "configChanged", "tnc host_replace_map config is null");
            } else {
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = new JSONObject();
                } else {
                    str = jSONObject3 != null ? "host_replace_map" : "host_replace_map";
                }
                jSONObjectOptJSONObject.put(str, jSONObject3);
            }
            aVarB = b(jSONObjectOptJSONObject);
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "configChanged", "parse config success", aVarB);
        } catch (Throwable th) {
            th.printStackTrace();
            this.f5087a = new com.bytedance.sdk.adtnc.a.a();
        } finally {
            this.f5088b.a().getSharedPreferences(com.bytedance.sdk.adtnc.g.a.a(this.f5088b.c()), 0).edit().putString("tnc_config_str", "").apply();
            com.bytedance.sdk.adtnc.g.c.a(this.f5088b, 1, "");
        }
        if (aVarB == null) {
            return;
        }
        this.f5087a = aVarB;
        String string = jSONObjectOptJSONObject.toString();
        this.f5088b.a().getSharedPreferences(com.bytedance.sdk.adtnc.g.a.a(this.f5088b.c()), 0).edit().putString("tnc_config_str", string).apply();
        com.bytedance.sdk.adtnc.g.c.a(this.f5088b, 1, string);
    }

    public synchronized void b() {
        String strA;
        try {
            strA = com.bytedance.sdk.adtnc.g.c.a(this.f5088b, 1);
        } catch (Throwable th) {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigOtherProcess", (Object) "loadLocalConfigOtherProcess exception", th);
        }
        if (TextUtils.isEmpty(strA)) {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigOtherProcess", "data empty");
            return;
        }
        com.bytedance.sdk.adtnc.a.a aVarB = b(new JSONObject(strA));
        com.bytedance.sdk.adtnc.g.b.a("TNCConfigManager", "loadLocalConfigOtherProcess", "parse success config:", aVarB);
        if (aVarB != null) {
            this.f5087a = aVarB;
        }
    }

    public com.bytedance.sdk.adtnc.a.a c() {
        return this.f5087a;
    }
}
