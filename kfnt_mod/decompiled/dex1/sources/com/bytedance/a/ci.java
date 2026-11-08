package com.bytedance.a;

import android.annotation.SuppressLint;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ci extends af {

    /* renamed from: a, reason: collision with root package name */
    public Context f3471a;

    public ci(Context context) {
        super(true, false);
        this.f3471a = context;
    }

    @Override // com.bytedance.a.af
    @SuppressLint({"MissingPermission"})
    public boolean a(JSONObject jSONObject) throws JSONException {
        boolean zA = bo.a(this.f3471a);
        if (zA) {
            jSONObject.put("new_user_mode", 1);
        }
        if (de.f3494c || zA) {
            de.a("new user mode = " + zA, null);
        }
        return true;
    }
}
