package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AvatarGuiderConfigItem extends b<AvatarGuiderConfig> {

    public static class AvatarGuiderConfig extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        public int showByPlayRate;
        public int showMaxTimes;
        public int showTimeLength;
    }

    public AvatarGuiderConfigItem() {
        super("avatarGuiderConfig", null);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences.Editor editor) {
        String strB;
        String string;
        if (a() == null || a().toJson() == null) {
            strB = b();
            string = "";
        } else {
            strB = b();
            string = a().toJson().toString();
        }
        editor.putString(strB, string);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences sharedPreferences) {
        AvatarGuiderConfig avatarGuiderConfigA = a();
        if (avatarGuiderConfigA == null) {
            avatarGuiderConfigA = new AvatarGuiderConfig();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(sharedPreferences.getString(b(), ""));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        if (jSONObject != null) {
            avatarGuiderConfigA.parseJson(jSONObject);
        }
        a((AvatarGuiderConfigItem) avatarGuiderConfigA);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(b())) == null) {
            com.kwad.sdk.core.d.a.a("AvatarGuiderConfigItem", "AvatarGuiderConfigItem = getDefault");
            a((AvatarGuiderConfigItem) c());
            return;
        }
        AvatarGuiderConfig avatarGuiderConfig = new AvatarGuiderConfig();
        avatarGuiderConfig.parseJson(jSONObjectOptJSONObject);
        com.kwad.sdk.core.d.a.a("AvatarGuiderConfigItem", "AvatarGuiderConfigItem = " + avatarGuiderConfig.toJson().toString());
        a((AvatarGuiderConfigItem) avatarGuiderConfig);
    }
}
