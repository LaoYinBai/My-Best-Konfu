package com.bytedance.bdturing.c;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements h {
    private String f = h.e;

    @Override // com.bytedance.bdturing.c.h
    public String a(com.bytedance.bdturing.f fVar, i iVar) {
        String strOptString;
        String strOptString2;
        String strOptString3;
        int iOptInt;
        int i;
        String strOptString4;
        String str;
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(iVar.t);
            iOptInt = jSONObject.optInt("result");
            try {
                strOptString = jSONObject.optString("mode");
            } catch (JSONException e) {
                e = e;
                strOptString = null;
                strOptString2 = null;
            }
        } catch (JSONException e2) {
            e = e2;
            strOptString = null;
            strOptString2 = null;
            strOptString3 = null;
            iOptInt = 0;
        }
        try {
            strOptString2 = jSONObject.optString("type");
            try {
                strOptString3 = jSONObject.optString("token");
                try {
                    i = iOptInt;
                    str = strOptString;
                    strOptString4 = jSONObject.optString("mobile");
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                    i = iOptInt;
                    strOptString4 = null;
                    str = strOptString;
                    fVar.a(i, str, strOptString2, strOptString3, strOptString4);
                    return null;
                }
            } catch (JSONException e4) {
                e = e4;
                strOptString3 = null;
            }
        } catch (JSONException e5) {
            e = e5;
            strOptString2 = null;
            strOptString3 = strOptString2;
            e.printStackTrace();
            i = iOptInt;
            strOptString4 = null;
            str = strOptString;
            fVar.a(i, str, strOptString2, strOptString3, strOptString4);
            return null;
        }
        fVar.a(i, str, strOptString2, strOptString3, strOptString4);
        return null;
    }

    @Override // com.bytedance.bdturing.c.h
    public String a() {
        return this.f;
    }
}
