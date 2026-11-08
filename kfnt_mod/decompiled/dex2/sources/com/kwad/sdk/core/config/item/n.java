package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n extends b<String> {
    private static volatile String[] a;

    public n(String str, String str2) {
        super(str, str2);
        a = null;
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a = str.split(",");
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences.Editor editor) {
        editor.putString(b(), a());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(b(), c());
        a((n) string);
        a(string);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            a((n) c());
            return;
        }
        String strOptString = jSONObject.optString(b(), c());
        a((n) strOptString);
        a(strOptString);
    }

    public boolean a(long j) {
        if (a == null) {
            return false;
        }
        for (String str : a) {
            if (str != null && String.valueOf(j).equals(str.trim())) {
                return true;
            }
        }
        return false;
    }
}
