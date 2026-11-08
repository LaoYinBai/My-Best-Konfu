package com.alibaba.sdk.android.httpdns;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    private String[] f2365a;

    /* renamed from: b, reason: collision with root package name */
    private long f2366b;

    /* renamed from: c, reason: collision with root package name */
    private long f2367c;
    private String hostName;

    e(com.alibaba.sdk.android.httpdns.b.e eVar) {
        this.hostName = eVar.h;
        this.f2367c = com.alibaba.sdk.android.httpdns.b.c.a(eVar.j);
        this.f2366b = -1000L;
        if (eVar.f2328a != null && eVar.f2328a.size() > 0) {
            int size = eVar.f2328a.size();
            this.f2365a = new String[size];
            for (int i = 0; i < size; i++) {
                this.f2365a[i] = eVar.f2328a.get(i).k;
            }
        }
        if (com.alibaba.sdk.android.httpdns.net64.a.a().m26a()) {
            ArrayList arrayList = new ArrayList();
            if (eVar.f2329b != null && eVar.f2329b.size() > 0) {
                for (int i2 = 0; i2 < eVar.f2329b.size(); i2++) {
                    arrayList.add(eVar.f2329b.get(i2).k);
                }
            }
            com.alibaba.sdk.android.httpdns.net64.a.a().a(this.hostName, arrayList);
        }
    }

    e(String str) throws JSONException {
        JSONArray jSONArray;
        JSONObject jSONObject = new JSONObject(str);
        this.hostName = jSONObject.getString(com.alipay.sdk.cons.c.f);
        try {
            if (jSONObject.has("ips")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("ips");
                int length = jSONArray2.length();
                this.f2365a = new String[length];
                for (int i = 0; i < length; i++) {
                    this.f2365a[i] = jSONArray2.getString(i);
                }
            }
            if (com.alibaba.sdk.android.httpdns.net64.a.a().m26a() && jSONObject.has("ipsv6") && (jSONArray = jSONObject.getJSONArray("ipsv6")) != null) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.getString(i2));
                }
                com.alibaba.sdk.android.httpdns.net64.a.a().a(this.hostName, arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f2366b = jSONObject.getLong("ttl");
        this.f2367c = System.currentTimeMillis() / 1000;
    }

    e(String str, String[] strArr, long j, long j2) {
        this.hostName = str;
        this.f2365a = strArr;
        this.f2366b = j;
        this.f2367c = j2;
    }

    long a() {
        return this.f2366b;
    }

    /* renamed from: a, reason: collision with other method in class */
    com.alibaba.sdk.android.httpdns.b.e m23a() {
        List<String> listA;
        com.alibaba.sdk.android.httpdns.b.e eVar = new com.alibaba.sdk.android.httpdns.b.e();
        eVar.h = this.hostName;
        eVar.j = String.valueOf(this.f2367c);
        eVar.i = com.alibaba.sdk.android.httpdns.b.b.g();
        if (this.f2365a != null && this.f2365a.length > 0) {
            eVar.f2328a = new ArrayList<>();
            for (String str : this.f2365a) {
                com.alibaba.sdk.android.httpdns.b.g gVar = new com.alibaba.sdk.android.httpdns.b.g();
                gVar.k = str;
                gVar.l = String.valueOf(this.f2366b);
                eVar.f2328a.add(gVar);
            }
        }
        if (com.alibaba.sdk.android.httpdns.net64.a.a().m26a() && (listA = com.alibaba.sdk.android.httpdns.net64.a.a().a(this.hostName)) != null && listA.size() > 0) {
            eVar.f2329b = new ArrayList<>();
            for (String str2 : listA) {
                com.alibaba.sdk.android.httpdns.b.g gVar2 = new com.alibaba.sdk.android.httpdns.b.g();
                gVar2.k = str2;
                gVar2.l = String.valueOf(this.f2366b);
                eVar.f2329b.add(gVar2);
            }
        }
        return eVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    String[] m24a() {
        return this.f2365a;
    }

    long b() {
        return this.f2367c;
    }

    /* renamed from: b, reason: collision with other method in class */
    boolean m25b() {
        return b() + a() < System.currentTimeMillis() / 1000 || c();
    }

    boolean c() {
        return a() == -1000;
    }

    public String toString() {
        String str = "host: " + this.hostName + " ip cnt: " + this.f2365a.length + " ttl: " + this.f2366b;
        for (int i = 0; i < this.f2365a.length; i++) {
            str = str + "\n ip: " + this.f2365a[i];
        }
        return str;
    }
}
