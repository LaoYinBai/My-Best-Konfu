package com.mbridge.msdk.mbbanner.common.f;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.same.net.c.b;
import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BannerResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends f<JSONObject> {
    private static final String a = "a";
    private String b = "";

    public abstract void a(int i, String str);

    public abstract void a(CampaignUnit campaignUnit);

    public final void a(String str) {
        this.b = str;
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(k<JSONObject> kVar) throws JSONException {
        CampaignUnit campaignUnit;
        super.a(kVar);
        List<b> list = kVar.c.d;
        JSONObject jSONObject = kVar.a;
        s.b(a, "parseLoad content = " + jSONObject);
        int iOptInt = jSONObject.optInt("status");
        if (1 == iOptInt) {
            a(System.currentTimeMillis());
            if ("v5".equals(jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION))) {
                campaignUnit = CampaignUnit.parseV5CampaignUnit(jSONObject.optJSONObject("data"), this.b);
            } else {
                campaignUnit = CampaignUnit.parseCampaignUnit(jSONObject.optJSONObject("data"), this.b);
            }
            if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                a(campaignUnit);
                if (!TextUtils.isEmpty(this.b)) {
                    b(1);
                }
                a(campaignUnit.getAds().size());
                return;
            }
            String msg = campaignUnit != null ? campaignUnit.getMsg() : null;
            if (TextUtils.isEmpty(msg)) {
                msg = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
            }
            a(iOptInt, msg);
            return;
        }
        a(iOptInt, jSONObject.optString(RemoteMessageConst.MessageBody.MSG));
    }

    @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        s.b(a, "onFailed errorCode = " + aVar.a);
        a(aVar.a, com.mbridge.msdk.foundation.same.net.g.a.a(aVar.a));
    }
}
