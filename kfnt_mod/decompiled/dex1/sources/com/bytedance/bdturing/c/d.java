package com.bytedance.bdturing.c;

import com.bytedance.bdturing.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements h {
    private String f = h.d;

    @Override // com.bytedance.bdturing.c.h
    public String a(com.bytedance.bdturing.f fVar, i iVar) {
        JSONArray jSONArrayOptJSONArray;
        fVar.b();
        try {
            jSONArrayOptJSONArray = new JSONObject(iVar.t).optJSONObject("log").optJSONArray("data");
        } catch (NullPointerException e) {
            com.bytedance.bdturing.g.a(e);
            jSONArrayOptJSONArray = null;
            j.a().a(6, jSONArrayOptJSONArray);
            return null;
        } catch (JSONException e2) {
            com.bytedance.bdturing.g.a(e2);
            jSONArrayOptJSONArray = null;
            j.a().a(6, jSONArrayOptJSONArray);
            return null;
        }
        j.a().a(6, jSONArrayOptJSONArray);
        return null;
    }

    @Override // com.bytedance.bdturing.c.h
    public String a() {
        return this.f;
    }
}
