package com.bytedance.sdk.b.a;

import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public String f5128a;

    /* renamed from: b, reason: collision with root package name */
    public int f5129b;

    /* renamed from: c, reason: collision with root package name */
    public List<InetAddress> f5130c;
    private long d = System.currentTimeMillis();

    private a() {
    }

    public boolean a() {
        return System.currentTimeMillis() - this.d > ((long) (this.f5129b * 1000));
    }

    public static a a(String str, int i, List<InetAddress> list) {
        a aVar = new a();
        aVar.f5128a = str;
        aVar.f5129b = i;
        aVar.f5130c = list;
        return aVar;
    }

    public static a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString(c.f);
        int iOptInt = jSONObject.optInt("ttl", -1);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ips");
        if (TextUtils.isEmpty(strOptString) || jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return null;
        }
        a aVar = new a();
        aVar.f5128a = strOptString;
        aVar.f5129b = iOptInt;
        aVar.d = jSONObject.optLong("updateTime", aVar.d);
        aVar.f5130c = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString2 = jSONArrayOptJSONArray.optString(i);
            if (com.bytedance.sdk.b.f.c.a(strOptString2)) {
                try {
                    aVar.f5130c.add(InetAddress.getByName(strOptString2));
                } catch (Throwable unused) {
                }
            }
        }
        if (aVar.f5130c.size() <= 0) {
            return null;
        }
        return aVar;
    }

    public String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<InetAddress> it = this.f5130c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getHostAddress());
            }
            jSONObject.put(c.f, this.f5128a);
            jSONObject.put("ips", jSONArray);
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "DNSConfig{host='" + this.f5128a + "', ttl=" + this.f5129b + ", ips=" + this.f5130c + '}';
    }
}
