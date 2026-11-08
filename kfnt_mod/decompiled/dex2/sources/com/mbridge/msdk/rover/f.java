package com.mbridge.msdk.rover;

import com.mbridge.msdk.foundation.same.net.k;
import org.json.JSONObject;

/* compiled from: RoverReportResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class f extends com.mbridge.msdk.foundation.same.net.f<JSONObject> {
    public abstract void a(String str);

    public abstract void e();

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        e();
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        a(aVar.a + "");
    }
}
