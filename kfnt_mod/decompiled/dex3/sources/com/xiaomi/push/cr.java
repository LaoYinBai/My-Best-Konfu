package com.xiaomi.push;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cr {
    private String a;

    /* renamed from: a, reason: collision with other field name */
    private final ArrayList<cq> f201a = new ArrayList<>();

    public cr() {
    }

    public cr(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.a = str;
    }

    public synchronized cq a() {
        for (int size = this.f201a.size() - 1; size >= 0; size--) {
            cq cqVar = this.f201a.get(size);
            if (cqVar.m176a()) {
                cu.a().m186a(cqVar.a());
                return cqVar;
            }
        }
        return null;
    }

    public synchronized cr a(JSONObject jSONObject) {
        this.a = jSONObject.getString(Constants.KEY_HOST);
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f201a.add(new cq(this.a).a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m177a() {
        return this.a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public ArrayList<cq> m178a() {
        return this.f201a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized JSONObject m179a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put(Constants.KEY_HOST, this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator<cq> it = this.f201a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m174a());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public synchronized void a(cq cqVar) {
        int i = 0;
        while (true) {
            if (i >= this.f201a.size()) {
                break;
            }
            if (this.f201a.get(i).a(cqVar)) {
                this.f201a.set(i, cqVar);
                break;
            }
            i++;
        }
        if (i >= this.f201a.size()) {
            this.f201a.add(cqVar);
        }
    }

    public synchronized void a(boolean z) {
        ArrayList<cq> arrayList;
        for (int size = this.f201a.size() - 1; size >= 0; size--) {
            cq cqVar = this.f201a.get(size);
            if (z) {
                if (cqVar.c()) {
                    arrayList = this.f201a;
                    arrayList.remove(size);
                }
            } else if (!cqVar.b()) {
                arrayList = this.f201a;
                arrayList.remove(size);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("\n");
        Iterator<cq> it = this.f201a.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
