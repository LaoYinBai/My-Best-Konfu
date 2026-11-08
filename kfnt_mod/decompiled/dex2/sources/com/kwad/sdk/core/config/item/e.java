package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends b<a> {
    private final String a;
    private String b;

    public static final class a implements com.kwad.sdk.core.b {
        public String a;
        public String b;
        public String c;

        a() {
            this.a = "";
            this.b = "";
            this.c = "";
        }

        a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.a = jSONObject.optString("iconUrl", this.a);
            this.b = jSONObject.optString("nightIconUrl", this.b);
            this.c = jSONObject.optString(CampaignEx.JSON_KEY_DESC, this.c);
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "icon", this.a);
            t.a(jSONObject, "nightIconUrl", this.b);
            t.a(jSONObject, CampaignEx.JSON_KEY_DESC, this.c);
            return jSONObject;
        }
    }

    public e(String str, String str2) {
        super(str, new a("", "", str2));
        this.a = str2;
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(SharedPreferences.Editor editor) {
        editor.putString(b(), this.b);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(SharedPreferences sharedPreferences) {
        this.b = sharedPreferences.getString(b(), null);
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.b);
            a aVar = new a();
            aVar.parseJson(jSONObject);
            a((e) aVar);
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(b());
        if (jSONObjectOptJSONObject == null) {
            return;
        }
        this.b = jSONObjectOptJSONObject.toString();
        a aVar = new a();
        aVar.parseJson(jSONObjectOptJSONObject);
        a((e) aVar);
    }
}
