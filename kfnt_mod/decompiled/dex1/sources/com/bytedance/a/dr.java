package com.bytedance.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dr extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3513a;

    public dr(Context context) {
        super(true, false);
        this.f3513a = context;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) {
        be.a(jSONObject, "sim_region", ((TelephonyManager) this.f3513a.getSystemService("phone")).getSimCountryIso());
        return true;
    }
}
