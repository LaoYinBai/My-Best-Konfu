package com.xiaomi.push;

import com.taobao.accs.common.Constants;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cz implements Comparable<cz> {
    protected int a;

    /* renamed from: a, reason: collision with other field name */
    private long f215a;

    /* renamed from: a, reason: collision with other field name */
    String f216a;

    /* renamed from: a, reason: collision with other field name */
    private final LinkedList<cp> f217a;

    public cz() {
        this(null, 0);
    }

    public cz(String str) {
        this(str, 0);
    }

    public cz(String str, int i) {
        this.f217a = new LinkedList<>();
        this.f215a = 0L;
        this.f216a = str;
        this.a = i;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(cz czVar) {
        if (czVar == null) {
            return 1;
        }
        return czVar.a - this.a;
    }

    public synchronized cz a(JSONObject jSONObject) {
        this.f215a = jSONObject.getLong("tt");
        this.a = jSONObject.getInt("wt");
        this.f216a = jSONObject.getString(Constants.KEY_HOST);
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f217a.add(new cp().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized JSONObject a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.f215a);
        jSONObject.put("wt", this.a);
        jSONObject.put(Constants.KEY_HOST, this.f216a);
        JSONArray jSONArray = new JSONArray();
        Iterator<cp> it = this.f217a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m172a());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    protected synchronized void a(cp cpVar) {
        if (cpVar != null) {
            this.f217a.add(cpVar);
            int iA = cpVar.a();
            if (iA > 0) {
                this.a += cpVar.a();
            } else {
                int i = 0;
                for (int size = this.f217a.size() - 1; size >= 0 && this.f217a.get(size).a() < 0; size--) {
                    i++;
                }
                this.a += iA * i;
            }
            if (this.f217a.size() > 30) {
                this.a -= this.f217a.remove().a();
            }
        }
    }

    public String toString() {
        return this.f216a + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + this.a;
    }
}
