package com.bytedance.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bj extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3409a;
    public final be f;

    public bj(Context context, be beVar) {
        super(false, false);
        this.f3409a = context;
        this.f = beVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) {
        TelephonyManager telephonyManager = (TelephonyManager) this.f3409a.getSystemService("phone");
        if (telephonyManager != null) {
            be.a(jSONObject, DispatchConstants.CARRIER, telephonyManager.getNetworkOperatorName());
            be.a(jSONObject, "mcc_mnc", telephonyManager.getNetworkOperator());
        }
        be.a(jSONObject, "clientudid", ((ah) this.f.h).a());
        be.a(jSONObject, "openudid", ((ah) this.f.h).a(true));
        if (bo.a(this.f3409a)) {
            jSONObject.remove("google_aid");
        }
        return true;
    }
}
