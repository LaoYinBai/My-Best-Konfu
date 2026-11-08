package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class InsertScreenConfigItem extends b<InsertScreenConfig> {

    public static class InsertScreenConfig extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        private static final long serialVersionUID = -8956767980880231273L;
        public int firstPosition = 1;
        public int interval = 3;
        public int threshold = 2;
        public int preRequestCount = 2;
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
        InsertScreenConfig insertScreenConfigA = a();
        if (insertScreenConfigA == null) {
            insertScreenConfigA = new InsertScreenConfig();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(sharedPreferences.getString(b(), ""));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        if (jSONObject != null) {
            insertScreenConfigA.parseJson(jSONObject);
        }
        a((InsertScreenConfigItem) insertScreenConfigA);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(b())) == null) {
            a((InsertScreenConfigItem) c());
            return;
        }
        InsertScreenConfig insertScreenConfig = new InsertScreenConfig();
        insertScreenConfig.parseJson(jSONObjectOptJSONObject);
        a((InsertScreenConfigItem) insertScreenConfig);
    }
}
