package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends b<a> {

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public String a = "";
        public String b = "";
    }

    public h() {
        super("idMapping", new a());
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
        String string = sharedPreferences.getString(b(), "");
        if (!TextUtils.isEmpty(string)) {
            try {
                aVarA.parseJson(new JSONObject(string));
            } catch (JSONException e) {
                com.kwad.sdk.core.d.a.b(e);
            }
        }
        a((h) aVarA);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(b());
        if (jSONObjectOptJSONObject == null) {
            return;
        }
        a aVar = new a();
        aVar.parseJson(jSONObjectOptJSONObject);
        a((h) aVar);
    }

    public String d() {
        a aVarA = a();
        return (aVarA == null || TextUtils.isEmpty(aVarA.a)) ? "" : aVarA.a;
    }

    public String e() {
        a aVarA = a();
        return (aVarA == null || TextUtils.isEmpty(aVarA.b)) ? "" : aVarA.b;
    }
}
