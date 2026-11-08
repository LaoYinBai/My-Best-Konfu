package com.mbridge.msdk.foundation.same.net.h;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import org.json.JSONObject;

/* compiled from: CommonMBListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c extends com.mbridge.msdk.foundation.same.net.f<JSONObject> {
    private static final String a = "c";

    public abstract void a(String str);

    public abstract void a(JSONObject jSONObject);

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        if (kVar != null) {
            s.b(a, "content = " + kVar.a);
            int iOptInt = kVar.a.optInt("status");
            if (iOptInt == 1 || iOptInt == 200) {
                a(kVar.a.optJSONObject("data"));
            } else {
                a(kVar.a.optString(RemoteMessageConst.MessageBody.MSG));
            }
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
        a(com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a));
    }
}
