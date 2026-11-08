package com.ss.union.game.sdk.core.base.account.model;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VisitorUser {
    private static final String a = "token_valid";
    public boolean tokenValid;
    public User user;

    public VisitorUser(User user, boolean z) {
        this.user = user;
        this.tokenValid = z;
    }

    public static VisitorUser fromJSON(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VisitorUser(User.parseUser(jSONObject), jSONObject.optBoolean(a));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.user != null) {
                jSONObject = this.user.toJson();
            }
            jSONObject.put(a, this.tokenValid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
