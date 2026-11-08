package com.mbridge.msdk.rover;

import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import org.json.JSONObject;

/* compiled from: RoverResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class h extends com.mbridge.msdk.foundation.same.net.f<JSONObject> {
    private static final String a = "h";

    public abstract void a(RoverCampaignUnit roverCampaignUnit);

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        if (kVar != null) {
            a(RoverCampaignUnit.parseCampaignUnit(kVar.a));
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
    }
}
