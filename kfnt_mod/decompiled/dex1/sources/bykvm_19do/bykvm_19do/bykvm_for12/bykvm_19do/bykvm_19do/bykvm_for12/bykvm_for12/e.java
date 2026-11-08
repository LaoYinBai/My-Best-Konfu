package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f1429a;

    /* renamed from: b, reason: collision with root package name */
    private d f1430b = new d();

    /* renamed from: c, reason: collision with root package name */
    private boolean f1431c;
    private int d;

    public e(Context context, boolean z, int i) {
        this.f1431c = true;
        this.f1429a = context;
        this.f1431c = z;
        this.d = i;
    }

    public void a(JSONObject jSONObject) {
        if (!this.f1431c) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", "handleConfigChanged: no mainProc");
            return;
        }
        h.a().a(this.d).h();
        try {
            boolean z = jSONObject.optInt("ttnet_url_dispatcher_enabled", 0) > 0;
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ttnet_dispatch_actions");
            JSONObject jSONObject2 = null;
            if (h.a().a(this.d).g() && z && jSONArrayOptJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject3 = ((JSONObject) jSONArrayOptJSONArray.get(i)).getJSONObject("param");
                    if (jSONObject3.optString("service_name", "").equals("idc_selection")) {
                        arrayList.add(jSONObject3.getJSONObject("strategy_info"));
                    }
                }
                if (!arrayList.isEmpty()) {
                    jSONObject2 = new JSONObject();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        JSONObject jSONObject4 = (JSONObject) it.next();
                        Iterator<String> itKeys = jSONObject4.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            jSONObject2.put(next, jSONObject4.getString(next));
                        }
                    }
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("tnc_config");
            if (jSONObjectOptJSONObject == null && jSONObject2 == null) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.b("TNCConfigHandler", " tnc host_replace_map config is null");
            } else if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
                jSONObjectOptJSONObject.put("host_replace_map", jSONObject2);
            } else if (jSONObject2 != null) {
                jSONObjectOptJSONObject.put("host_replace_map", jSONObject2);
            }
            d dVarB = b(jSONObjectOptJSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append("handleConfigChanged, newConfig: ");
            sb.append(dVarB == null ? "null" : dVarB.toString());
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", sb.toString());
            if (dVarB == null) {
                synchronized (e) {
                    this.f1429a.getSharedPreferences(h.a().a(this.d).f(), 0).edit().putString("tnc_config_str", "").apply();
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.c.a(this.f1429a, 1, "", this.d);
                }
            } else {
                this.f1430b = dVarB;
                String string = jSONObjectOptJSONObject.toString();
                synchronized (e) {
                    this.f1429a.getSharedPreferences(h.a().a(this.d).f(), 0).edit().putString("tnc_config_str", string).apply();
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.c.a(this.f1429a, 1, string, this.d);
                }
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                this.f1430b = new d();
                synchronized (e) {
                    this.f1429a.getSharedPreferences(h.a().a(this.d).f(), 0).edit().putString("tnc_config_str", "").apply();
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.c.a(this.f1429a, 1, "", this.d);
                }
            } catch (Throwable th2) {
                synchronized (e) {
                    this.f1429a.getSharedPreferences(h.a().a(this.d).f(), 0).edit().putString("tnc_config_str", "").apply();
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.c.a(this.f1429a, 1, "", this.d);
                    throw th2;
                }
            }
        }
    }

    public void b() {
        if (this.f1431c) {
            String string = this.f1429a.getSharedPreferences(h.a().a(this.d).f(), 0).getString("tnc_config_str", null);
            if (TextUtils.isEmpty(string)) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", "loadLocalConfig: no existed");
                return;
            }
            try {
                d dVarB = b(new JSONObject(string));
                if (dVarB != null) {
                    this.f1430b = dVarB;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("loadLocalConfig: ");
                sb.append(dVarB == null ? "null" : dVarB.toString());
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", sb.toString());
            } catch (Throwable th) {
                th.printStackTrace();
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", "loadLocalConfig: except: " + th.getMessage());
            }
        }
    }

    public void c() {
        try {
            String strA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.c.a(this.f1429a, 1, this.d);
            if (TextUtils.isEmpty(strA)) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", "loadLocalConfigForOtherProcess, data empty");
                return;
            }
            d dVarB = b(new JSONObject(strA));
            StringBuilder sb = new StringBuilder();
            sb.append("loadLocalConfigForOtherProcess, config: ");
            sb.append(dVarB == null ? "null" : dVarB.toString());
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", sb.toString());
            if (dVarB != null) {
                this.f1430b = dVarB;
            }
        } catch (Throwable th) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCConfigHandler", "loadLocalConfigForOtherProcess, except: " + th.getMessage());
        }
    }

    private d b(JSONObject jSONObject) {
        try {
            d dVar = new d();
            if (jSONObject.has("local_enable")) {
                dVar.f1426a = jSONObject.getInt("local_enable") != 0;
            }
            if (jSONObject.has("probe_enable")) {
                dVar.f1427b = jSONObject.getInt("probe_enable") != 0;
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
                dVar.f1428c = map;
            } else {
                dVar.f1428c = null;
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
                dVar.d = map2;
            } else {
                dVar.d = null;
            }
            dVar.e = jSONObject.optInt("req_to_cnt", dVar.e);
            dVar.f = jSONObject.optInt("req_to_api_cnt", dVar.f);
            dVar.g = jSONObject.optInt("req_to_ip_cnt", dVar.g);
            dVar.h = jSONObject.optInt("req_err_cnt", dVar.h);
            dVar.i = jSONObject.optInt("req_err_api_cnt", dVar.i);
            dVar.j = jSONObject.optInt("req_err_ip_cnt", dVar.j);
            dVar.k = jSONObject.optInt("update_interval", dVar.k);
            dVar.l = jSONObject.optInt("update_random_range", dVar.l);
            dVar.m = jSONObject.optString("http_code_black", dVar.m);
            return dVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public d a() {
        return this.f1430b;
    }
}
