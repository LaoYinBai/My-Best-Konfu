package com.mbridge.msdk.mbbid.common.b;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import java.util.List;
import org.json.JSONObject;

/* compiled from: BidResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b extends f<JSONObject> {
    private static final String a = "b";

    public abstract void a(int i, String str);

    public abstract void a(BidResponsedEx bidResponsedEx);

    public b(String str, String str2) {
        this.d = str2;
        this.e = str;
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        super.a(kVar);
        if (kVar == null || kVar.c == null) {
            return;
        }
        List<com.mbridge.msdk.foundation.same.net.c.b> list = kVar.c.d;
        JSONObject jSONObject = kVar.a;
        int iOptInt = jSONObject.optInt("status");
        if (200 == iOptInt) {
            BidResponsedEx bidResponsedEx = BidResponsedEx.parseBidResponsedEx(jSONObject.optJSONObject("data"), this.d);
            if (bidResponsedEx != null) {
                a(bidResponsedEx);
                return;
            } else {
                a(iOptInt, jSONObject.optString(RemoteMessageConst.MessageBody.MSG));
                return;
            }
        }
        a(iOptInt, jSONObject.optString(RemoteMessageConst.MessageBody.MSG));
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
        a(aVar.a, com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a));
    }
}
