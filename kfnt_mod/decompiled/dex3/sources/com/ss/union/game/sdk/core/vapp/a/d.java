package com.ss.union.game.sdk.core.vapp.a;

import com.ss.union.game.sdk.core.base.account.model.User;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public boolean a;
    public String b;
    public String c;
    public List<c> d = new ArrayList();

    public static d a(JSONObject jSONObject) {
        d dVar = new d();
        dVar.a = jSONObject.optBoolean("has_bound");
        dVar.b = jSONObject.optString(User.KEY_NICK_NAME);
        dVar.c = jSONObject.optString(User.KEY_AVATAR);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(com.ss.union.game.sdk.redemptionCode.a.n);
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    dVar.d.add(c.a(jSONObjectOptJSONObject));
                }
            }
        }
        return dVar;
    }
}
