package com.bytedance.bdturing.c;

import com.baidu.mobads.sdk.api.IAdInterListener;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements h {
    private String f = h.f3783a;

    @Override // com.bytedance.bdturing.c.h
    public String a(com.bytedance.bdturing.f fVar, i iVar) {
        try {
            JSONObject jSONObject = new JSONObject(iVar.t);
            fVar.a(jSONObject.getInt(IAdInterListener.AdReqParam.WIDTH), jSONObject.getInt(IAdInterListener.AdReqParam.HEIGHT));
            return null;
        } catch (JSONException e) {
            com.bytedance.bdturing.g.a(e);
            return null;
        }
    }

    @Override // com.bytedance.bdturing.c.h
    public String a() {
        return this.f;
    }
}
