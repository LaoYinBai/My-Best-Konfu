package com.baidu.mobads.sdk.internal;

import android.view.ViewGroup;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ab implements o {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ y f3093a;

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(JSONObject jSONObject) {
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(JSONObject jSONObject) {
    }

    ab(y yVar) {
        this.f3093a = yVar;
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3093a.g());
        map.put("interstitial_container", viewGroup);
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        this.f3093a.a(cg.q, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3093a.g());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3093a.h()));
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        map.put("novel_info", jSONObject);
        this.f3093a.a(cg.r, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3093a.g());
        map.put("banner_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3093a.h()));
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        map.put("novel_info", jSONObject);
        this.f3093a.a(cg.s, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, ViewGroup viewGroup2, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3093a.g());
        map.put("banner_container", viewGroup2);
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3093a.h()));
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        map.put("backgroundColor", Integer.valueOf(i));
        this.f3093a.a(cg.v, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strG = this.f3093a.g("get_cuid");
        String strG2 = this.f3093a.g("get_imei");
        String strG3 = this.f3093a.g("get_oaid");
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
        this.f3093a.h.a("单次阅读器打开时长 = " + j);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", viewGroup.getContext());
        map.put("banner_container", viewGroup);
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        this.f3093a.a(cg.u, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3093a.g());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3093a.h()));
        map.put("entry", 2);
        map.put("channelId", Integer.valueOf(this.f3093a.q[0]));
        map.put("count_down", Integer.valueOf(i));
        this.f3093a.a(cg.w, map);
    }
}
