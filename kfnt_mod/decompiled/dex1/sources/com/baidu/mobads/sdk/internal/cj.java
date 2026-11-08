package com.baidu.mobads.sdk.internal;

import android.view.ViewGroup;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class cj implements o {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cg f3210a;

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(JSONObject jSONObject) {
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(JSONObject jSONObject) {
    }

    cj(cg cgVar) {
        this.f3210a = cgVar;
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3210a.g());
        map.put("interstitial_container", viewGroup);
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        this.f3210a.a(cg.q, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3210a.g());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3210a.h()));
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        map.put("novel_info", jSONObject);
        this.f3210a.a(cg.r, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup, JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3210a.g());
        map.put("banner_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3210a.h()));
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        map.put("novel_info", jSONObject);
        this.f3210a.a(cg.s, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, ViewGroup viewGroup2, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3210a.g());
        map.put("banner_container", viewGroup2);
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3210a.h()));
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        map.put("backgroundColor", Integer.valueOf(i));
        this.f3210a.a(cg.v, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strG = this.f3210a.g("get_cuid");
        String strG2 = this.f3210a.g("get_imei");
        String strG3 = this.f3210a.g("get_oaid");
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
        if (this.f3210a.G != null) {
            this.f3210a.G.onReadTime(j);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void b(ViewGroup viewGroup) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", viewGroup.getContext());
        map.put("banner_container", viewGroup);
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        this.f3210a.a(cg.u, map);
    }

    @Override // com.baidu.mobads.sdk.internal.o
    public void a(ViewGroup viewGroup, int i) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novel_activity", this.f3210a.g());
        map.put("interstitial_container", viewGroup);
        map.put("isnight", Boolean.valueOf(this.f3210a.h()));
        map.put("entry", Integer.valueOf(this.f3210a.C));
        map.put("channelId", Integer.valueOf(this.f3210a.D));
        map.put("novel_id", this.f3210a.E);
        map.put("count_down", Integer.valueOf(i));
        this.f3210a.a(cg.w, map);
    }
}
