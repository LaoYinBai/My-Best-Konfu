package com.baidu.mobads.sdk.internal;

import android.view.ViewGroup;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class cf implements o {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3205a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3206b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3207c;
    final /* synthetic */ cb d;

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(JSONObject jSONObject) {
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(JSONObject jSONObject) {
    }

    cf(cb cbVar, int i, int i2, String str) {
        this.d = cbVar;
        this.f3205a = i;
        this.f3206b = i2;
        this.f3207c = str;
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.d.f());
        map.put("interstitial_container", viewGroup);
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        this.d.a(cg.q, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.d.f());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.d.g()));
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        map.put("novel_info", jSONObject);
        this.d.a(cg.r, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.d.f());
        map.put("banner_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.d.g()));
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        map.put("novel_info", jSONObject);
        this.d.a(cg.s, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, ViewGroup viewGroup2, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.d.f());
        map.put("banner_container", viewGroup2);
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.d.g()));
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        map.put("backgroundColor", Integer.valueOf(i));
        this.d.a(cg.v, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strG = this.d.g("get_cuid");
        String strG2 = this.d.g("get_imei");
        String strG3 = this.d.g("get_oaid");
        try {
            jSONObject.put("cuid", strG);
            jSONObject.put("imei", strG2);
            jSONObject.put("oaid", strG3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(long j) {
        this.d.h.a("单次阅读器打开时长 = " + j);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", viewGroup.getContext());
        map.put("banner_container", viewGroup);
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        this.d.a(cg.u, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.d.f());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.d.g()));
        map.put("entry", Integer.valueOf(this.f3205a));
        map.put("channelId", Integer.valueOf(this.f3206b));
        map.put("novel_id", this.f3207c);
        map.put("count_down", Integer.valueOf(i));
        this.d.a(cg.w, map);
    }
}
