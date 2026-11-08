package com.mbridge.msdk.c.b;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ShortCutsResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b extends f<JSONObject> {
    private static final String a = "b";
    private long b;

    public abstract void a(int i, String str, CampaignUnit campaignUnit);

    public abstract void a(List<com.mbridge.msdk.foundation.same.net.c.b> list, CampaignUnit campaignUnit);

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a() {
        super.a();
        this.b = System.currentTimeMillis();
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        CampaignUnit campaignUnit;
        if (kVar == null || kVar.c == null) {
            return;
        }
        List<com.mbridge.msdk.foundation.same.net.c.b> list = kVar.c.d;
        JSONObject jSONObject = kVar.a;
        int iOptInt = jSONObject.optInt("status");
        if (1 == iOptInt) {
            a(System.currentTimeMillis());
            if ("v5".equals(jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION))) {
                campaignUnit = CampaignUnit.parseV5CampaignUnit(jSONObject.optJSONObject("data"));
            } else {
                campaignUnit = CampaignUnit.parseCampaignUnit(jSONObject.optJSONObject("data"));
            }
            if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                a(list, campaignUnit);
                a(campaignUnit.getAds().size());
                return;
            } else {
                String msg = campaignUnit != null ? campaignUnit.getMsg() : null;
                if (TextUtils.isEmpty(msg)) {
                    msg = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
                }
                a(iOptInt, msg, campaignUnit);
                return;
            }
        }
        a(iOptInt, jSONObject.optString(RemoteMessageConst.MessageBody.MSG), null);
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
        a(aVar.a, com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a), null);
    }
}
