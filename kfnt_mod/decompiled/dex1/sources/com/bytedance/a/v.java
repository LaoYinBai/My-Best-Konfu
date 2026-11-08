package com.bytedance.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class v extends af {

    /* renamed from: a, reason: collision with root package name */
    public Context f3550a;

    public v(Context context) {
        super(true, false);
        this.f3550a = context;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException {
        try {
            Bundle bundle = this.f3550a.getPackageManager().getApplicationInfo(this.f3550a.getPackageName(), 128).metaData;
            if (bundle == null || TextUtils.isEmpty("UMENG_APPKEY")) {
                return true;
            }
            jSONObject.put("appkey", bundle.getString("UMENG_APPKEY"));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            de.a("", e);
            return true;
        }
    }
}
