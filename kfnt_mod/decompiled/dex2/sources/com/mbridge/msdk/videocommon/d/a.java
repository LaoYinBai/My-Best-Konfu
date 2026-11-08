package com.mbridge.msdk.videocommon.d;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.videocommon.b.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardSetting.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private Map<String, Integer> a;
    private Map<String, d> b;
    private long c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private int i = 0;
    private String j;

    public final String a() {
        return this.j;
    }

    public final long b() {
        return this.c * 1000;
    }

    public final void a(long j) {
        this.c = j;
    }

    public final long c() {
        return this.d * 1000;
    }

    public final void b(long j) {
        this.d = j;
    }

    public final long d() {
        return this.e * 1000;
    }

    public final void c(long j) {
        this.e = j;
    }

    public final long e() {
        return this.f;
    }

    public final void d(long j) {
        this.f = j;
    }

    public final long f() {
        return this.g;
    }

    public final void e(long j) {
        this.g = j;
    }

    public final long g() {
        return this.h;
    }

    public final Map<String, Integer> h() {
        if (this.a == null) {
            this.a = new HashMap();
            this.a.put("1", 1000);
            this.a.put("9", 1000);
            this.a.put("8", 1000);
        }
        return this.a;
    }

    public final void a(Map<String, Integer> map) {
        this.a = map;
    }

    public final Map<String, d> i() {
        return this.b;
    }

    public final void b(Map<String, d> map) {
        this.b = map;
    }

    public final void a(int i) {
        this.i = i;
    }

    public static a a(String str) {
        a aVar;
        a aVar2 = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                aVar = new a();
            } catch (Exception e) {
                e = e;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("caplist");
                aVar.j = jSONObject.optString("ab_id", "");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                    HashMap map = new HashMap();
                    Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                    while (itKeys != null && itKeys.hasNext()) {
                        String next = itKeys.next();
                        int iIntValue = Integer.valueOf(jSONObjectOptJSONObject.optInt(next, 1000)).intValue();
                        if (!TextUtils.isEmpty(next)) {
                            if (!TextUtils.isEmpty(next) && iIntValue == 0) {
                                map.put(next, 1000);
                            } else {
                                map.put(next, Integer.valueOf(iIntValue));
                            }
                        }
                    }
                    aVar.a = map;
                }
                aVar.b = d.a(jSONObject.optJSONArray("reward"));
                aVar.c = jSONObject.optLong("getpf", 43200L);
                aVar.d = jSONObject.optLong("ruct", 5400L);
                aVar.e = jSONObject.optLong(CampaignEx.JSON_KEY_PLCT, 3600L);
                aVar.f = jSONObject.optLong("dlct", 3600L);
                aVar.g = jSONObject.optLong("vcct", 5L);
                aVar.h = jSONObject.optLong("current_time");
                return aVar;
            } catch (Exception e2) {
                e = e2;
                aVar2 = aVar;
                e.printStackTrace();
                return aVar2;
            }
        }
        return aVar2;
    }

    public final JSONObject j() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.a != null && this.a.size() > 0) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Map.Entry<String, Integer> entry : this.a.entrySet()) {
                        jSONObject2.put(entry.getKey(), entry.getValue().intValue());
                    }
                    jSONObject.put("caplist", jSONObject2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.b != null && this.b.size() > 0) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, d> entry2 : this.b.entrySet()) {
                        JSONObject jSONObject3 = new JSONObject();
                        String key = entry2.getKey();
                        d value = entry2.getValue();
                        if (value != null) {
                            jSONObject3.put("name", value.a());
                            jSONObject3.put(RewardPlus.AMOUNT, value.b());
                            jSONObject3.put("id", key);
                        }
                        jSONArray.put(jSONObject3);
                    }
                    jSONObject.put("reward", jSONArray);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            jSONObject.put("getpf", this.c);
            jSONObject.put("ruct", this.d);
            jSONObject.put(CampaignEx.JSON_KEY_PLCT, this.e);
            jSONObject.put("dlct", this.f);
            jSONObject.put("vcct", this.g);
            jSONObject.put("current_time", this.h);
            jSONObject.put("isDefault", this.i);
            return jSONObject;
        } catch (Exception e3) {
            e3.printStackTrace();
            return jSONObject;
        }
    }
}
