package com.bytedance.a;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ap extends af {

    /* renamed from: a, reason: collision with root package name */
    public final az f3365a;

    public ap(az azVar) {
        super(true, false);
        this.f3365a = azVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException {
        String strA = o.a(this.f3365a.e);
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        jSONObject.put("cdid", strA);
        return true;
    }
}
