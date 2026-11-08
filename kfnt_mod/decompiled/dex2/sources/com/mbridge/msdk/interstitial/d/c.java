package com.mbridge.msdk.interstitial.d;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.Frame;
import java.util.List;
import org.json.JSONObject;

/* compiled from: InterstitialResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c extends f<JSONObject> {
    private static final String a = "c";
    private int b;

    public abstract void a(List<Frame> list);

    public abstract void a(List<com.mbridge.msdk.foundation.same.net.c.b> list, CampaignUnit campaignUnit);

    public abstract void b(int i, String str);

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a() {
        super.a();
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) {
        CampaignUnit campaignUnit;
        String msg;
        CampaignUnit campaignUnit2;
        super.a(kVar);
        if (kVar == null || kVar.c == null) {
            return;
        }
        if (this.b == 0) {
            List<com.mbridge.msdk.foundation.same.net.c.b> list = kVar.c.d;
            JSONObject jSONObject = kVar.a;
            s.d("", "content = " + jSONObject);
            int iOptInt = jSONObject.optInt("status");
            if (1 == iOptInt) {
                a(System.currentTimeMillis());
                if ("v5".equals(jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION))) {
                    campaignUnit2 = CampaignUnit.parseV5CampaignUnit(jSONObject.optJSONObject("data"));
                } else {
                    campaignUnit2 = CampaignUnit.parseCampaignUnit(jSONObject.optJSONObject("data"));
                }
                if (campaignUnit2 != null && campaignUnit2.getAds() != null && campaignUnit2.getAds().size() > 0) {
                    a(list, campaignUnit2);
                    a(campaignUnit2.getAds().size());
                    return;
                } else {
                    msg = campaignUnit2 != null ? campaignUnit2.getMsg() : null;
                    if (TextUtils.isEmpty(msg)) {
                        msg = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
                    }
                    b(iOptInt, msg);
                    return;
                }
            }
            b(iOptInt, jSONObject.optString(RemoteMessageConst.MessageBody.MSG));
            return;
        }
        if (this.b == 1) {
            List<com.mbridge.msdk.foundation.same.net.c.b> list2 = kVar.c.d;
            JSONObject jSONObject2 = kVar.a;
            s.d("", "content frames = " + jSONObject2);
            int iOptInt2 = jSONObject2.optInt("status");
            if (1 == iOptInt2) {
                a(System.currentTimeMillis());
                if ("v5".equals(jSONObject2.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION))) {
                    campaignUnit = CampaignUnit.parseV5CampaignUnit(jSONObject2.optJSONObject("data"));
                } else {
                    campaignUnit = CampaignUnit.parseCampaignUnit(jSONObject2.optJSONObject("data"));
                }
                if (campaignUnit != null && campaignUnit.getListFrames() != null && campaignUnit.getListFrames().size() > 0) {
                    List<Frame> listFrames = campaignUnit.getListFrames();
                    a(listFrames);
                    a(listFrames.size());
                    return;
                } else {
                    msg = campaignUnit != null ? campaignUnit.getMsg() : null;
                    if (TextUtils.isEmpty(msg)) {
                        msg = jSONObject2.optString(RemoteMessageConst.MessageBody.MSG);
                    }
                    b(iOptInt2, msg);
                    return;
                }
            }
            b(iOptInt2, jSONObject2.optString(RemoteMessageConst.MessageBody.MSG));
        }
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.d(a, "errorCode = " + aVar.a);
        b(aVar.a, com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a));
    }
}
