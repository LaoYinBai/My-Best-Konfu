package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.contentalliance.coupon.model.CouponStatus;
import com.kwad.sdk.contentalliance.coupon.model.CouponStatusInfo;
import com.kwad.sdk.core.response.model.SdkConfigData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aq implements com.kwad.sdk.core.d<CouponStatus> {
    @Override // com.kwad.sdk.core.d
    public void a(CouponStatus couponStatus, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        couponStatus.couponActiveConfig = new SdkConfigData.CouponActiveConfig();
        couponStatus.couponActiveConfig.parseJson(jSONObject.optJSONObject("couponActiveConfig"));
        couponStatus.couponStatusInfo = new CouponStatusInfo();
        couponStatus.couponStatusInfo.parseJson(jSONObject.optJSONObject("couponStatusInfo"));
        couponStatus.currentWatchVideoCount = jSONObject.optInt("currentWatchVideoCount");
        couponStatus.winningTimes = jSONObject.optInt("winningTimes");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(CouponStatus couponStatus, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "couponActiveConfig", couponStatus.couponActiveConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "couponStatusInfo", couponStatus.couponStatusInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "currentWatchVideoCount", couponStatus.currentWatchVideoCount);
        com.kwad.sdk.utils.t.a(jSONObject, "winningTimes", couponStatus.winningTimes);
        return jSONObject;
    }
}
