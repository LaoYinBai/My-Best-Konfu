package com.qq.e.comm.managers.setting;

import com.qq.e.comm.constants.Constants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends b {
    a() {
    }

    a(String str) {
        super(str);
    }

    Object a(String str, String str2) {
        JSONObject jSONObjectOptJSONObject = this.a.optJSONObject(Constants.KEYS.PLACEMENTS);
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optJSONObject(str2) : null;
        if (jSONObjectOptJSONObject2 != null) {
            return jSONObjectOptJSONObject2.opt(str);
        }
        return null;
    }
}
