package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i extends b<a> {

    public static class a extends com.kwad.sdk.core.response.kwai.a {
        public int a = 180000;
        public int b = 90000;
    }

    public i(String str) {
        super(str, new a());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(SharedPreferences.Editor editor) {
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
    public void a(SharedPreferences sharedPreferences) {
        a aVarA = a();
        if (aVarA == null) {
            aVarA = new a();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(sharedPreferences.getString(b(), ""));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        if (jSONObject != null) {
            aVarA.parseJson(jSONObject);
        }
        a((i) aVarA);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(b())) == null) {
            a((i) c());
            return;
        }
        a aVar = new a();
        aVar.parseJson(jSONObjectOptJSONObject);
        a((i) aVar);
    }
}
