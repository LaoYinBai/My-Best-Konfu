package com.ss.union.game.sdk.vcenter.account;

import com.sigmob.sdk.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static String a(int i, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
            jSONObject.put("message", str);
            jSONObject.put(Constants.EXT, str2);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }
}
