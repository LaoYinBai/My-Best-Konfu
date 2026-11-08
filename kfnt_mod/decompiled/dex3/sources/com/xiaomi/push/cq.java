package com.xiaomi.push;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cq {

    /* renamed from: a, reason: collision with other field name */
    private long f197a;

    /* renamed from: a, reason: collision with other field name */
    public String f198a;

    /* renamed from: b, reason: collision with other field name */
    public String f200b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    protected String h;
    private String i;

    /* renamed from: a, reason: collision with other field name */
    private ArrayList<cz> f199a = new ArrayList<>();
    private double a = 0.1d;
    private String j = "s.mi1.cc";
    private long b = 86400000;

    public cq(String str) {
        this.f198a = "";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f197a = System.currentTimeMillis();
        this.f199a.add(new cz(str, -1));
        this.f198a = cu.m182a();
        this.f200b = str;
    }

    private synchronized void c(String str) {
        Iterator<cz> it = this.f199a.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().f216a, str)) {
                it.remove();
            }
        }
    }

    public synchronized cq a(JSONObject jSONObject) {
        this.f198a = jSONObject.optString("net");
        this.b = jSONObject.getLong("ttl");
        this.a = jSONObject.getDouble("pct");
        this.f197a = jSONObject.getLong("ts");
        this.d = jSONObject.optString("city");
        this.c = jSONObject.optString("prv");
        this.g = jSONObject.optString("cty");
        this.e = jSONObject.optString("isp");
        this.f = jSONObject.optString("ip");
        this.f200b = jSONObject.optString(Constants.KEY_HOST);
        this.h = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            a(new cz().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized String a() {
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        if (TextUtils.isEmpty(this.e)) {
            return "hardcode_isp";
        }
        this.i = bm.a(new String[]{this.e, this.c, this.d, this.g, this.f}, "_");
        return this.i;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized ArrayList<String> m173a() {
        return a(false);
    }

    public ArrayList<String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (!TextUtils.equals(url.getHost(), this.f200b)) {
            throw new IllegalArgumentException("the url is not supported by the fallback");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = a(true).iterator();
        while (it.hasNext()) {
            cs csVarA = cs.a(it.next(), url.getPort());
            arrayList.add(new URL(url.getProtocol(), csVarA.m181a(), csVarA.a(), url.getFile()).toString());
        }
        return arrayList;
    }

    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        int iIndexOf;
        cz[] czVarArr = new cz[this.f199a.size()];
        this.f199a.toArray(czVarArr);
        Arrays.sort(czVarArr);
        arrayList = new ArrayList<>();
        int length = czVarArr.length;
        for (int i = 0; i < length; i++) {
            cz czVar = czVarArr[i];
            String strSubstring = (z || (iIndexOf = czVar.f216a.indexOf(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR)) == -1) ? czVar.f216a : czVar.f216a.substring(0, iIndexOf);
            arrayList.add(strSubstring);
        }
        return arrayList;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized JSONObject m174a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.f198a);
        jSONObject.put("ttl", this.b);
        jSONObject.put("pct", this.a);
        jSONObject.put("ts", this.f197a);
        jSONObject.put("city", this.d);
        jSONObject.put("prv", this.c);
        jSONObject.put("cty", this.g);
        jSONObject.put("isp", this.e);
        jSONObject.put("ip", this.f);
        jSONObject.put(Constants.KEY_HOST, this.f200b);
        jSONObject.put("xf", this.h);
        JSONArray jSONArray = new JSONArray();
        Iterator<cz> it = this.f199a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public void a(double d) {
        this.a = d;
    }

    public void a(long j) {
        if (j > 0) {
            this.b = j;
            return;
        }
        throw new IllegalArgumentException("the duration is invalid " + j);
    }

    synchronized void a(cz czVar) {
        c(czVar.f216a);
        this.f199a.add(czVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m175a(String str) {
        a(new cz(str));
    }

    public void a(String str, int i, long j, long j2, Exception exc) {
        a(str, new cp(i, j, j2, exc));
    }

    public void a(String str, long j, long j2) {
        try {
            b(new URL(str).getHost(), j, j2);
        } catch (MalformedURLException unused) {
        }
    }

    public void a(String str, long j, long j2, Exception exc) {
        try {
            b(new URL(str).getHost(), j, j2, exc);
        } catch (MalformedURLException unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
    
        r1.a(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(java.lang.String r4, com.xiaomi.push.cp r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.ArrayList<com.xiaomi.push.cz> r0 = r3.f199a     // Catch: java.lang.Throwable -> L20
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L20
        L7:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L1e
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L20
            com.xiaomi.push.cz r1 = (com.xiaomi.push.cz) r1     // Catch: java.lang.Throwable -> L20
            java.lang.String r2 = r1.f216a     // Catch: java.lang.Throwable -> L20
            boolean r2 = android.text.TextUtils.equals(r4, r2)     // Catch: java.lang.Throwable -> L20
            if (r2 == 0) goto L7
            r1.a(r5)     // Catch: java.lang.Throwable -> L20
        L1e:
            monitor-exit(r3)
            return
        L20:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.cq.a(java.lang.String, com.xiaomi.push.cp):void");
    }

    public synchronized void a(String[] strArr) {
        int i;
        int size = this.f199a.size() - 1;
        while (true) {
            i = 0;
            if (size < 0) {
                break;
            }
            int length = strArr.length;
            while (true) {
                if (i < length) {
                    if (TextUtils.equals(this.f199a.get(size).f216a, strArr[i])) {
                        this.f199a.remove(size);
                        break;
                    }
                    i++;
                }
            }
            size--;
        }
        Iterator<cz> it = this.f199a.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            cz next = it.next();
            if (next.a > i2) {
                i2 = next.a;
            }
        }
        while (i < strArr.length) {
            a(new cz(strArr[i], (strArr.length + i2) - i));
            i++;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m176a() {
        return TextUtils.equals(this.f198a, cu.m182a());
    }

    public boolean a(cq cqVar) {
        return TextUtils.equals(this.f198a, cqVar.f198a);
    }

    public void b(String str) {
        this.j = str;
    }

    public void b(String str, long j, long j2) {
        a(str, 0, j, j2, null);
    }

    public void b(String str, long j, long j2, Exception exc) {
        a(str, -1, j, j2, exc);
    }

    public boolean b() {
        return System.currentTimeMillis() - this.f197a < this.b;
    }

    boolean c() {
        long j = 864000000 < this.b ? this.b : 864000000L;
        long jCurrentTimeMillis = System.currentTimeMillis();
        return jCurrentTimeMillis - this.f197a > j || (jCurrentTimeMillis - this.f197a > this.b && this.f198a.startsWith("WIFI-"));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f198a);
        sb.append("\n");
        sb.append(a());
        Iterator<cz> it = this.f199a.iterator();
        while (it.hasNext()) {
            cz next = it.next();
            sb.append("\n");
            sb.append(next.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
